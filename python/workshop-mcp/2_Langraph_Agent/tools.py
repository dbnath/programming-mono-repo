# define tools
from langchain_core.tools import tool
import datetime

@tool
def get_return_policy() -> str:  
    """Provide the return policy for the product"""
    return "You can return the product within 30 days of purchase for a full refund."


# get_return_policy

# @tool
# def get_return_policy_for_tablet() -> str:  
#     """Provide the return policy for the tablets"""
#     return "You can return the product within 10 days of purchase for a full refund."

# @tool
# def get_return_policy_for_phone() -> str:  
#     """Provide the return policy for the phone"""
#     return "You can return the product within 30 days of purchase for a full refund."

@tool
def get_current_date() -> str:
    """ Provide the today's date in yyyy-mm-dd format"""
    current_time = datetime.datetime.now()
    return current_time.strftime("%Y-%m-%d")

@tool
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


