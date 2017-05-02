<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/public.tld" prefix="public" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<title>Simpla Admin | Sign In</title>
		
		<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=basePath %>resources/css/invalid.css" type="text/css" media="screen" />	
		
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>resources/scripts/simpla.jquery.configuration.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>resources/scripts/facebox.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>sys_js/login/login.js"></script>
		
	</head>
  
	<body id="login">
		<form name="mainform" method="post" action="<%=basePath %>admin/admin_login.do">
		<div id="login-wrapper" class="png_bg">
			<div id="login-top">
				<font style="font-size: 28px"><public:i18n key="system.name2" module="system"></public:i18n></font>
			</div> 
			
			<div id="login-content">
				
				<form action="index.html">
				
					<div class="notification information png_bg" style="display: none;" id="login_error_message">
						<div>
							Just click "Sign In". No password needed.
						</div>
					</div>
					
					<p>
						<label><public:i18n key="login.username" module="system"></public:i18n></label>
						<input class="text-input" type="text" name="username" id="username"/>
					</p>
					<div class="clear"></div>
					<p>
						<label><public:i18n key="login.password" module="system"></public:i18n></label>
						<input class="text-input" type="password" name="password" id="password"/>
					</p>
					<div class="clear"></div>
					<p>
						<label><public:i18n key="login.usertype" module="system"></public:i18n></label>
						<select name="usertype" id="usertype" class="medium-input">
							<!-- 
							<option value="0"><public:i18n key="usertype.superadmin" module="system"></public:i18n></option>
							<option value="1"><public:i18n key="usertype.admin" module="system"></public:i18n></option>
							 -->
							 <option value="3"><public:i18n key="usertype.author" module="system"></public:i18n></option>
						</select>
					</p>
					<div class="clear"></div>
					<p>
						<input class="button" type="button" id="signIn" value="<public:i18n key='login.submit' module='system'></public:i18n>" />
					</p>
					
				</form>
			</div>
			
		</div>
		</form>
  </body>
  </html>
