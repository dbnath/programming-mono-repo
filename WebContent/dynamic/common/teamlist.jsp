<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="ContentTable" border="0">
	<thead class="TopazPortal_lookupFieldNames">
		<tr class="subHeading">
			<td>Select</td>
			<td>Agreement Id</td>
			<td>LOB</td>
			<td>Agreement Type</td>
			<td>Maker Status</td>
			<td>Maker Details</td>
			<td>Assigned To</td>
	</tr>
	</thead>
	<TBODY>
	<c:forEach items="${teamDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.docId}" />" name="docId" value="<c:out value="${document.docId}" />" /> </td>
          <td><c:out value="${document.docId}" /></td>
		  <td>Not found from service</td>
		  <td><c:out value="${document.docName}" /></td>
		  <td><c:out value="${document.wfStatusDesc}" /></td>
          <td><c:out value="${document.wfActivityDesc}" /></td>
		  <td><c:out value="${document.assignedTo}" /></td>
        </tr>
    </c:forEach>
	</TBODY>
</table>