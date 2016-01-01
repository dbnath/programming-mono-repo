<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../common/banner.jsp" />

<%
	long ver = java.lang.System.currentTimeMillis();
%>

<script language="Javascript" src="<%=request.getContextPath()%>/js/landing.js?ver=<%=ver%>"></script>
  <!-- slide in menu (mobile only) -->
  
  <div class="container bootcards-container" id="main" ng-init="hc.inithome();" style="padding-top:40px">
    
			  <a class="btn btn-primary" href="#" ng-click="hc.refreshGrid()" >
				<i class="glyphicon glyphicon-refresh"></i> 
				<span>Reload Documents</span>
			  </a>
			  <a class="btn btn-primary" href="rest/docadmin/dump">
				<i class="glyphicon glyphicon-refresh"></i> 
				<span>Export Completed Document Workflow List</span>
			  </a><br><br>			  
			  


	<div class="row">
	
		<div class="col-sm-5">
			<ul class="nav nav-tabs" id="myTabs">
				<li role="presentation" class="active"><a href="#home"><span class="glyphicon glyphicon-tags"></span>&nbspGlobal Inbox <span class="badge">{{hc.count}}</span></a></li>
				<li role="presentation"><a href="#profile"><span class="glyphicon glyphicon-tag"></span>&nbsp;My Inbox <span class="badge">{{hc.countmylist}}</span></a></li>
			</ul>
 
	 
			<div id="myTabContent" class="tab-content">
		       <div role="tabpanel" class="tab-pane fade in active" id="home" aria-labelledBy="home-tab">
		         <div class="gridStyle" ui-grid="gridOptions"  ui-grid-selection ui-grid-edit class="grid" >
		          </div>
		           <div class="col-xs-4">
		             <br>
		                  <a class="btn btn-primary btn-block" href="#" ng-click="hc.assignMe()" >
		                    <i class="glyphicon glyphicon-hand-up"></i> 
		                    <span>Assign Me</span>
		                  </a>
		             </div>
		       </div>
		       <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledBy="profile-tab">
		           <div class="gridStyle" ui-grid="gridOptionsmylist"  ui-grid-selection ui-grid-edit class="grid">         
		          </div>
		          <!-- start stop button -->
		          	<div class="btn-group btn-group-justified">
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
			   </div>
		
		</div>
	</div>
	
	
	
	<!--details panel on right side-->
   

</div> <!-- end of row -->
</div> <!-- end of main container -->

<%@ include file="../common/footer.jsp" %>
	

 <script >
 
    </script>