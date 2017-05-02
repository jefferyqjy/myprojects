<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.base.common.util.Const"%>
<%@ include file="sys_jsp/base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<title><public:i18n key="system.name" module="system"></public:i18n></title>
	</head>
  
	<body>
		
		<div id="main-content"> <!-- Main Content Section with everything -->
			
			<noscript> <!-- Show a notification if the user has disabled javascript -->
				<div class="notification error png_bg">
					<div>
						Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
					Download From <a href="http://www.exet.tk">exet.tk</a></div>
				</div>
			</noscript>
			
			<!-- Page Head -->
			<h2><public:i18n key="index.welcome.info" module="system"></public:i18n>&nbsp;<public:i18n key="system.name" module="system"></public:i18n></h2>
			<p id="page-intro">
			<!-- 
			What would you like to do?
			 -->
			
			<font style="font-family: serif; font-size: 12px;">
			&nbsp;&nbsp;&nbsp;&nbsp;西京学院(Xijing University)是一所具有研究生教育资格的普通高校，坐落于古城西安西部大学城内。2005年升格为本科院校，定名为"西京学院"，2009年取得学士学位授予权，2011年获得专业学位研究生教育资格。

截至2015年11月，学校占地1391亩，建筑面积54万多平方米。图书馆藏书292万册(含电子图书)。设有17个院、系，10个社区(书院)。有5个专业硕士学位授权点、29个本科专业、22个专科专业。拥有全日制在校生2.4万余人，教职员工近2000人。
			</font>
			
			</p>
			
			<ul class="shortcut-buttons-set">
				<!-- 
				<li><a class="shortcut-button" href="<%=basePath %>sys_jsp/shortcut/shortcut_add.jsp"><span>
					<img src="resources/images/icons/pencil_48.png" alt="icon" /><br />
					<public:i18n key="add.shortcut" module="system"></public:i18n>
				</span></a></li>
				<li><a class="shortcut-button" href="<%=basePath %>shortcut/shortcut_query.do"><span>
					<img src="resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
					<public:i18n key="manager.shortcut" module="system"></public:i18n>
				</span></a></li>
				
				<public:shortcut></public:shortcut>
				 -->
			</ul><!-- End .shortcut-buttons-set -->
		</div> <!-- End #main-content -->
		
</body>
</html>
