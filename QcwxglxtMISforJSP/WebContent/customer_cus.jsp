<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维修安排管理</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jstable.js"></script>
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
								name="Customer_form" method="post"></form>
							<table width="1050" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="30">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr align="center" style="font-size: 25px;">
												<td width="15" height="30"><img src="img/tab_03.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_05.gif">个人信息</td>
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
												<td bgcolor="#f3ffe3" height="390" valign="top"><table
														width="99%" border="0" align="center" cellpadding="0"
														cellspacing="1" bgcolor="#6699CC">
														<tr bgcolor="#f3ffe3">
															<td><c:if test="${sessionScope.type =='customer'}">
																	<table width="100%">
																		<tr height="30">
																			<td align="right"><font face="隶书">客户编号：</font></td>
																			<td align="left"><input type="text" readonly
																				style="background: #CCFFFF" name="cusID"
																				value="${customer.cusID}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">客户姓名：</font></td>
																			<td align="left"><input type="text"
																				name="cusName" readonly style="background: #CCFFFF"
																				value="${customer.cusName}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">客户电话：</font></td>
																			<td align="left"><input type="text"
																				name="cusphone" readonly style="background: #CCFFFF"
																				value="${customer.cusphone}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">问题：</font></td>
																			<td align="left"><input type="text"
																				name="question" readonly style="background: #CCFFFF"
																				value="${customer.question}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">答案：</font></td>
																			<td align="left"><input type="text"
																				name="answer" readonly style="background: #CCFFFF"
																				value="${customer.answer}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">客户地址：</font></td>
																			<td align="left"><input type="text"
																				name="cusadd" readonly style="background: #CCFFFF"
																				value="${customer.cusadd}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><a
																				href="CustomerQuery?operator=1&&cusID=${customer.cusID}"><input
																					type="button" name="button"
																					class="btn btn82 btn_save2" value="修改"> </a></td>

																		</tr>
																	</table>
																</c:if></td>
														</tr>
													</table></td>
												<td bgcolor="#f3ffe3" height="390" valign="top"></td>
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
												<td width="1101" background="img/tab_21.gif"></td>
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