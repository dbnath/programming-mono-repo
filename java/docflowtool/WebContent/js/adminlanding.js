/**
 * 
 */
var adminlanding = function () {
	
	
	var $ = getElementById;
	var commonfn = new commonframework();

	function getElementById(elemId){
		return document.getElementById(elemId);
	}
	
	function isIE () {
		  var myNav = navigator.userAgent.toLowerCase();
		  return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
	}	

	function isIE () {
		  var myNav = navigator.userAgent.toLowerCase();
		  return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
	}

	var selectedItemsTeamList = [];
	var selectedItemsMyList = null;
	
	this.landinginit = landinginit;
	function landinginit() {
		serviceObj = new service();
		
		var inputObj = {};
		inputObj.roleId = $("viewId").value;
		
		serviceObj.getAdminDocList(inputObj,landinginitMyListResponse);	
		
	}
	
	this.landinginitMyListResponse = landinginitMyListResponse;
	function landinginitMyListResponse(responseData) {
		//alert(responseData.responseText);
		$("myGrid").innerHTML = responseData.responseText;
		//selectRole(inputObj.roleId);
		//alert(1);
		if($("mytable")){
			//try{
			sorter.init();
			/*} catch(ex){
				for(var k in ex){
					alert(k + ex[k]);
				}
			}*/
		}
		//alert(2);
	}
	
	this.selectRole=selectRole;
	function selectRole(roleId){
		$("viewId").value=roleId;
	}
	
	/*this.setAssignment=setAssignment;
	function setAssignment(control){
		if(control.checked == true){
			//selectedItemsTeamList[selectedItemsTeamList.length] = control.value;
			if(control.value != null && control.value != ''){
				selectedItemsTeamList.push(control.value);
			}
		}
	}*/
	
	this.assignMe=assignMe;
	function assignMe(agreementId){
		var input = [];
		input.push(agreementId);
		serviceObj.assignToMe(input,assignRespFn);
		
	}
	
	this.assignRespFn=assignRespFn;
	function assignRespFn(responseData){
		if(responseData.responseText === "true"){
			showMessage("Assignment(s) assigned successfully.","success");
			//resetAssignmentList();
			reloadGridData();
		} else {
			showMessage("There was an error in the process. <br> Please try again.","error");
		}
	}
	
	/*function resetAssignmentList(){
		for(var k in selectedItemsTeamList){
			$(selectedItemsTeamList[k]).disabled = true;
			selectedItemsTeamList[k]=null;
		}
		//alert(selectedItemsTeamList);
	}*/
	
	this.setMyAssignment=setMyAssignment;
	function setMyAssignment(control){
		
		if(control.checked == true){
			//selectedItemsMyList[selectedItemsMyList.length] = control.value;
			//alert(selectedItemsMyList);
			if(selectedItemsMyList == null){
				selectedItemsMyList = control.value;
			} else {
				showMessage("You can work on one agreement at a time","warning");
				//jDialog('You can work on one agreement at a time.');
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
		$('checkerStatus').value=-1;
		$('checkerComments').value='';
			
		if(selectedItemsMyList != null){
			
			var statusCd = $(selectedItemsMyList+'statusCode').value;
			var numPages = $(selectedItemsMyList+'numPages').value;
			var numFields = $(selectedItemsMyList+'numFields').value;
			
			if(!statusCd) {
				statusCd = $(selectedItemsMyList+'statusCode').innerHTML;
			}
			if(!numPages){
				numPages = $(selectedItemsMyList+'numPages').innerHTML;
			}
			if(!numFields){
				numFields = $(selectedItemsMyList+'numFields').innerHTML;
			}
			$('numPages').value = numPages;
			$('numFields').value = numFields;
			
			if($("viewId").value == "1"){
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
					$('checkerComments').disabled = false;
				}
			} else if($("viewId").value == "2"){
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
					$('checkerComments').disabled = false;
				} else {
					assignMe(selectedItemsMyList);
				}
				
			} else if($("viewId").value == "3"){
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
					$('checkerComments').disabled = false;
				} else if(statusCd == 21){
					$('checkerStart').disabled=true;
					$('checkerHold').disabled=true;
					$('checkerComplete').disabled=false;
					$('checkerStatus').disabled=true;
					$('checkerComments').disabled = false;
					var smeComments = $(selectedItemsMyList+'smeComments').value;
					if (! smeComments) {
						smeComments = $(selectedItemsMyList+'smeComments').innerHTML;
					}
					$('checkerComments').value = smeComments;
				} else {
					assignMe(selectedItemsMyList);
				}
			}
			
		} 
	}
	
	function getAgreementTimeStamp(agrId){
		var lastUpdationDate = $(agrId+'lastUpdationDate').value;
		
		if(!lastUpdationDate){
			lastUpdationDate = $(agrId+'lastUpdationDate').innerHTML;
		}
		
		return lastUpdationDate;
	}	
	
	this.startClick=startClick;
	function startClick(){
		var inputObj = {};
		inputObj.agreementId=selectedItemsMyList;//[0];
		inputObj.roleId=$("viewId").value;
		inputObj.lastUpdationDate=getAgreementTimeStamp(selectedItemsMyList);
		
		if($("viewId").value == "1"){
			inputObj.statusCode=2; //For Maker Start hardcoding the value
		} else if($("viewId").value == "2"){
			inputObj.statusCode=17; //For Checker Start hardcoding the value
		} else if($("viewId").value == "3"){
			inputObj.statusCode=20; //For SME Start hardcoding the value
		}					
		inputObj.user={userId:$("selectedUserId").value, roleId:$("viewId").value};
		
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
					
					showMessage(responseObject.message, "success");
					
					$(agrId+'statusDescription').innerHTML=docList[k].statusDescription;
					$(agrId+'numPages').innerHTML=docList[k].numPages;
					$(agrId+'numFields').innerHTML=docList[k].numFields;
					$(agrId+'statusCode').innerHTML=docList[k].statusCode;
					$(agrId+'statusCode').value=docList[k].statusCode;
					$(agrId+'lastUpdationDate').innerHTML=docList[k].lastUpdationDateStr;
					$(agrId+'lastUpdationDate').value=docList[k].lastUpdationDateStr;					
					
					var stsCode = docList[k].statusCode;
					
					if($("viewId").value == "1"){
						$(agrId+'makerComments').innerHTML=docList[k].makerComments;
						if(stsCode != 1 && stsCode != 2){
							$(agrId).disabled=true;
						}
					} else if($("viewId").value == "2"){
						$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
						$(agrId+'checkerComments').innerHTML=docList[k].checkerComments;
						$(agrId+'errorReason').innerHTML=docList[k].errorReason;						
						if(stsCode != 16 && stsCode != 17){
							$(agrId).disabled=true;
						}
					} else if($("viewId").value == "3"){
						$(agrId+'checkerComments').innerHTML=docList[k].checkerComments;
						$(agrId+'smeComments').innerHTML=docList[k].smeComments;
						if(stsCode != 19 && stsCode != 20 && stsCode != 21){
							$(agrId).disabled=true;
						}
					}				
				}
			}
			disableAll();
		} else {			
			var agrId = responseObject.agreementId;
			showMessage(responseObject.message, "error");
			if(agrId){
				$(agrId).checked=false;
			}			
			disableAll();
		}
		
	}
	
	function disableAll(){
		$('checkerStart').disabled=true;
		$('checkerHold').disabled=true;
		$('checkerComplete').disabled=true;
		$('checkerStatus').disabled=true;
		$('checkerComments').disabled=true;
		$('checkerComments').value='';
		$('checkerStatus').value=-1;			
		$('numPages').value='';
		$('numFields').value='';
		selectedItemsMyList = null;	
		if($("viewId").value == "2"){
		  $('errorReasonList').value = -1;
		}
	}
	
	this.completeClick=completeClick;
	function completeClick(){
		//if($("viewId").value == "2" || $("viewId").value == "3"){
			if ($('numPages').value == null || $('numPages').value == '' || $('numPages').value == 0) {
				showMessage("Please fill up Comments, Number of Pages and Number of Fields in Comment Section befor marking an agreement as Complete...","warning");
				return false;
			}
			if ($('numFields').value == null || $('numFields').value == '' || $('numFields').value == 0) {
				showMessage("Please fill up Comments, Number of Pages and Number of Fields in Comment Section befor marking an agreement as Complete...","warning");
				return false;
			}
			if ($('checkerComments').value == null || $('checkerComments').value == '') {
				showMessage("Please fill up Comments, Number of Pages and Number of Fields in Comment Section befor marking an agreement as Complete...","warning");
				return false;
			}
		//}
		var inputObj = {};
		inputObj.agreementId=selectedItemsMyList;//[0];
		inputObj.roleId=$("viewId").value;
		inputObj.comment=$('checkerComments').value;
		inputObj.numPages=$('numPages').value;
		inputObj.numFields=$('numFields').value;
		inputObj.lastUpdationDate=getAgreementTimeStamp(selectedItemsMyList);
		
		if($("viewId").value == "1"){
			inputObj.statusCode=3; //For Maker Complete hardcoding the value
		} else if($("viewId").value == "2"){
			inputObj.statusCode=18; //For Checker Complete hardcoding the value
		} else if($("viewId").value == "3"){
			inputObj.statusCode=22; //For SME Complete hardcoding the value
		}
		inputObj.user={userId:$("selectedUserId").value, roleId:$("viewId").value};
		
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
				if(agrId){
					$(agrId).checked=false;
					showMessage(responseObject.message, "success");
					//$(agrId).disabled=true;
					$(agrId+'statusDescription').innerHTML=docList[k].statusDescription;
					$(agrId+'numPages').innerHTML=docList[k].numPages;
					$(agrId+'numFields').innerHTML=docList[k].numFields;
					$(agrId+'statusCode').innerHTML=docList[k].statusCode;
					$(agrId+'statusCode').value=docList[k].statusCode;
					$(agrId+'lastUpdationDate').innerHTML=docList[k].lastUpdationDateStr;
					$(agrId+'lastUpdationDate').value=docList[k].lastUpdationDateStr;					
					
					var stsCode = docList[k].statusCode;
					if($("viewId").value == "1"){
						$(agrId+'makerComments').innerHTML=docList[k].makerComments;
						if(stsCode != 1 && stsCode != 2){
							$(agrId).disabled=true;
						}
					} else if($("viewId").value == "2"){
						$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
						$(agrId+'checkerComments').innerHTML=docList[k].checkerComments;
						$(agrId+'errorReason').innerHTML=docList[k].errorReason;
						
						if(stsCode != 16 && stsCode != 17){
							$(agrId).disabled=true;
						}
					} else if($("viewId").value == "3"){
						$(agrId+'checkerComments').innerHTML=docList[k].checkerComments;
						$(agrId+'smeComments').innerHTML=docList[k].smeComments;					
						if(stsCode != 19 && stsCode != 20 && stsCode != 21){
							$(agrId).disabled=true;
						}
					}
				}
			}
			disableAll();
		} else {			
			var agrId = responseObject.agreementId;
			showMessage(responseObject.message,"error");
			if(agrId){
				$(agrId).checked=false;
			}			
			disableAll();
		}
	}	
	
	this.holdClick=holdClick;
	function holdClick(){

		//jDialog('Do you realy want to hold it?',function(){
			if($("viewId").value == "1" && $('checkerStatus').value == 15){				
				if($('checkerComments').value == null || $('checkerComments').value == ''){
					showMessage("Please fill Comments in Comments Section before marking an agreement as Hold...","warning");
					return false;
				}
			}
	
			if($("viewId").value == "2" && $('checkerStatus').value == 15){
				if($('checkerComments').value == null || $('checkerComments').value == '' || $('errorReasonList').value == -1){
					showMessage("Please fill in both Comments and Error Reason in Comment Section before marking an agreement as Hold...","warning");
					return false;
				}
			}
			
			if($("viewId").value == "3" && $('checkerStatus').value == 21){
				if($('checkerComments').value == null || $('checkerComments').value == ''){
					showMessage("Please fill in Comment in Comment Section before marking an agreement as Hold...","warning");
					return false;
				}
			}		
			
			if(isIE () && isIE () < 9) {
				var result = confirm("Hold Agreement. Are you sure?");
				if (result) {
					holdwork();
				}
			}
			else {
				/*jDialog({
				  title:"Hold Agreement",
				  content:"Hold Agreement. Are you sure?",
				  callBack:function(){
					  holdwork();
					  jDialog.currentDialog.remove();
				  }
				});*/
				swal({   
						title: "Hold Agreement! Are you sure?",   
						text: "You will not be able to recover the previous workflow state if  you click Yes, hold it Button!",   
						type: "warning",   
						showCancelButton: true,   
						confirmButtonColor: "rgb(66, 184, 221)",  
						confirmButtonText: "Yes, hold it!",
						closeOnConfirm: true,   
						closeOnCancel: false
					},
					function(isConfirm){ 
						if(isConfirm){
							holdwork();
							//showMessage("Agreement Held Successfully!", "success");
						} else {
							showMessage("Not marking the agreement as Hold!!!", "error");
						}
						
					});
			}			
			
			/*var inputObj = {};
			inputObj.agreementId=selectedItemsMyList;//[0];
			inputObj.roleId=$("viewId").value;
			inputObj.statusCode=$('checkerStatus').value;
			inputObj.user={userId:$("selectedUserId").value, roleId:$("viewId").value};
			inputObj.comment=$('checkerComments').value;
			if($("viewId").value == "2"){
			  inputObj.errorReasonCode=$('errorReasonList').value;
			}
			inputObj.lastUpdationDate=getAgreementTimeStamp(selectedItemsMyList);
			inputObj.numPages=$('numPages').value;
			inputObj.numFields=$('numFields').value;
			serviceObj.holdProcess(inputObj,holdRespFn);*/

	}
	
	this.holdwork=holdwork;
	function holdwork() {
		var inputObj = {};
		inputObj.agreementId=selectedItemsMyList;//[0];
		inputObj.roleId=$("viewId").value;
		inputObj.statusCode=$('checkerStatus').value;
		inputObj.user={userId:$("selectedUserId").value, roleId:$("viewId").value};
		inputObj.comment=$('checkerComments').value;
		if($("viewId").value == "2"){
		  inputObj.errorReasonCode=$('errorReasonList').value;
		}
		inputObj.lastUpdationDate=getAgreementTimeStamp(selectedItemsMyList);
		inputObj.numPages=$('numPages').value;
		inputObj.numFields=$('numFields').value;
		serviceObj.holdProcess(inputObj,holdRespFn);
	}	
	
	this.holdRespFn=holdRespFn;
	function holdRespFn(responseData){
		
		var responseObject = JSON.parse(responseData.responseText);
		
		var respStatus = responseObject.response.responseMessage;
		
		if(respStatus == "Success"){
			var docList = responseObject.docList;
			
			for(var k in docList){
				var agrId = docList[k].agreementId;
				if(agrId){
					$(agrId).checked=false;
					showMessage(responseObject.message, "success");
					//$(agrId).disabled=true;
					$(agrId+'statusDescription').innerHTML=docList[k].statusDescription;
					$(agrId+'numPages').innerHTML=docList[k].numPages;
					$(agrId+'numFields').innerHTML=docList[k].numFields;
					$(agrId+'statusCode').innerHTML=docList[k].statusCode;
					$(agrId+'statusCode').value=docList[k].statusCode;
					$(agrId+'lastUpdationDate').innerHTML=docList[k].lastUpdationDateStr;
					$(agrId+'lastUpdationDate').value=docList[k].lastUpdationDateStr;					
					
					var stsCode = docList[k].statusCode;
					if($("viewId").value == "1"){
						$(agrId+'makerComments').innerHTML=docList[k].makerComments;
						if(stsCode != 1 && stsCode != 2){
							$(agrId).disabled=true;
						}
					} else if($("viewId").value == "2"){
						$(agrId+'makerStatus').innerHTML=docList[k].makerStatus;
						$(agrId+'checkerComments').innerHTML=docList[k].checkerComments;
						$(agrId+'errorReason').innerHTML=docList[k].errorReason;
						if(stsCode != 16 && stsCode != 17){
							$(agrId).disabled=true;
						}
					} else if($("viewId").value == "3"){
						$(agrId+'checkerComments').innerHTML=docList[k].checkerComments;
						$(agrId+'smeComments').innerHTML=docList[k].smeComments;
					
						if(stsCode != 19 && stsCode != 20 && stsCode != 21){
							$(agrId).disabled=true;
						}
					}
				}
			}
			disableAll();
		} else {
			var agrId = responseObject.agreementId;
			showMessage(responseObject.message, "error");
			if(agrId){
				$(agrId).checked=false;
			}					
			disableAll();
		}
	}	
	
	this.reloadGridData=reloadGridData
	function reloadGridData(){
		//serviceObj.getTeamDocList(landinginitResponse);
		var inputObj = {};
		inputObj.roleId=$("viewId").value;
		serviceObj.getAdminDocList(inputObj,landinginitMyListResponse);
		selectedItemsMyList = null;
	}
	
	this.setHoldStatus=setHoldStatus;
	function setHoldStatus(stsCombo){
		
		if(stsCombo.value != -1){
			$('checkerHold').disabled=false;
			$('checkerStart').disabled=true;
			$('checkerComplete').disabled=false;
			$('checkerComments').disabled=false;
			if($("viewId").value == "2"){
				$('errorReasonList').disabled=false;
			}
		}
	}
	
	function showMessage(msg,msgTitle){
		if(isIE () && isIE () < 9) {
			alert(msg);
		}
		else {
			/*jDialog({
			  title:msgTitle,
			  content:msg				  
			});*/
			swal({
				title:msg,
				type: msgTitle
				});
		}
		
	}	
	  
} 