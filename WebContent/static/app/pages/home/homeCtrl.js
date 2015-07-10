app.controller("homeCtrl",['$stateParams','service','$scope','$rootScope','$templateCache','$log','uiGridConstants',"$filter",function($stateParams,service,$scope,$rootScope,$templateCache,$log,uiGridConstants,$filter){
  console.log("Inside Home controller");
  var home = this;
  home.userId = $rootScope.selectedUserRole.userId;
  home.userName = $rootScope.selectedUserRole.userName;
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
			        		
			        		var documentWorkflow = {}
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
			        	
			        		 $scope.DocumentWorkflowProcess.docObj = documentWorkflow;
			        		
			        	    if(obj.status == 200){
			        	    	
			        	    	home.docdetails = obj.data;
			        	       $scope.DocumentWorkflowProcess.docDetail = 	home.docdetails;
			        	       home.tagnames = home.docdetails.docTagRelationship;
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
			        	            	    	
			        	            	    	
			        	            	    	tag.children.push(subtag);
			        	            	    });
			        	            	    tagarray.push(tag);  
			        	            	   });
			        	            	 //  alert(angular.toJson(tagarray, true));
			        	            	   var pdfLink = 'http://www.irs.gov/pub/irs-pdf/f1065.pdf';
						        	    	var title = row.entity.docName;
						        	    	var availableTag = tagarray;  //get the tag from service for a doc type
						        	    	var checkedTag = ''; // all save tags, for new doc its empty
								        	createPDF(pdfLink, title, availableTag, checkedTag);
			        	            } else {
			        	              alert("Error"+obj.data);
			        	            }
			        	          });
			        	    	//send below parameter from service
			        	    	
			        	    } else {
			        	      alert("Error"+obj.data); 
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
			        		
			        		var documentWorkflow = {}
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
			        	
			        		 $scope.DocumentWorkflowProcess.docObj = documentWorkflow;
			        		
			        	    if(obj.status == 200){
			        	    	
			        	    	home.docdetails = obj.data;
			        	       $scope.DocumentWorkflowProcess.docDetail = 	home.docdetails;
			        	       home.tagnames = home.docdetails.docTagRelationship;
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
			        	            	    	
			        	            	    	
			        	            	    	tag.children.push(subtag);
			        	            	    });
			        	            	    tagarray.push(tag);  
			        	            	   });
			        	            	 //  alert(angular.toJson(tagarray, true));
			        	            	   var pdfLink = 'http://www.bodossaki.gr/userfiles/file/dummy.pdf';
						        	    	var title = row.entity.docName;
						        	    	var availableTag = tagarray;  //get the tag from service for a doc type
						        	    	var checkedTag = ''; // all save tags, for new doc its empty
								        	createPDF(pdfLink, title, availableTag, checkedTag);
			        	            } else {
			        	              alert("Error"+obj.data);
			        	            }
			        	          });
			        	    	//send below parameter from service
			        	    	
			        	    } else {
			        	      alert("Error"+obj.data); 
			        	    }
			        	  });

			         }
			    }
	}
	
	function createPDF(pdfLink, doctitle, availableTag, checkedTag) {
		var pdf_link = pdfLink; //$(this).attr('href');
        var iframe = '<div class="iframe-container"><iframe src="'+pdf_link+'"></iframe></div>' ;
        $.createModal({
        title:doctitle,
        message: iframe,
        closeButton:true,
        scrollable:false,
        tag:availableTag
        });
	}
	
	service.retrieveUserDetais(home.userId).then(function(obj){
	    if(obj.status == 200){
	    	$scope.mappedRoles = obj.data.userRoleList;
	    	//console.log("Assigned role size :"+$scope.mappedRoles.length);
	    } else {
	    	alert("Error"+obj.data);
	    }
	});
	
    service.getAllDoc().then(function(obj){
    	
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
      });

home.changeRole = function(roleId) {
	console.log('New Role changed ::'+roleId);
	$rootScope.selectedUserRole.selectedRoleId = roleId;
}

home.logout = function() {
	  service.logout().then(function(obj){
		  if(obj.status == 200){
			  window.location.href = '#/login'
		  } else {
			  alert("Error:"+obj.data)
		  }
	  });
}

home.assignMe = function(){
	
	var log = [];
	/*$scope.docids =[];
	angular.forEach( $scope.rows, function(row, key) {
		//alert(angular.toJson(row, true));
	//	alert(key);
		var newrow ={};
		 var index = $scope.gridOptions.data.indexOf(row.entity);
		 newrow.assignedTo =  home.userId;
		 newrow.docName  = row.entity.docName;
		 newrow.wfStatusDesc = row.entity.wfStatusDesc;
		 newrow.wfAssignmentGroupName = row.entity.wfAssignmentGroupName;
		 newrow.docId = row.entity.docId;
		 $scope.docids.push(row.entity.docId);
		 home.countmylist =  home.countmylist+1;
		 angular.extend( $scope.gridOptions.data[index], newrow);
			$scope.gridOptionsmylist.data.push(newrow);
		}, log);*/
	
service.assignToMe($scope.docids).then(function(obj){
		
		var docIdList = [];
		angular.forEach($scope.rows, function(row, key) {
			docIdList.push(row.entity.docId);
		});
		service.assignToMe(docIdList).then(function(obj){
			if (obj.status == 200) {
				home.countmylist =  home.countmylist+docIdList.length;
				angular.forEach($scope.rows, function(row, key) {
					var newrow ={};
					var index = $scope.gridOptions.data.indexOf(row.entity);
					newrow.assignedTo =  home.userId;
					newrow.docName  = row.entity.docName;
					newrow.wfStatusDesc = row.entity.wfStatusDesc;
					newrow.wfAssignmentGroupName = row.entity.wfAssignmentGroupName;
					newrow.docId = row.entity.docId;
					angular.extend( $scope.gridOptions.data[index], newrow);
					$scope.gridOptionsmylist.data.push(newrow);
				}, log);
			}
		});
	
});
};


home.inithome = function(){
	//alert('home');
}


$scope.gridOptions.onRegisterApi = function(gridApi){
    //set gridApi on scope
    $scope.gridApi = gridApi;
    $scope.rows = [];
    gridApi.selection.on.rowSelectionChanged($scope,function(row){
      var msg = 'row selected ' + row.isSelected;
      if(row.isSelected){
    	 
    	  $scope.rows.push(row);
    	 
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
			docTagRelationship.lastUpdatedBy =   home.userId ;
			docTagRelationship.lastUpdatedDt =   $filter('date')(new Date(),'yyyy-MM-dd') ;
			
			docTagRelationship.createdBy =   home.userId ;
			docTagRelationship.creationDt =   $filter('date')(new Date(),'yyyy-MM-dd') ;
			$scope.DocumentWorkflowProcess.isFinalSubmit = false;
			$scope.DocumentWorkflowProcess.docDetail.docTagRelationship.push(docTagRelationship);
		});
		
		// alert(angular.toJson($scope.DocumentWorkflowProcess, true));
		service.submitWorkflow($scope.DocumentWorkflowProcess).then(function(obj){
	    	alert(obj.status);
	        if(obj.status == 200){
	        	alert("Success");
	        //	$scope.gridOptionsmylist.data = obj.data;
	      	//  home.countmylist =  ($scope.gridOptionsmylist.data.length);
	        } else {
	          alert("Error"+obj.data);
	        }
	      });
		
  };
  
  home.submitDoc  = function(){  
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
		
		// alert(angular.toJson($scope.DocumentWorkflowProcess, true));
		service.submitWorkflow($scope.DocumentWorkflowProcess).then(function(obj){
	    	alert(obj.status);
	        if(obj.status == 200){
	        	alert("Success");
	        //	$scope.gridOptionsmylist.data = obj.data;
	      	//  home.countmylist =  ($scope.gridOptionsmylist.data.length);
	        } else {
	          alert("Error"+obj.data);
	        }
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
  tags += '<div class="input-group"><span class="input-group-addon" id="basic-addon1">Comment&nbsp;&nbsp;&nbsp;&nbsp;</span>'
  tags += '<textarea class="form-control" rows="3"></textarea></div>';
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
          html += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>';
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
  
  
  
//  $(function(){    
////      $('.submit-tag').on('click',function(){
////    	  alert('submit tag here');      
////          return false;        
////      });    
//  });
  
  $(function(){    
      $('.save-tag').on('click',function(){
    	  $('#example-multiple-optgroups').multiselect('refresh');
    
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