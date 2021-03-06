<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jstable.js"></script>
<script language="JavaScript">
	function doSearch() {
		if (document.all.searchValue.value == "") {
			alert("请输入查询关键字!");
		} else {
			window.location.href = "Customer?currentPage=1&&pagerMethod=1&&operator=0&&queryName="
					+ document.all.searchName.value
					+ "&&queryValue="
					+ document.all.searchValue.value;
		}
	}
	function doSearchAll() {
		window.location.href = "Customer?operator=1&&currentPage=1&&pagerMethod=1";
	}
	function doAdd() {
		window.location.href = "Customer?operator=2";
	}
	
	function doManage(cusID) {
		window.location.href = "Membercard?operator=0&&currentPage=1&&pagerMethod=1&&cusId=" + cusID;
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
					page="/left.jsp" /></td>
			<td width="1050" align="center" valign="top">
				<table width="1050" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" height="390"><form
								name="Customer_form" method="post"></form>
							<table width="1050" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="30">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr align="center" style="font-size: 25px;">
												<td width="15" height="30"><img src="img/tab_03.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_05.gif">客户管理</td>
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
													<table>
														<tr align="center" height="50">
															<td valign="top" class="mt10">请选择查询条件:<select
																name="searchName" class="select">
																	<option value="cusID">客户编号</option>
																	<option value="cusName">客户姓名</option>
																	<option value="cusadd">客户地址</option>
															</select> <input type="text" name="searchValue"
																class="input-text lh25" value="" size="10" /> <input
																type="button" name="button" class="btn btn82 btn_search"
																value="查询" style='font-size: 11px' onClick="doSearch();">
																<input type="button" name="button"
																class="btn btn82 btn_searchall" value="查询全部"
																style='font-size: 11px' onclick="doSearchAll();">
																<input type="button" name="button"
																class="btn btn82 btn_add" value="增加"
																style='font-size: 11px' onclick="doAdd();"></td>
														</tr>
													</table>
													<table>
														<tr>
															<td><img src="img/311.gif" width="16" height="16" />
																<span class="STYLE4">客户信息列表</span></td>
														</tr>
													</table>
													<table width="99%" border="0" align="center"
														cellpadding="0" cellspacing="1" bgcolor="#6699CC"
														onmouseover="changeto()" onmouseout="changeback()">
														<tr>
															<td width="8%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">客户编号</td>
															<td width="9%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">客户姓名</td>
															<td width="11%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">客户电话</td>
															<td width="25%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">客户地址</td>
															<td width="18%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">是否会员(总消费>=3000)</td>
															<td width="10%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">总消费</td>
															<td width="5%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">修改</td>
															<td width="5%" height="30" background="img/tab_14.gif"
																bgcolor="#FFFFFF" align="center">删除</td>
														</tr>
														<tbody align="center" onmouseover="changeto()"
															onmouseout="changeback()">
															<c:forEach var="item" items="${customers}"
																varStatus="customer">
																<tr align="center" bgcolor="#FFFFFF" height="25">
																	<td>${item.cusID}</td>
																	<td>${item.cusName}</td>
																	<td>${item.cusphone}</td>
																	<td>${item.cusadd}</td>
																	<c:if test="${item.vip==1}">
																		<td>
																			<font color="red">
																				<a title="查看会员卡" style="cursor:pointer" onclick="doManage('${item.cusID}')" >是</a>
																			</font>
																		</td>
																	</c:if>
																	<c:if test="${item.vip==0}">
																		<td>否</td>
																	</c:if>
																	<td>${item.zongxiaofei}</td>
																	<td><a href="Customer?operator=3&&cusID=${item.cusID}">
																		<input type="button" name="button" class="btn btn82 btn_save2" value="修改"> </a></td>
																	<td><a href="Customer?operator=4&&cusID=${item.cusID}"><input
																			type="button" name="button" class="btn btn82 btn_del"
																			value="删除"> </a></td>
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
																第${currentPage}页&nbsp; 共${totalPage}页&nbsp; <c:if
																	test="${operator==0}">
																	<a
																		href="Customer?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=1">首页</a>
																	<a
																		href="Customer?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=2">上一页</a>
																	<a
																		href="Customer?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=3">下一页</a>
																	<a
																		href="Customer?operator=0&&queryName=${queryName}&&queryValue=${queryValue}&&currentPage=${currentPage}&&pagerMethod=4">尾页</a>
																</c:if> <c:if test="${operator==1}">
																	<a
																		href="Customer?operator=1&&currentPage=${currentPage}&&pagerMethod=1">首页</a>
																	<a
																		href="Customer?operator=1&&currentPage=${currentPage}&&pagerMethod=2">上一页</a>
																	<a
																		href="Customer?operator=1&&currentPage=${currentPage}&&pagerMethod=3">下一页</a>
																	<a
																		href="Customer?operator=1&&currentPage=${currentPage}&&pagerMethod=4">尾页</a>
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