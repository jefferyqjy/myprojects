<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>plugin_js/jfile/jfile.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="jfile"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="jfile"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>jfile/jfile_add.do" method="post" name="mainform" enctype="multipart/form-data">
					<fieldset>
					<p>
						<input class="button" type="button" value="添加文件..." onclick="newUploadRow()">
						<table id="normalT">
						</table>
						<span id="attach_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<label><public:i18n key="remark" module="jfile"></public:i18n>:</label>
						<textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule=""></textarea>
						<span id="remark_errorMsg" class="input-notification png_bg"></span>
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
