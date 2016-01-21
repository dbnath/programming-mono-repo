/**
 * 
 */
var commonframework = function() {
	this.request = request;
	function request(requestObject){
		if (!requestObject || !requestObject.url){
			alert('Required Parameters are not passed');
			return false;
		}
		
		var ajaxObject = createAJAXObject();
		if (!ajaxObject){
			return false;
		}
		ajaxObject.open(requestObject.method,requestObject.url+(requestObject.url.indexOf("?")>=0 ? "&" : "?") + 'anti-cache=' + new Date().getTime(),true);
		ajaxObject.onreadystatechange = function(){
				if(ajaxObject.readyState == 4){
				response(ajaxObject,requestObject); 
			}
		};
		
		if (requestObject.closeConnection === true){
			ajaxObject.setRequestHeader("Connection", "close");
		}
		if(requestObject.contentType) {
				ajaxObject.setRequestHeader("Content-type", requestObject.contentType);                                  
			}
		var postBody = '';
		if (requestObject.postBody){					
			ajaxObject.setRequestHeader("Content-length", requestObject.postBody.length);
			postBody = requestObject.postBody;
		}
		if(requestObject.headers) {
			for ( var prop in requestObject.headers) {				
				ajaxObject.setRequestHeader(prop, requestObject.headers[prop]);	
			}
		}
		try{
			ajaxObject.send(postBody);
		} catch (exception) {
			alert("Ajax engine unexpected exception on send "+exception);
		}
	}

	this.response = response;
	function response(ajaxObject,requestObject){
		var responseObject = {};
		responseObject.success = false;
		responseObject.statusCode = ajaxObject.status===0?200:ajaxObject.status; 
		responseObject.requestObject = requestObject;
		responseObject.responseText = ajaxObject.responseText;
		responseObject.responseXML = ajaxObject.responseXML;
		try{
			ajaxObject.abort();
		} catch (e){
			alert("error in "+e);
		}
		var responseAction = "success";	
		responseAction = validateResponse(requestObject,responseObject);
		
		switch(responseAction){
			case "success" : responseObject.success = true; break;
			case "failure" : responseObject.success = false; break;		
		}

		if (requestObject.responseFunction){
			try{
				requestObject.responseFunction(responseObject);
			} catch (exception){
				alert("Exception in response "+exception);			
			}
		}
	}

	function validateResponse(requestObject,responseObject){	
		var answer;
		if (responseObject.statusCode != 200){
			return "failure";
		} else {		
			return "success";		
		}
	}

	function createAJAXObject() {
		var ajaxObject;
		if (window.XMLHttpRequest){
			ajaxObject = new XMLHttpRequest();
		}
		if (!ajaxObject && window.ActiveXObject){
			ajaxObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (!ajaxObject){
			alert('Your browser doesnt supports AJAX!');
			return {};
		}
		return ajaxObject;
	}
	

}