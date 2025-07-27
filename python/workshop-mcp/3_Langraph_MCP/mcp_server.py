from mcp.server.fastmcp import FastMCP
import datetime

# Create an MCP server
mcp = FastMCP("Langgraph_MCP_Server")


# Add an addition tool
@mcp.tool()
def get_return_policy() -> str:  
    """Provide the return policy for the product"""
    return "You can return the product within 30 days of purchase for a full refund."

@mcp.tool()
def get_current_date() -> str:
    """ Provide the current date in yyyy-mm-dd format """
    current_time = datetime.datetime.now()
    return current_time.strftime("%Y-%m-%d")

@mcp.tool()
def get_product_details(order_id: str) -> str:
    """Provide the product details for the given order ID"""
    order_details = {
        '1001': {'product_name': 'Laptop', 'date_of_purchase': '2025-05-01', 'quantity': 1, 'price': 100},
        '1002': {'product_name': 'Tablet', 'date_of_purchase': '2025-04-01', 'quantity': 1, 'price': 100},
    }
    return f"The product details for order {order_id} are as follows:\
          product_name: {order_details[order_id]['product_name']},\
          date_of_purchase: {order_details[order_id]['date_of_purchase']}, \
          quantity: {order_details[order_id]['quantity']}, \
          price: {order_details[order_id]['price']}"

if __name__ == "__main__":
    mcp.run() #stdio