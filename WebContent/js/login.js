/**
 * 
 */
var login = function () {
	
	//var service = new service();
	this.alertMe =  alertMe;
	function alertMe() {
		
	}
	

	var $ = getElementById;

	function getElementById(elemId){
		return document.getElementById(elemId);
	}

	//Code -E, S, A
	function showMessage(code, targetdiv, message) {
		$(targetdiv).style.display='';
		$(targetdiv).innerHTML = message;
	}
	
	function clearMessage(targetdiv) {
		$(targetdiv).style.display='none';
		$(targetdiv).innerHTML = '';
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
			  $("loginform").action=landingURL;
			  $("loginform").method="POST";
			  $("loginform").submit();
		  } else {   	  
			  showMessage('E',"login-alert",responseObject.responseMessage);
			  
			  $("login-username").value ="";
			  $("login-password").value="";
		  }
		
	  }
} 