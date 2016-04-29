<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/student/student.js"></script>

			<script type="text/javascript">
				function toPage(pageNum){
					var url = "student/student_select.do?curPage="+pageNum;
					self.location.href = encodeURI(url);
				}
			</script>
		</head>

		<body>
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
					<form id="searchForm" name="searchForm" action="student/student_select.do" method="post">
			<public:i18n key="number" module="student"></public:i18n>: <input type="text" name="number"/>
			<public:i18n key="name" module="student"></public:i18n>: <input type="text" name="name"/>
						<input name="queryButton" type="submit" class="button" value='<public:i18n key="button.query" module="common"></public:i18n>' />
					</form>
					<c:choose>
						<c:when test="${empty result}">
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
								<th><public:i18n key="number" module="student"></public:i18n></th>
								<th><public:i18n key="name" module="student"></public:i18n></th>
								<th><public:i18n key="age" module="student"></public:i18n></th>
								<th><public:i18n key="sex" module="student"></public:i18n></th>
								<th><public:i18n key="collage" module="student"></public:i18n></th>
								<th><public:i18n key="className" module="student"></public:i18n></th>
								<th><public:i18n key="examId" module="student"></public:i18n></th>
								<th><public:i18n key="money" module="student"></public:i18n></th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="10">
										<div class="pagination">${pageInfo }</div>
										<div class="clear"></div>
										</td>
									</tr>
								</tfoot>
								<tbody>
								<c:forEach items="${result}" var="record">
									<tr>
										<!--<td><a href="<%=basePath %>student/student_detail.do?id=${record.id }" title="title">${record.id }</a></td>-->
											<td>${record.number}</td>
											<td>${record.name}</td>
											<td>${record.age}</td>
											<td>${record.sex}</td>
											<td>${record.collage}</td>
											<td>${record.className}</td>
											<td>${record.examId}</td>
											<td>${record.money}</td>
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
