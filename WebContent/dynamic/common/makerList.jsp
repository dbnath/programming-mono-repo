<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="tablewrapper">
		<div id="tableheader">
        	<div class="search">
                <select id="columns" onchange="sorter.search('query')"></select>
                <input type="text" id="query" onkeyup="sorter.search('query')" />
            </div>
            <span class="details">
				<div>Records <span id="startrecord"></span>-<span id="endrecord"></span> of <span id="totalrecords"></span></div>
        		<div><a href="javascript:sorter.reset()">reset</a></div>
        	</span>
        </div>
<table cellpadding="0" cellspacing="0" border="0" id="mytable" class="tinytable">
	<thead class="nosort">
	<tr>	
		<th><h3>Select</h3></th>
		<th><h3>Agreement Id</h3></th>
		<th><h3>LOB</h3></th>
		<th><h3>Agreement Type</h3></th>
		<th><h3>Maker Status</h3></th>
		<th style="display:none"><h3>StatusCode</h3></th>	
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
</fieldset>
</form>
<div>
	<div class="btn-group">
		<button  class="button-secondary pure-button" id="checkerStart" onclick="landingObj.startClick()" disabled>
			Start
		</button >		
		<button  class="button-warning pure-button" id="checkerHold" onclick="landingObj.holdClick()" disabled>
			Hold 
		</button >
		<button  class="button-success pure-button" id="checkerComplete" onclick="landingObj.completeClick()" disabled>
			Done
		</button >				
	</div>															
</div>


