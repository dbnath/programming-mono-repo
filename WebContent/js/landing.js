/**
 * 
 */
var landing = function () {
	
	
	var $ = getElementById;
	var commonfn = new commonframework();

	function getElementById(elemId){
		return document.getElementById(elemId);
	}

	var selectedItemsTeamList = [];
	var selectedItemsMyList = null;
	
	this.landinginit = landinginit;
	function landinginit() {
		serviceObj = new service();
		if($("selectedRoleId").value == "1") {
			$("home").style.display = 'none';
			$("myInboxSwitch").style.display = 'none';
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
		if($("teamtable")){
			teamSorter.init();
		}
	}
	
	this.landinginitMyListResponse = landinginitMyListResponse;
	function landinginitMyListResponse(responseData) {
		$("myGrid").innerHTML = responseData.responseText;
		if($("mytable")){
		//setTimeout(function(){
			sorter.init();
		}
		//}, 2000);
		
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
		//alert(selectedItemsTeamList);
	}
	
	this.setMyAssignment=setMyAssignment;
	function setMyAssignment(control){
		
		if(control.checked == true){
			//selectedItemsMyList[selectedItemsMyList.length] = control.value;
			//alert(selectedItemsMyList);
			if(selectedItemsMyList == null){
				selectedItemsMyList = control.value;
			} else {
				//alert('You can work on one agreement at a time','E');
				jDialog('You can work on one agreement at a time.');
				control.checked=false;
				return false;
			}
		} else { //User unchecks a row
			selectedItemsMyList = null;
		}
		$('checkerStart').disabled=true;
		$('checkerHold').disabled=true;
		$('checkerComplete').disabled=true;
		$('checkerStatus').disabled=true;	
		if(selectedItemsMyList != null){
			
			var statusCd = $(selectedItemsMyList+'statusCode').value;
			if(!statusCd) {
				statusCd = $(selectedItemsMyList+'statusCode').innerHTML;
			}
			if($("selectedRoleId").value == "1"){
				if(statusCd == 1){
					$('checkerStart').disabled=false;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=true;
					$('checkerStatus').disabled=true;
				} else if(statusCd == 2){
					$('checkerStart').disabled=true;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=false;
					$('checkerStatus').disabled=false;
				}
			} else if($("selectedRoleId").value == "2"){
				if(statusCd == 16){
					$('checkerStart').disabled=false;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=true;
					$('checkerStatus').disabled=true;
				} else if(statusCd == 17){
					$('checkerStart').disabled=true;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=false;
					$('checkerStatus').disabled=false;
				}				
			} else if($("selectedRoleId").value == "3"){
				if(statusCd == 19){
					$('checkerStart').disabled=false;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=true;
					$('checkerStatus').disabled=true;
				} else if(statusCd == 20){
					$('checkerStart').disabled=true;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=false;
					$('checkerStatus').disabled=false;
				} else if(statusCd == 21){
					$('checkerStart').disabled=true;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=false;
					$('checkerStatus').disabled=true;
				}
			}
			
		} 
	}
	
	this.startClick=startClick;
	function startClick(){
		/*if(selectedItemsMyList.length == 0){
			alert("Please select an agreement");
			return false;
		}
		if(selectedItemsMyList.length > 1){
			alert("You can work with only one Agreement at a time");
			return false;
		}*/
		
		var inputObj = {};
		inputObj.agreementId=selectedItemsMyList;//[0];
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
				//alert('3a1'+agrId+'###'+k);
				if(agrId){
					$(agrId).checked=false;
					$(agrId+'statusDescription').innerHTML=docList[k].statusDescription;
					$(agrId+'statusCode').innerHTML=docList[k].statusCode;
					if($("selectedRoleId").value == "2"){
						$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
					}
				}
			}
			$('checkerStart').disabled=true;
			$('checkerComplete').disabled=false;
			$('checkerStatus').disabled=false;
			//$('makerActionsPanel').style="display:none";
		}
		
	}
	
	this.completeClick=completeClick;
	function completeClick(){
		/*if(selectedItemsMyList.length == 0){
			alert("Please select an agreement");
			return false;
		}
		if(selectedItemsMyList.length > 1){
			alert("You can work with only one Agreement at a time");
			return false;
		}*/
		
		var inputObj = {};
		inputObj.agreementId=selectedItemsMyList;//[0];
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
				$(agrId+'statusCode').innerHTML=docList[k].statusCode;
				if($("selectedRoleId").value == "2"){
					$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
				}
			}
			$('checkerStart').disabled=true;
			$('checkerHold').disabled=true;
			$('checkerComplete').disabled=true;
			$('checkerStatus').disabled=true;	
			$('checkerComments').disabled=true;	
		}
	}	
	
	this.holdClick=holdClick;
	function holdClick(){
		/*if(selectedItemsMyList.length == 0){
			alert("Please select an agreement");
			return false;
		}
		if(selectedItemsMyList.length > 1){
			alert("You can work with only one Agreement at a time");
			return false;
		}*/
		jDialog('Do you realy want to hold it?',function(){
			if($("selectedRoleId").value == "1" && $('checkerStatus').value == 15){
				if($('checkerComments').value == null){
					alert('Please fill in comments section');
					return false;
				}
			}
	
			if($("selectedRoleId").value == "2" && $('checkerStatus').value == 15){
				if($('checkerComments').value == null || $('errorReasonList').value == -1){
					alert('Please fill in both comments section and Error Reason');
					return false;
				}
			}
			
			if($("selectedRoleId").value == "3" && $('checkerStatus').value == 21){
				if($('checkerComments').value == null){
					alert('Please fill in comments section');
					return false;
				}
			}		
			
			
			var inputObj = {};
			inputObj.agreementId=selectedItemsMyList;//[0];
			inputObj.roleId=$("selectedRoleId").value;
			inputObj.statusCode=$('checkerStatus').value;
			inputObj.user={userId:$("selectedUserId").value, roleId:$("selectedRoleId").value};
			inputObj.comment=$('checkerComments').value;
			if($("selectedRoleId").value == "2"){
			  inputObj.errorReasonCode=$('errorReasonList').value;
			}
			serviceObj.holdProcess(inputObj,holdRespFn);
			jDialog.currentDialog.remove();
		});
	}
	
	this.holdRespFn=holdRespFn;
	function holdRespFn(responseData){
		
		var responseObject = JSON.parse(responseData.responseText);
		
		var respStatus = responseObject.response.responseMessage;
		
		if(respStatus == "Success"){
			var docList = responseObject.docList;
			
			for(var k in docList){
				var agrId = docList[k].agreementId;
				$(agrId+'statusDescription').innerHTML=docList[k].statusDescription;
				$(agrId+'statusCode').innerHTML=docList[k].statusCode;
				if($("selectedRoleId").value == "2"){
					$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
				}
			}
			$('checkerStart').disabled=true;
			$('checkerHold').disabled=true;
			$('checkerComplete').disabled=true;
			$('checkerStatus').disabled=true;	
			$('checkerComments').disabled=true;	
			
			if($("selectedRoleId").value == "3"){
				$('checkerComplete').disabled=false;
			}
		}
	}	
	
	this.reloadGridData=reloadGridData
	function reloadGridData(){
		serviceObj.getTeamDocList(landinginitResponse);
		serviceObj.getMyDocList(landinginitMyListResponse);
		selectedItemsMyList = null;
	}
	
	this.setHoldStatus=setHoldStatus;
	function setHoldStatus(stsCombo){
		
		if(stsCombo.value != -1){
			$('checkerHold').disabled=false;
			$('checkerStart').disabled=true;
			$('checkerComplete').disabled=false;
			$('checkerComments').disabled=false;
			if($("selectedRoleId").value == "2"){
				$('errorReasonList').disabled=false;
			}
		}
	}
	
	function showMessage(msg,msgType){
		//if($('msgPanel')){
			//document.getElementById('msgPanel').style="display:''";
			/*if(msgType === 'I'){
				$('msgPanel').style="color:Blue";
			}
			if(msgType === 'E'){
				$('msgPanel').style="color:Red";
			}*/
			//$('msgPanel').outerHTML = "<div style='display:visible'>"+msg+"</div>";
		//}
		window.showModalDialog(msg);
	}	
	  
} 