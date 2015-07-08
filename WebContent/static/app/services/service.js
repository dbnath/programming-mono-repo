app.factory("service",function($http,$q){
  var data = {
    addUser : addUser,
    login : login,
    setting : setting,
    getApproval : getApproval,
    addUser: addUser,
    getAllDoc : getAllDoc,
    getDocByUser : getDocByUser,
    getDocDetails : getDocDetails
  
  };
  return data;

  function addUser(data){
    return request('POST','/api/users/add',data,{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'});
  }

  function login(data){
    return request('POST','rest/UserService/login',data,{'X-DOCWRKFLOW-AUTH' :  undefined});
  }

  function setting(data){
	 return request('POST','/api/users/setting',data,{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'});
  }



  function getApproval(){
    return request('GET','/api/users/getApproval',null,{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'});
  }

  
  function getAllDoc(){
	 return request('GET','rest/WflService/docs',null,{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'});
  }
  
  function getDocByUser(data){
	 return request('GET','rest/WflService/docs',null,{'X-DOCWRKFLOW-AUTH' :  '1|PRATIK|1'},{'userId':data});
  }
  
  function getDocDetails(data){
	 return request('GET','rest/WflService/getdetail',null,{'X-DOCWRKFLOW-AUTH' : '1|PRATIK|1'},{'docId':data});
  }
 
  function request(method,url,data,headers,params){
    var deferred = $q.defer();
    $http({
      method : method,
      headers : headers,
      url : url,
      data : data,
      params :params
      
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
