<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="tablewrapper">
		<div id="tableheader">
        	<div class="search">
                <select id="columns" onchange="sorter.search('query')"></select>
                <input type="text" id="query" onkeyup="sorter.search('query')" />
            </div>
            <span class="details">
				<div class="btn-group">
					<button  class="button-secondary pure-button" id="checkerStart" onclick="landingObj.startClick()" disabled>
						<b>Start</b>
					</button >&nbsp;&nbsp;&nbsp;		
					<button  class="button-warning pure-button" id="checkerHold" onclick="landingObj.holdClick()" disabled>
						<b>Hold</b> 
					</button >&nbsp;&nbsp;&nbsp;
					<button  class="button-success pure-button" id="checkerComplete" onclick="landingObj.completeClick()" disabled>
						<b>Complete</b>
					</button >&nbsp;&nbsp;&nbsp;				
				</div>				
				<div><input type="image" src="../../images/icon_refresh.jpg" style="width:30px;height:30px;" onclick="landingObj.reloadGridData()" /></div>
				<div>Records <span id="startrecord"></span>-<span id="endrecord"></span> of <span id="totalrecords"></span></div>
        		<div><a href="javascript:sorter.reset()">reset</a></div>
        	</span>
        </div>
<table cellpadding="0" cellspacing="0" border="0" id="mytable" class="tinytable">
	<thead class="nosort">
	<tr>	
		<th><h3 style="Color:#ffffff">Select</h3></th>
		<th><h3 style="Color:#ffffff">Agreement Id</h3></th>
		<th><h3 style="Color:#ffffff">LOB</h3></th>
		<th><h3 style="Color:#ffffff">Agreement Type</h3></th>
		<th style="display:'none'" ><h3 style="Color:#ffffff">Assigned To</h3></th>
		<th><h3 style="Color:#ffffff">Maker Status</h3></th>
		<th><h3 style="Color:#ffffff">Details</h3></th>
		<th><h3 style="Color:#ffffff">Number of Pages</h3></th>
		<th><h3 style="Color:#ffffff">Number of Fields</h3></th>
		<th style="display:'none'" ><h3 style="Color:#ffffff">StatusCode</h3></th>	
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${myDocumentList}" var="document">
        <tr>
		  <td><input type="checkbox" id="<c:out value="${document.agreementId}" />" name="docId" value="<c:out value="${document.agreementId}" />" onclick="landingObj.setMyAssignment(this)"/> </td>
          <td id="<c:out value="${document.agreementId}agreementId" />"><c:out value="${document.agreementId}" /></td>
		  <td id="<c:out value="${document.agreementId}lob" />"><c:out value="${document.lob}" /></td>
		  <td id="<c:out value="${document.agreementId}agreementTypeDesc" />"><c:out value="${document.agreementTypeDesc}" /></td>
		  <td style="display:'none'" id="<c:out value="${document.agreementId}assignedTo" />"><c:out value="${document.assignedTo}" /></td>
		  <td id="<c:out value="${document.agreementId}statusDescription" />"><c:out value="${document.statusDescription}" /></td>		  
		  <td id="<c:out value="${document.agreementId}makerComments" />"><c:out value="${document.makerComments}" /></td>		  
		  <td id="<c:out value="${document.agreementId}numPages" />"><c:out value="${document.numPages}" /></td>		  
		  <td id="<c:out value="${document.agreementId}numFields" />"><c:out value="${document.numFields}" /></td>		  
		  <td style="display:'none'" id="<c:out value="${document.agreementId}statusCode" />" value="<c:out value="${document.statusCode}" />" ><c:out value="${document.statusCode}" /></td>
        </tr>
    </c:forEach>
   </tbody>
</table>
<div id="tablefooter">
          <div id="tablenav">
            	<div>
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1,true)" />
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/previous.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1)" />
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/next.gif" width="16" height="16" alt="First Page" onclick="sorter.move(1)" />
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/last.gif" width="16" height="16" alt="Last Page" onclick="sorter.move(1,true)" />
                </div>
                <div>
                	<select id="pagedropdown"></select>
				</div>
                <div>
                	<a href="javascript:sorter.showall()">view all</a>
                </div>
            </div>
			<div id="tablelocation">
            	<div>
                    <select onchange="sorter.size(this.value)">
                    <option value="5">5</option>
                        <option value="10" selected="selected">10</option>
                        <option value="20">20</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                    <span>Entries Per Page</span>
                </div>
                <div class="page">Page <span id="currentpage"></span> of <span id="totalpages"></span></div>
            </div>
        </div>
    </div>
<br>



<div id="makerActionPanel" > <!-- style="display:none"> -->
	<table align="left" width="100%">
		<tr>
			<table>
				<tr>
					<td>
					<fieldset>
						<legend>
							<Font color="Blue"><b>Comments Section:</b></Font>
						</legend>
						<b>Status:</b>
						<select id="checkerStatus" onchange="landingObj.setHoldStatus(this)" disabled >
						<c:forEach items="${workflowStatusList}" var="status">
							<option value="${status.statusCode}">${status.statusDescription}</option>
						</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<b>Comments:</b>
						<textarea id="checkerComments" class="pure-input-4-3" disabled rows="2" cols="65"></textarea>
						<br>
						<b>Number of Pages:</b>
						<input type="text" id="numPages">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<b>Number of Fields:</b>
						<input type="text" id="numFields">						
					</fieldset>
					</td>
				</tr>
				
			</table>
		</tr>
	</table>
</div>


<!--<table border="1" width="100%">
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
</table>-->
<!--<div>
	<div class="btn-group">
		<button  class="button-secondary pure-button" id="checkerStart" onclick="landingObj.startClick()" disabled>
			Start
		</button >&nbsp;&nbsp;&nbsp;		
		<button  class="button-warning pure-button" id="checkerHold" onclick="landingObj.holdClick()" disabled>
			Hold 
		</button >&nbsp;&nbsp;&nbsp;
		<button  class="button-success pure-button" id="checkerComplete" onclick="landingObj.completeClick()" disabled>
			Complete
		</button >&nbsp;&nbsp;&nbsp;				
	</div>															
</div>-->


