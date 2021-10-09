/**
 * 
 */
var docworkflow = docworkflow || {} ;
docworkflow = function () {
	this.alertMe = alertMe;
function alertMe() {
		alert('Ekla cholo re');
}

	var $ = getElementById;

	function getElementById(elemId){
		return document.getElementById(elemId);
	}

	//Code -E, S, A
	function showMessage(code, targetdiv, message) {
		$(targetdiv).style.display='';
		$(targetdiv).innertHTML = message;
	}

function login() {
	
		var data = {
		  "userId" : $("username").value,
		  "password" : $("password").value
		};
		
		if(!$("username").value ) {
		  showMessage('E',"login-alert", "Please provide username.");  	  
		  return;
		}
		
		service.login(data).then(function(obj){
		  if(obj.status == 200){
			  service.setDocWorkflowAuthorizationId(obj.data);
			  console.log("The user role set :"+service.getDocWorkflowAuthorizationId());
			  console.log("Selected User-Role in rootscope: "+$rootScope.selectedUserRole.selectedRoleId);
			  //if(login.user.role === 'System Admin'){        
			  if($rootScope.selectedUserRole.selectedRoleId == 4){
				  window.location.href = '#/adminHome/'+$rootScope.selectedUserRole.userId;
			  } else {
				  window.location.href = '#/home/'+$rootScope.selectedUserRole.userId;
			  }    	  
		  } else {   	  
			  login.loginErrorMessage = obj.data.responseMessage;
			  $scope.hasloginerror = true;
			  login.user.username="";
			  login.user.password="";
		  }
		});
	  }

} 