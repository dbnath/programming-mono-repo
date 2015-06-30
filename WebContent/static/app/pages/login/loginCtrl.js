app.controller("loginCtrl",['service',function(service){
  console.log("Inside controller");
  var login = this;
  login.user = {};

  login.signIn=function(){
    window.location.href = '/#/userhome'
  }
  login.signUp = function(){
    login.user = {};
    $("#signUp").modal("show");
  }

  login.signIn = function(){
    var data = {
      "email" : login.email,
      "password" : login.password
    };
    service.login(data).then(function(obj){
      if(obj.status == 200){
        if(obj.data.type === 'user'){
          window.location.href = '#/userhome/'+obj.data._id;
        } else {
          window.location.href = '#/adminHome/'+obj.data._id;
        }
      } else {
        alert("Error"+data.data);
      }
    });
  }
  login.save = function(){
    if(login.user.password === login.user.confirmPass){
      login.user.status = 'notapplied';
      service.addUser(login.user).then(function(data){
        if(data.status == 200){
          $("#signUp").modal("hide");
        } else {
          alert("Error"+data.data);
        }
      });
    } else {
      alert("Password mismatch");
    }
  }
}]);
