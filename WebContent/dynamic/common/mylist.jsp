<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="documentlist_table">
	<tr>	
		<th>Agreement Id</th>
		<th>LOB</th>
		<th>Agreement Type</th>
		<th>Maker Status</th>
		<th>Maker Details</th>
		<th>QC Details</th>
	</tr>
	<c:forEach items="${myDocumentList}" var="document">
        <tr>
          <td><c:out value="${document.docId}" /><td>
          <td><c:out value="${document.docLocation}" /><td>
		  <td>Not found from service<td>
		  <td><c:out value="${document.wfStatusDesc}" /><td>
          <td>Not found from service<td>
		  <td><c:out value="${document.assignedTo}" /><td>
        </tr>
    </c:forEach>
</table>