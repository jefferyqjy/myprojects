<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本月利润</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jstable.js"></script>
<script language="JavaScript">
</script>

</head>
<body>
	<table width="1200" border="0" align="center" height="100%"
		style="border-left: 1px solid #7BD676; border-right: 1px solid #7BD676; border-top: 1px solid #7bd676; border-bottom: 1px solid #7BD676;">
		<tr height="120">
			<td colspan="2"><jsp:include flush="true" page="/top.jsp" /></td>
		</tr>
		<tr>
			<td width="150" valign="top">
				<jsp:include flush="true" page="/left.jsp" />
			</td>
			<td width="1050" align="center" valign="top">
				<table width="1050" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" height="390">
							<form name="Wage_form" method="post"></form>
							<table width="1050" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td height="30">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr align="center" style="font-size: 25px;">
												<td width="15" height="30"><img src="img/tab_03.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_05.gif">本月利润</td>
												<td width="14"><img src="img/tab_07.gif" width="14"
													height="30" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="9" background="img/tab_12.gif">&nbsp;</td>
												<td bgcolor="#f3ffe3" height="390" valign="top">
													<table>
														<tr>
															<td><img src="img/311.gif" width="16" height="16" /><span class="STYLE4">本月利润</span></td>
														</tr>
													</table>
													<table width="99%" border="0" align="center"
														cellpadding="0" cellspacing="1" bgcolor="#6699CC"
														onmouseover="changeto()" onmouseout="changeback()">
														<tr>
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">编号</td>
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">结账单利润</td>
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">内配利润</td>
															<!-- <td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">退货损失</td> -->
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">本月工资</td>
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">本月总利润</td>
														</tr>
														<tbody align="center"  >
															<tr align="center" bgcolor="#FFFFFF" height="25">
																<td>1</td>
																<td>${profitmonthly.checkoutProfit}</td>
																<td>${profitmonthly.innerProfit}</td>
																<%-- <td>${profitmonthly.returnCost}</td> --%>
																<td>${profitmonthly.salaryCost}</td>
																<td>${profitmonthly.totalProfit}</td>
															</tr>
														</tbody>
													</table>
												</td>
												<td width="9" background="img/tab_16.gif">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="30">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="15" height="30"><img src="img/tab_20.gif" width="15" height="30" /></td>
												<td width="1101" background="img/tab_21.gif">
													<table align="right" border="0">
														<tr>
															<td colspan="9">共${totalRows}行&nbsp;
																第${currentPage}页&nbsp; 共${totalPage}页&nbsp; 
																<c:if test="${operator==0}">
																	<a href="Profit?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=1">首页</a>
																	<a href="Profit?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=2">上一页</a>
																	<a href="Profit?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=3">下一页</a>
																	<a href="Profit?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=4">尾页</a>
																</c:if> 
																<c:if test="${operator==1}">
																	<a href="Profit?operator=1&&currentPage=${currentPage}&&pagerMethod=1">首页</a>
																	<a href="Profit?operator=1&&currentPage=${currentPage}&&pagerMethod=2">上一页</a>
																	<a href="Profit?operator=1&&currentPage=${currentPage}&&pagerMethod=3">下一页</a>
																	<a href="Profit?operator=1&&currentPage=${currentPage}&&pagerMethod=4">尾页</a>
																</c:if>
															</td>
														</tr>
													</table>
												</td>
												<td width="14"><img src="img/tab_22.gif" width="14" height="30" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="24">
			<td colspan="2"><jsp:include flush="true" page="/down.jsp" /></td>
		</tr>
	</table>
</body>
</html>