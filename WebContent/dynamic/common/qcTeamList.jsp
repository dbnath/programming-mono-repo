<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
<c:when test="${not empty teamDocumentList}">
<div id="tablewrapper">
	<div id="tableheader">
		<div class="search">
			<select id="teamcolumns" onchange="teamSorter.search('queryTeam')"></select>
			<input type="text" id="queryTeam" onkeyup="teamSorter.search('queryTeam')" />
		</div>
		<span class="details">
			<div><input type="image" src="../../images/icon_refresh.jpg" style="width:30px;height:30px;" onclick="landingObj.reloadGridData()" /></div>
			<div>Records <span id="teamstartrecord"></span>-<span id="teamendrecord"></span> of <span id="teamtotalrecords"></span></div>
			<div><a href="javascript:teamSorter.reset()">reset</a></div>
		</span>
	</div>
	<table cellpadding="0" cellspacing="0" border="0" id="teamtable" class="tinytable">
		<thead class="nosort">
		<tr>	
			<th><h3 style="Color:#ffffff">Select</h3></th>
			<th><h3 style="Color:#ffffff">Agreement Id</h3></th>
			<th><h3 style="Color:#ffffff">LOB</h3></th>
			<th><h3 style="Color:#ffffff">Agreement Type</h3></th>
			<th><h3 style="Color:#ffffff">QC Status</h3></th>
			<th><h3 style="Color:#ffffff">QC Details</h3></th>
			<th><h3 style="Color:#ffffff">Assigned To</h3></th>
			<th><h3 style="Color:#ffffff">Current Status</h3></th>
			<th><h3 style="Color:#ffffff">SME Details</h3></th>
			<th><h3 style="Color:#ffffff">Number of Pages</h3></th>
			<th><h3 style="Color:#ffffff">Number of Fields</h3></th>
		</tr>
		</thead>
		<tbody>

		<c:forEach items="${teamDocumentList}" var="document">
			<tr>
			  <td><input type="checkbox" id="<c:out value="${document.agreementId}" />Team" name="docId" value="<c:out value="${document.agreementId}" />"  onclick="landingObj.setAssignment(this)" /> </td>
			  <td><c:out value="${document.agreementId}" /></td>
			  <td><c:out value="${document.lob}" /></td>
			  <td><c:out value="${document.agreementTypeDesc}" /></td>
			  <td><c:out value="${document.checkerStatus}" /></td>
			  <td><c:out value="${document.checkerComments}" /></td>
			  <td><c:out value="${document.assignedTo}" /></td>
			  <td><c:out value="${document.statusDescription}" /></td>
			  <td><c:out value="${document.smeComments}" /></td>
			  <td><c:out value="${document.numPages}" /></td>
			  <td><c:out value="${document.numFields}" /></td>
			</tr>
		</c:forEach>
	   </tbody>   
	</table>
	<div id="teamtablefooter">
          <div id="teamtablenav">
            	<div>
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/first.gif" width="16" height="16" alt="First Page" onclick="teamSorter.move(-1,true)" />
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/previous.gif" width="16" height="16" alt="First Page" onclick="teamSorter.move(-1)" />
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/next.gif" width="16" height="16" alt="First Page" onclick="teamSorter.move(1)" />
                    <img src="<%=request.getContextPath()%>/static/TinyTableV3/images/last.gif" width="16" height="16" alt="Last Page" onclick="teamSorter.move(1,true)" />
                </div>
                <div>
                	<select id="teampagedropdown"></select>
				</div>
                <div>
                	<a href="javascript:teamSorter.showall()">view all</a>
                </div>
            </div>
			<div id="teamtablelocation">
            	<div>
                    <select onchange="teamSorter.size(this.value)">
                    <option value="5">5</option>
                        <option value="10" selected="selected">10</option>
                        <option value="20">20</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                    <span>Entries Per Page</span>
                </div>
                <div class="page">Page <span id="teamcurrentpage"></span> of <span id="teamtotalpages"></span></div>
            </div>
        </div>
    </div>

	<!--<div class="col-xs-4">
          <a class="btn btn-primary btn-block" href="#" onclick="landingObj.assignMe()" >
            <i class="glyphicon glyphicon-hand-up"></i> 
            <span>Assign to Me</span>
          </a>
	</div>-->
	<!--<button  class="button-secondary pure-button" id="assign" onclick="landingObj.assignMe()" >
		<b>Assign to Me</b>
	</button >-->
	<div class="col-xs-6" style="text-align: left;margin-left: 195px;">
		<button  class="button-secondary pure-button" id="assign" onclick="landingObj.assignMe()" >
			<b>Assign to Me</b>
		</button >
	</div>
</c:when>
<c:when test="${empty teamDocumentList}"><h2>No records available</h2></c:when>
</c:choose>