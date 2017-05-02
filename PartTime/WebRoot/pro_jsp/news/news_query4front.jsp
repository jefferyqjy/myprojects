<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/news/news.js"></script>

			<script type="text/javascript">
				function toPage(pageNum){
					var id = document.getElementById("newsid").value;
					var url = "news/news_query4front.do?curPage="+pageNum+"&id="+id;
					self.location.href = encodeURI(url);
				}
			</script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3>新闻信息</h3>
				<ul class="content-box-tabs">
					<li>新闻信息</li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
				<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					<form id="searchForm" name="searchForm" action="news/news_query4front.do" method="post">
					
			<public:i18n key="title" module="news"></public:i18n>: <input type="text" name="title"/>
			<input type="hidden" name="id" id ="newsid" value="${newsid}"/>
			
			
						<input name="queryButton" type="submit" class="button" value='<public:i18n key="button.query" module="common"></public:i18n>' />
					</form>
					<c:choose>
						<c:when test="${empty result}">
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
									
								<th><public:i18n key="title" module="news"></public:i18n></th>
								
								
								
								<th><public:i18n key="datetime" module="news"></public:i18n></th>
									
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="7">
										
										
										<div class="pagination">${pageInfo }</div>
										<div class="clear"></div>
										</td>
									</tr>
								</tfoot>
								<tbody>
								<c:forEach items="${result}" var="record">
									<tr>
										
										
											<td>
											<a href="<%=basePath %>news/news_detail4front.do?id=${record.id }" title="title">
											${record.title}
											</a>
											</td>
											
											
											
											<td>${record.datetime}</td>
										
										
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
