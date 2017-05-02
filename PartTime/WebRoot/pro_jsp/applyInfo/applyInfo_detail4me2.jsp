<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/applyInfo/applyInfo.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.detail" module="applyInfo"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.detail" module="applyInfo"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
				<fieldset>
					<p>
						<b><public:i18n key="parttimeid" module="applyInfo"></public:i18n>:</b>
						<label>${result.parttimeid}</label>
					</p>
					<p>
						<b><public:i18n key="parttimetitle" module="applyInfo"></public:i18n>:</b>
						<label>${result.parttimetitle}</label>
					</p>
					<p>
						<b><public:i18n key="stuid" module="applyInfo"></public:i18n>:</b>
						<label>${result.stuid}</label>
					</p>
					<p>
						<b><public:i18n key="date" module="applyInfo"></public:i18n>:</b>
						<label>${result.date}</label>
					</p>
					<p>
						<b><public:i18n key="remark" module="applyInfo"></public:i18n>:</b>
						<label>${result.remark}</label>
					</p>
							<p>
								
							</p>
				</fieldset>
				<div class="clear"></div>
			</div>
			</div>
			</div>
			</div>
		</body>
	</html>
