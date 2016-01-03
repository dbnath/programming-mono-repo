/**
 * 
 */
var landing = function () {
	
	
	var $ = getElementById;

	function getElementById(elemId){
		return document.getElementById(elemId);
	}

	var selectedItemsTeamList = [];
	var selectedItemsMyList = [];
	
	this.landinginit = landinginit;
	function landinginit() {
		serviceObj = new service();
		if($("selectedRoleId").value == "1") {
			$("home").style.display = 'none';
			$("globalInboxSwitch").style.display = 'none';
		}
		if($("home").style.display != 'none') {
			serviceObj.getTeamDocList(landinginitResponse);
			$("profile").style.display = 'none';
		}
		
		serviceObj.getMyDocList(landinginitMyListResponse);
	}
	
	this.landinginitResponse = landinginitResponse;
	function landinginitResponse(responseData) {
		$("teamGrid").innerHTML = responseData.responseText;
	}
	
	this.landinginitMyListResponse = landinginitMyListResponse;
	function landinginitMyListResponse(responseData) {
		$("myGrid").innerHTML = responseData.responseText;
	}
	
	this.setAssignment=setAssignment;
	function setAssignment(control){
		if(control.checked == true){
			selectedItemsTeamList[selectedItemsTeamList.length] = control.value;
		}
	}
	
	this.assignMe=assignMe;
	function assignMe(){
		if(selectedItemsTeamList.length == 0){
			alert("Please select at least one agreement");
			return false;
		}
		serviceObj.assignToMe(selectedItemsTeamList,assignRespFn);
		
	}
	
	this.assignRespFn=assignRespFn;
	function assignRespFn(responseData){
		
		if(responseData.responseText === "true"){
			alert("Assignments assigned successfully. Please go to My Inbox to process further.");
			resetAssignmentList();
			reloadGridData();
		} else {
			alert("There was an error in the process. Please try again.");
		}
	}
	
	function resetAssignmentList(){
		for(var k in selectedItemsTeamList){
			$(selectedItemsTeamList[k]).disabled = true;
			selectedItemsTeamList[k]=null;
		}
		alert(selectedItemsTeamList);
	}
	
	this.setMyAssignment=setMyAssignment;
	function setMyAssignment(control){
		if(control.checked == true){
			selectedItemsMyList[selectedItemsMyList.length] = control.value;
		}
		alert(selectedItemsMyList.length+ $('checkerStart'));
		if(selectedItemsMyList.length > 0){
			$('checkerStart').disabled=false;
		}		
	}
	
	this.startClick=startClick;
	function startClick(){
		if(selectedItemsMyList.length == 0){
			alert("Please select an agreement");
			return false;
		}
		if(selectedItemsMyList.length > 1){
			alert("You can work with only one Agreement at a time");
			return false;
		}
		
		var inputObj = {};
		inputObj.agreementId=selectedItemsMyList[0];
		inputObj.roleId=$("selectedRoleId").value;
		
		if($("selectedRoleId").value == "1"){
			inputObj.statusCode=2; //For Maker Start hardcoding the value
		} else if($("selectedRoleId").value == "2"){
			inputObj.statusCode=17; //For Checker Start hardcoding the value
		} else if($("selectedRoleId").value == "3"){
			inputObj.statusCode=20; //For SME Start hardcoding the value
		}					
		inputObj.user={userId:$("selectedUserId").value, roleId:$("selectedRoleId").value};
		
		serviceObj.startProcess(inputObj,startRespFn);		
	}
	
	this.startRespFn=startRespFn;
	function startRespFn(responseData){
		
		var responseObject = JSON.parse(responseData.responseText);
		
		var respStatus = responseObject.response.responseMessage;
		
		if(respStatus == "Success"){
			var docList = responseObject.docList;
			
			for(var k in docList){
				var agrId = docList[k].agreementId;
				
				$(agrId+'statusDescription').innerHTML=docList[k].statusDescription;
				if($("selectedRoleId").value == "2"){
					$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
				}
				
			}
			$('checkerStart').disabled=true;
			$('checkerComplete').disabled=false;
			$('checkerStatus').disabled=false;	
		}
		
	}
	
	this.completeClick=completeClick;
	function completeClick(){
		if(selectedItemsMyList.length == 0){
			alert("Please select an agreement");
			return false;
		}
		if(selectedItemsMyList.length > 1){
			alert("You can work with only one Agreement at a time");
			return false;
		}
		
		var inputObj = {};
		inputObj.agreementId=selectedItemsMyList[0];
		inputObj.roleId=$("selectedRoleId").value;
		
		if($("selectedRoleId").value == "1"){
			inputObj.statusCode=3; //For Maker Complete hardcoding the value
		} else if($("selectedRoleId").value == "2"){
			inputObj.statusCode=18; //For Checker Complete hardcoding the value
		} else if($("selectedRoleId").value == "3"){
			inputObj.statusCode=22; //For SME Complete hardcoding the value
		}
		inputObj.user={userId:$("selectedUserId").value, roleId:$("selectedRoleId").value};
		
		serviceObj.completeProcess(inputObj,completeRespFn);		
	}
	
	this.completeRespFn=completeRespFn;
	function completeRespFn(responseData){
		
		var responseObject = JSON.parse(responseData.responseText);
		
		var respStatus = responseObject.response.responseMessage;
		
		if(respStatus == "Success"){
			var docList = responseObject.docList;
			
			for(var k in docList){
				var agrId = docList[k].agreementId;
				$(agrId+'statusDescription').innerHTML=docList[k].statusDescription;
				if($("selectedRoleId").value == "2"){
					$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
				}
			}
			$('checkerStart').disabled=true;
			$('checkerComplete').disabled=false;
			$('checkerStatus').disabled=false;	
		}
		
	}	
	
	this.reloadGridData=reloadGridData
	function reloadGridData(){
		serviceObj.getTeamDocList(landinginitResponse);
		serviceObj.getMyDocList(landinginitMyListResponse);
	}
	  
} 