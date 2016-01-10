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
	
	
</head>

<body>

<div class="header">
    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed" style="height:80px;">
        <a class="pure-menu-heading" href="">AM Remediation Workflow</a>

        <ul class="pure-menu-list">
            <!-- <li class="pure-menu-item pure-menu-selected"><a href="#" class="pure-menu-link">Home</a></li>
            <li class="pure-menu-item"><a href="#" class="pure-menu-link">Help</a></li> -->
            <c:if test="${userDetails != null}">
            <li class="pure-menu-item">
            	<a href="#" class="pure-menu-link" style="font-size: 12px;">Welcome <c:out value="${userDetails.userName}" /> (<c:out value="${userDetails.userRoleList[0].roleName}" />)</a>            
            </li>
            <li class="pure-menu-item"><a href="#" onclick="logout()" style="font-size: 12px;">Logout</a></li>
            </c:if>
        </ul>
    </div>
</div>


<%-- <table border="1" style="width:100%; background-color:#000099;height:50px">
	<tr>
		<td style="width:60%;font-family:'Arial Bold', 'Arial';font-weight:500;font-style:normal;font-size:20px;color:#ffffff;text-align:right;padding-left:100px;line-height:normal;" >AM Remediation Workflow</td>
		<td style="width:40%;text-align:right;" align="right">
			<c:if test="${userDetails != null}">
			<nav id="docworkflow_nav_wrap">
				<ul>
					<li class="current-menu-item" style="background-color:#000099;">
						<a href="#" class="dropdown-toggle">
					Welcome <c:out value="${userDetails.userName}" /> (<c:out value="${userDetails.userRoleList[0].roleName}" />) <span class="caret"></span></a>
						<ul style="color:#ffffff;background-color:#000099;">
							<li><a href="#" onclick="logout()" style="color:#ffffff;">Logout</a></li>
				</ul>
			</li>           			
          </ul>
			</nav>  
		  </c:if>
      
		</td>
	</tr>
</table> --%>
<input type="hidden" id="selectedUserId" name="selectedUserId" value="<c:out value="${userDetails.userId}" />">
<input type="hidden" id="selectedRoleId" name="selectedRoleId" value="<c:out value="${userDetails.userRoleList[0].roleId}" />">


<script>
	var cotextPathTop = "${pageContext.request.contextPath}";
	function logout() {
		new service().logout();
		window.location=cotextPathTop+"/view/login";
	}
</script>
