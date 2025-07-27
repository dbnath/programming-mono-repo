import asyncio
from mcp import ClientSession, StdioServerParameters
from mcp.client.stdio import stdio_client
from mcp.client.sse import sse_client

async def main():
    # Define server parameters
    server_params = StdioServerParameters(
        command="python",  # The command to run your server
        args=["1_MCP/server.py"],  # Arguments to the command
    )

    async with stdio_client(server_params) as (read_stream, write_stream):
    # async with sse_client("http://localhost:8030/sse") as (read_stream, write_stream): (for sse clients)
        async with ClientSession(read_stream, write_stream) as session:
            await session.initialize()
            
            # Get available MCP tools
            tools_result = await session.list_tools()
            print("Available tools:")
            for tool in tools_result.tools:
                print(f"  - {tool.name}: {tool.description}")
            
            # Call  tool
            result = await session.call_tool("add", arguments={"a": 2, "b": 3})
            print(f"Tool call result: {result.content[0].text}")


if __name__ == "__main__":
    asyncio.run(main())