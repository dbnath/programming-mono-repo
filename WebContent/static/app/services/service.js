app.factory("service",function($http,$q){
  var data = {
    addUser : addUser,
    login : login,
    setting : setting,
    getApproval : getApproval,
    addUser: addUser,
    getAllDoc : getAllDoc,
    getDocByUser : getDocByUser
    
  
  };
  return data;

  function addUser(data){
    return request('POST','/api/users/add',data,null);
  }

  function login(data){
    return request('POST','rest/UserService/login',data,null);
  }

  function setting(data){
	    return request('POST','/api/users/setting',data,null);
	  }



  function getApproval(){
    return request('GET','/api/users/getApproval',null,null);
  }

  
function getAllDoc(){
	
	 return request('GET','rest/WflService/docs',null,null);
	
}
  
function getDocByUser(data){
	
	 return request('GET','rest/WflService/docs',data,null);
	
}
  

  function request(method,url,data,headers){
    var deferred = $q.defer();
    $http({
      method : method,
      headers : headers,
      url : url,
      data : data
    }).success(function(data,status){
      var result = {
        data : data || {},
        status : status
      }
      deferred.resolve(result);
    }).error(function(error,status){
      var result= {
        data : error || {},
          status : status
      }
      deferred.resolve(result);
    })
    return deferred.promise;
  }
});
