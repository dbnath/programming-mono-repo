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
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${myDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.agreementId}" />" name="docId" value="<c:out value="${document.agreementId}" />" /> </td>
          <td><c:out value="${document.agreementId}" /></td>
		  <td><c:out value="${document.lob}" /></td>
		  <td><c:out value="${document.agreementTypeDesc}" /></td>
		  <td><c:out value="${document.makerStatus}" /></td>
          <td><c:out value="${document.makerComments}" /></td>
        </tr>
    </c:forEach>
   </tbody>
</table>
<br>
<div class="btn-group btn-group-justified">
	<div class="btn-group">
		<a class="btn btn-default" ng-click="hc.startWork()">
			<i class="fa fa-play"></i>
			Unassign
		</a>
	</div>
	<div class="btn-group">
		<a class="btn btn-default" ng-click="hc.startWork()">
			<i class="fa fa-play"></i>
			Start
		</a>
	</div>
	<div class="btn-group">
		<button class="btn btn-default view-comment">
			<i class="fa fa-stop""></i>
			Hold 
		</button>
	</div>
	<div class="btn-group" >
		<a class="btn btn-default assign-tag">
			<i class="fa fa-check"></i>
			Done
		</a>
	</div>															
</div>