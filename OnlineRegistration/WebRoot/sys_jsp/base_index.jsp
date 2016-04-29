<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/public.tld" prefix="public" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Date date = new Date();
Calendar cal = Calendar.getInstance();
cal.setTime(date);
String year = String.valueOf(cal.get(cal.YEAR));
String month = String.valueOf(cal.get(cal.MONTH)+1);
String day = String.valueOf(cal.get(cal.DATE));
if(day.length() == 1){
	day = "0"+day;
}
String time = year + "-" + month + "-" + day;
request.getSession().setAttribute("s_currentTime", time);
%>

<!doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>System</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=basePath %>resources/css/invalid.css" type="text/css" media="screen" />	
		<link rel="stylesheet" href="<%=basePath %>resources/css/base/jquery.ui.all.css" type="text/css" media="screen" />	
  
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/simpla.jquery.configuration.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/facebox.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery.ui.core.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery.ui.datepicker.js"></script>
  </head>
</html>
