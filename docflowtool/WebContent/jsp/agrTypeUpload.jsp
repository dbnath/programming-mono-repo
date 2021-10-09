<%
	String userId = request.getParameter("userId");	
	System.out.println("###### userId in JSP "+userId);
	response.setHeader("x-docwrkflow-auth",userId+"|1");
%>

<html>
<body>
	<h1>Bulk Upload Agreement Types</h1>
	
	<div><a href="../rest/docadmin/agrtemplate"> Click here to download template with existing Agreement Types</a></div>
 
	<form action="../rest/docadmin/uploadagrtype" method="post" enctype="multipart/form-data">
 
	   <p>
		Select file to Bulk Upload : <input type="file" name="file" size="45" />
	   </p>
	   <input type="hidden" name="userId" value="<%=userId%>" />
 
	   <input type="submit" value="Upload It" />
	</form>
 
</body>
</html>