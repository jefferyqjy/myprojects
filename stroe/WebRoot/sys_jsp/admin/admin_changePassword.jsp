<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.base.common.util.Const"%>
<%@ include file="/sys_jsp/base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><public:i18n key="system.name" module="system"></public:i18n></title>
		<script type="text/javascript" src="<%=basePath %>sys_js/admin/admin.js"></script>
	</head>
  
	<body>
		
		<div id="main-content">
			
			<div class="content-box">
				
				<div class="content-box-header">
					
					<h3><public:i18n key="function.changepwd" module="admin"></public:i18n></h3>
					
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab"><public:i18n key="function.changepwd" module="admin"></public:i18n></a></li>
					</ul>
					
					<div class="clear"></div>
					
				</div> 
				
				<div class="content-box-content">
					
						<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					
					<div class="tab-content default-tab" id="tab1">
					
						<form action="<%=basePath %>admin/admin_changePassword.do" method="post" name="changeForm">
							
							<fieldset> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								<p>
									<label><public:i18n key="username" module="admin"></public:i18n></label>
										<input class="text-input small-input" type="text" id="username" name="username" rule="CHAR_M_120" readonly="readonly" value="<%=request.getSession().getAttribute(Const.Session.ADMIN_USER_NAME) %>"/>
								</p>
								<p>
									<label><public:i18n key="oldpassword" module="admin"></public:i18n></label>
										<input class="text-input small-input" type="password" id="oldpassword" name="oldpassword" rule="CHAR_M_120"/>
										<span id="oldpassword_errorMsg" class="input-notification png_bg"></span> 
								</p>
								<p>
									<label><public:i18n key="newpassword" module="admin"></public:i18n></label>
										<input class="text-input small-input" type="password" id="newpassword" name="newpassword" rule="CHAR_M_120"/>
										<span id="newpassword_errorMsg" class="input-notification png_bg"></span>
								</p>
								<p>
									<label><public:i18n key="cpassword" module="admin"></public:i18n></label>
										<input class="text-input small-input" type="password" id="cpassword" name="cpassword" rule="CHAR_M_120"/>
										<span id="cpassword_errorMsg" class="input-notification png_bg"></span>
								</p>
								<p>
									<input class="button" type="button" name="commit" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
								</p>
								
							</fieldset>
							
							<div class="clear"></div>
							
						</form>
						
					</div> 
					
				</div> 
				
			</div>
		</div> 
		
</body>
</html>
