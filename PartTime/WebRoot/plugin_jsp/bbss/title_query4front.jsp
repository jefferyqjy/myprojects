<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>plugin_js/bbss/title.js"></script>

			<script type="text/javascript">
				function toPage(pageNum){
					var url = "title/title_query4front.do?curPage="+pageNum;
					self.location.href = encodeURI(url);
				}
				
				
			</script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.list" module="title"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
				<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					<form id="searchForm" name="searchForm" action="title/title_query4front.do" method="post">
			<public:i18n key="title" module="title"></public:i18n>: <input type="text" name="title"/>
			
						<input name="queryButton" type="submit" class="button" value='<public:i18n key="button.query" module="common"></public:i18n>' />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%if(request.getSession().getAttribute("frontUsername") != null) {
						//if(request.getSession().getAttribute("ses_admin_username") !=null){%>
						<input name="queryButton" type="button" onclick="tofatie();" class="button" value='发帖' />
						<%} %>
						<script type="text/javascript">
							function tofatie(){
								//alter("aaa");
								var url = "title/title_tofatie.do";
								self.location.href = encodeURI(url);
							}
				
			</script>
					</form>
					<c:choose>
						<c:when test="${empty result}">
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
										
								<th><public:i18n key="title" module="title"></public:i18n></th>
								
								<th><public:i18n key="datetime" module="title"></public:i18n></th>
										
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
										
										<!--<td><a href="<%=basePath %>title/title_detail.do?id=${record.id }" title="title">${record.id }</a></td>-->
											<td>
													<a href="<%=basePath %>title/title_detail4gentie.do?id=${record.id }" title="title">
													${record.title}
													</a>
											</td>
											
											<td>${record.datetimestr}</td>
											<!-- 
										<td>
											<a style="cursor: pointer;" onclick="gotoEdit('<%=basePath %>title/title_edit4front.do?id=${record.id }')" title="Edit"><img src="resources/images/icons/pencil.png" alt="回帖" /></a>
											
										</td>
										 -->
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
