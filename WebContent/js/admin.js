/**
 * 
 */
var admin = function () {
	
	var home = this;
	var $ = getElementById;

	function getElementById(elemId){
		return document.getElementById(elemId);
	}

	 home.uploadDocs =function(contextPath){
			window.open(contextPath+'/jsp/fileUpload.jsp?userId='+home.userId);
		};
		
      home.uploadErrorReasons =function(contextPath){
			window.open(contextPath+'/jsp/errReasonUpload.jsp?userId='+home.userId);
      };  
	  
	  
} 