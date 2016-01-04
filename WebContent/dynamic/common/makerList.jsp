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
<form class="pure-form pure-form-stacked">
<fieldset>
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
			<td><textarea id="checkerComments" value="" class="pure-input-4-3" disabled></textarea></td>
		</tr>
	</table>
</div>
<br>
<div>
	<div class="btn-group">
		<a class="button-secondary pure-button" id="checkerStart" onclick="landingObj.startClick()" disabled>
			Start
		</a>		
		<a class="button-warning pure-button" id="checkerHold" onclick="landingObj.holdClick()" disabled>
			Hold 
		</a>
		<a class="button-success pure-button" id="checkerComplete" onclick="landingObj.completeClick()" disabled>
			Done
		</a>				
	</div>															
</div>
</fieldset>
</form>