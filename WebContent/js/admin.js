/**
 * 
 */
var admin = function () {
	
	var home = this;
	var $ = getElementById;
	var svc = new service();

	function getElementById(elemId){
		return document.getElementById(elemId);
	}

	 home.uploadDocs =function(contextPath){
			window.open(contextPath+'/jsp/fileUpload.jsp?userId='+$("selectedUserId").value);
		};
		
      home.uploadErrorReasons =function(contextPath){
			window.open(contextPath+'/jsp/errReasonUpload.jsp?userId='+$("selectedUserId").value);
      }; 
      
      home.uploadagreementTypes =function(contextPath){
			window.open(contextPath+'/jsp/agrTypeUpload.jsp?userId='+$("selectedUserId").value);
    };  
    
    home.uploadUserRoles = function(contextPath){
    	window.open(contextPath+'/jsp/userRoleUpload.jsp?userId='+$("selectedUserId").value);
    };
	  
	this.loadView=loadView;
	function loadView(){
		var roleId = $('u8_input').value;
		
		var landingURL = cotextPathTop+'/view/adminlandingHome/';
		//alert(roleId);
			  $("adminViewForm").action=landingURL;
			  $("adminViewForm").method="POST";
			  $("adminViewForm").submit();
	}
	
	function adminViewFn(responseData){
		alert(responseData.responseText);
		$('adminGrid').innerHTML=responseData.responseText;
	}
	  
	  
} 