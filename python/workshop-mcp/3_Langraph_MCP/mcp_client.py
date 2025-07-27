from langchain_mcp_adapters.tools import load_mcp_tools
from langgraph.prebuilt import create_react_agent
from dotenv import load_dotenv
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.messages import HumanMessage
import asyncio
from mcp import ClientSession, StdioServerParameters
from mcp.client.stdio import stdio_client


load_dotenv()

llm = ChatGoogleGenerativeAI(model="gemini-2.0-flash")


server_params = StdioServerParameters(
        command="python",  # The command to run your server
        args=["3_Langraph_MCP/mcp_server.py"],  # Arguments to the command
    )


async def main():
    
    async with stdio_client(server_params) as (read, write):
        async with ClientSession(read, write) as session:
            # Initialize the connection
            await session.initialize()

            # Get tools
            tools = await load_mcp_tools(session)

            # Create and run the agent
            # Create a react agent
            agent = create_react_agent(
                model=llm,
                tools=tools,
                prompt="You are a helpful support agent."
                " You are given a user's query and you need to reason about the user's query"
                " and call the necessary tools to answer the user's query.",
            )

            user_message = "What is the return policy?" 

            # Invoke the support agent
            response = await agent.ainvoke({"messages": [HumanMessage(content=user_message)]})
            print(response['messages'][-1].content)


if __name__ == "__main__":
    asyncio.run(main())
