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
								  <form:input class="input-xlarge focused" id="name" path="title" type="text" />
							  	</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">内容</label>
							  	<div class="controls">
								  <form:textarea class="cleditor"  rows="3"  path="content" />
							  	</div>
							  </div>
							</fieldset>
						</form:form>
					</div>
				</div><!--/span-->
			</div><!--/row-->
			</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		<div class="row-fluid sortable">
				<div class="box span12">
				<div><a href="#newModal" role="button" class="btn" data-toggle="modal">发表留言</a></div>
				<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 博客留言</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width="20%">好友</th>
								  <th width="80%">留言内容</th>
							  </tr>
						  </thead>
					  </table>
					</div>
				</div><!--/span-->
			</div><!--/row-->
			</div><!--/#content.span10-->

		<div class="modal hide fade" id="newModal" style="z-index:9999">
	
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	    <h3>发表留言</h3>
	  </div>
	  <div class="modal-body" style="height:500px;">
	    <p>内容： <textarea class="cleditor"  rows="3" id="message" ></textarea></p>
	  </div>
	  <div class="modal-footer">
	    <a href="#" class="btn" onclick="$('#newModal').modal('hide');
	    	$('.datatable').dataTable().fnDraw();">关闭</a>
	    <a href="#" class="btn btn-primary" onClick="addMessage()">Save changes</a>
	  </div>
	</div>
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
	
	initializeEvents($("#bokeId").val());
	function addMessage() {
		var url = "/friend/boke/addMessage.spring";
		var message = $('#message').val();
		var id = $("#bokeId").val();
		var data = {id:id, message:message};
		 $.ajax({  
		        url : url,  
		        type : "POST",  
		        data : data,  
		        success : function(input){
		        	if (input == "1") {
		        		$('.datatable').dataTable().fnDraw();
		        		$("#newModal").modal('hide');
		        		openMsg('消息', '发表留言成功');
		        	} else {
		        		$('.datatable').dataTable().fnDraw();
		        		$("#newModal").modal('hide');
		        		openMsg('消息', '发表留言失败');
		        	}
		        },  
		        error : function(x, e) {  
		            alert(e);  
		        }  
		    });  
	}
	
	function initializeEvents(id) {
		$('.datatable').dataTable({
			"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
			"bServerSide" : true,
			"sAjaxSource" : "/friend/boke/listMessage.spring?id="+id,
		    "sServerMethod" : "GET" ,
		    "bProcessing" : false,
		    "bPaginate": true,
			"bLengthChange" : true,
			"iDisplayLength" : 10,
			"fnAdjustColumnSizing" : false,
			"bStateSave": false,
			"bSort":false,
			"bFilter":false,
			"aoColumnDefs" : makeCollumnDef(),
			"aoColumns" : makeCollomns(),
			"sPaginationType": "bootstrap",
			"oLanguage": {
			"sLengthMenu": "_MENU_ records per page"
			}
		} );
	}
	function makeCollumnDef() {
		return [
		        { "fnRender" : function (oObj, sVal) {
		      	        return oObj.aData.userName;
		           },
		           "bVisible" : true ,
		           "aTargets" : [ 0 ]
		        },
		        { "fnRender" : function (oObj, sVal) {
		      	        return oObj.aData.message;
		           },
		           "bVisible" : true ,
		           "aTargets" : [ 1 ]
		        }]; 
	}
	function makeCollomns(){
		return [{ "mDataProp" : "userName", "sHeight":"50px"}, 
	        	{ "mDataProp" : "message"}];
	}
	</script>
</body>
</html>
