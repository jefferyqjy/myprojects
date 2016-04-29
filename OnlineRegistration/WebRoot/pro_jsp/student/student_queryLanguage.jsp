<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<%@page import="com.base.common.util.Const"%>
<%
String loginType = request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE)+"";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/student/student.js"></script>

			<script type="text/javascript">
				function toPage(pageNum){
					var url = "student/student_query.do?curPage="+pageNum;
					self.location.href = encodeURI(url);
				}
				
				function query() {
					var loginType = "<%=loginType %>";
					if (loginType == '2') {
						var number = document.getElementById("number").value;
						var name = document.getElementById("name").value;
						if (name != "" && number != "") {
							document.searchForm.submit();
						} else {
							document.searchForm.submit();
							//alert ("请输入学号和姓名!");
						}
					} else {
						document.searchForm.submit();
					}
				}
				
				function jiaofei(number,examId){
					if (examId == null || examId == "") {
						alert("您目前没有需要交费的科目！");
						return;
					}
					if(confirmJiaofei()== true){
						if(jiaofeiSucc()== true){
							var url = "student/student_update.do?number="+number+"&types=jiaofei";
							self.location.href = encodeURI(url);
						}
					}
				}
				
				function confirmJiaofei(){
					return confirm('是否确认缴费！');
				}
				
				function jiaofeiSucc(){
					return confirm('缴费成功！');
				}
				
				function batchInserts(){
					var url = "student/student_batchInsert.do";
					self.location.href = encodeURI(url);
				}
			</script>
		</head>

		<body>
			<c:set var="loginType" scope="session" value="<%=loginType%>"/>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.manager" module="student"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.list" module="student"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
				<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					<form id="searchForm" name="searchForm" action="student/student_queryLanguage.do" method="post">
					<public:i18n key="number" module="student"></public:i18n>: 
					<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("2")) {%>
						<input type="text" name="number" id="number"/>
					<%} else { 
						String userName = request.getSession().getAttribute(Const.Session.ADMIN_USER_NAME)+"";
					%>
						<input type="text" name="number" id="number" value="<%=userName %>" readonly="readonly"/>
					<%} %>
					
					<public:i18n key="name" module="student"></public:i18n>:
					<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("2")) {%>
						<input type="text" name="name" id="name"/>
					<%} else { 
						String name = request.getSession().getAttribute(Const.Session.SES_USER_NAME)+"";
					%>
						<input type="text" name="name" id="name" value="<%=name %>" readonly="readonly"/>
					<%} %>
						<input name="queryButton" type="button" class="button" value='<public:i18n key="button.query" module="common"></public:i18n>' onclick="query()"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%if(request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("1")) {%>
						<input class="button" type="button" name="batchInsert" value="批量导入学生信息" style="padding-right:100px" onclick="batchInserts()" />
						<%} %>
					</form>
					<c:choose>
						<c:when test="${empty result}">
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
								<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("2")) {%>
										<th><input class="check-all" type="checkbox" /></th>
								<%} %>
								<th><public:i18n key="number" module="student"></public:i18n></th>
								<th><public:i18n key="name" module="student"></public:i18n></th>
								<th><public:i18n key="age" module="student"></public:i18n></th>
								<th><public:i18n key="sex" module="student"></public:i18n></th>
								<th><public:i18n key="collage" module="student"></public:i18n></th>
								<th><public:i18n key="className" module="student"></public:i18n></th>
								<th><public:i18n key="examId" module="student"></public:i18n></th>
								<th><public:i18n key="money" module="student"></public:i18n></th>
								<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("2")) {%>
										<th><public:i18n key="action" module="common"></public:i18n></th>
								<%} %>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="10">
										<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("2")) {%>
										<div class="bulk-actions align-left">
											<select name="dropdown">
												<option value=""><public:i18n key="action.select" module="common"></public:i18n></option>
												<option value="D"><public:i18n key="label.delete" module="common"></public:i18n></option>
											</select>
											<a class="button" id="applyButton" style="cursor: pointer;"><public:i18n key="button.apply" module="common"></public:i18n></a>
										</div>
										<%} %>
										<div class="pagination">${pageInfo }</div>
										<div class="clear"></div>
										</td>
									</tr>
								</tfoot>
								<tbody>
								<c:forEach items="${result}" var="record">
									<tr>
										<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("2")) {%>
										<td>
										<input name="primaryKey" type="checkbox" value="${record.id }"/></td>
										<%}%>
											<td><a href="<%=basePath %>student/student_editLanguage.do?id=${record.id }" title="title">${record.number }</a></td>
											<td>${record.name} 4323453</td>
											<td>${record.age}</td>
											<td>${record.sex}</td>
											<td>${record.collage}</td>
											<td>${record.className}</td>
											<td><a href="<%=basePath %>exam/exam_detail.do?examId=${record.examId }&type=student&id=${record.id }" title="title">${record.examId }</a></td>
											
											<c:choose>
												<c:when test="${record.money eq '已缴'}">
													<td>${record.money}</td>
												</c:when>
												<c:when test="${loginType eq '2'}">
													<td><input type="button" value="我要缴费" onclick="jiaofei(${record.number},'${record.examId }')"/></td>
												</c:when>
												<c:otherwise>
													<td>${record.money}</td>
												</c:otherwise>
											</c:choose>
										<%if(!request.getSession().getAttribute(Const.Session.ADMIN_USER_TYPE).equals("2")) {%>
										<td>
											<a style="cursor: pointer;" onclick="gotoEdit('<%=basePath %>student/student_edit.do?id=${record.id }')" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
											<a style="cursor: pointer;" onclick="actionDel('<%=basePath %>student/student_del.do?id=${record.id }')" title="Delete"><img src="resources/images/icons/cross.png" alt="Delete" /></a>
										</td>
										<%} %>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	</body>
	</html>
