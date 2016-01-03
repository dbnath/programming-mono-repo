<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="pure-table pure-table-bordered">
	<thead>
	<tr>	
		<th>Select</th>
		<th>Agreement Id</th>
		<th>LOB</th>
		<th>Agreement Type</th>
		<th>Maker Status</th>
		<th>Maker Details</th>
		<th>Assigned To</th>
		<th>QC Status</th>
		<th>QC Details</th>
		<th>Error Reason</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${teamDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.docId}" />" name="docId" value="<c:out value="${document.docId}" />" /> </td>
          <td><c:out value="${document.docId}" /></td>
		  <td>Not found from service</td>
		  <td><c:out value="${document.docName}" /></td>
		  <td><c:out value="${document.wfStatusDesc}" /></td>
          <td><c:out value="${document.wfActivityDesc}" /></td>
		  <td><c:out value="${document.assignedTo}" /></td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
        </tr>
    </c:forEach>
   </tbody>
</table>