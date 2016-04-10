<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>城市List</title>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	<link id='bs-css' href='../css/bootstrap-cerulean.css' rel='stylesheet'>
	<style type="text/css">
 body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="../css/bootstrap-responsive.css" rel="stylesheet">
	<link href="../css/charisma-app.css" rel="stylesheet">
	<link href="../css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='../css/fullcalendar.css' rel='stylesheet'>
	<link href='../css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='../css/chosen.css' rel='stylesheet'>
	<link href='../css/uniform.default.css' rel='stylesheet'>
	<link href='../css/colorbox.css' rel='stylesheet'>
	<link href='../css/jquery.cleditor.css' rel='stylesheet'>
	<link href='../css/jquery.noty.css' rel='stylesheet'>
	<link href='../css/noty_theme_default.css' rel='stylesheet'>
	<link href='../css/elfinder.min.css' rel='stylesheet'>
	<link href='../css/elfinder.theme.css' rel='stylesheet'>
	<link href='../css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='../css/opa-icons.css' rel='stylesheet'>
	<link href='../css/uploadify.css' rel='stylesheet'>

</head>
<body>
	<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="home.spring"> <span>基于Java web的大学生同城交友平台</span></a>
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> ${ONLINE_USER}</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- topbar ends -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
				   <ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">城市管理</li>
						<li><a class="ajax-link" href="/friend/admin/city/listAll.spring"><i class="icon-eye-open"></i><span class="hidden-tablet">城市查询</span></a></li>
						<li><a class="ajax-link" href="/friend/admin/city/preAdd.spring"><i class="icon-align-justify"></i><span class="hidden-tablet">城市添加</span></a></li>
						<li class="nav-header hidden-tablet">大学管理</li>
						<li><a class="ajax-link" href="/friend/admin/university/listAll.spring"><i class="icon-eye-open"></i><span class="hidden-tablet">大学查询</span></a></li>
						<li><a class="ajax-link" href="/friend/admin/university/preAdd.spring"><i class="icon-align-justify"></i><span class="hidden-tablet">大学添加</span></a></li>
						<li class="nav-header hidden-tablet">会员管理</li>
						<li><a class="ajax-link" href="/friend/admin/member/listAll.spring"><i class="icon-eye-open"></i><span class="hidden-tablet">会员查询</span></a></li>
						<li><a class="ajax-link" href="/friend/admin/member/preAdd.spring"><i class="icon-align-justify"></i><span class="hidden-tablet">会员添加</span></a></li>
						<li class="nav-header hidden-tablet">兴趣管理</li>
						<li><a class="ajax-link" href="/friend/admin/interest/listAll.spring"><i class="icon-eye-open"></i><span class="hidden-tablet">兴趣查询</span></a></li>
						<li><a class="ajax-link" href="/friend/admin/interest/preAdd.spring"><i class="icon-align-justify"></i><span class="hidden-tablet">兴趣添加</span></a></li>
					</ul>
				</div>
			</div>
			<div id="content" class="span10">
				<div>
					<ul class="breadcrumb">
						<li>
							<a href="#">城市</a> <span class="divider">/</span>
						</li>
						<li>
							<a href="#"> Update </a>
						</li>
					</ul>
				</div>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> Form Elements: 城市</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form:form method="Post" action="/friend/admin/city/update.spring" commandName="cityBean" id="cityBean_form" class="form-horizontal">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">ID</label>
							  	<div class="controls">
								  <form:input class="input-xlarge focused" id="id" path="id" type="text" readonly="true" />
							  	</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">NAME</label>
							  	<div class="controls">
								  <form:input class="input-xlarge focused" id="name" path="name" type="text"  />
							  	</div>
							  </div>
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">Save changes</button>
							  </div>
							</fieldset>
						</form:form>
						<B>${MESSAGE}</B>
					</div>
				</div><!--/span-->
			</div><!--/row-->
			</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		<hr>
		<footer>
         <p class="pull-left">&copy; <a href="#">基于Java web的大学生同城交友平台</a> 2016</p>
			<p class="pull-right">Powered by: <a href="#">基于Java web的大学生同城交友平台</a></p>
		</footer>
	</div><!--/.fluid-container-->
	<script src='../js/jquery-1.7.2.min.js'></script>
	<script src='../js/jquery-ui-1.8.21.custom.min.js'></script>
	<script src='../js/bootstrap-transition.js'></script>
	<script src='../js/bootstrap-alert.js'></script>
	<script src='../js/bootstrap-modal.js'></script>
	<script src='../js/bootstrap-dropdown.js'></script>
	<script src='../js/bootstrap-scrollspy.js'></script>
	<script src='../js/bootstrap-tab.js'></script>
	<script src='../js/bootstrap-tooltip.js'></script>
	<script src='../js/bootstrap-popover.js'></script>
	<script src='../js/bootstrap-button.js'></script>
	<script src='../js/bootstrap-collapse.js'></script>
	<script src='../js/bootstrap-carousel.js'></script>
	<script src='../js/bootstrap-typeahead.js'></script>
	<script src='../js/bootstrap-tour.js'></script>
	<script src='../js/jquery.cookie.js'></script>
	<script src='../js/fullcalendar.min.js'></script>
	<script src='../js/jquery.dataTables.min.js'></script>
	<script src='../js/excanvas.js'></script>
	<script src='../js/jquery.flot.min.js'></script>
	<script src='../js/jquery.flot.pie.min.js'></script>
	<script src='../js/jquery.flot.stack.js'></script>
	<script src='../js/jquery.flot.resize.min.js'></script>
	<script src='../js/jquery.chosen.min.js'></script>
	<script src='../js/jquery.uniform.min.js'></script>
	<script src='../js/jquery.colorbox.min.js'></script>
	<script src='../js/jquery.cleditor.min.js'></script>
	<script src='../js/jquery.noty.js'></script>
	<script src='../js/jquery.elfinder.min.js'></script>
	<script src='../js/jquery.raty.min.js'></script>
	<script src='../js/jquery.iphone.toggle.js'></script>
	<script src='../js/jquery.autogrow-textarea.js'></script>
	<script src='../js/jquery.uploadify-3.1.min.js'></script>
	<script src='../js/jquery.history.js'></script>
	<script src='../js/charisma.js'></script>
</body>
</html>
