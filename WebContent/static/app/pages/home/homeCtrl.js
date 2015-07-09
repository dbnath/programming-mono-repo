app.controller("homeCtrl",['$stateParams','service','$scope','$rootScope','$templateCache','$log','uiGridConstants',function($stateParams,service,$scope,$rootScope,$templateCache,$log,uiGridConstants){
  console.log("Inside Home controller");
  var home = this;
  home.userId = $rootScope.selectedUserRole.userId;
  home.userName = $rootScope.selectedUserRole.userName;
  home.docdetails = {};
  document.title = 'Docflow::Home';
  home.appState ="hide";
  $scope.mappedRoles = [];

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
			 appScopeProvider: { 
			        onDblClick : function(row) {
			        	home.appState = 'show';
			        	
			        	service.getDocDetails(row.entity.docId).then(function(obj){
			        		
			        	    if(obj.status == 200){
			        	    //alert(angular.toJson(obj.data, true));
			        	    	home.docdetails ={};
			        	    	home.docdetails = obj.data;
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
			enableHorizontalScrollbar: false,
			 appScopeProvider: { 
			        onDblClick : function(row) {
			        	home.appState = 'show';
  	service.getDocDetails(row.entity.docId).then(function(obj){
			        		
			        	    if(obj.status == 200){
			        	    //alert(angular.toJson(obj.data, true));
			        	    	home.docdetails ={};
			        	    	home.docdetails = obj.data;
			        	    } else {
			        	      alert("Error"+obj.data); 
			        	    }
			        	  });

			         }
			    }
	}
	
	service.retrieveUserDetais(home.userId).then(function(obj){
	    if(obj.status == 200){
	    	$scope.mappedRoles = obj.data.userRoleList;
	    	console.log("Assigned role size :"+$scope.mappedRoles.length);
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

home.changeRole() = function(roleId) {
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
	/*angular.forEach( $scope.rows, function(row, key) {
		//alert(angular.toJson(row, true));
	//	alert(key);
		var newrow ={};
		 var index = $scope.gridOptions.data.indexOf(row.entity);
		 newrow.assignedTo =  home.userId;
		 newrow.docName  = row.entity.docName;
		 newrow.wfStatusDesc = row.entity.wfStatusDesc;
		 newrow.wfAssignmentGroupName = row.entity.wfAssignmentGroupName;
		 newrow.docId = row.entity.docId;
		 home.countmylist =  home.countmylist+1;
		 angular.extend( $scope.gridOptions.data[index], newrow);
			$scope.gridOptionsmylist.data.push(newrow);
		}, log);*/
		
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
     
      $log.log(msg);
   
    });

    gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
      var msg = 'rows changed ' + rows.length;
      
      $scope.rows =  rows;
      $log.log(msg);
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
  

  var tags = '<select id="example-multiple-optgroups" multiple="multiple">' +'</select>';
  tags += '<textarea class="form-control" rows="3"></textarea>';
  tags += ' <a href="#" class="btn btn-primary"><span class="glyphicon glyphicon-floppy-disk"></span>Save</a>';
  
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
          html += '<div class="row"><div class="col-xs-9">' + b.message + '</div><div class="col-xs-3"></div></div>';/* +  '<div>' + tags +'</div>'+ '</div></div>'*/;
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
          
          var optgroups = [
                           {
                               label: 'Group 1', children: [
                                   {label: 'Option 1.1', value: '1-1'},
                                   {label: 'Option 1.2', value: '1-2'},
                                   {label: 'Option 1.3', value: '1-3'}
                               ]
                           },
                           {
                               label: 'Group 2', children: [
                                   {label: 'Option 2.1', value: '2-1'},
                                   {label: 'Option 2.2', value: '2-2'},
                                   {label: 'Option 2.3', value: '2-3'}
                               ]
                           },
                           {
                               label: 'Group 3', children: [
                                   {label: 'Option 3.1', value: '3-1'},
                                   {label: 'Option 3.2', value: '3-2'},
                                   {label: 'Option 3.3', value: '3-3'}
                               ]
                           }
                       ];
                       $('#example-multiple-optgroups').multiselect('dataprovider', optgroups);
                       var selectconfig = {
                               enableFiltering: true,
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
         /* $('#example-multiple-optgroups').multiselect({
              enableFiltering: true
          });*/
    	  
    	  
    	  $("#myModal").css("max-height", height);
    	  
          a("#myModal").modal().on("hidden.bs.modal", function() {
              a(this).remove()
          })
      }
  })(jQuery);
  /*
  */
  $(function(){    
      $('.view-pdf').on('click',function(){
          var pdf_link = $(this).attr('href');
          var iframe = '<div class="iframe-container"><iframe src="'+pdf_link+'"></iframe></div>' ;
          $.createModal({
          title:'My Title',
          message: iframe,
          closeButton:true,
          scrollable:false
          });
          return false;        
      });    
  });
  
  $(function(){    
      $('.tagmodal').on('click',function(){
    	  e.preventDefault();   
    	  $('#editModal').modal('show')
      });    
  });
  
  $(function(){    
      $('.wfl-doc_upload').on('click',function(){
          var iframe = '<div><a href="rest/docadmin/template">Download Template for Bulk Upload</a></div><div><form method="POST" enctype="multipart/form-data" ng-upload="uploadComplete(content)" action="rest/docadmin/uploaddoc"><p><label>Select File:</label><input type="file" name="file" /></p><input type="submit" class="btn" value="Submit" ng-disabled="$isUploading" /></div></form>' ;
          $.createModal({
          title:'Bulk Upload',
          message: iframe,
          closeButton:true,
          scrollable:false
          });
          return false;        
      });    
  });  
    
}]);