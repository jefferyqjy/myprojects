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
<title>动态管理</title>
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

.twitter {
	font-size: 16px;
}
.comment {
 	font-size: 10px;
}

.twitterContent {
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
<body  oncontextmenu="window.event.returnValue=false">
	<!-- topbar starts -->
	<!--------------Header--------------->
	<tags:header />

	<!--------------Navigation--------------->

	<tags:nav />

	<!-- topbar ends -->
	<div class="container-fluid"
		style="min-height: 600px; width: 960px; margin: 0 auto 15px auto;">
		<div class="row-fluid">
			<div id="content" class="span10" style="width: 100%">
				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i> 我的动态
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="height:490px; min-height: 490px; max-height:490px">
							<div id="twitterDiv" style="padding-left:50px;height:450px; min-height:450px; max-height:450px; OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
								<div class="controls control-row">
									<div class="span8" >
										<input type="text" name="twitter" class="input-xlarge"/>
										<a href="#" class="btn btn-primary" onclick="postTwitter();" style="margin-bottom:10px;margin-left:10px" >发布</a>										
									</div>
								</div>
								<div class="twitterContent" >
									<div class="clearfix"></div>
									<c:forEach items="${TwitterList}" var="t">
										<div class="controls controls-row twitter">
									  		<div class="span2"><font size="bold">${t.userName }</font> 说：</div>
									  		<div class="span8">${t.content }</div>
									  	<div class="clearfix"></div>
									  	<c:forEach items="${t.commentList}" var="c">
								  			<div class="controls controls-row comment">
										  		<div class="span4">${c.createTime } ${c.fromUserName } 对 ${c.toUserName } 说：</div>
											  	<div class="span4">${c.content }</div>
											  	<div class="span4">	
											  		<a href="#" id="${c.toUserId }" onclick="addComment('${c.toUserId}')" class="btn btn-add btn-round"><font size="bold">+</font></a>
											  		<a href="#" id="${c.id }" onclick="deleteComment('${c.id}')" class="btn btn-close btn-round"><font size="bold">&times</font></a>
												</div>
											</div>
											<div class="clearfix"></div>
									  	</c:forEach>
									</div>
									</c:forEach>
								</div>
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
		
		<!-- comment modal div begin -->
		<div class="modal hide fade" id="newModal13" style="z-index: 9999">
	
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3>评论</h3>
			</div>
			<div class="modal-body" style="height: 100px;">
				<p>
					 <input class="input-xlarge focused" id="commentContent" type="text" />
				</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" onclick="$('#newModal13').modal('hide');">关闭</a>
				<a href="#" class="btn btn-primary" onClick="doReport()">举报</a>
			</div>
		</div>
		<!-- comment modal div end -->
	</div>
	
	<form id="hiddenForm" name="hiddenForm" action="/friend/frd/home.spring">
	<input type="hidden" name="userId" />
	</form>
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
		var oTable;
		var curMemberId, curGroupId;
		initializeEvents();
		
		function refresh() {
			window.location.href = '/friend/frd/preList.spring';
		}
		
		function initializeEvents() {
			var userName = "";
			var userGender = "";
			var subject = "";
			var universityId = "";			
			var url = "/friend/frd/searchUser.spring?universityId=" + universityId
			+ "&userName=" + userName + "&userGender=" + userGender + "&subject=" + subject;
			url= encodeURI(encodeURI(url)); 
			oTable = $('#userTable') .dataTable(
				{
					"sDom": "<t<'span12 center'p>>",
					"bServerSide" : true,
					"sAjaxSource" : url,
					"sServerMethod" : "POST",
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
			
			$(".groupLi").mousedown(groupMouseDown);
			$(".memberLi").mousedown(memberMouseDown);
		}

		function groupMouseDown(e) {
			if(3 == e.which){ 
				curGroupId = $(this).val();
				//var mr = $("#groupRightClickDiv");
				//mr.css('top', e.screenY);
				//mr.css('left', e.screenX);
				//mr.show();
			} else if(1 == e.which){ 
				if ("none" == $(this).parent().find('li a').css('display')) {
					$(this).parent().find('li a').show();
				} else {
					$(this).parent().find('li a').hide();
				}
			}		
		}

		// right click on the friend
		function memberMouseDown(e) {
			if(3 == e.which){ 
				curMemberId = $(this).val();
				var mr = $("#memberRightClickDiv");
				mr.css('top', e.screenY - 60);
				mr.css('left', e.screenX);
				mr.show();
			} else if(1 == e.which){ 
				
			}	
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
					return oObj.aData.gender;
				},
				"bVisible" : true,
				"aTargets" : [ 1 ]
			}, {
				"fnRender" : function(oObj, sVal) {
					return oObj.aData.age;
				},
				"bVisible" : true,
				"aTargets" : [ 2 ]
			}, {
				"fnRender" : function(oObj, sVal) {
					return createAction(oObj.aData.id);
				},
				"bVisible" : true,
				"aTargets" : [ 3 ]
			} ];
		}
		function makeCollomns() {
			return [ {
				"mDataProp" : "userName",
				"sHeight" : "15px"
			}, {
				"mDataProp" : "gender"
			}, {
				"mDataProp" : "age"
			}, {
				"mDataProp" : null
			} ];
		}
		function addGroup() {
			var url = "/friend/frd/addGroup.spring";
			var name = $('#groupName').val();
			var data = {
				name : name
			};
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				success : function(input) {
					if (input == "1") {
						$("#newModal").modal('hide');
						$('#groupName').val('');
						openMsg('消息', '创建新的组成功', refresh);
					} else {
						$("#newModal").modal('hide');
						$('#groupName').val('');
						openMsg('消息', '发创建新的组失败', refresh);
					}
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}

		function postTwitter() {
			var content = $("input[name='twitter']").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/twitter/addTwitter.spring",
				type : "POST",
				data : {content : content},
				success : function(input) {
					window.location.href = "${pageContext.request.contextPath}/twitter/preList.spring";
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}

		function deleteComment(id) {
			$.ajax({
				url : "${pageContext.request.contextPath}/twitter/deleteComment.spring",
				type : "POST",
				data : {commentId : id},
				success : function(input) {
					window.location.href = "${pageContext.request.contextPath}/twitter/preList.spring";
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}

	</script>
</body>
</html>
