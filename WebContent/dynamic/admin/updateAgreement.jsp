<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../common/banner.jsp" />

<%
	long ver = java.lang.System.currentTimeMillis();
%>

<script language="Javascript" src="<%=request.getContextPath()%>/js/admin.js?ver=<%=ver%>"></script>
  
<div id="adminUpdate" ng-init="admupdctrl.init();" style="padding-top:10px; padding-left:10px; padding-right:10px">
	<table border="0" style="width:100%; height:50px">
		<tr>
			<td style="text-align:right;padding:2px;width:120px;font-family:'Arial Bold', 'Arial';font-style:bold;font-size:18px;">
				Select View :
			</td>
			<td style="text-align:left;padding-left:10px;">
				<select id="u8_input" style="height:25px; width:100px">
				  <option selected value=" ">&nbsp;</option>
				  <option value="Maker">Maker</option>
				  <option value="Checker">Checker</option>
				</select>
			</td>
		</tr>
	</table>

</div> <!-- end of main container -->