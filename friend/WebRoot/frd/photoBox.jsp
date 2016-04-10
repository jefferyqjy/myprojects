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
<title>相册</title>
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
<body>
	<!-- topbar starts -->
	<!--------------Header--------------->
	<tags:header />
	<!-- topbar ends -->
	<div class="container-fluid"
		style="min-height: 700px; width: 960px; margin: 0 auto 15px auto;">
		<div class="row-fluid">
			<div id="content" class="span10" style="width: 100%">
				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-edit"></i> ${photoBoxBean.name }照片
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content">
							<form:form method="Post" action="" commandName="photoBoxBean"
								id="photoBoxBean_form" class="form-horizontal">
								<form:hidden path="id" id="photoBoxId" />
							</form:form>
							<ul class="thumbnails gallery">
							<c:forEach items="${photoList}" var="item">
							<li id="image-${item.id}" class="thumbnail"><a
									style="background: url(${item.path})"
									title="photo_${item.name}" href="${item.path}"
									class="cboxElement"><img class="grayscale"
										src="${item.path}" alt="photo_${item.name}"
										style="display: block;"></a></li>
							</c:forEach>
								

							</ul>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
			</div>
			<!--/#content.span10-->
		</div>
		<!--/fluid-row-->
		<div class="row-fluid sortable">
			<div class="box span12">
				<div>
					<a href="#newModal" role="button" class="btn" data-toggle="modal">发表留言</a>
				</div>
				<div class="box-header well" data-original-title>
					<h2>
						<i class="icon-edit"></i> 相册留言
					</h2>
					<div class="box-icon">
						<a href="#" class="btn btn-minimize btn-round"><i
							class="icon-chevron-up"></i></a> <a href="#"
							class="btn btn-close btn-round"><i class="icon-remove"></i></a>
					</div>
				</div>
				<div class="box-content">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable">
						<thead>
							<tr>
								<th width="20%">好友</th>
								<th width="80%">留言内容</th>
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

	<div class="modal hide fade" id="newModal" style="z-index: 9999">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>发表留言</h3>
		</div>
		<div class="modal-body" style="height: 500px;">
			<p>
				内容：
				<textarea class="cleditor" rows="3" id="message"></textarea>
			</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn"
				onclick="$('#newModal').modal('hide');
	    	$('.datatable').dataTable().fnDraw();">关闭</a>
			<a href="#" class="btn btn-primary" onClick="addMessage()">Save
				changes</a>
		</div>
	</div>
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
	function refresh() {
		var id = $('#photoBoxId').val();
		window.location.href='/friend/photoBox/view.spring?id=' + id;
		}
		initializeEvents($("#photoBoxId").val());
		function addMessage() {
			var url = "/friend/photoBox/addMessage.spring";
			var message = $('#message').val();
			var id = $("#photoBoxId").val();
			var data = {
				id : id,
				message : message
			};
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				success : function(input) {
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
			$('.datatable')
					.dataTable(
							{
								"sDom": "<t<'span12 center'p>>",
								"bServerSide" : true,
								"sAjaxSource" : "/friend/photoBox/listMessage.spring?id="
										+ id,
								"sServerMethod" : "GET",
								"bProcessing" : false,
								"bPaginate" : true,
								"bLengthChange" : true,
								"iDisplayLength" : 10,
								"fnAdjustColumnSizing" : false,
								"bStateSave" : false,
								"bSort" : false,
								"bFilter" : false,
								"aoColumnDefs" : makeCollumnDef(),
								"aoColumns" : makeCollomns(),
								"sPaginationType" : "bootstrap",
								"oLanguage" : {
									"sLengthMenu" : "_MENU_ records per page"
								}
							});

			$("#multiple_file_upload").uploadify(
					{
						'uploader' : '/friend/photo/upload.spring',//************ action **************  
						'height' : 25,//表示按钮的高度，默认30PX。若要改为50PX，如下设置：'height' : 50，  
						'width' : 70,//表示按钮的宽度  
						'buttonText' : 'SELECT',//按钮上显示的文字，默认”SELECT FILES”  
						'buttonCursor' : 'hand',//上传按钮Hover时的鼠标形状，默认值是’hand’  
						'auto' : false, //是否自动开始     
						'multi' : true, //是否支持多文件上传，默认为true  
						'method' : 'post',//默认是’post’,也可以设置为’get’  
						'swf' : '/friend/admin/img/uploadify.swf',//进度条显示文件  
						'cancelImg' : '/friend/admin/img/uploadify-cancel.png',//取消按钮的图片  
						'fileTypeDesc' : '上传照片',//允许上传的文件类型的描述，在弹出的文件选择框里会显示  
						'fileTypeExts' : '*.png; *.PNG; *.gif; *.jpg',//指定文件格式  
						'fileSizeLimit' : '50MB',//上传文件大小限制，默认单位是KB，若需要限制大小在100KB以内，可设置该属性为：'100KB'  
						'fileObjName' : 'myFile',//文件对象名称。用于在服务器端获取文件。  
						'formData' : {
							'photoBoxId' : '0',
						},
						'progressData' : 'all', // 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度  
						'preventCaching' : true,//若设置为true，一个随机数将被加载swf文件URL的后面，防止浏览器缓存。默认值为true  
						'timeoutuploadLimit' : 5,//能同时上传的文件数目  
						'removeCompleted' : false,//默认是True，即上传完成后就看不到上传文件进度条了。  
						'removeTimeout' : 5,//上传完成后多久删除队列中的进度条，默认为3，即3秒。  
						'requeueErrors' : true,//若设置为True，那么在上传过程中因为出错导致上传失败的文件将被重新加入队列。  
						'successTimeout' : 30,//表示文件上传完成后等待服务器响应的时间。不超过该时间，那么将认为上传成功。默认是30，表示30秒。  
						'uploadLimit' : 20,//最多上传文件数量，默认999  
						'onUploadStart' : function(file) {
							$("#multiple_file_upload").uploadify("settings",
									"formData", {
										'photoBoxId' : $('#photoBoxId').val()
									});
							//$("#file_upload").uploadify("settings", "buttonText", "aaa");  
							//alert(file.name + " is ready to go!")  
							$("#stopUpload").removeAttr("hidden");
						},
						'onUploadSuccess' : function(file, data, response) {
							//alert(file.name + " upload success !");  
							//alert(data + "----" + response);  
							$("#stopUpload").hide();
						},
						'onSWFReady' : function(obj) {
							$('span.action').hide();
							$('span.filename').hide();
						}
					});
		}
		function makeCollumnDef() {
			return [ {
				"fnRender" : function(oObj, sVal) {
					return oObj.aData.userName;
				},
				"bVisible" : true,
				"aTargets" : [ 0 ]
			}, {
				"fnRender" : function(oObj, sVal) {
					return oObj.aData.message;
				},
				"bVisible" : true,
				"aTargets" : [ 1 ]
			} ];
		}
		function makeCollomns() {
			return [ {
				"mDataProp" : "userName",
				"sHeight" : "50px"
			}, {
				"mDataProp" : "message"
			} ];
		}
		function openUpload() {
			$('.uploadify-queue').empty();
		}
	</script>
</body>
</html>
