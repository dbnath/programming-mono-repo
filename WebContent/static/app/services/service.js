app.factory("service",function($http,$q){
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
	    retrieveAllDocRepos : retrieveAllDocRepos,
	    retrieveAllUnmappedTypeTags : retrieveAllUnmappedTypeTags,
	    retrieveAllUnmappedTagsSubTags : retrieveAllUnmappedTagsSubTags,
	    retrieveAllUsers : retrieveAllUsers,
	    retrieveAllRoles : retrieveAllRoles,
	    retrieveUserDetais : retrieveUserDetais,
	    retrieveRoleUserMap : retrieveRoleUserMap,
	    retrieveUnmappedRoleUser : retrieveUnmappedRoleUser,
	    uploadDocument : uploadDocument,
	    downloadDocTemplate : downloadDocTemplate
	 };
	 return data;

	function addUser(data){
		return request('POST','/api/users/add',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},data);
	}
	
	function login(data){
		return request('POST','rest/UserService/login',{'X-DOCWRKFLOW-AUTH' :  undefined},data);
	}
	
	function setting(data){
		return request('POST','/api/users/setting',{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'},data);
	}
	
	function getApproval(){
	    return request('GET','/api/users/getApproval',{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'});
	}
	  
	function getAllDoc(){
		return request('GET','rest/WflService/docs',{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'});
	}
	  
	function getDocByUser(data){
		return request('GET','rest/WflService/docs',{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'},null,{'userId':data});
	}
	  
	function getDocDetails(data){
		return request('GET','rest/WflService/getdetail',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'docId':data});
	}
 
	function assignToMe(data){
		return request('POST', 'rest/WflService/assigndocto', {'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'}, data);
	}
	
	function submitWorkflow(data){
		return request('POST', 'rest/WflService/submitwf', {'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'}, data);
	}
	
	function retrieveTags(data){
		 return request('GET','rest/docadmin/doctypetags',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'docTypeId':data});
	}
	
	function retrieveSubTags(data){
		 return request('GET','rest/docadmin/doctagsubtags',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'docTagId':data});
	}
	
	function retrieveAllDocTypes() {
		 return request('GET','rest/docadmin/doctype',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
	}
	
	function retrieveAllDocTags() {
		 return request('GET','rest/docadmin/doctags',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
	}
	
	function retrieveAllDocSubTags() {
		 return request('GET','rest/docadmin/docsubtags',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
	}
	
	function retrieveAllDocRepos() {
		 return request('GET','rest/docadmin/docrepo',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
	}
	
	function retrieveAllUnmappedTypeTags(data) {
		 return request('GET','rest/docadmin/docunmappedtypetags',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'docTypeId':data});
	}

	function retrieveAllUnmappedTagsSubTags(data) {
		 return request('GET','rest/docadmin/docunmappedtagsubtags',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'docTagId':data});
	}
	
	function retrieveAllUsers() {
		 return request('GET','rest/useradmin/users',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
	}
	
	function retrieveAllRoles() {
		 return request('GET','rest/useradmin/roles',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
	}
	
	function retrieveUserDetais(data) {
		 return request('GET','rest/useradmin/userdetail',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'userId':data});
	}

	function retrieveRoleUserMap(data) {
		 return request('GET','rest/useradmin/roleusermap',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'roleId':data});
	}

	function retrieveUnmappedRoleUser(data) {
		 return request('GET','rest/useradmin/unmappedroleuser',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},null,{'roleId':data});
	}

	function uploadDocument(data) {
		 return request('GET','rest/docadmin/uploaddoc',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'}, null, {});
	}
	
	function downloadDocTemplate() {
		 return request('GET','rest/docadmin/template',{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
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
