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
		<th>Onshore QC Status</th>
		<th>Onshore QC Details</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${teamDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.agreementId}" />" name="docId" value="<c:out value="${document.agreementId}" />" /> </td>
          <td><c:out value="${document.agreementId}" /></td>
		  <td><c:out value="${document.lob}" /></td>
		  <td><c:out value="${document.agreementTypeDesc}" /></td>
		  <td><c:out value="${document.makerStatus}" /></td>
          <td><c:out value="${document.makerComments}" /></td>
		  <td><c:out value="${document.assignedTo}" /></td>
		  <td><c:out value="${document.checkerStatus}" /></td>
		  <td><c:out value="${document.checkerComments}" /></td>
        </tr>
    </c:forEach>
   </tbody>
</table>

<div class="col-xs-4">
          <a class="btn btn-primary btn-block" href="#" ng-click="hc.assignMe()" >
            <i class="glyphicon glyphicon-hand-up"></i> 
            <span>Assign to Me</span>
          </a>
          <a class="btn btn-primary btn-block" href="#" ng-click="hc.assignMe()" >
            <i class="glyphicon glyphicon-hand-up"></i> 
            <span>Export Pending with Client</span>
          </a>
</div>
