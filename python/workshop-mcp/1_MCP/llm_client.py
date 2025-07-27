import asyncio
import nest_asyncio
from mcp import ClientSession, StdioServerParameters
from mcp.client.stdio import stdio_client
import google.generativeai as genai
from dotenv import load_dotenv
import os

# load_dotenv('../.env')
load_dotenv()


# Define server parameters
server_params = StdioServerParameters(
    command="python",  # The command to run your server
    args=["1_MCP/server.py"],  # Arguments to the command
) # will be different for sse

#model configuration
genai.configure(api_key=os.getenv("GOOGLE_API_KEY"))
model_name = "gemini-1.5-flash-latest"


#user query 
user_query = "What are the number of days between 2025-04-10 and 2025-05-11?" 



#helper function to make the mcp inputschema compatible with gemini
async def clean_schema_for_gemini(schema: dict) -> dict:
    if not schema or not isinstance(schema, dict):
        return schema
        
    # Create a copy to avoid modifying the original
    cleaned = schema.copy()
    
    # Remove fields not supported by Gemini
    cleaned.pop("title", None)
    cleaned.pop("$schema", None)
    
    # Recursively clean nested properties
    if "properties" in cleaned and isinstance(cleaned["properties"], dict):
        for prop_name, prop in list(cleaned["properties"].items()):
            cleaned["properties"][prop_name] = await clean_schema_for_gemini(prop)
            
    # Clean items in arrays
    if "items" in cleaned and isinstance(cleaned["items"], dict):
        cleaned["items"] = await clean_schema_for_gemini(cleaned["items"])
        
    return cleaned


async def main():

    # Connect to the server
    async with stdio_client(server_params) as (read_stream, write_stream):
        async with ClientSession(read_stream, write_stream) as session:

            # Initialize the connection
            await session.initialize()

            # Get available MCP tools
            tools_result = await session.list_tools()

            # Create a list of tools to register with the gemini model
            gemini_tools = []
            for tool in tools_result.tools:
 
                # Create function declaration for Gemini
                function_declaration = {
                    "name": tool.name,
                    "description": tool.description,
                    "parameters": await clean_schema_for_gemini(tool.inputSchema)
                }
                
                gemini_tool = genai.types.Tool(
                    function_declarations=[
                        genai.types.FunctionDeclaration(**function_declaration)
                    ]
                )

                gemini_tools.append(gemini_tool)

            #define model
            model = genai.GenerativeModel(model_name=model_name)

            # Generate a response from the model
            response = await model.generate_content_async(
                contents=user_query,
                tools=gemini_tools,
            )
            
            # print("LLM Response: ", response)
            
            # Check if the response contains a function call
            # If it does, call the MCP tool with the predicted arguments
            # If it doesn't, print the response
            if hasattr(response.candidates[0].content.parts[0], 'function_call'):
                function_call = response.candidates[0].content.parts[0].function_call

                print('MCP tool requested: ', function_call.name)
                
                # Call the MCP tool with predicted arguments
                result = await session.call_tool(
                    function_call.name, arguments=function_call.args
                )
                # print("tool call result object: ", result)
                print("Tool call result:", result.content[0].text)
            else:
                print("No function call found in the response.")
                print(response.text)


if __name__ == "__main__":
    asyncio.run(main())