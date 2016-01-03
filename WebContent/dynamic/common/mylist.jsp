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
		<th>QC Status</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${myDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.docId}" />" name="docId" value="<c:out value="${document.docId}" />" /> </td>	
          <td><c:out value="${document.docId}" /></td>
          <td><c:out value="${document.docLocation}" /></td>
		  <td>Not found from service</td>
		  <td><c:out value="${document.wfStatusDesc}" /></td>
          <td>Not found from service</td>
		  <td><c:out value="${document.assignedTo}" /></td>
        </tr>
    </c:forEach>
   </tbody>
</table>