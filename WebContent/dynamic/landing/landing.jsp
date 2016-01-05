<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../common/banner.jsp" />

<%
	long ver = java.lang.System.currentTimeMillis();
%>

<script language="Javascript" src="<%=request.getContextPath()%>/js/landing.js?ver=<%=ver%>"></script>
  <!-- slide in menu (mobile only) -->
  <div class="container bootcards-container" id="main" ng-init="hc.inithome();" style="padding-top:40px">
    
			  <a class="btn btn-primary" href="#" onclick="landingObj.reloadGridData()" >
				<i class="glyphicon glyphicon-refresh"></i> 
				<span>Reload Documents</span>
			  </a><br><br>			  
			  


	<div class="row">
		<div width="100%">
		<div class="pure-menu pure-menu-horizontal" id="myTabs" style="height: 50px;">
		    <ul class="pure-menu-list">
		        <li id="globalInboxSwitch" class="pure-menu-item"><a href="javascript:activateTab('home')" class="pure-menu-link">Global Inbox<span class="badge" id="teamCount">&nbsp;</span></a></li>
		        <li id="myInboxSwitch" class="pure-menu-item"><a href="javascript:activateTab('profile')" class="pure-menu-link">My Inbox</span><span class="badge" id="myCount">&nbsp;</span></a></li>
		    </ul>
		</div>
		<div id="myTabContent" width="100%">
	       	<div id="home" width="100%">
	         	<div id="teamGrid" class="documentlist_div_parent">
	          	</div>
	       	</div>
	       	<div id="profile" width="100%">
	          <div id="myGrid" class="documentlist_div_parent">         
	          </div>
		   	</div>
		</div>
	</div>
	
	
	
	<!--details panel on right side-->
   

</div> <!-- end of row -->
</div> <!-- end of main container -->

<%@ include file="../common/footer.jsp" %>
	

<script>
	var sorter = new TINY.table.sorter('sorter','mytable',{
		headclass:'head',
		ascclass:'asc',
		descclass:'desc',
		evenclass:'evenrow',
		oddclass:'oddrow',
		evenselclass:'evenselected',
		oddselclass:'oddselected',
		paginate:true,
		size:5,
		colddid:'columns',
		currentid:'currentpage',
		totalid:'totalpages',
		startingrecid:'startrecord',
		endingrecid:'endrecord',
		totalrecid:'totalrecords',
		hoverid:'selectedrow',
		pageddid:'pagedropdown',
		navid:'tablenav',
		sortcolumn:2,
		sortdir:1,
		//sum:[8],
		//avg:[6,7,8,9],
		//columns:[{index:6, format:'%', decimals:1},{index:8, format:'$', decimals:0}],
		init:false
	});

 	var landingObj = new landing();
 	landingObj.landinginit();
	
	function activateTab(pageId) {
          var tabCtrl = document.getElementById('myTabContent');
          var pageToActivate = document.getElementById(pageId);
          for (var i = 0; i < tabCtrl.childNodes.length; i++) {
              var node = tabCtrl.childNodes[i];
              if (node.nodeType == 1) { /* Element */
                  node.style.display = (node == pageToActivate) ? 'block' : 'none';
              }
          }
      }
	
	

</script>