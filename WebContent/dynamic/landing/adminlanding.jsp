<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	long ver = java.lang.System.currentTimeMillis();
%>

<jsp:include page="../admin/updateAgreement.jsp" />

<script language="Javascript" src="<%=request.getContextPath()%>/js/adminlanding.js?ver=<%=ver%>"></script>
  <!-- slide in menu (mobile only) -->
  <div class="container bootcards-container" id="main" style="padding-top:40px">

	<div align="center" id="myTabContent" width="100%">
		<div id="profile" width="100%">			
			<div id="myGrid" class="documentlist_div_parent">         
			</div>
		</div>
	</div>
</div> <!-- end of main container -->
<input type="hidden" id="viewId"value="<%=request.getParameter("u8_input")%>" />

<script>
	document.getElementById("u8_input").value=document.getElementById("viewId").value;
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

	/*var teamSorter = new TINY.table.sorter('teamSorter','teamtable',{
		headclass:'head',
		ascclass:'asc',
		descclass:'desc',
		evenclass:'evenrow',
		oddclass:'oddrow',
		evenselclass:'evenselected',
		oddselclass:'oddselected',
		paginate:true,
		size:5,
		colddid:'teamcolumns',
		currentid:'teamcurrentpage',
		totalid:'teamtotalpages',
		startingrecid:'teamstartrecord',
		endingrecid:'teamendrecord',
		totalrecid:'teamtotalrecords',
		hoverid:'selectedrow',
		pageddid:'teampagedropdown',
		navid:'teamtablenav',
		sortcolumn:2,
		sortdir:1,
		//sum:[8],
		//avg:[6,7,8,9],
		//columns:[{index:6, format:'%', decimals:1},{index:8, format:'$', decimals:0}],
		init:false
	});	*/
	
 	var landingObj = new adminlanding();
 	landingObj.landinginit();
	
	/*function activateTab(pageId) {
          var tabCtrl = document.getElementById('myTabContent');
          var pageToActivate = document.getElementById(pageId);
          for (var i = 0; i < tabCtrl.childNodes.length; i++) {
              var node = tabCtrl.childNodes[i];
              if (node.nodeType == 1) { /* Element */
                  /*node.style.display = (node == pageToActivate) ? 'block' : 'none';
				  
				  if(pageId == 'home'){
					document.getElementById("globalInboxSwitch").style.backgroundColor="#C2C9CC";
					document.getElementById("myInboxSwitch").style.backgroundColor="#ffffff";	
				  }
				  if(pageId == 'profile'){
					document.getElementById("myInboxSwitch").style.backgroundColor="#C2C9CC";
					document.getElementById("globalInboxSwitch").style.backgroundColor="#ffffff";	
				  }				  
				  
              }
          }
      }*/
	
	

</script>