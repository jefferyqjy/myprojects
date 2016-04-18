<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			<h3><public:i18n key="function.detail" module="jfile"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.detail" module="jfile"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
				<input type="hidden" name="id" id="id" value="${result.id}"/>
				<fieldset>
					<p>
						<b><public:i18n key="oriname" module="jfile"></public:i18n>:</b>
						<label>${result.oriname}</label>
					</p>
					<p>
						<b><public:i18n key="type" module="jfile"></public:i18n>:</b>
						<label>${result.type}</label> &nbsp;&nbsp;
						<b><public:i18n key="size" module="jfile"></public:i18n>:</b>
						<label>${result.size}</label>
					</p>
					<p>
						<b><public:i18n key="authorid" module="jfile"></public:i18n>:</b>
						<label>${result.authorid}</label> &nbsp;&nbsp;
						<b><public:i18n key="date" module="jfile"></public:i18n>:</b>
						<label>${result.date}</label>
					</p>
					<p>
						<b><public:i18n key="remark" module="jfile"></public:i18n>:</b>
						<label>${result.remark}</label>
					</p>
							<p>
								<input class="button" type="button" id="downloadButton" name="downloadButton" value='<public:i18n key="button.download" module="common"></public:i18n>' />
								<input class="button" type="button" id="cancelButton" name="cancelButton" value='<public:i18n key="button.cancel" module="common"></public:i18n>' />
							</p>
				</fieldset>
				<div class="clear"></div>
			</div>
			</div>
			</div>
			</div>
		</body>
	</html>
