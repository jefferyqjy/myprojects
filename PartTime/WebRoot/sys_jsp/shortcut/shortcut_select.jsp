<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.base.web.nav.NavTree"%>
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
				<h3><public:i18n key="function.list" module="system"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.list" module="system"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					<%
						Map<String, String> hmFunction = NavTree.getHmFunction();
						Set<Map.Entry<String, String>> set = hmFunction.entrySet();
						for (Map.Entry<String, String> entry : set) {
					%>	
						<input type="button" class="button" value="<%=entry.getKey() %>" onclick="setParent('<%=entry.getValue() %>','<%=entry.getKey() %>')"/>
					<%	
						}
					%>
				</div>
			</div>
		</div>
	</div>
	</body>
	</html>
