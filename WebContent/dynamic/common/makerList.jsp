<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="pure-table pure-table-bordered">
	<thead>
	<tr>	
		<th>Select</th>
		<th>Agreement Id</th>
		<th>LOB</th>
		<th>Agreement Type</th>
		<th>Maker Status</th>
		<th style="display:none">StatusCode</th>	
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${myDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.agreementId}" />" name="docId" value="<c:out value="${document.agreementId}" />" onclick="landingObj.setMyAssignment(this)"/> </td>
          <td id="<c:out value="${document.agreementId}agreementId" />"><c:out value="${document.agreementId}" /></td>
		  <td id="<c:out value="${document.agreementId}lob" />"><c:out value="${document.lob}" /></td>
		  <td id="<c:out value="${document.agreementId}agreementTypeDesc" />"><c:out value="${document.agreementTypeDesc}" /></td>
		  <td id="<c:out value="${document.agreementId}statusDescription" />"><c:out value="${document.statusDescription}" /></td>		  
		  <td style="display:none" id="<c:out value="${document.agreementId}statusCode" />" value="<c:out value="${document.statusCode}" />" ><c:out value="${document.statusCode}" /></td>
        </tr>
    </c:forEach>
   </tbody>
</table>
<br>
<div id="makerActionsPanel" style="display:block">
	<table>
		<tr>
			<td>Status:</td>
			<td>
				<select id="checkerStatus" onchange="landingObj.setHoldStatus(this)" disabled >
					<c:forEach items="${workflowStatusList}" var="status">
				        <option value="${status.statusCode}">${status.statusDescription}</option>
				    </c:forEach>
				</select>
			</td>
			<td>Comments:</td>
			<td><input type="textarea" id="checkerComments" value="" disabled /></td>
		</tr>
	</table>
</div>
<br>
<div class="btn-group btn-group-justified">
	<div class="btn-group">
		<button class="btn btn-default view-comment" id="checkerStart" onclick="landingObj.startClick()" disabled>
			<i class="fa fa-play"></i>
			Start &nbsp;&nbsp;&nbsp;
		</button>&nbsp;&nbsp;&nbsp;		
		<button class="btn btn-default view-comment" id="checkerHold" onclick="landingObj.holdClick()" disabled>
			<i class="fa fa-stop""></i>
			Hold &nbsp;&nbsp;&nbsp;
		</button>&nbsp;&nbsp;&nbsp;
		<button class="btn btn-default view-comment" id="checkerComplete" onclick="landingObj.completeClick()" disabled>
			<i class="fa fa-check"></i>
			Complete
		</button>				
	</div>															
</div>
