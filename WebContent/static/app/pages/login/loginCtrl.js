<<<<<<< HEAD
app.controller("loginCtrl",['service', '$scope','$rootScope',function(service, $scope, $rootScope){
 
  var login = this;
  login.user = {};
  login.loginErrorMessage = "";
  
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
    
    if(!login.user.username ) {
      login.loginErrorMessage = "Please provide username.";
  	  $scope.hasloginerror = true;
  	  return;
    }
    
    service.login(data).then(function(obj){
      if(obj.status == 200){
    	  service.setDocWorkflowAuthorizationId(obj.data);
    	  console.log("The user role set :"+service.getDocWorkflowAuthorizationId());
    	  console.log("Selected User-Role in rootscope: "+$rootScope.selectedUserRole.selectedRoleId);
    	  if(login.user.role === 'System Admin'){        
    		  window.location.href = '#/setting';
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
=======
app.controller("loginCtrl",['service',function(service){
  console.log("Inside controller");
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
      "username" : login.user.username,
      "password" : login.user.password,
      "role" : login.user.role
    };
    service.login(data).then(function(obj){
    	
      if(obj.status == 200){
        if(login.user.role === 'System Admin'){
        
          window.location.href = '#/setting';
        } else {
          window.location.href = '#/setting';
        }
      } else {
        alert("Error"+obj.data);
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
>>>>>>> branch 'master' of https://github.com/toolsrepo/docflowtool.git
