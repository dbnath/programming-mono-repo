<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="pure-table pure-table-bordered">
	<thead>
	<tr>	
		<th>Select</th>
		<th>Agreement Id</th>
		<th>LOB</th>
		<th>Agreement Type</th>
		<th>QC Status</th>
		<th>QC Details</th>
		<th>Assigned To</th>
		<th>Current Status</th>
		<th>Onshore QC Details</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${teamDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.agreementId}" />" name="docId" value="<c:out value="${document.agreementId}" />" onclick="landingObj.setAssignment(this)" /> </td>
          <td><c:out value="${document.agreementId}" /></td>
		  <td><c:out value="${document.lob}" /></td>
		  <td><c:out value="${document.agreementTypeDesc}" /></td>
		  <td><c:out value="${document.checkerStatus}" /></td>
          <td><c:out value="${document.checkerComments}" /></td>
		  <td><c:out value="${document.assignedTo}" /></td>
		  <td><c:out value="${document.statusDescription}" /></td>
		  <td><c:out value="${document.smeComments}" /></td>
        </tr>
    </c:forEach>
   </tbody>
</table>

<div class="col-xs-4">
          <a class="btn btn-primary btn-block" href="#" onclick="landingObj.assignMe()" >
            <i class="glyphicon glyphicon-hand-up"></i> 
            <span>Assign to Me</span>
          </a>
</div>