<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/finance/finance.js"></script>

			<script type="text/javascript">
				function toPage(pageNum){
					var url = "finance/finance_query.do?curPage="+pageNum;
					self.location.href = encodeURI(url);
				}
			</script>
			<script type="text/javascript">
				function checkAndSubmit() {
					//alert($("#datefin1").val());
					if($("#datefin1").val() == null ||$("#datefin1").val() == "" ||$("#datefin2").val() == null ||$("#datefin2").val() == ""){
						alert("请选择时间");
						return false;
					}else{
						document.searchForm.submit();
					}
					return false;
					
				}
			</script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3>查询支出</h3>
				<ul class="content-box-tabs">
					<li>查询支出</li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
				<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
					<form id="searchForm" name="searchForm" action="finance/finance_queryzhichu.do" method="post">
			从: 
			 <input type="text" id="datefin1" name="datefin1" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#datefin1").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script> 
			到
			 <input type="text" id="datefin2" name="datefin2" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#datefin2").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script> 
						<button name="queryButton"  onclick="checkAndSubmit()" class="button">查询</button> 
					</form>
					<c:choose>
						<c:when test="${empty result}">
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
									
								<th>名称</th>
								<th>类别</th>
								<th>金额</th>
									</tr>
								</thead>
								
								<tbody>
								<c:forEach items="${result}" var="record">
									<tr>
										
											<td>${record.name}</td>
											<td>${record.type}</td>
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
