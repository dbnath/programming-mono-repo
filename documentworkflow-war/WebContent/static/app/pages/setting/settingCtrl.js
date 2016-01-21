

app.controller("settingCtrl",['service',function(service){
	
  var cc = this;
  cc.candidate = {};
  cc.setting = function(){
    service.setting(cc.candidate).then(function(obj){
      if(obj.status == 200){
        cc.candidate = {};
        alert("Added");
      } else {
        alert("Error");
      }
    })
  }
}]);
