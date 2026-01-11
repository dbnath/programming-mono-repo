## General
* Chunks playarea https://chunkviz.up.railway.app
* For Dial API Key, check the url https://dial-keys.lab.epam.com/details/EPM-GPT-Debasish_Nath_PERSONAL

## Example curl command to get a list of available models
curl  "https://ai-proxy.lab.epam.com/openai/models" -H "Api-Key: $DIAL_API_KEY"| jq .data.[].model

## EPAM links
https://epa.ms/copilot

