app.factory("service",function($http,$q,$rootScope){
	 var data = {
	    addUser : addUser,
	    login : login,
	    setting : setting,
	    getApproval : getApproval,
	    addUser: addUser,
	    getAllDoc : getAllDoc,
	    getDocByUser : getDocByUser,
	    getDocDetails : getDocDetails,
	    assignToMe : assignToMe,
	    submitWorkflow : submitWorkflow,
	    retrieveTags : retrieveTags,
	    retrieveSubTags : retrieveSubTags,
	    retrieveAllDocTypes : retrieveAllDocTypes,
	    retrieveAllDocTags : retrieveAllDocTags,
	    retrieveAllDocSubTags : retrieveAllDocSubTags,
	    retrieveTypeTagSubTagsMap : retrieveTypeTagSubTagsMap,
	    retrieveAllDocRepos : retrieveAllDocRepos,
	    retrieveAllUnmappedTypeTags : retrieveAllUnmappedTypeTags,
	    retrieveAllUnmappedTagsSubTags : retrieveAllUnmappedTagsSubTags,
	    retrieveAllUsers : retrieveAllUsers,
	    retrieveAllRoles : retrieveAllRoles,
	    retrieveUserDetais : retrieveUserDetais,
	    retrieveRoleUserMap : retrieveRoleUserMap,
	    retrieveUnmappedRoleUser : retrieveUnmappedRoleUser,
	    uploadDocument : uploadDocument,
	    downloadDocTemplate : downloadDocTemplate,
	    setDocWorkflowAuthorizationId : setDocWorkflowAuthorizationId,
	    getDocWorkflowAuthorizationId : getDocWorkflowAuthorizationId
	 };
	 return data;

	function addUser(data){
		return request('POST','/api/users/add',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},data);
	}
	
	function login(data){
		return request('POST','rest/UserService/login',{'x-docwrkflow-auth' :  undefined},data);
	}
	
	function setting(data){
		return request('POST','/api/users/setting',{'x-docwrkflow-auth' :  getDocWorkflowAuthorizationId()},data);
	}
	
	function getApproval(){
	    return request('GET','/api/users/getApproval',{'x-docwrkflow-auth' :  getDocWorkflowAuthorizationId()});
	}
	  
	function getAllDoc(){
		return request('GET','rest/WflService/docs',{'x-docwrkflow-auth' :  getDocWorkflowAuthorizationId()});
	}
	  
	function getDocByUser(data){
		return request('GET','rest/WflService/docs',{'x-docwrkflow-auth' :  getDocWorkflowAuthorizationId()},null,{'userId':data});
	}
	  
	function getDocDetails(data){
		return request('GET','rest/WflService/getdetail',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'docId':data});
	}
 
	function assignToMe(data){
		return request('POST', 'rest/WflService/assigndocto', {'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()}, data);
	}
	
	function submitWorkflow(data){
		return request('POST', 'rest/WflService/submitwf', {'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()}, data);
	}
	
	function retrieveTags(data){
		 return request('GET','rest/docadmin/doctypetags',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'docTypeId':data});
	}
	
	function retrieveSubTags(data){
		 return request('GET','rest/docadmin/doctagsubtags',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'docTagId':data});
	}
	
	function retrieveTypeTagSubTagsMap(data){
		 return request('GET','rest/docadmin/doctypetagsubtags',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'docTypeId':data});
	}

	function retrieveAllDocTypes() {
		 return request('GET','rest/docadmin/doctype',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()});
	}
	
	function retrieveAllDocTags() {
		 return request('GET','rest/docadmin/doctags',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()});
	}
	
	function retrieveAllDocSubTags() {
		 return request('GET','rest/docadmin/docsubtags',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()});
	}
	
	function retrieveAllDocRepos() {
		 return request('GET','rest/docadmin/docrepo',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()});
	}
	
	function retrieveAllUnmappedTypeTags(data) {
		 return request('GET','rest/docadmin/docunmappedtypetags',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'docTypeId':data});
	}

	function retrieveAllUnmappedTagsSubTags(data) {
		 return request('GET','rest/docadmin/docunmappedtagsubtags',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'docTagId':data});
	}
	
	function retrieveAllUsers() {
		 return request('GET','rest/useradmin/users',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()});
	}
	
	function retrieveAllRoles() {
		 return request('GET','rest/useradmin/roles',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()});
	}
	
	function retrieveUserDetais(data) {
		 return request('GET','rest/useradmin/userdetail',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'userId':data});
	}

	function retrieveRoleUserMap(data) {
		 return request('GET','rest/useradmin/roleusermap',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'roleId':data});
	}

	function retrieveUnmappedRoleUser(data) {
		 return request('GET','rest/useradmin/unmappedroleuser',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()},null,{'roleId':data});
	}

	function uploadDocument(data) {
		 return request('GET','rest/docadmin/uploaddoc',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()}, null, {});
	}
	
	function downloadDocTemplate() {
		 return request('GET','rest/docadmin/template',{'x-docwrkflow-auth' : getDocWorkflowAuthorizationId()});
	}
	
	function setDocWorkflowAuthorizationId(userData) {
		$rootScope.selectedUserRole.userId = userData.userId;
		$rootScope.selectedUserRole.userName = userData.userName;
		$rootScope.selectedUserRole.selectedRoleId = userData.roleId;
	}
	
	function getDocWorkflowAuthorizationId() {
		return $rootScope.selectedUserRole.userId+"|"+$rootScope.selectedUserRole.selectedRoleId;
	}

  	function request(method,url,headers,data,params){
	    var deferred = $q.defer();
	    $http({
	      method : method,
	      headers : headers,
	      url : url,
	      data : data,
	      params :params
	      
	    }).success(function(data,status, headers,config){
			var results = [] ;
			results.data = data || {} ;
			results.headers = headers();
			results.status = status;
			results.config = config;		  
			deferred.resolve(results);
	    }).error(function(error,status){
			var results = [] ;
			results.data = error || {} ;
			results.status = status;
			deferred.resolve(results);
	    })
	    return deferred.promise;
  	}
});
