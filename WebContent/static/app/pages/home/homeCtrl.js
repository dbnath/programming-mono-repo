app.controller("homeCtrl",['$stateParams','service','$scope','$rootScope','$templateCache','$log','uiGridConstants',"$filter","ngDialog",function($stateParams,service,$scope,$rootScope,$templateCache,$log,uiGridConstants,$filter,ngDialog){
  console.log("Inside Home controller");
  var home = this;
  $rootScope.theme = 'ngdialog-theme-plain';
  home.userId = $rootScope.selectedUserRole.userId;
  home.userName = $rootScope.selectedUserRole.userName;
  home.roleId = $rootScope.selectedUserRole.selectedRoleId;
  home.roleName = '';
  home.docdetails = {};
  document.title = 'Docflow::Home';
  home.appState ="hide";
  $scope.mappedRoles = [];
  $scope.docids =[];
  $("#savepanel").hide();
  $scope.DocumentWorkflowProcess ={};

//  $templateCache.put('ui-grid/selectionRowHeader',
//		    "<div class=\"ui-grid-disable-selection\"><div class=\"ui-grid-cell-contents\"><ui-grid-selection-row-header-buttons></ui-grid-selection-row-header-buttons></div></div>"
//		  );
//		  
//		  $templateCache.put('ui-grid/selectionRowHeaderButtons',
//		    "<div class=\"ui-grid-selection-row-header-buttons\" ng-class=\"{'ui-grid-row-selected': row.isSelected , 'ui-grid-icon-cancel':!grid.appScope.isSelectable(row.entity), 'ui-grid-icon-ok':grid.appScope.isSelectable(row.entity)}\" ng-click=\"selectButtonClick(row, $event)\">&nbsp;</div>"
//		  );

		  $scope.isSelectable = function(entity)
		  {
		    console.log(entity);
//		    if(entity.company ==='Enersol')
//		      return false;
//		    else
		   
		      return true;
		  };
	$scope.users = [{"name": "Legal Doc", "location": "file://doc", "status": "New","assignto" : "Mrinal","documentid":"1"},
	                {"name": "Contracts",  "location": "file://doc", "status": "Assigned","assignto" : "Bidit","documentid":"1"},
	                {"name": "Legal",  "location": "file://doc", "status": "Rejected","assignto" : "Joy","documentid":"1"},
	                {"name": "contracts", "location": "file://doc", "status": "Approved","assignto" : "Debashis","documentid":"1"},
	                {"name": "legals",  "location": "file://doc", "status": "Rejected","assignto" : "Pratik","documentid":"1"}];
	var rowTemplate =  '<div>' +
    '  <div ng-dblclick=\"grid.appScope.onDblClick(row)\" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell></div>' +
    '</div>';
	$scope.gridOptions = {
			rowTemplate:rowTemplate,
		//	data: 'users',
			columnDefs: [
			           
			             
			             {field: "docName", displayName: "Name"},
			           
			             {field: "wfStatusDesc", displayName: "Status",
			            	 cellTemplate: '<div ng-class="{green: COL_FIELD == Rejected}"><div class="ngCellText">{{row.entity.wfStatusDesc}}</div></div>'},
			            	 {field: "assignedTo", displayName: "Assigned To"},
			            	 {field: "wfAssignmentGroupName", displayName: "GroupName"},
			            	 {field: "docId", displayName: "ID"},
			             ],
			enableCellEdit: true,
			enableColumnResize: true,
			enableColumnReordering: true,
			showFooter: true,
		    enableRowSelection: true,
		    enableSelectAll: true,
		    selectionRowHeaderWidth: 35,
			enablePaginationControls : true,
			enableFiltering :true,
			enableHorizontalScrollbar: 0,
			 appScopeProvider: { 
			        onDblClick : function(row) {
			        	home.appState = 'show';
			        //	alert($filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss'));
			        	service.getDocDetails(row.entity.docId).then(function(obj){
			        		
			        		var documentWorkflow = {};
			        		documentWorkflow.docId = row.entity.docId;
			        		documentWorkflow.docName = row.entity.docName;
			        		documentWorkflow.docTypeId = row.entity.docTypeId;
			        		documentWorkflow.docTypeDesc = row.entity.docTypeDesc;
			        		documentWorkflow.docRepoId = row.entity.docRepoId;
			        		documentWorkflow.docRepoDesc = row.entity.docRepoDesc;
			        		documentWorkflow.docHyperlink = row.entity.docHyperlink;
			        		documentWorkflow.docLocation = row.entity.docLocation;
			        		documentWorkflow.wfStatusId = row.entity.wfStatusId;
			        		documentWorkflow.wfStatusDesc = row.entity.wfStatusDesc;
			        		documentWorkflow.userRole = row.entity.userRole;
			        		documentWorkflow.wfAssignmentGroupId = row.entity.wfAssignmentGroupId;
			        		documentWorkflow.wfAssignmentGroupName = row.entity.wfAssignmentGroupName;
			        		documentWorkflow.wfActivityDesc = row.entity.wfActivityDesc;
			        		documentWorkflow.isReworked = row.entity.isReworked;
			        		documentWorkflow.assignedTo = row.entity.assignedTo;
			        		documentWorkflow.assignedDt = row.entity.assignedDt;
			        		documentWorkflow.lastUpdatedBy =row.entity.lastUpdatedBy;
			        		documentWorkflow.lastUpdateDt = row.entity.lastUpdateDt;
							home.roleName = row.entity.wfAssignmentGroupName;
							home.wfStatusId = row.entity.wfStatusId;
			        	
			        		$scope.DocumentWorkflowProcess.docObj = documentWorkflow;
							
			        	    if(obj.status == 200){
			        	    	
								home.docdetails = obj.data;
								$scope.DocumentWorkflowProcess.docDetail = 	home.docdetails;
								home.tagnames = home.docdetails.docTagRelationship;
								var selectedtag = [];
								//alert(angular.toJson(obj.data, true));
			        	        service.retrieveTypeTagSubTagsMap(home.docdetails.document.docTypeId).then(function(obj){
			        	        	
			        	            if(obj.status == 200){
			        	            	  
			        	            	   var tagarray = [];
			        	            	   angular.forEach(  obj.data.docTagSubTagMap, function(row, key) {
			        	            		   var tag = {};
			        	            	    tag.label = row.docTagDesc;
			        	            	   
			        	            	    tag.children =[];
			        	            	    angular.forEach(  row.docSubTags, function(subrow, key) {
			        	            	    	var subtag = {};
			        	            	    	subtag.label = subrow.docSubTagDesc;
			        	            	    	
			        	            	    	subtag.value = row.docTagId +'-' + subrow.docSubTagId;
			        	            	    	//disabled after QA reviewed or closed
			        	            	    	if(documentWorkflow.wfStatusId == 7 || documentWorkflow.wfStatusId == 8) {
			        	            	    		subtag.disabled = 'true';
			        	            	    	}
			        	            	    	tag.children.push(subtag);
			        	            	    });
			        	            	    tagarray.push(tag);  
			        	            	   });
										    //  alert(angular.toJson(tagarray, true));
											var pdfLink = 'http://www.irs.gov/pub/irs-pdf/f1065.pdf';
						        	    	var title = row.entity.docName;
						        	    	var availableTag = tagarray;  //get the tag from service for a doc type
						        	    	var checkedTag = ''; // all save tags, for new doc its empty
								        	createPDF(pdfLink, title, availableTag, checkedTag, home.roleId, documentWorkflow.wfStatusId);
                                                angular.forEach(  home.tagnames, function(tag, key) {
												 
												 selectedtag.push(tag.docTagId +'-' + tag.docSubTagId);
												  $('#example-multiple-optgroups').multiselect('select', tag.docTagId +'-' + tag.docSubTagId);
											 });
											$('#divLocation').hide();
											$('#divComment').hide();
											
											if(home.roleId == 2 || home.roleId == 3){
												$('#divLocation').hide();
												$('#divComment').show();
											}
											if(home.roleId == 1 && home.wfStatusId == 7){
												$('#divLocation').show();
												$('#divComment').show();
												//$('#divComment').disabled();
											}
											
											
			        	            } else {
			        	            	 ngDialog.open({
			    	    	                 template: 'firstDialogId',
			    	    	                 data: {Message: "Error in retrive document"},
			    	    	                 className: 'ngdialog-theme-default'
			    	    	             });
			        	            }
			        	          });
			        	    	//send below parameter from service
			        	    	
			        	    } else {
			        	   	 ngDialog.open({
    	    	                 template: 'firstDialogId',
    	    	                 data: {Message: "Error in Retrive document"},
    	    	                 className: 'ngdialog-theme-default'
    	    	             });
			        	    }
			        	  });

			         }
			    }
			
			
			
			
	}
	
	
	$scope.gridOptionsmylist = {
			rowTemplate:rowTemplate,
		//	data: 'users',
			columnDefs: [
			           
			             
			             {field: "docName", displayName: "Name"},
			             {field: "wfStatusDesc", displayName: "Status",
			            	 cellTemplate: '<div ng-class="{green: COL_FIELD == Rejected}"><div class="ngCellText">{{row.entity.wfStatusDesc}}</div></div>'},
			            	 {field: "assignedTo", displayName: "Assigned To"},
			            	 {field: "wfAssignmentGroupName", displayName: "GroupName"},
			            	 {field: "docId", displayName: "ID"},
			             ],
			enableCellEdit: true,
			enableColumnResize: true,
			enableColumnReordering: true,
			showFooter: true,
			enableRowSelection: true,
		    enableSelectAll: true,
		    selectionRowHeaderWidth: 35,
			enablePaginationControls : true,
			enableFiltering :true,
			enableHorizontalScrollbar: 0,
			 appScopeProvider: { 
			        onDblClick : function(row) {
			        	home.appState = 'show';
			        	service.getDocDetails(row.entity.docId).then(function(obj){
			        		
			        		var documentWorkflow = {};
			        		documentWorkflow.docId = row.entity.docId;
			        		documentWorkflow.docName = row.entity.docName;
			        		documentWorkflow.docTypeId = row.entity.docTypeId;
			        		documentWorkflow.docTypeDesc = row.entity.docTypeDesc;
			        		documentWorkflow.docRepoId = row.entity.docRepoId;
			        		documentWorkflow.docRepoDesc = row.entity.docRepoDesc;
			        		documentWorkflow.docHyperlink = row.entity.docHyperlink;
			        		documentWorkflow.docLocation = row.entity.docLocation;
			        		documentWorkflow.wfStatusId = row.entity.wfStatusId;
			        		documentWorkflow.wfStatusDesc = row.entity.wfStatusDesc;
			        		documentWorkflow.userRole = row.entity.userRole;
			        		documentWorkflow.wfAssignmentGroupId = row.entity.wfAssignmentGroupId;
			        		documentWorkflow.wfAssignmentGroupName = row.entity.wfAssignmentGroupName;
			        		documentWorkflow.wfActivityDesc = row.entity.wfActivityDesc;
			        		documentWorkflow.isReworked = row.entity.isReworked;
			        		documentWorkflow.assignedTo = row.entity.assignedTo;
			        		documentWorkflow.assignedDt = row.entity.assignedDt;
			        		documentWorkflow.lastUpdatedBy =row.entity.lastUpdatedBy;
			        		documentWorkflow.lastUpdateDt = row.entity.lastUpdateDt;
							home.roleName = row.entity.wfAssignmentGroupName;
							home.wfStatusId = row.entity.wfStatusId;
			        	
			        		$scope.DocumentWorkflowProcess.docObj = documentWorkflow;
			        		
			        	    if(obj.status == 200){
			        	    	
			        	    	home.docdetails = obj.data;
								$scope.DocumentWorkflowProcess.docDetail = 	home.docdetails;
								home.tagnames = home.docdetails.docTagRelationship;
								var selectedtag = [];
//								 angular.forEach(  home.tagnames, function(tag, key) {
//									 
//									 selectedtag.push(tag.docTagId +'-' + tag.docSubTagId);
//									 
//								 });
						//		alert(angular.toJson(obj.data, true));
			        	        service.retrieveTypeTagSubTagsMap(home.docdetails.document.docTypeId).then(function(obj){
			        	        	
			        	            if(obj.status == 200){
			        	            	  
			        	            	   var tagarray = [];
			        	            	   angular.forEach(  obj.data.docTagSubTagMap, function(row, key) {
			        	            		   var tag = {};
			        	            	    tag.label = row.docTagDesc;
			        	            	   
			        	            	    tag.children =[];
			        	            	    angular.forEach(  row.docSubTags, function(subrow, key) {
			        	            	    	var subtag = {};
			        	            	    	subtag.label = subrow.docSubTagDesc;
			        	            	    	
			        	            	    	subtag.value = row.docTagId +'-' + subrow.docSubTagId;
			        	            	    	//disabled after QA reviewed or closed
			        	            	    	if(documentWorkflow.wfStatusId == 7 || documentWorkflow.wfStatusId == 8) {
			        	            	    		subtag.disabled = 'true';
			        	            	    	}
			        	            	    	
			        	            	    	tag.children.push(subtag);
			        	            	    });
			        	            	    tagarray.push(tag);  
			        	            	   });
											//  alert(angular.toJson(tagarray, true));
											var pdfLink = 'http://www.irs.gov/pub/irs-pdf/f1065.pdf';
						        	    	var title = row.entity.docName;
						        	    	var availableTag = tagarray;  //get the tag from service for a doc type
						        	    	var checkedTag = ''; // all save tags, for new doc its empty
								        	createPDF(pdfLink, title, availableTag, checkedTag, home.roleId, documentWorkflow.wfStatusId);
								        	 angular.forEach(  home.tagnames, function(tag, key) {
												 
												 selectedtag.push(tag.docTagId +'-' + tag.docSubTagId);
												  $('#example-multiple-optgroups').multiselect('select', tag.docTagId +'-' + tag.docSubTagId);
											 });
								        	
											$('#divLocation').hide();
											$('#divComment').hide();
											
											if(home.roleId == 2 || home.roleId == 3){
												$('#divLocation').hide();
												$('#divComment').show();
											}
											if(home.roleId == 1 && home.wfStatusId == 7){
												$('#divLocation').show();
												$('#divComment').show();
												$('#divComment').disabled();
											}
											
			        	            } else {
			        	           	 ngDialog.open({
		    	    	                 template: 'firstDialogId',
		    	    	                 data: {Message: "Error in retrive document"},
		    	    	                 className: 'ngdialog-theme-default'
		    	    	             });
			        	            }
			        	          });
			        	    	//send below parameter from service
			        	    	
			        	    } else {
			        	      	 ngDialog.open({
	    	    	                 template: 'firstDialogId',
	    	    	                 data: {Message: "Error in retrive document"},
	    	    	                 className: 'ngdialog-theme-default'
	    	    	             });
			        	    }
			        	  });

			         }
			    }
	}
	
	function createPDF(pdfLink, doctitle, availableTag, checkedTag, userrole, docstatus) {
		var pdf_link = pdfLink; //$(this).attr('href');
        var iframe = '<div class="iframe-container"><iframe src="'+pdf_link+'#zoom=90"></iframe></div>' ;
        $.createModal({
        title:doctitle,
        message: iframe,
        closeButton:true,
        scrollable:false,
        tag:availableTag
        });
        handleAssignTag(userrole, docstatus);
	}
	
	function handleAssignTag(userrole, docstatus) {
		//alert(userrole);
		//alert(docstatus);
		$('.assign-tag').addClass('disabled'); //default
		if(docstatus == 1 || docstatus == 3 || docstatus == 5 || docstatus == 8) { //document is new, ...  status , assign me first
        	$('.assign-tag').addClass('disabled');
        }
		else if(docstatus == 2 && userrole == 1) { //Assigned to Analyst and logged user is  analyst
			$('.assign-tag').removeClass('disabled');
		}
		else if(docstatus == 4 && userrole == 2) { //Assigned to Attorney and logged useris attorney
			$('.assign-tag').removeClass('disabled');
		}
		else if(docstatus == 6 && userrole == 3) { //Assigned to QA and logged user is QA
			$('.assign-tag').removeClass('disabled');
		}
		else if(docstatus == 7 && userrole == 1) { //QA Reviewed and logged user is Analyst
			$('.assign-tag').removeClass('disabled');
		}
	}
	
	// Extended disable function
	jQuery.fn.extend({
	    disable: function(state) {
	        return this.each(function() {
	            var $this = $(this);
	            if($this.is('input, button'))
	              this.disabled = state;
	            else
	              $this.toggleClass('disabled', state);
	        });
	    }
	});

	
	service.retrieveUserDetais(home.userId).then(function(obj){
	    if(obj.status == 200){
	    	$scope.mappedRoles = obj.data.userRoleList;
			//alert(obj.data.userRoleList[0].roleName);
	    	//console.log("Assigned role size :"+$scope.mappedRoles.length);
	    } else {
	    	alert("Error"+obj.data);
	    }
	});
	
	home.setupMenu = function(){
		if(home.roleId == 1 || home.roleId == 4){
			$('#doc_upload').show();
		}
	
	};
	
	home.refreshGrid = function(obj) {
		service.getAllDoc().then(function(obj){
			if(obj.status == 200){
				$scope.gridOptions.data = obj.data;
				home.count =  ($scope.gridOptions.data.length);
			} else {
			   	 ngDialog.open({
	                 template: 'firstDialogId',
	                 data: {Message: "Error in retrive document"},
	                 className: 'ngdialog-theme-default'
	             });
			}
		  });
		service.getDocByUser(home.userId).then(function(obj){
			//alert(home.userId);
			if(obj.status == 200){
			
				$scope.gridOptionsmylist.data = obj.data;
				home.countmylist =  ($scope.gridOptionsmylist.data.length);
			} else {
			   	 ngDialog.open({
	                 template: 'firstDialogId',
	                 data: {Message: "Error in retrive document"},
	                 className: 'ngdialog-theme-default'
	             });
			}
		});
	  };
	
    /*service.getAllDoc().then(function(obj){
    	
        if(obj.status == 200){
        	
        	$scope.gridOptions.data = obj.data;
      	  home.count =  ($scope.gridOptions.data.length);
        	
        	
        } else {
          alert("Error"+obj.data);
        }
      });
	

	service.getDocByUser(home.userId).then(function(obj){
    	
        if(obj.status == 200){
        	$scope.gridOptionsmylist.data = obj.data;
      	  home.countmylist =  ($scope.gridOptionsmylist.data.length);
        } else {
          alert("Error"+obj.data);
        }
      });*/

	home.changeRole = function(roleId, roleName) {
		console.log('New Role changed ::'+roleId+","+roleName);
		$rootScope.selectedUserRole.selectedRoleId = roleId;
		$rootScope.selectedUserRole.selectedRoleName = roleName;
	}

	home.logout = function() {
		  service.logout().then(function(obj){
			  if(obj.status == 200){
				  window.location.href = '#/login'
			  } else {
				   	 ngDialog.open({
    	                 template: 'firstDialogId',
    	                 data: {Message: "Error in retrive document"},
    	                 className: 'ngdialog-theme-default'
    	             });
			  }
		  });
	}

	home.assignMe = function(){
		  
		  var log = [];
		  var docIdList = [];
		  var msg = '';
		  var validassign = true;
		  angular.forEach( $scope.rows, function(row, key) {
		   //alert(angular.toJson(row, true));
		  // alert(key);
		   validassign = validateAssignMe(home.roleId, row.entity.wfStatusId);
		   if(!validassign) {
		    msg = "can not assign Document "  + row.entity.docName + " to " + home.userId + " as dcoument status is " + row.entity.wfStatusDesc;
		    return false //break loop
		   }   
		   var newrow ={};
		    var index = $scope.gridOptions.data.indexOf(row.entity);
		    newrow.assignedTo =  home.userId;
		    newrow.docName  = row.entity.docName;
		    newrow.wfStatusDesc = row.entity.wfStatusDesc;
		    newrow.wfAssignmentGroupName = row.entity.wfAssignmentGroupName;
		    newrow.docId = row.entity.docId;
		    $scope.docids.push(row.entity.docId);
		   // home.countmylist =  home.countmylist+1;
		  //  angular.extend( $scope.gridOptions.data[index], newrow);
		    $scope.gridOptionsmylist.data.push(newrow);
		   }, log);
		   //end of for each
		   
		 
		   if(validassign) {
		 
		    service.assignToMe( $scope.docids).then(function(obj){
		       // alert(obj.status);
		     if (obj.status == 200) {
		      home.refreshGrid(obj);
		      home.appState ="hide";
		      ngDialog.open({
	                 template: 'firstDialogId',
	                 data: {Message: "Assignment Successfull"},
	                 className: 'ngdialog-theme-default'
	             });
				
		     }
		    });
		   }
		   else {
			   ngDialog.open({
	                 template: 'firstDialogId',
	                 data: {Message: "Can't be Assign"},
	                 className: 'ngdialog-theme-default'
	             });
				
		   }
		  };
	function validateAssignMe(userrole, docstatus) {
		  var ret = false;
		  if((docstatus == 1 ||  docstatus == 2) && userrole == 1) { //Assigned to Analyst and logged user is  analyst
		   ret = true;
		  }
		  else if((docstatus == 3 || docstatus == 4) && userrole == 2) { //Assigned to Attorney and logged useris attorney
		   ret = true;
		  }
		  else if((docstatus == 5 || docstatus == 6) && userrole == 3) { //Assigned to QA and logged user is QA
		   ret = true;
		  }
		  return ret;
		 };

	home.inithome = function(obj){
		//alert('home');
		//alert($rootScope.selectedUserRole.selectedRoleId);
		//Debasish da please fix root scope issue, untill then redrect to login -- mnl
		if(home.userId == null) {
			window.location.href = '#/login';
		}
		else {
			home.refreshGrid(obj);
			home.setupMenu();
		}
	};

	$scope.gridOptions.onRegisterApi = function(gridApi){
		  //set gridApi on scope
		  $scope.gridApi = gridApi;
		  $scope.rows = [];
		  gridApi.selection.on.rowSelectionChanged($scope,function(row){
		    var msg = 'row selected ' + row.isSelected;
		    if(row.isSelected){    
		     $scope.rows.push(row);    
		    }
		    else {
		     $scope.rows = jQuery.grep($scope.rows, function(value) {
		      return value != row;
		    });
		    }
		   
		    $log.log(msg);
		    
		  });

		    gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
		      var msg = 'rows changed ' + rows.length;
		      
		      $scope.rows =  rows;
		      $log.log(msg);
		    });
		  };

  home.saveDoc = function(){  
		$scope.DocumentWorkflowProcess.docDetail.docTagRelationship =[];
		var  selectedtagarray = $('#example-multiple-optgroups option:selected');
    	
		angular.forEach( selectedtagarray, function(selectedtag, key){
			
			var docTagRelationship = {};
			
			var tagarray =	selectedtag.value.split('-');
			docTagRelationship.docId =  $scope.DocumentWorkflowProcess.docObj.docId;
			docTagRelationship.docTypeId =  $scope.DocumentWorkflowProcess.docObj.docTypeId;
			//docTagRelationship.docTypeDesc =  $scope.DocumentWorkflowProcess.docObj.docTypeDesc;
			docTagRelationship.docTagId =  tagarray[0];
			docTagRelationship.docSubTagId =  tagarray[1];
			docTagRelationship.lastUpdatedBy =   home.user ;
			docTagRelationship.lastUpdatedDt =   $filter('date')(new Date(),'yyyy-MM-dd') ;
			
			docTagRelationship.createdBy =   home.user ;
			docTagRelationship.creationDt =   $filter('date')(new Date(),'yyyy-MM-dd') ;
			$scope.DocumentWorkflowProcess.isFinalSubmit = false;
			
			$scope.DocumentWorkflowProcess.docDetail.docTagRelationship.push(docTagRelationship);
		});
		
		// alert(angular.toJson($scope.DocumentWorkflowProcess, true));
		service.submitWorkflow($scope.DocumentWorkflowProcess).then(function(obj){
			 ngDialog.open({
                 template: '<h2>Notice that there is no overlay!</h2>',
                 className: 'ngdialog-theme-default',
                 plain: true,
                 overlay: false
             });
	    	//alert(obj.status);
	        if(obj.status == 200){
	        	//alert("Success");
	        	 ngDialog.open({
	                 template: 'firstDialogId',
	                 data: {Message: "Save Successfull"},
	                 className: 'ngdialog-theme-default'
	             });
				home.refreshGrid(obj);
				$('#myModal').modal('hide');
			
	        } else {
	        	 ngDialog.open({
	                 template: 'firstDialogId',
	                 data: {Message: "WorkFlow error fails"},
	                 className: 'ngdialog-theme-default'
	             });
	        }
	      });
		
  };
  
  home.submitDoc  = function(){  
	  $scope.loading = true;
	  $scope.DocumentWorkflowProcess.docDetail.docTagRelationship =[];
		var  selectedtagarray = $('#example-multiple-optgroups option:selected');
  	
		angular.forEach( selectedtagarray, function(selectedtag, key){
			
			var docTagRelationship = {};
			
			var tagarray =	selectedtag.value.split('-');
			docTagRelationship.docId =  $scope.DocumentWorkflowProcess.docObj.docId;
			docTagRelationship.docTypeId =  $scope.DocumentWorkflowProcess.docObj.docTypeId;
			//docTagRelationship.docTypeDesc =  $scope.DocumentWorkflowProcess.docObj.docTypeDesc;
			docTagRelationship.docTagId =  tagarray[0];
			docTagRelationship.docSubTagId =  tagarray[1];
			docTagRelationship.lastUpdatedBy =   home.userId ;
			docTagRelationship.lastUpdatedDt =   $filter('date')(new Date(),'yyyy-MM-dd') ;
			
			docTagRelationship.createdBy =   home.userId ;
			docTagRelationship.creationDt =   $filter('date')(new Date(),'yyyy-MM-dd') ;
			$scope.DocumentWorkflowProcess.isFinalSubmit = true;
			$scope.DocumentWorkflowProcess.docDetail.docTagRelationship.push(docTagRelationship);
		});
		if( (home.roleId == 1 && home.wfStatusId == 7)){
			   var target = $('#targetloc').val();
		
		$scope.DocumentWorkflowProcess.docDetail.targetDocLocation = target;
		}
		
		if( (home.roleId == 1 && home.wfStatusId == 7)){
			   var text = $('#usercomment').val();
		
		$scope.DocumentWorkflowProcess.docDetail.tagOverrideReason = text;
		}
		// alert(angular.toJson($scope.DocumentWorkflowProcess, true));
		service.submitWorkflow($scope.DocumentWorkflowProcess).then(function(obj){
	    	//alert(obj.status);
	        if(obj.status == 200){
	        	//alert("Success");
				home.refreshGrid(obj);
				
				service.getDocDetails( $scope.DocumentWorkflowProcess.docObj.docId).then(function(obj){
	        			        	
	        	    if(obj.status == 200){
	        	    	 ngDialog.open({
	    	                 template: 'firstDialogId',
	    	                 data: {Message: "Work Flow submit Successfull"},
	    	                 className: 'ngdialog-theme-default'
	    	             });
	        	    	home.docdetails = obj.data;
	        	    	home.docdetails.document.lastUpdatedBy =  home.userId ;
	        	    	home.docdetails.document.lastUpdatedDt = $filter('date')(new Date(),'yyyy-MM-dd')  ;
	        	    	home.tagnames = home.docdetails.docTagRelationship;
	        	    	
	        	    } else {
	        	    	 ngDialog.open({
	    	                 template: 'firstDialogId',
	    	                 data: {Message: "Work Flow submit fails"},
	    	                 className: 'ngdialog-theme-default'
	    	             });
	        	    }
	        	  });	
				home.refreshGrid(obj);
				$('#myModal').modal('hide');
				home.appState ="hide";
	        } else {
	        	 ngDialog.open({
	                 template: 'firstDialogId',
	                 data: {Message: "Work Flow submit fails"},
	                 className: 'ngdialog-theme-default'
	             });
	        }
	        $scope.loading = false;
		});
		
};

  $scope.grid = {
	enableHorizontalScrollbar: 0
  };

  $scope.uploadComplete = function (content) {
    $scope.response = content; // Presumed content is a json string!  JSON.parse(
    $scope.response.style = {
      color: $scope.response.color,
      "font-weight": "bold"
    };
  };

  $('#myTabs a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
  });
  

  var tags = '<div class="input-group"><span class="input-group-addon" id="basic-addon1">Assign Tags</span>';
  tags += '<select id="example-multiple-optgroups" multiple="multiple">' +'</select></div><br>';
  tags += '<div class="input-group" id="divComment"><span class="input-group-addon" id="basic-addon2">Comment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'
  tags += '<textarea class="form-control" rows="3" id="usercomment"></textarea></div>';
  tags += '<div class="input-group" id="divLocation"><span class="input-group-addon" id="basic-addon3">Target Location</span>'
  tags += '<textarea class="form-control" rows="3" id="targetloc"></textarea></div>';
  //tags += '<div class="btn-group btn-group-justified">'
  //tags += '<div class="btn-group"><a href="#" class="btn btn-primary" id="save-tag"><span class="glyphicon glyphicon-floppy-disk"></span>Save</a></div>';
  //tags += '<div class="btn-group" id="b_sub"></div>';
  tags += '<div id="savebut"></div>';
  
  /* document viewer  */
  
  (function(a) {
      a.createModal = function(b) {
          defaults = {
              title: "",
              message: "Your Message Goes Here!",
              closeButton: true,
              scrollable: false
          };
          var b = a.extend({}, defaults, b);
          var c = (b.scrollable === true) ? 'style="max-height: 420px;overflow-y: auto;"' : "";
          html = '<div class="modal fade modal-wide" id="myModal" >';
          html += '<div class="modal-dialog">';
          html += '<div class="modal-content">';
          html += '<div class="modal-header">';
          html += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
          if (b.title.length > 0) {
              html += '<h4 class="modal-title">' + b.title + "</h4>"
          }
          html += "</div>";
          html += '<div class="modal-body" ' + c + ">";
          html += '<div class="row"><div class="col-xs-8">' + b.message + '</div><div class="col-xs-4" id="tagaction">' +  '<div>' + tags +'</div>'+ '</div></div>';
          html += "</div>";
          html += '<div class="modal-footer">';
          if (b.closeButton === true) {
              html += '<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>'
          }
          html += "</div>";
          html += "</div>";
          html += "</div>";
          html += "</div>";
          a("body").prepend(html);
          var height = $(window).height() - 50;
          
//          var optgroups = [
//                           {
//                               label: 'Group 1', children: [
//                                   {label: 'Option 1.1', value: '1-1'},
//                                   {label: 'Option 1.2', value: '1-2'},
//                                   {label: 'Option 1.3', value: '1-3'}
//                               ]
//                           },
//                           {
//                               label: 'Group 2', children: [
//                                   {label: 'Option 2.1', value: '2-1'},
//                                   {label: 'Option 2.2', value: '2-2'},
//                                   {label: 'Option 2.3', value: '2-3'}
//                               ]
//                           },
//                           {
//                               label: 'Group 3', children: [
//                                   {label: 'Option 3.1', value: '3-1'},
//                                   {label: 'Option 3.2', value: '3-2'},
//                                   {label: 'Option 3.3', value: '3-3'}
//                               ]
//                           }
//                       ];
          
                        var optgroups = b.tag;
                       $('#example-multiple-optgroups').multiselect('dataprovider', optgroups);
                       //alert('hi');
                       var selectconfig = {
                               enableFiltering: true,
                               //buttonWidth: '300px',
                               //maxHeight: 100,
                               nonSelectedText: 'Select Tags',
                               //dropRight: true,
                               onChange: function(option, checked) {
                                   // Get selected options.
                                   var selectedOptions = $('#example-multiple-optgroups option:selected');
                                   
                                   var sopt = [];
                                   selectedOptions.each(function() {
                                	   sopt.push($(this).parent().attr('label'));
                                   });
                                   
                                   var result = $.grep(sopt, function(e){ return e == $(option).parent().attr('label'); });
                                   //alert(result);
                                   if(checked === true && sopt.length > 1 && result.length > 1) {
                                	   //alert($(option).val());
                                	   $('#example-multiple-optgroups').multiselect('deselect', $(option).val());
                                   }
                                   
                               }                          
                       };
                       $('#example-multiple-optgroups').multiselect('setOptions', selectconfig);
                       $('#example-multiple-optgroups').multiselect('rebuild');
                       //$(".submit-tag").show();
                       $("#savepanel").show();
                       $( "#savebut" ).append( $( "#savepanel" ) );
         /* $('#example-multiple-optgroups').multiselect({
              enableFiltering: true
          });*/
    	  
    	  
    	  $("#myModal").css("max-height", height);
    	  
          /*a("#myModal").modal().on("hidden.bs.modal", function() {
              a(this).remove()
          })*/
      }
  })(jQuery);
  /*
  */
  $(function(){    
      $('.assign-tag').on('click',function(){
          $('#myModal').modal('show');
          $('#tagaction').show();		  
          return false;        
      });    
  });
  
  $(function(){    
      $('.view-pdf').on('click',function(){
         $('#myModal').modal('show');
         $('#tagaction').hide();
          return false;        
      });    
  });
  
  $(function(){    
      $('.view-comment').on('click',function(){
    	  $('#commentModal').modal('show');
          return false;        
      });    
  });
  
  $(function(){    
      $('.wfl-doc_upload').on('click',function(){
			window.open('jsp/fileUpload.jsp');
      });    
  });
  
  
  $(function(){    
      $('.submit-tag').on('click',function(){
    	
    	  home.submitDoc();     
          return false;        
      });    
 });
  
  $(function(){    
      $('.save-tag').on('click',function(){
    	  $('#example-multiple-optgroups').multiselect('refresh');
    	  home.saveDoc();
//    	  var selectedtag =  $('#example-multiple-optgroups').multiselect('getSelects');
//    	  alert(selectedtag);
//    	  alert(selectedtag[0]);   
          return false;        
      });    
  });
  
  /*$(document).on("click", "#save-tag", function(event){
    	  e.preventDefault();   
    	  alert('save tag here');
          return false;        
      });    
 
  
  $(document).on("click", "#submit-tag", function(event){
	      e.preventDefault();  
    	  alert('submit tag here');
          return false;        
  });
  */
  
  /*home.init = function(){
	  alert('me');
	  $('#save-tag').on('click',function(){
    	  e.preventDefault();  
    	  alert('save tag here');
          return false;        
          });
 
  
	  $('#save-submit').on('click',function(){
	      e.preventDefault();  
    	  alert('submit tag here');
          return false;        
      });    
  }*/
  

  
    
  /*$(function(){    
      $('.tagmodal').on('click',function(){
    	  e.preventDefault();   
    	  $('#editModal').modal('show')
  });  
  });*/
  
    
}]);