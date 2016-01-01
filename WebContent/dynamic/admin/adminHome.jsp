<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../common/banner.jsp" />

<%
	long ver = java.lang.System.currentTimeMillis();
%>

<script language="Javascript" src="<%=request.getContextPath()%>/js/admin.js?ver=<%=ver%>"></script>

  <!-- slide in menu (mobile only) -->
  
<div class="container bootcards-container" id="main" ng-init="ahc.inithome();" style="padding-top:60px">
    
			  <a class="btn btn-primary" href="#" ng-click="ahc.uploadDocs()" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload Agreements</span>
			  </a><br><br>
			  <a class="btn btn-primary" href="rest/docadmin/dump" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-save"></i> 
				<span>Export Agreement List</span>
			  </a><br><br>
			  <a class="btn btn-primary" href="#" ng-click="ahc.uploadDocs()" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload User Roles</span>
			  </a><br><br>			  
			  <a class="btn btn-primary" href="#" ng-click="ahc.uploadErrorReasons()" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload Error Types</span>
			  </a><br><br>			  
			  <a class="btn btn-primary" href="#" ng-click="ahc.uploadDocs()" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-upload"></i> 
				<span>Bulk Upload Agreement Status</span>
			  </a><br><br>
			  <a class="btn btn-primary" href="#/adminUpdateAgreement/{{selectedUserRole.userId}}" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-record"></i> 
				<span>Update Agreements</span>
			  </a><br><br>

</div> <!-- end of main container -->