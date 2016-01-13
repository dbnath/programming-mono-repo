<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<%
	long ver = java.lang.System.currentTimeMillis();
%>

         

	<script language="Javascript" src="<%=request.getContextPath()%>/js/commonframework.js?ver=<%=ver%>"></script>
	<script language="Javascript" src="<%=request.getContextPath()%>/js/service.js?ver=<%=ver%>"></script>
	<script language="Javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pure-release-0.6.0/pure-min.css">


<!--[if lte IE 8]>
  
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-old-ie-min.css">
  
<![endif]-->
<!--[if gt IE 8]><!-->
  
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
  
<!--<![endif]-->

	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/TinyTableV3/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/TinyTableV3/script.js"></script>
	
	 <!--[if lte IE 8]>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mainstyle-old-ie.css">
    <![endif]-->
    <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mainstyle.css">
    <!--<![endif]-->

	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jDialog.css">
	<script language="Javascript" src="<%=request.getContextPath()%>/css/jDialog.js"></script>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pikaday.css">
	
</head>

<body>

<div>
    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed" style="height:60px;">
		<table style="width:100%; height:100%;">
			<tr>
				<td style="width:50%;text-align:left;vertical-align:center;">
					<a class="pure-menu-heading" style="text-transform:capitalize;" href="">AM Remediation Workflow</a>
				</td>
				<td style="width:50%;text-align:right;vertical-align:center;">
            <!-- <li class="pure-menu-item pure-menu-selected"><a href="#" class="pure-menu-link">Home</a></li>
            <li class="pure-menu-item"><a href="#" class="pure-menu-link">Help</a></li> -->
            <c:if test="${userDetails != null}">
							<span style="font-size: 12px;font-color:#ffffff;">Welcome <c:out value="${userDetails.userName}" /> (<c:out value="${userDetails.userRoleList[0].roleName}" />)</span><br>
							<a href="#" onclick="logout()" style="font-size: 12px;">Logout</a>
            </c:if>
				</td>
			</tr>
		</table>
    </div>
</div>


      
<input type="hidden" id="selectedUserId" name="selectedUserId" value="<c:out value="${userDetails.userId}" />">
<input type="hidden" id="selectedRoleId" name="selectedRoleId" value="<c:out value="${userDetails.userRoleList[0].roleId}" />">


<script>
	var cotextPathTop = "${pageContext.request.contextPath}";
	function logout() {
		new service().logout();
		window.location=cotextPathTop+"/view/login";
	}
</script>
