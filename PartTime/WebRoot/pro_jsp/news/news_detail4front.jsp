<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/news/news.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3>新闻信息</h3>
			<ul class="content-box-tabs">
				<li>新闻信息</li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
				<fieldset>
					
					<p align="center">
						<label><font style="font-size: 20px; font-weight: bold;">${result.title}</font></label>
					</p>
					<p align="right">
						
						<label><font style="font-size: 10px; font-weight: bold; font-style: italic;">${result.username}-${result.datetime}</font></label>
					</p>
					<p>
						<label>${result.content}</label>
					</p>
					
					
					
					
				</fieldset>
				<div class="clear"></div>
			</div>
			</div>
			</div>
			</div>
		</body>
	</html>
