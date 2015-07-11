app.controller("reportsCtrl",['$stateParams','service','$scope','$rootScope','$templateCache','$log',function($stateParams,service,$scope,$rootScope,$templateCache,$log){
	
	document.title = 'Docflow::Reports';
	var reports = this;
	reports.userId = $rootScope.selectedUserRole.userId;
	reports.userName = $rootScope.selectedUserRole.userName;
	reports.roleId = $rootScope.selectedUserRole.selectedRoleId;
	
	reports.initReports = function(obj){
		drawCharts();
	};
	
	var drawCharts = function() {
	    $("#barChart").empty();
	    Morris.Donut({
	    	  element: 'barChart',
	    	  data: [
	    	    {label: "Analyst Queue", value: 12},
	    	    {label: "Legal Queue", value: 30},
	    	    {label: "QA Queue", value: 20},
	    	    {label: "Completed", value: 9}
	    	  ]
	    	});
	    
	    $("#dailychart").empty();
	    var day_data = [
	                    {"period": "2015-06-01", "Completed": 6, "sorned": 70},
	                    {"period": "2015-06-30", "Completed": 8, "sorned": 62},
	                    {"period": "2015-06-29", "Completed": 10, "sorned": 55},
	                    {"period": "2015-06-20", "Completed": 11, "sorned": 48},
	                    {"period": "2015-06-19", "Completed": 17, "sorned": 41},
	                    {"period": "2015-06-18", "Completed": 19, "sorned": 36},
	                    {"period": "2015-06-17", "Completed": 25, "sorned": 32},
	                    {"period": "2015-06-16", "Completed": 32, "sorned": 26},
	                    {"period": "2015-06-15", "Completed": 36, "sorned": 21},
	                    {"period": "2015-06-10", "Completed": 41, "sorned": 18}
	                  ];
          Morris.Line({
            element: 'dailychart',
            data: day_data,
            xkey: 'period',
            ykeys: ['Completed', 'sorned'],
            labels: ['Completed', 'Pending in Queue']
          });
          
	  }
	  
	
}]);
