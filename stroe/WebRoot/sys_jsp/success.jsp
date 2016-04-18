<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.base.web.tag.BTAGI18N"%>
<%@ include file="/sys_jsp/base.jsp" %>
<%request.setAttribute("pageNotificationSuccess", BTAGI18N.getI18NValue("action.success","common")); %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><public:i18n key="system.name" module="system"></public:i18n></title>
	</head>
  
	<body>
		
		<div id="main-content"> 
			
			
						
						<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
						
			
		</div> 
		
</body>
</html>
