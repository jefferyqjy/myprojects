<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>plugin_js/bbss/message.js"></script>

			<script type="text/javascript">
				function toPage(pageNum){
					var url = "message/message_query.do?curPage="+pageNum;
					self.location.href = encodeURI(url);
				}
			</script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.manager" module="message"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.list" module="message"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
				<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					
					<c:choose>
						<c:when test="${empty result}">
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
										
								
								<th><public:i18n key="content" module="message"></public:i18n></th>
								<th><public:i18n key="username" module="message"></public:i18n></th>
								<th><public:i18n key="datetime" module="message"></public:i18n></th>
										<th><public:i18n key="action" module="common"></public:i18n></th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="6">
										
										<div class="pagination">${pageInfo }</div>
										<div class="clear"></div>
										</td>
									</tr>
								</tfoot>
								<tbody>
								<c:forEach items="${result}" var="record">
									<tr>
										
										<!--<td><a href="<%=basePath %>message/message_detail.do?id=${record.id }" title="title">${record.id }</a></td>-->
											
											<td>${record.content}</td>
											<td>${record.username}</td>
											<td>${record.datetime}</td>
										<td>
											
											<a style="cursor: pointer;" onclick="actionDel('<%=basePath %>title/title_del4message.do?id=${record.id }&title=${record.title}')" title="Delete"><img src="resources/images/icons/cross.png" alt="Delete" /></a>
										</td>
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
