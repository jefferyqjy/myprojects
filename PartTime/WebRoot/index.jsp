<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="sys_jsp/base_index.jsp" %>
<%@page import="com.base.common.util.Const"%>
<%@page import="com.base.web.nav.NavTree"%>

<%
	String tp = (String)request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE);
	String navInfo = NavTree.genNav(tp,"0");
	request.setAttribute("navInfo",navInfo);
 %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title><public:i18n key="system.name" module="system"></public:i18n></title>
		<script type="text/javascript" src="<%=basePath %>sys_js/index/index.js"></script>
	</head>
  
	<body><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		<div id="sidebar"><div id="sidebar-wrapper"> <!-- Sidebar with logo and menu -->
			
			<h1 id="sidebar-title"><a href="#"><public:i18n key="system.name" module="system"></public:i18n></a></h1>
		  
			<br/><br/>
			<%if("Y".equals(request.getSession().getAttribute(Const.Session.ADMIN_USER_FlAG))) {%>
			<font style="font-size: 22px"><public:i18n key="system.name2" module="system"></public:i18n></font>
			<%}else{ %>
			<font style="font-size: 22px"><public:i18n key="system.name" module="system"></public:i18n></font>
		  <%}%>
			<!-- Sidebar Profile links -->
			<div id="profile-links">
				<public:i18n key="index.hello" module="system"></public:i18n>, <a href="#" title="Edit your profile"><%=request.getSession().getAttribute(Const.Session.ADMIN_USER_NAME) %> </a>
				<!-- , you have <a href="#messages" rel="modal" title="3 Messages">3 Messages</a> -->
				<br />
				<br />
				<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("0")) {%>
				<a target="right" href="<%=basePath %>sys_jsp/admin/admin_changePassword.jsp" title="Change Password"><public:i18n key="function.changepwd" module="admin"></public:i18n></a> |
				<%} %>
				<a href="<%=basePath %>/admin/admin_logout.do" title="Sign Out"><public:i18n key="index.signout" module="system"></public:i18n></a>
			</div>        
			
			<ul id="main-nav">  <!-- Begin #main-nav -->
				<li>
					<a href="<%=basePath %>shortcut.jsp" class="nav-top-item no-submenu">
						欢迎页
					</a>       
				</li>
				${navInfo }
			</ul> <!-- End #main-nav -->
			
			<div id="messages" style="display: none"> <!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->
				
				<h3>3 Messages</h3>
			 
				<p>
					<strong>17th May 2009</strong> by Admin<br />
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue.
					<small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
				</p>
			 
				<p>
					<strong>2nd May 2009</strong> by Jane Doe<br />
					Ut a est eget ligula molestie gravida. Curabitur massa. Donec eleifend, libero at sagittis mollis, tellus est malesuada tellus, at luctus turpis elit sit amet quam. Vivamus pretium ornare est.
					<small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
				</p>
			 
				<p>
					<strong>25th April 2009</strong> by Admin<br />
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue.
					<small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
				</p>
				
				<form action="#" method="post">
					
					<h4>New Message</h4>
					
					<fieldset>
						<textarea class="textarea" name="textfield" cols="79" rows="5"></textarea>
					</fieldset>
					
					<fieldset>
					
						<select name="dropdown" class="small-input">
							<option value="option1">Send to...</option>
							<option value="option2">Everyone</option>
							<option value="option3">Admin</option>
							<option value="option4">Jane Doe</option>
						</select>
						
						<input class="button" type="submit" value="Send" />
						
					</fieldset>
					
				</form>
				
			</div> <!-- End #messages -->
			
		</div></div> <!-- End #sidebar -->
		
		<div id="main-content"> <!-- Main Content Section with everything -->
			
			<iframe width="100%" style="height: 550px" scrolling="auto" frameborder="no" src="<%=basePath %>shortcut.jsp" id="rightMain" name="right" id="right"></iframe>
			
			<div id="footer">
				<small> 
						<public:i18n key="index.copyright.info" module="system"></public:i18n>
				</small>
			</div><!-- End #footer -->
			
		</div> <!-- End #main-content -->
		
	</div></body>
  

<!-- Download From www.exet.tk-->
</html>
