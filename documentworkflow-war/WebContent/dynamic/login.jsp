<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="common/banner.jsp" />

<%
	long ver = java.lang.System.currentTimeMillis();
%>
	<script language="Javascript" src="<%=request.getContextPath()%>/js/login.js?ver=<%=ver%>"></script>

<!-- ===============new login layout ========================= -->
<div class="content" style="padding-top:120px">
<br>
        <h2 class="content-head is-center">Sign In</h2>

        <div class="pure-g">
        	<div class="pure-u-1-4">
		    </div>
            <div class="pure-u-1-2">
                <form class="pure-form pure-form-stacked" id="loginform">
                    <fieldset>

                        <label for="name">User ID</label>
                        <input type="text" placeholder="Your Name" id="login-username" name="username">
                        <label for="password">Your Password</label>
                        <input type="password" placeholder="Your Password" id="login-password" name="password">
                        <br>
                        <div id="login-alert" style="color:red;" style="display:none"></div>
						<div></div><a id="btn-login" href='#' onclick="login.login()" class="pure-button">Login  </a>
                    </fieldset>
                </form>
             </div>
                <div class="pure-u-1-4">
	    		</div>
            </div>
           
        </div>
</div>

<%@ include file="common/footer.jsp" %>

<script>
	var login = new login();	
	//login.alertMe();
</script>
