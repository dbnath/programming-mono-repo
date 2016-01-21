app.controller("adminCtrl",['$stateParams','service','$scope','$rootScope','$templateCache','$log','uiGridConstants',"$filter","ngDialog",function($stateParams,service,$scope,$rootScope,$templateCache,$log,uiGridConstants,$filter,ngDialog){
  console.log("Inside Admin Home controller");
  var home = this;
  $rootScope.theme = 'ngdialog-theme-plain';
  home.userId = $rootScope.selectedUserRole.userId;
  home.userName = $rootScope.selectedUserRole.userName;
  home.roleId = $rootScope.selectedUserRole.selectedRoleId;
  home.roleName = '';
  home.assignedToName = '';
  home.docdetails = {};
  document.title = 'Docflow::Home';
  home.appState ="hide";
  $scope.mappedRoles = [];
  $scope.docids =[];
  $("#savepanel").hide();
  $scope.DocumentWorkflowProcess ={};

	  
  
      home.uploadDocs =function(){
			window.open('jsp/fileUpload.jsp?userId='+home.userId);
      }; 
      
      home.uploadErrorReasons =function(){
			window.open('jsp/errReasonUpload.jsp?userId='+home.userId);
      };      
  
	home.changeRole = function(roleId, roleName) {
		console.log('New Role changed ::'+roleId+","+roleName);
		$rootScope.selectedUserRole.selectedRoleId = roleId;
		$rootScope.selectedUserRole.selectedRoleName = roleName;
	}

	home.logout = function() {
		  service.logout().then(function(obj){
			  if(obj.status == 200){
				  $rootScope.selectedUserRole = {
					userId : null,
					userName : null,
					selectedRoleId : null,
					selectedRoleName : null
				  };
				  window.location.href = '#/logout';
			  } else {
				   	 ngDialog.open({
    	                 template: 'firstDialogId',
    	                 data: {Message: "Error in retrive document"},
    	                 className: 'ngdialog-theme-default'
    	             });
			  }
		  });
	}

		  

	home.inithome = function(obj){
		//alert('home');
		//alert($rootScope.selectedUserRole.selectedRoleId);
		//Debasish da please fix root scope issue, untill then redrect to login -- mnl
		if(home.userId == null) {
			window.location.href = '#/login';
		}
		
	};

  
  
    
}]);