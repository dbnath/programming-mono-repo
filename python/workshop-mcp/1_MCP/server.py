from mcp.server.fastmcp import FastMCP
from datetime import datetime

# Create an MCP server
mcp = FastMCP("Workshop Demo")
# mcp = FastMCP("Workshop Demo", host="127.0.0.1", port=8030) #for sse

# Add tool
@mcp.tool()
def days_between_dates(start_date: str, end_date: str) -> int:
    """Calculate the number of days between two dates in 'yyyy-mm-dd' format."""

    # Parse the date strings into datetime objects
    date1 = datetime.strptime(start_date, "%Y-%m-%d")
    date2 = datetime.strptime(end_date, "%Y-%m-%d")

    # Calculate the difference in days
    return abs((date2 - date1).days)

# Add an addition tool
@mcp.tool()
def add(a: int, b: int) -> int:
    """Add two numbers"""
    return a + b


if __name__ == "__main__":
    mcp.run() #stdio
    # mcp.run(transport="sse") #sse