app.controller("loginCtrl",['service', '$rootScope',function(service, $rootScope){
 
  var login = this;
  login.user = {};

  login.signIn=function(){
    window.location.href = '/#/userhome'
  }
  
  login.signUp = function(){
    login.user = {};
    $("#signUp").modal("show");
  }

  login.login = function(){
	 
    var data = {
      "userId" : login.user.username,
      "password" : login.user.password
    };
    service.login(data).then(function(obj){
      if(obj.status == 200){
    	  service.setDocWorkflowAuthorizationId(obj.data);
    	  console.log("The user role set :"+service.getDocWorkflowAuthorizationId());
    	  console.log("Selected User-Role in rootscope: "+$rootScope.selectedUserRole.selectedRoleId);
    	  if(login.user.role === 'System Admin'){        
    		  window.location.href = '#/setting';
    	  } else {
<<<<<<< HEAD
    		  window.location.href = '#/home/'+$rootScope.selectedUserRole.userId;
=======
    		  window.location.href = '#/home/'+$rootScope.selectedUserRole.userName;
>>>>>>> branch 'master' of https://github.com/toolsrepo/docflowtool
    	  }
      } else {    	  
    	  alert(obj.data.responseMessage);
    	  login.user.username="";
    	  login.user.password="";
      }
    });
  }
  login.save = function(){
    if(login.user.password === login.user.confirmPass){
      login.user.status = 'notapplied';
      service.addUser(login.user).then(function(data){
        if(data.status == 200){
          $("#signUp").modal("hide");
        } else {
          alert("Error"+data.data);
        }
      });
    } else {
      alert("Password mismatch");
    }
  }
}]);
