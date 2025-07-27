from langchain_google_genai import ChatGoogleGenerativeAI
from dotenv import load_dotenv
from langchain_core.messages import SystemMessage, ToolMessage
from tools import get_return_policy, get_current_date, get_product_details
from state import GraphState
import os

load_dotenv(dotenv_path="../.env")

# os.getenv("GOOGLE_API_KEY")

#define llm
# https://ai.google.dev/gemini-api/docs/models


# llm = ChatGoogleGenerativeAI(model="gemini-1.5-pro")
# llm  = ChatGoogleGenerativeAI( model = 'gemini-2.5-flash-preview-05-20')
# llm = ChatOpenAI 
llm = ChatGoogleGenerativeAI(model="gemini-2.0-flash")



#bind tools
tools = [get_return_policy, get_current_date, get_product_details]
# tools = [get_return_policy_for_phone, get_return_policy_for_tablet, get_current_date, get_product_details]
tools_by_name = {tool.name: tool for tool in tools}
llm_with_tools = llm.bind_tools(tools) 


#Lets define graph nodes

#Reasoning node : Uses LLM to reason about the user's query

# def reasoning_node(state: GraphState):
#     """
#     Uses the LLM to reason about the user's query.
#     """
#     return {
#     "messages": [
#         llm_with_tools.invoke(
#             [
#                 SystemMessage(
#                     content="You are a helpful support agent. You are given a user's query and you need to reason about the user's query and call the necessary tools to answer the user's query."
#                 )
#             ]
#             + state["messages"]
#             )
#         ]
#     }


def reasoning_node(state: GraphState):
    """
    Uses the LLM to reason about the user's query.
    """

    llm_response = llm_with_tools.invoke(
            [
                SystemMessage(
                    content="You are a helpful support agent. You are given a user's query and you need to reason about the user's query and call the necessary tools to answer the user's query."
                )
            ]
            + state["messages"]
            )
    
    return {
    "messages": [
        llm_response,
        ]
    }


#Action node: Calls necessary tools based on the reasoning
def action_node(state: GraphState):
    """
    Calls the  tools that reasoning node has requested.
    """
    result = []
    for tool_call in state["messages"][-1].tool_calls:
        tool = tools_by_name[tool_call["name"]]
        observation = tool.invoke(tool_call["args"])
        result.append(ToolMessage(content=observation, tool_call_id=tool_call["id"]))
    return {"messages": result}

