<%
	String userId = request.getParameter("userId");	
	System.out.println("###### userId in JSP "+userId);
	response.setHeader("x-docwrkflow-auth",userId+"|1");
%>

<html>
<body>
	<h1>Bulk Upload Documents for Tagging</h1>
	
	<div><a href="../rest/docadmin/template"> Click here to download Bulk Upload template </a></div>
 
	<form action="../rest/docadmin/uploaddoc" method="post" enctype="multipart/form-data">
 
	   <p>
		Select file to Bulk Upload : <input type="file" name="file" size="45" />
	   </p>
	   <input type="hidden" name="userId" value="<%=userId%>" />
 
	   <input type="submit" value="Upload It" />
	</form>
 
</body>
</html>