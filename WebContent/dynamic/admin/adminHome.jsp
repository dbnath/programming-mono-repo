<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../common/banner.jsp" />

<%
	long ver = java.lang.System.currentTimeMillis();
%>

<script language="Javascript" src="<%=request.getContextPath()%>/js/admin.js?ver=<%=ver%>"></script>

  <!-- slide in menu (mobile only) -->
  
<div class="content" style="margin-top: 100px;">
    
			  <a class="btn btn-primary" href="#" onclick="ahc.uploadDocs('<%=request.getContextPath()%>')" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload Agreements</span>
			  </a><br><br>
			  <a class="btn btn-primary" href="#" onclick="setReport('Agreement')" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-save"></i> 
				<span>Export Agreement List</span>
			  </a><br><br>
			  <a class="btn btn-primary" href="#" onclick="setReport('Audit')" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-save"></i> 
				<span>Export Agreements Audit Trail</span>
			  </a><br><br>
			  <a class="btn btn-primary" href="#" onclick="ahc.uploadUserRoles('<%=request.getContextPath()%>')" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload User Roles</span>
			  </a><br><br>			  
			  <a class="btn btn-primary" href="#" onclick="ahc.uploadErrorReasons('<%=request.getContextPath()%>')" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload Error Reasons</span>
			  </a><br><br>			  
			  <a class="btn btn-primary" href="#" onclick="ahc.uploadagreementTypes('<%=request.getContextPath()%>')" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload Agreement Types</span>
			  </a><br><br>
			  <a class="btn btn-primary" href="<%=request.getContextPath()%>/view/adminUpdateAgreementView" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-record"></i> 
				<span>Work with Agreements</span>
			  </a><br><br>

</div> <!-- end of main container -->
<div id="reportInput" style="padding-top:10px; padding-left:10px; padding-right:10px;display:none;">
	<!-- <table border="0" style="width:35%; height:50px">
		<tr>
			<td style="text-align:left;padding:2px;width:120px;font-family:'Arial Bold', 'Arial';font-style:bold;font-size:16px;" colspan="4" >
				<b>Select Date:</b>
			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td style="text-align:right;padding-left:10px;"><b>From Date:</b></td>		
			<td><input type="text" value="" id="rptFromDt"></td>	
			<td style="text-align:right;padding-left:10px;"><b>To Date:</b></td>		
			<td><input type="text" value="" id="rptToDt"></td>			
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td style="text-align:left;padding:2px;width:120px;font-family:'Arial Bold', 'Arial';font-style:bold;font-size:12px;" colspan="4" >
			<button  class="pure-button" id="submitRpt" onclick="" >
				<b>Submit Report</b>
			</button>
			</td>
		</tr>		
	</table> -->
	
	<form class="pure-form" id="rptForm">
	    <fieldset>
	        <legend>Report Input</legend>
	        <div class="pure-control-group pure-u-1-6">
				<label for="rptFromDt">From Date</label>
		        <input type="text" placeholder="From Date" id="rptFromDt" name="rptFromDt">
		    </div>
		    <div class="pure-control-group pure-u-1-6">
		        <label for="rptToDt">To Date</label>
		        <input type="text" placeholder="To Date" id="rptToDt" name="rptToDt">		        
		    </div>
	    	<button class="pure-button" id="submitRpt" onclick="getReport();" style="margin-top: 15px">Submit Report</button>
			<input type="hidden" id="rptName" value="" >
	    </fieldset>
	</form>
	
</div> <!-- end of main container -->

<%@ include file="../common/footer.jsp" %>

<script language="Javascript" src="<%=request.getContextPath()%>/css/pikaday.js"></script>

<script>
	ahc = new admin();

    var picker = new Pikaday(
    {
        field: document.getElementById('rptFromDt'),
        firstDay: 1,
        minDate: new Date(2000, 0, 1),
        maxDate: new Date(2020, 12, 31),
        yearRange: [2000,2020]
    });
    
    var picker = new Pikaday(
    {
        field: document.getElementById('rptToDt'),
        firstDay: 1,
        minDate: new Date(2000, 0, 1),
        maxDate: new Date(2020, 12, 31),
        yearRange: [2000,2020]
    });
	
    /*
    	check https://github.com/dbushell/Pikaday for further implementation
    	but be careful to include oter js e.g. moment.js that may screw up ie8 support
    */
    //move it to admin.js
    //make from date and to date disabled to avoid hand validation
	function setReport(reportName) {
		
		document.getElementById("rptName").value=reportName;
		
		//alert(document.getElementById("rptName").value);
		
		/*alert(reportName);*/
		if(!reportName){
			document.getElementById("reportInput").style.display="none";
			//alert(1);
		} else {
			document.getElementById("reportInput").style.display='';
			//alert(2);
		}
		
	}
	
	function getReport() {
		
		var frmDate = document.getElementById('rptFromDt').value;
		var toDate = document.getElementById('rptToDt').value;
		var rptName = document.getElementById("rptName").value;
		
		if(!frmDate || !toDate){
			alert('Please enter both From date and To date to proceed');
			return false;
		}
		/*alert(rptName);*/
		if(!rptName){
			alert('Please select the report you want to run');
			return false;			
		}
		
		
		if(document.getElementById("rptName").value === 'Agreement'){
			document.getElementById("rptForm").action="<%=request.getContextPath()%>/rest/docadmin/dump";	
		} else if(document.getElementById("rptName").value === 'Audit'){
			document.getElementById("rptForm").action="<%=request.getContextPath()%>/rest/docadmin/auditdump";	
		}
		
		
		document.getElementById("rptForm").submit();
		
		document.getElementById("rptName").value = null;
		document.getElementById("reportInput").style.display="none";
		
		//return false;
	}
    
</script>