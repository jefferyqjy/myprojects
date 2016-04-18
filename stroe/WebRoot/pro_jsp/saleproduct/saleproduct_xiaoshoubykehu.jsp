<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/saleproduct/saleproduct.js"></script>

			<script type="text/javascript">
				function toPage(pageNum){
					var url = "saleproduct/saleproduct_query.do?curPage="+pageNum;
					self.location.href = encodeURI(url);
				}
			</script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3>销售汇总（客户）</h3>
				<ul class="content-box-tabs">
					<li>销售汇总（客户）</li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
				<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					<!-- 
					<form id="searchForm" name="searchForm" action="saleproduct/saleproduct_query.do" method="post">
			<public:i18n key="productname" module="saleproduct"></public:i18n>: <input type="text" name="productname"/>
			<public:i18n key="orderstatus" module="saleproduct"></public:i18n>: <input type="text" name="orderstatus"/>
						<input name="queryButton" type="submit" class="button" value='<public:i18n key="button.query" module="common"></public:i18n>' />
					</form>
					 -->
					<c:choose>
						<c:when test="${empty result}">
							无记录！
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
										
								<th>客户</th>
								<th>数量</th>
									</tr>
								</thead>
								
								<tbody>
								<c:forEach items="${result}" var="record">
									<tr>
											<td>${record.productname}</td>
											<td>${record.amount}</td>
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
