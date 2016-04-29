<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>sys_js/shortcut/shortcut.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="shortcut"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="shortcut"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>shortcut/shortcut_add.do" method="post" name="mainform">
					<fieldset>
					<p>
						<b><public:i18n key="url" module="shortcut"></public:i18n>:</b>
						<input class="text-input small-input" type="text" id="url" name="url" rule="CHAR_M_200"/>
						<input class="button" type="button" name="openPage" value="..." onclick="gotoSelectPage('<%=basePath %>sys_jsp/shortcut/shortcut_select.jsp')"/>
						<span id="url_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<b><public:i18n key="name" module="shortcut"></public:i18n>:</b>
						<input class="text-input small-input" type="text" id="name" name="name" rule="CHAR_M_200"/>
						<span id="name_errorMsg" class="input-notification png_bg"></span>
					</p>
						<p>
							<input class="button" type="button" name="submitButton" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
							<input onclick="window.location.href='<%=basePath %>shortcut.jsp'" class="button" type="button" name="returnButton" value='<public:i18n key="button.return" module="common"></public:i18n>' />
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
