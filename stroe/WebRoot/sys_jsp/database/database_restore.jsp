<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>plugin_js/announce/announce.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3>数据库还原</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">数据库还原</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>database/database_restore.do" method="post" name="mainform" enctype="multipart/form-data">
					<fieldset>
					<p>
						<b>数据库类型:</b>
						<select name="type">
							<option value="mysql">MySQL</option>
						</select>
						<b>数据库编码:</b>
						<select name="encoding">
							<option value="gb2312" selected="selected">GB2312</option>
							<option value="utf8">UTF-8</option>
						</select>
						<b>操作系统:</b>
						<select name="system">
							<option value="windows">Windows</option>
						</select>
					</p>
					<p>
						<b>数据库还原文件:</b>
						<input type="file" id="attach" name="attach" rule="CHAR_M_120"/>
						<span id="attach_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<b>数据库用户名:</b>
						<input class="text-input small-input" type="text" id="username" name="username" rule="CHAR_M_120"/>
						<span id="username_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<b>数据库密码:</b>
						<input class="text-input small-input" type="text" id="password" name="password" rule="CHAR_M_120"/>
						<span id="password_errorMsg" class="input-notification png_bg"></span>
					</p>
						<p>
							<input class="button" type="button" name="submitButton" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
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
