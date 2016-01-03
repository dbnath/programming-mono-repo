<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="common/banner.jsp" />

<%
	long ver = java.lang.System.currentTimeMillis();
%>
	<script language="Javascript" src="<%=request.getContextPath()%>/js/login.js?ver=<%=ver%>"></script>
<!-- 	
<div class="container bootcards-container" id="main">    
<div class="row">
	 <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Sign In</div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <form id="loginform" class="form-horizontal" role="form">
                        	<div id="login-alert" class="alert alert-danger col-sm-12" style="display:none"></div>
                            <br>        
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username" ng-model="login.user.username">                                        
                            </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password" ng-model="login.user.password">
                            </div>
                                
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                      </div>
                                    </div>


                                <div style="margin-top:10px" class="form-group">
                                    <div class="col-sm-12 controls">
                                      <a id="btn-login" href='#' onclick="login.login()" class="btn btn-success">Login  </a>
                                 </div>
                                </div>
                            </form>     



                        </div>                     
                    </div>  
        </div>

</div>
 -->
<!-- ===============new login layout ========================= -->
<div class="content">
<br>
        <h2 class="content-head is-center">Sign In</h2>

        <div class="pure-g">
            <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
                <form class="pure-form pure-form-stacked" id="loginform">
                    <fieldset>

                        <label for="name">User ID</label>
                        <input type="text" placeholder="Your Name" id="login-username" name="username">
                        <label for="password">Your Password</label>
                        <input type="password" placeholder="Your Password" id="login-password" name="password">
                        <br>
                        <div id="login-alert" style="color:red;" style="display:none"></div>
						<div></div><a id="btn-login" href='#' onclick="login.login()" class="pure-button">Login  </a></div>
                    </fieldset>
                </form>
            </div>
            
        </div>
</div>

<%@ include file="common/footer.jsp" %>

<script>
	var login = new login();	
	//login.alertMe();
</script>
