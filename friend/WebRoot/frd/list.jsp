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
<title>好友管理</title>
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
					<div class="box span3">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i> 我的好友
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="min-height: 450px">
							<div class="span2 main-menu-span" style="width: 100%">
								<div class="well nav-collapse sidebar-nav">
									<c:forEach items="${GROUPLIST}" var="group">
										<ul class="nav nav-tabs nav-stacked main-menu"
											style="margin-top: 5px; margin-bottom: 2px">
											<li class="nav-header hidden-tablet groupLi" value="${group.id }">${group.name }</li>
											<c:forEach items="${group.friends}" var="member">
												<li class="memberLi" style="margin-left: -2px;" value="${member.id }"><a class="ajax-link"
													href="#" style="margin-left:10px"><span
														class="hidden-tablet"> ${member.userName }</span></a></li>
											</c:forEach>
										</ul>
									</c:forEach>
								</div>
								<!--/.well -->
							</div>
						</div>
						<div style="margin: 5px auto 5px auto">
							<a style="margin-left: 30px" href="#newModal" role="button"
								class="btn" data-toggle="modal">创建新组</a> <a href="#newModal2"
								role="button" class="btn" data-toggle="modal">搜索好友</a>
						</div>
					</div>
					<!--/span-->

					<div class="box span9">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i> 聊天窗口
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="height:490px; min-height: 490px; max-height:490px">
							<div id="chatMsgDiv" style="height:450px; min-height:450px; max-height:450px; OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
								
							</div>
							<div style="min-height:40px; max-height:40px">
								发送消息： <input type="text"  style="width:400px; display:none" id="chatInput" onkeydown="if(event.keyCode==13) {addMsg()};"/>
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
	<div class="modal hide fade" id="moveGroup" style="z-index: 9999">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>请选择移动的用户组</h3>
		</div>
		<div class="modal-body" style="height: 100px;">
			<p>
				组名称：
				<select id="moveGroupSelect">
				 <c:forEach items="${GROUPLIST}" var="group">
					<option value="${group.id }">${group.name }</option>
				 </c:forEach>
				</select>
			</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" onclick="$('#moveGroup').modal('hide');">关闭</a>
			<a href="#" class="btn btn-primary" onClick="moveGroup()">移动</a>
		</div>
	</div>
	
	<div class="modal hide fade" id="newModal" style="z-index: 9999">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>创建新的组</h3>
		</div>
		<div class="modal-body" style="height: 100px;">
			<p>
				组名称： <input class="input-xlarge focused" id="groupName" type="text" />
			</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" onclick="$('#newModal').modal('hide');">关闭</a>
			<a href="#" class="btn btn-primary" onClick="addGroup()">添加</a>
		</div>
	</div>

	<div class="modal hide fade" id="newModal2" style="z-index: 9999">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>搜索好友</h3>
		</div>
		<div class="modal-body" style="height: 400px;">
			<p>
				编号： <input class="input-xlarge focused" id="userId" type="text"
					style="width: 100px" /> 姓名： <input class="input-xlarge focused"
					id="userName" type="text" style="width: 100px" /> 性别： <select
					id="userGender" data-rel="chosen" style="width: 100px"><option
						value="男">男</option>
					<option value="女">女</option></select>
			</p>
			<table id="userTable"
				class="table table-striped table-bordered bootstrap-datatable datatable">
				  <thead>
				<tr>
					<th width="100px">姓名</th>
					<th width="100px">性别</th>
					<th width="100px">年龄</th>
					<th width="100px">操作</th>
				</tr>
				</thead>
			</table>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" onclick="$('#newModal2').modal('hide');">关闭</a>
			<a href="#" class="btn btn-primary" onClick="searchUser()">搜索</a>
		</div>
	</div>
	<div id="groupRightClickDiv" style="display:none; z-index:99998; position: absolute;">
		<table class="table table-hover" style="width: 80px; height:20px; border:1px solid green; text-align:center">
			<tr height=20px><td align=center>删除</td></tr>
		</table>
	</div>
	<div id="memberRightClickDiv" style="display:none; z-index:99998; position: absolute;">
		<table class="table table-hover" style="width: 80px; height:80px; border:1px solid green; text-align:center">
			<tr height=20px><td><center><a href="#" onClick="accessM()">访问空间</a></center></td></tr>
			<tr height=20px><td><center><a href="#" onClick="chatM()">聊天</a></center></td></tr>
			<tr height=20px><td><center><a href="#" onClick="moveM()">移动</a></center></td></tr>
			<tr height=20px><td><center><a href="#" onClick="deleteM()">删除</a></center></td></tr>
			<tr height=20px><td><center><a href="#" onClick="closeM()">关闭</a></center></td></tr>
		</table>
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
		getMessage();
		function accessM() {
			hiddenForm.userId.value = curMemberId;
			$("#hiddenForm").attr("target", "_blank");
			$("#hiddenForm").submit();
		}
		
		function refresh() {
			window.location.href = '/friend/frd/preList.spring';
		}
		
		function addMsg() {
			var msg = $("#chatInput").val();
			if (msg=="" || msg.trim() =="") {
				return;
			} else {
				var url = "/friend/frd/addMsg.spring";
				var data = {
						userId : curMemberId,
						msg:msg
				};
				$.ajax({
					url : url,
					type : "POST",
					data : data,
					success : function(input) {
						var div = "<div style='float:right; color:green'>我@刚刚</div>";
						$("#chatMsgDiv").append(div);
						div = "<div style='clear:both'></div>";
						$("#chatMsgDiv").append(div);
						div = "<div style='float:right; color:green'>" + msg +"</div>";
						$("#chatMsgDiv").append(div);
						div = "<div style='clear:both'></div>";
						$("#chatMsgDiv").append(div);
						$("#chatInput").val('');
						$("#chatMsgDiv").scrollTop(10000000);
					},
					error : function(x, e) {
						alert(e);
					}
				});
			}
		}
		
		function chatM() {
			$("#chatMsgDiv").empty();
			$("#chatInput").show();
			$("#chatInput").val('');
			closeM();
			getUserMsg();
		}
		
		function getUserMsg() {
			$("#chatMsgDiv").empty();
			var url = "/friend/frd/getMsg.spring";
			var data = {
					userId : curMemberId
				};
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				success : function(input) {
					var len = input.length;
					for(var i=0; i < len; i++) {
						var message = input[i];
						if (message.order == 1) {
							var div = "<div style='color:blue'>" + message.fromUserName +"@" + message.date+"<br>" + message.message+"</div>";
							$("#chatMsgDiv").append(div);
						} else {
							var div = "<div style='float:right; color:green'>我@" + message.date+"</div>";
							$("#chatMsgDiv").append(div);
							div = "<div style='clear:both'></div>";
							$("#chatMsgDiv").append(div);
							div = "<div style='float:right; color:green'>" + message.message+"</div>";
							$("#chatMsgDiv").append(div);
							div = "<div style='clear:both'></div>";
							$("#chatMsgDiv").append(div);
						}
					}
					$("#chatMsgDiv").scrollTop(10000000);
					setTimeout(getUserMsg, 5000);
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}
		
		function moveM() {
			$('#moveGroup').modal();
			closeM();
		}
		
		function moveGroup() {
			closeM();
			var url = "/friend/frd/moveFriend.spring";
			var groupId = $('#moveGroupSelect').val();
			var data = {
				userId : curMemberId,
				groupId : groupId
			};
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				success : function(input) {
					$('#moveGroup').modal('hide');
					if (input == "1") {
						openMsg('消息', '移动好友成功', refresh);
					} else {
						openMsg('消息', '移动好友失败');
					}
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}

		function deleteM() {
			closeM();
			var url = "/friend/frd/deleteFriend.spring";
			var data = {
				userId : curMemberId
			};
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				success : function(input) {
					if (input == "1") {
						openMsg('消息', '删除好友成功', refresh);
					} else {
						openMsg('消息', '添加好友失败');
					}
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}
		
		function closeM() {
			$('#memberRightClickDiv').hide();
		}
		function getMessage() {
			var url = "/friend/frd/getMessage.spring";
			$.ajax({
				url : url,
				type : "POST",
				success : function(input) {
					
					if (input != "0") {
						openMsg('消息', input);
					}
					
					setTimeout(getMessage, 1000);
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}
		
		function addUser(userId) {
			var url = "/friend/frd/addFriend.spring";
			var data = {
				userId : userId
			};
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				success : function(input) {
					$("#newModal2").modal('hide');
					if (input == "1") {
						openMsg('消息', '添加好友成功，默认到你的第一个分组中', refresh);
					} else if (input == "-1") {
						openMsg('消息', '自己不能添加自己为好友');
					} else if (input == "-2"){
						openMsg('消息', '他已经是你的好友，无需再次添加');
					} else if (input == "-3"){
						openMsg('消息', '你还没有添加小组，请先创建用户组');
					} else {
						openMsg('消息', '添加好友失败');
					}
				},
				error : function(x, e) {
					alert(e);
				}
			});
		}
		function searchUser() {

			var userId = $('#userId').val();
			var userName = $('#userName').val();
			var userGender = $('#userGender').val();		
			var url = "/friend/frd/searchUser.spring?userId=" + userId
					+ "&userName=" + userName + "&userGender=" + userGender;

			url= encodeURI(encodeURI(url)); 
			oTable.fnSettings().sAjaxSource = url;
			oTable.fnClearTable(0);
			oTable.fnDraw();
			
		}
		function initializeEvents() {
			var userId = "";
			var userName = "";
			var userGender = "";
			var url = "/friend/frd/searchUser.spring?userId=" + userId
			+ "&userName=" + userName + "&userGender=" + userGender;
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
		function createAction(id) {
			var inhtml = '<a class="btn btn-success" onClick="addUser(' + id+')" href="#">';
			inhtml += '<i class="icon-zoom-in icon-white"></i>加为好友</a> ';
			return inhtml;
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
	</script>
</body>
</html>
