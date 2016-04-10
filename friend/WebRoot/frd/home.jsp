<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>好友空间</title>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<link id='bs-css' href='/friend/admin/css/bootstrap-cerulean.css'
	rel='stylesheet'>
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
<link href="/friend/admin/css/jquery-ui-1.8.21.custom.css"
	rel="stylesheet">
<link href='/friend/admin/css/fullcalendar.css' rel='stylesheet'>
<link href='/friend/admin/css/fullcalendar.print.css' rel='stylesheet'
	media='print'>
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
<body >
	<!-- topbar starts -->
	<!--------------Header--------------->
	<tags:header />


	<!-- topbar ends -->
	<div class="container-fluid"
		style="min-height: 600px; width: 960px; margin: 0 auto 15px auto;">
		<div class="row-fluid">
			<div id="content" class="span10" style="width: 100%">
				<div class="row-fluid sortable">
					<div class="box span4">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i> 个人信息
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="min-height: 490px">
						<form:form method="Post" commandName="memberBean" id="memberBean_form" class="form-horizontal">
							<div style="width:500px; height:25px"> 
							 <form:hidden id="userId" path="id"  />
								<div style="width:60px;float:left; text-align:right">用户名： </div>
								<div style="width:400px;float:left"> ${memberBean.userName}</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:60px;float:left; text-align:right">性别： </div>
								<div style="width:400px;float:left">${memberBean.gender}</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:60px;float:left; text-align:right">年龄： </div>
								<div style="width:400px;float:left">${memberBean.age}</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:60px;float:left; text-align:right">出生地： </div>
								<div style="width:400px;float:left">${memberBean.address}</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:60px;float:left; text-align:right">大学： </div>
								<div style="width:400px;float:left">${memberBean.university.name} </div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:60px;float:left; text-align:right">兴趣： </div>
								<div style="width:400px;float:left"><c:forEach var="myItem"  items="${InterestBeanList}" varStatus="status">
							  		<input type="checkbox" name="interestCheckBox" value="${myItem.id}" ${myItem.checked}/> ${myItem.name}
							  	</c:forEach></div>
							</div>
							</form:form>
						</div>

					</div>
					<div class="box span4">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i> 我的博客
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="min-height: 490px">
							<table id="bokeTable" class=" table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th width="70%">标题</th>
									  <th>操作</th>
								  </tr>
							  </thead>
						  </table>
						</div>

					</div>
					<div class="box span4">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i> 我的相册
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="min-height: 490px">
						<table id="photoBoxTable"class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width="70%">相册名称</th>
								  <th>操作</th>
							  </tr>
						  </thead>
					  </table>
						</div>

					</div>
					<!--/span-->
				</div>
				<!--/row-->
			</div>
			<!--/#content.span10-->
		</div>
		<!--/fluid-row-->
	</div>
	<!--/.fluid-container-->
	
	<tags:copyright />
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
	initializeEvents();
	function initializeEvents() {
		$('#bokeTable').dataTable({
			"sDom": "<t<'span12 center'p>>",
			"bServerSide" : true,
			"sAjaxSource" : "/friend/boke/listByUserId.spring?userId="+$("#userId").val(),
		    "sServerMethod" : "POST" ,
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
		
		$('#photoBoxTable').dataTable({
			"sDom": "<t<'span12 center'p>>",
			"bServerSide" : true,
			"sAjaxSource" : "/friend/photoBox/list.spring?userId="+$("#userId").val(),
		    "sServerMethod" : "POST" ,
		    "bProcessing" : false,
		    "bPaginate": true,
			"bLengthChange" : true,
			"iDisplayLength" : 10,
			"fnAdjustColumnSizing" : false,
			"bStateSave": false,
			"bSort":false,
			"bFilter":false,
			"aoColumnDefs" : makeCollumnDef2(),
			"aoColumns" : makeCollomns2(),
			"sPaginationType": "bootstrap",
			"oLanguage": {
			"sLengthMenu": "_MENU_ records per page"
			}
		} );
	}
	function makeCollumnDef() {
		return [
		        { "fnRender" : function (oObj, sVal) {
		      	        return oObj.aData.title;
		           },
		           "bVisible" : true ,
		           "aTargets" : [ 0 ]
		        },
		        { "fnRender" : function (oObj, sVal) {
		      	        return createAction(oObj.aData.id);
		           },
		           "bVisible" : true ,
		           "aTargets" : [ 1 ]
		        }]; 
	}
	function makeCollomns(){
		return [{ "mDataProp" : "title", "sHeight":"15px"}, 
	        	{ "mDataProp" : null}];
	}
	function createAction(id) {
		var inhtml = '<a class="btn btn-success" href="/friend/boke/viewBoke.spring?id=' + id + '">';
		inhtml += '<i class="icon-zoom-in icon-white"></i>查看</a> ';	
		return inhtml;
	}
	
	function makeCollumnDef2() {
		return [
		        { "fnRender" : function (oObj, sVal) {
		      	        return oObj.aData.name;
		           },
		           "bVisible" : true ,
		           "aTargets" : [ 0 ]
		        },
		        { "fnRender" : function (oObj, sVal) {
		      	        return createAction2(oObj.aData.id);
		           },
		           "bVisible" : true ,
		           "aTargets" : [ 1 ]
		        }]; 
	}
	function makeCollomns2(){
		return [{ "mDataProp" : "name", "sHeight":"15px"}, 
	        	{ "mDataProp" : null}];
	}
	function createAction2(id) {
		var inhtml = '<a class="btn btn-success" href="/friend/photoBox/viewPhotoBox.spring?id=' + id + '">';
		inhtml += '<i class="icon-zoom-in icon-white"></i>进入</a> ';
		return inhtml;
	}
	</script>
</body>
</html>
