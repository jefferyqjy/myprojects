<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>大学List</title>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	<link id='bs-css' href='/friend/admin/css/bootstrap-cerulean.css' rel='stylesheet'>
	<style type="text/css">
 body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link rel="stylesheet" href="/friend/css/zerogrid.css">
	<link rel="stylesheet" href="/friend/css/style.css">
	<link href="/friend/admin/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="/friend/admin/css/charisma-app.css" rel="stylesheet">
	<link href="/friend/admin/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='/friend/admin/css/fullcalendar.css' rel='stylesheet'>
	<link href='/friend/admin/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='/friend/admin/css/chosen.css' rel='stylesheet'>
	<link href='/friend/admin/css/uniform.default.css' rel='stylesheet'>
	<link href='/friend/admin/css/colorbox.css' rel='stylesheet'>
	<link href='/friend/admin/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='/friend/admin/css/jquery.noty.css' rel='stylesheet'>
	<link href='/friend/admin/css/noty_theme_default.css' rel='stylesheet'>
	<link href='/friend/admin/css/elfinder.min.css' rel='stylesheet'>
	<link href='/friend/admin/css/elfinder.theme.css' rel='stylesheet'>
	<link href='/friend/admin/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='/friend/admin/css/opa-icons.css' rel='stylesheet'>
	<link href='/friend/admin/css/uploadify.css' rel='stylesheet'>

</head>
<body>
	<!-- topbar starts -->
	   <!--------------Header--------------->
<tags:header/>

<!--------------Navigation--------------->

	<tags:nav/>
	
	<!-- topbar ends -->
	<div class="container-fluid" style="min-height:700px; width:960px; margin: 0 auto 15px auto;">
		<div class="row-fluid">
			<div id="content" class="span10" style="width:100%">		
			<div class="row-fluid sortable">
				<div class="box span12">
				<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 博客</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form:form method="Post" action="" commandName="bokeBean" id="bokeBean_form" class="form-horizontal">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">标题</label>
							  	<div class="controls">
							 	  <form:hidden path="id" id="bokeId"/>
								  <form:input class="input-xlarge focused" id="bokeTitle" path="title" type="text" />
							  	</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">内容</label>
							  	<div class="controls">
								  <form:textarea class="cleditor"  rows="3" id="bokeContent" path="content" />
							  	</div>
							  </div>
							   <div class="form-actions">
								<button type="button" class="btn btn-primary" onClick="updateBoke()">Save changes</button>
							  </div>
							</fieldset>
						</form:form>
					</div>
				</div><!--/span-->
			</div><!--/row-->
			</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		<hr>

	</div><!--/.fluid-container-->
	
		<tags:copyright/>
	<script src='/friend/admin/js/jquery-1.7.2.min.js'></script>
	<script src='/friend/admin/js/jquery-ui-1.8.21.custom.min.js'></script>
	<script src='/friend/admin/js/bootstrap-transition.js'></script>
	<script src='/friend/admin/js/bootstrap-alert.js'></script>
	<script src='/friend/admin/js/bootstrap-modal.js'></script>
	<script src='/friend/admin/js/bootstrap-dropdown.js'></script>
	<script src='/friend/admin/js/bootstrap-scrollspy.js'></script>
	<script src='/friend/admin/js/bootstrap-tab.js'></script>
	<script src='/friend/admin/js/bootstrap-tooltip.js'></script>
	<script src='/friend/admin/js/bootstrap-popover.js'></script>
	<script src='/friend/admin/js/bootstrap-button.js'></script>
	<script src='/friend/admin/js/bootstrap-collapse.js'></script>
	<script src='/friend/admin/js/bootstrap-carousel.js'></script>
	<script src='/friend/admin/js/bootstrap-typeahead.js'></script>
	<script src='/friend/admin/js/bootstrap-tour.js'></script>
	<script src='/friend/admin/js/jquery.cookie.js'></script>
	<script src='/friend/admin/js/fullcalendar.min.js'></script>
	<script src='/friend/admin/js/jquery.dataTables.min.js'></script>
	<script src='/friend/admin/js/excanvas.js'></script>
	<script src='/friend/admin/js/jquery.flot.min.js'></script>
	<script src='/friend/admin/js/jquery.flot.pie.min.js'></script>
	<script src='/friend/admin/js/jquery.flot.stack.js'></script>
	<script src='/friend/admin/js/jquery.flot.resize.min.js'></script>
	<script src='/friend/admin/js/jquery.chosen.min.js'></script>
	<script src='/friend/admin/js/jquery.uniform.min.js'></script>
	<script src='/friend/admin/js/jquery.colorbox.min.js'></script>
	<script src='/friend/admin/js/jquery.cleditor.min.js'></script>
	<script src='/friend/admin/js/jquery.noty.js'></script>
	<script src='/friend/admin/js/jquery.elfinder.min.js'></script>
	<script src='/friend/admin/js/jquery.raty.min.js'></script>
	<script src='/friend/admin/js/jquery.iphone.toggle.js'></script>
	<script src='/friend/admin/js/jquery.autogrow-textarea.js'></script>
	<script src='/friend/admin/js/jquery.uploadify-3.1.min.js'></script>
	<script src='/friend/admin/js/jquery.history.js'></script>
	<script src='/friend/admin/js/charisma.js'></script>
	<script>
	function updateBoke() {
		var url = "/friend/boke/update.spring";
		var title = $('#bokeTitle').val();
		var content = $('#bokeContent').val();
		var id = $('#bokeId').val();
		var data = {id:id, title:title, content:content};
		 $.ajax({  
		        url : url,  
		        type : "POST",  
		        data : data,  
		        success : function(input){
		        	if (input == "1") {
		        		openMsg('消息', '修改博客成功');
		        	} else {
		        		openMsg('消息', '修改博客失败');
		        	}
		        },  
		        error : function(x, e) {  
		            alert(e);  
		        }  
		    });  
	}
	</script>
</body>
</html>
