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
			  <a class="btn btn-primary" href="<%=request.getContextPath()%>/rest/docadmin/dump" style="text-align:left;width:240px">
				<i class="glyphicon glyphicon-save"></i> 
				<span>Export Agreement List</span>
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
<%@ include file="../common/footer.jsp" %>

<script>
	ahc = new admin();
</script>