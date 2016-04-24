<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.entity.Sreader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet"
			href="${pageContext.request.contextPath }/admin/commfiles/css/common.css"
			type="text/css" />
		<title>通用后台管理系统</title>
	</head>
	<script language="javascript">
	function checkout() {
		var b = n > document.documentElement.scrollWidth - 20;
		if (event.clientX > document.body.clientWidth && event.clientY < 0
				|| event.altKey) {
			alert("");
		}
	}
</script>
	<body onunload="checkout();"
		style="background-image: url(/libmanage/admin/commfiles/images/index_top_bg.gif); background-repeat: repeat-x;">
		<div class="header_content">
			<div class="logo">
				<div style="height: 28px"></div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<strong><font style="font-family: 华文行楷; font-size: 32px" color="#3995FF">图书管理系统</font></strong>
			</div>
			<div class="right_nav">
				<div class="text_left">
					<%
						String admin = "";
						String utype = "";
						if(session.getAttribute("admin")!=null) {
							Sysuser map = (Sysuser)request.getSession().getAttribute("admin"); 
		 					admin = "/libmanage/admin/pupdatesysusers.jsp";
		 					utype="管理员";
						}
						if(session.getAttribute("reader")!=null) {
							Sreader map = (Sreader)request.getSession().getAttribute("reader"); 
		 					admin = "/libmanage/admin/rupdatesreader.jsp";
		 					utype="读者";
						}
					%>
					<a href="javascript:parent.manFrame.location.replace('<%=admin %>')" class="link_a">个人信息</a>
				</div>
				<div class="text_right">
					<a href="javascript:top.location='/libmanage/index.jsp'" class="link_a">退出系统</a>
				</div>
			</div>
		</div>
	</body>
</html>
