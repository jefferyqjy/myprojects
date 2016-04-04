<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护客户信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<table width="1200" border="0" align="center"
		style="border-left: 1px solid #7BD676; border-right: 1px solid #7BD676; border-top: 1px solid #7bd676; border-bottom: 1px solid #7BD676;">
		<tr>
			<td colspan="2"><jsp:include flush="true" page="/top.jsp" /></td>
		</tr>
		<tr>
			<td width="150" valign="top"><jsp:include flush="true"
					page="/left_cus.jsp" /></td>
			<td width="1050" align="center" valign="top">
				<table width="1050" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<table width="1050" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="30"><table width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr align="left">
												<td width="15" height="30"><img src="img/tab_03.gif"
													width="15" height="30" /></td>
												<td width="1101" background="img/tab_05.gif"><img
													src="img/311.gif" width="16" height="16" /> <span
													class="STYLE4"> <c:if
															test="${operatorStr=='modify'}">修改个人信息</c:if>
												</span></td>
												<td width="14"><img src="img/tab_07.gif" width="14"
													height="30" /></td>
											</tr>
										</table></td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="9" background="img/tab_12.gif">&nbsp;</td>
												<td bgcolor="#f3ffe3" height="390" valign="top"><table
														width="99%" border="0" align="center" cellpadding="0"
														cellspacing="1" bgcolor="#6699CC" onmouseover="changeto()"
														onmouseout="changeback()">
														<tr bgcolor="#f3ffe3">
															<td><c:if test="${operatorStr=='modify'}">
																	<form name="edit_Form"
																		action="CustomerQuery?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<tr height="30">
																				<td align="right"><font face="隶书">客户编号：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="cusID"
																					value="${customer.cusID}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户姓名：</font>
																				</td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="cusName"
																					value="${customer.cusName}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户电话：</font></td>
																				<td align="left"><input type="text"
																					name="cusphone" value="${customer.cusphone}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">问题：</font></td>
																				<td align="left"><input type="text"
																					name="question" readonly
																					style="background: #CCFFFF"
																					value="${customer.question}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">答案：</font></td>
																				<td align="left"><input type="text"
																					name="answer" value="${customer.answer}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户地址：</font></td>
																				<td align="left"><input type="text"
																					name="cusadd" value="${customer.cusadd}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><input type="submit"
																					class="btn btn82 btn_save2" name="sub" value="确定"></td>
																				<td align="left"><input type="reset" name="res"
																					class="btn btn82 btn_res" value="重填"></td>
																			</tr>
																		</table>
																	</form>

																</c:if>
																<p align="left">
																	<font color="red"><b>${message}</b></font>
																</p></td>
														</tr>
													</table></td>
												<td width="9" background="img/tab_16.gif">&nbsp;</td>
											</tr>
										</table>
									</td>
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
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include flush="true" page="/down.jsp" /></td>
		</tr>
	</table>

</body>
</html>