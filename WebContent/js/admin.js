/**
 * 
 */
var admin = function () {
	

	var $ = getElementById;

	function getElementById(elemId){
		return document.getElementById(elemId);
	}

	this.login = login;
	function login() {
		clearMessage("login-alert"); 
		var data = {
		  "userId" : $("login-username").value,
		  "password" : $("login-password").value
		};
		
		if(!$("login-username").value ) {
		  showMessage('E',"login-alert", "Please provide username.");  	  
		  return;
		}
		
		//service = getService();
		new service().login(data, loginResponse);
	  }
	  
	  function loginResponse(responseData) {
		  //alert(responseData.responseText);
		  responseObject = JSON.parse(responseData.responseText);
		  //alert(responseObject);
		  if(responseData.statusCode == 200){
			  //service.setDocWorkflowAuthorizationId(responseObject);
			  //alert("The user role set :"+service.getDocWorkflowAuthorizationId());
			  //alert("Selected User-Role in rootscope: "+$rootScope.selectedUserRole.selectedRoleId);
			  //if(login.user.role === 'System Admin'){        
			  landingURL = "";
			  if(responseObject.roleId == 4){
				  landingURL = cotextPathTop+'/view/adminHome/';
			  } else {
				  landingURL = cotextPathTop+'/view/landingHome/';
			  }    	  
		  } else {   	  
			  showMessage('E',"login-alert",responseObject.responseMessage);
			  
			  $("login-username").value ="";
			  $("login-password").value="";
		  }
		
	  }
} 