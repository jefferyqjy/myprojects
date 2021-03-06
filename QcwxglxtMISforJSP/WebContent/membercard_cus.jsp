<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员卡管理</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jstable.js"></script>
<script language="JavaScript">
	/*
	 * operator = 2 add
	 * operator = 3 udpate
	 * operator = 4 delete
	 */
	function doSearch() {
		var cardNo = $("input[name='cardNo']").val();
		var cusId = $("input[name='cusId']").val();
		var operator = "1";
		if(cardNo != null && cardNo != "") {
			operator = "0";
		}
		if(cusId != null && cusId != "") {
			operator = "0";
		}
		window.location.href = "Membercard?currentPage=1&&pagerMethod=1&&operator=" + operator + "&&cardNo=" + cardNo + "&&cusId=" + cusId;
	}
	
	function doAdd() {
		window.location.href = "Membercard?operator=2";
	}
</script>

</head>
<body>
	<table width="1200" border="0" align="center" height="100%"
		style="border-left: 1px solid #7BD676; border-right: 1px solid #7BD676; border-top: 1px solid #7bd676; border-bottom: 1px solid #7BD676;">
		<tr height="120">
			<td colspan="2"><jsp:include flush="true" page="/top.jsp" /></td>
		</tr>
		<tr>
			<td width="150" valign="top"><jsp:include flush="true"
					page="/left_cus.jsp" /></td>
			<td width="1050" align="center" valign="top">
				<table width="1050" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" height="390"><form
								name="Membercard?_form" method="post"></form>
							<table width="1050" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="30">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr align="center" style="font-size: 25px;">
												<td width="15" height="30"><img src="img/tab_03.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_05.gif">会员卡管理</td>
												<td width="14"><img src="img/tab_07.gif" width="14"
													height="30" /></td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td><table width="100%" border="0" cellspacing="0"
											cellpadding="0">
											<tr>
												<td width="9" background="img/tab_12.gif">&nbsp;</td>
												<td bgcolor="#f3ffe3" height="390" valign="top">
													<!-- <table>
														<tr align="center" height="50">
															<td valign="top" class="mt10">
																<input type="button" name="button" class="btn btn82 btn_search" value="查询" style='font-size: 11px' onClick="doSearch();">
															</td>
														</tr>
													</table> -->
													<table>
														<tr>
															<td><img src="img/311.gif" width="16" height="16" />
																<span class="STYLE4">会员卡列表</span></td>
														</tr>
													</table>
													<table width="99%" border="0" align="center"
														cellpadding="0" cellspacing="1" bgcolor="#6699CC"
														onmouseover="changeto()" onmouseout="changeback()">
														<tr>
															<td width="13%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">序号</td>
															<td width="13%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">会员卡号编号</td>
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">客户编号</td>
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">客户姓名</td>
															<td width="20%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">余额</td>
															<td width="20%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">开通时间</td>
														</tr>
														<tbody align="center" onmouseover="changeto()"
															onmouseout="changeback()">
															<c:forEach var="mc" items="${membercards}" varStatus="item">
																<tr align="center" bgcolor="#FFFFFF" height="25">
																	<td>${mc.mId}</td>
																	<td>${mc.cardNo}</td>
																	<td>${mc.cusId}</td>
																	<td>${mc.cusName}</td>
																	<td>${mc.amount}</td>
																	<td>${mc.createTime}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</td>
												<td width="9" background="img/tab_16.gif">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
								<tr>
									<td height="30"><table width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr>
												<td width="15" height="30"><img src="img/tab_20.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_21.gif"><table
														align="right" border="0">
														<tr>
															<td colspan="9">共${totalRows}行&nbsp;
																第${currentPage}页&nbsp; 共${totalPage}页&nbsp; 
																<c:if test="${operator==0}">
																	<a href="Membercard??operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=1">首页</a>
																	<a href="Membercard??operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=2">上一页</a>
																	<a href="Membercard??operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=3">下一页</a>
																	<a href="Membercard??operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=4">尾页</a>
																</c:if> 
																<c:if test="${operator==1}">
																	<a href="Membercard??operator=1&&currentPage=${currentPage}&&pagerMethod=1">首页</a>
																	<a href="Membercard??operator=1&&currentPage=${currentPage}&&pagerMethod=2">上一页</a>
																	<a href="Membercard??operator=1&&currentPage=${currentPage}&&pagerMethod=3">下一页</a>
																	<a href="Membercard??operator=1&&currentPage=${currentPage}&&pagerMethod=4">尾页</a>
																</c:if>
															</td>
														</tr>
													</table></td>
												<td width="14"><img src="img/tab_22.gif" width="14"
													height="30" /></td>
											</tr>
										</table></td>
								</tr>
							</table></td>
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