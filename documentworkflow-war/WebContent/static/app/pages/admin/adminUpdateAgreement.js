app.controller("adminUpdateAgreementCtrl",['$stateParams','service','$scope','$rootScope','$templateCache','$log','uiGridConstants',"$filter","ngDialog",function($stateParams,service,$scope,$rootScope,$templateCache,$log,uiGridConstants,$filter,ngDialog){
  console.log("Inside Admin Update Agreement controller");
  var home = this;
  $rootScope.theme = 'ngdialog-theme-plain';
  home.userId = $rootScope.selectedUserRole.userId;
  home.userName = $rootScope.selectedUserRole.userName;
  home.roleId = $rootScope.selectedUserRole.selectedRoleId;
  home.roleName = '';
  home.assignedToName = '';
  home.docdetails = {};
  document.title = 'Update Agreement';
  home.appState ="hide";
  $scope.mappedRoles = [];
  $scope.docids =[];
  $("#savepanel").hide();
  $scope.DocumentWorkflowProcess ={};
	
	
	

	home.init = function(obj){
		//alert('Admin Update Agreement ');
		//alert($rootScope.selectedUserRole.selectedRoleId);
		//Debasish da please fix root scope issue, untill then redrect to login -- mnl
		if(home.userId == null) {
			window.location.href = '#/login';
		}
		
	};


  
    
}]);