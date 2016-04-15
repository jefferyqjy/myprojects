<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>基于Java web的大学生同城交友平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- The styles -->
	<link id="bs-css" href="admin/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="admin/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="admin/css/charisma-app.css" rel="stylesheet">
	<link href="admin/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='admin/css/fullcalendar.css' rel='stylesheet'>
	<link href='admin/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='admin/css/chosen.css' rel='stylesheet'>
	<link href='admin/css/uniform.default.css' rel='stylesheet'>
	<link href='admin/css/colorbox.css' rel='stylesheet'>
	<link href='admin/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='admin/css/jquery.noty.css' rel='stylesheet'>
	<link href='admin/css/noty_theme_default.css' rel='stylesheet'>
	<link href='admin/css/elfinder.min.css' rel='stylesheet'>
	<link href='admin/css/elfinder.theme.css' rel='stylesheet'>
	<link href='admin/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='admin/css/opa-icons.css' rel='stylesheet'>
	<link href='admin/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
		
</head>

<body>
		<div class="container-fluid">
		<div class="row-fluid">
		
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>基于Java web的大学生同城交友平台</h2>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info">
						请使用您的用户名及密码登录
					</div>
					<form class="form-horizontal" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
						<fieldset>
							<div class="input-prepend" title="Username" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="j_username" id="username" type="text" value="admin" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="Password" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="j_password" id="password" type="password" value="admin123456" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend">
							<a href="home.jsp">主页</a>
							</div>
							<div class="clearfix"></div>

							<p class="center span5">
							<button type="submit" class="btn btn-primary">登录</button>
							</p>
						</fieldset>
					</form>
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="admin/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="admin/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="admin/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="admin/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="admin/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="admin/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="admin/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="admin/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="admin/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="admin/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="admin/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="admin/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="admin/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="admin/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="admin/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="admin/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='admin/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='admin/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="admin/js/excanvas.js"></script>
	<script src="admin/js/jquery.flot.min.js"></script>
	<script src="admin/js/jquery.flot.pie.min.js"></script>
	<script src="admin/js/jquery.flot.stack.js"></script>
	<script src="admin/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="admin/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="admin/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="admin/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="admin/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="admin/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="admin/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="admin/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="admin/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="admin/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="admin/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="admin/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="admin/js/charisma.js"></script>
	
		
</body>
</html>
