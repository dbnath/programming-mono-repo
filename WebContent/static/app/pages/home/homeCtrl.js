app.controller("homeCtrl",['service',function(service){
  console.log("Inside Home controller");
  var home = this;
  home.user = {};
  document.title = 'Docflow::Home';
  
  $('#myTabs a').click(function (e) {
	  alert('Hi');
	  e.preventDefault()
	  $(this).tab('show')
  })

}]);


