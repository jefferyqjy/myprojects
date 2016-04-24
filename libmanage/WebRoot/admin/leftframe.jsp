<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.entity.Sreader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/libmanage/admin/commfiles/css/common.css" type="text/css" />
<script type="text/javascript" src="/libmanage/admin/commfiles/js/outlook.js"></script>
<%
String admin = "";
String utype = "";
if(session.getAttribute("admin")!=null) {
	Sysuser map = (Sysuser)request.getSession().getAttribute("admin"); 
 	admin = map.getUname();
 	utype="管理员";
}

if(session.getAttribute("reader")!=null) {
	Sreader map = (Sreader)request.getSession().getAttribute("reader"); 
 	admin = map.getUname();
 	utype="读者";
}
%>
<%
if(utype.equals("管理员")) {
%>
	<script type="text/javascript">
	document.write("<script src=/libmanage/admin/commfiles/js/nav.js></" + "script>");
	</script>
<%} else { %>
	<script type="text/javascript">
	document.write("<script src=/libmanage/admin/commfiles/js/navr.js></" + "script>");
	</script>
<%} %>
<title>左侧导航栏</title>
</head>
<%
if(utype.equals("管理员")){
%>
<body onload="initinav('业务信息')">
<%} %>

<%
if(utype.equals("读者")){
%>
<body onload="initinav('还借信息')">
<%} %>

<div id="left_content">
     <div id="user_info">欢迎您，<strong><%=admin %></strong><br />
      [<%=utype %>] 
     </div>
	 <div id="main_nav">
	     <div id=""></div>
		 <div id="right_main_nav"></div>
	 </div>
</div>
</body>
</html>
