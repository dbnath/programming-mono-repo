#define state
import operator
from typing import Annotated, TypedDict

class GraphState(TypedDict):
    messages: Annotated[list, operator.add]

# Can use MessageState as well
# from langgraph.graph import MessagesState