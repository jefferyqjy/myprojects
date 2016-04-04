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
					page="/left.jsp" /></td>
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加客户</c:if>
														<c:if test="${operatorStr=='modify'}">修改客户</c:if> <c:if
															test="${operatorStr=='delete'}">删除客户</c:if>
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
															<td><c:if test="${operatorStr=='modify' || operatorStr=='add'}">
																	<form name="edit_Form"
																		action="CheckCustomer?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<c:if test="${operatorStr=='modify'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">客户编号：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="cusID"
																						value="${customer.cusID}"></td>
																				</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户姓名：</font>
																				</td>
																				<td align="left"><input type="text"
																					name="cusName" value="${customer.cusName}"></td>
																			</tr>
																			<c:if test="${operatorStr=='add'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">客户密码：</font></td>
																					<td align="left"><input type="text"
																						name="cuspwd" value="${customer.cuspwd}"></td>
																				</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户电话：</font></td>
																				<td align="left"><input type="text"
																					name="cusphone" value="${customer.cusphone}"></td>
																			</tr>
																			<c:if test="${operatorStr=='add'}">
																			<tr height="30">
																				<td align="right"><font face="隶书">问题：</font></td>
																				<td align="left"><select name="question">
																						<option value="" selected>请选择一个问题</option>
																						<option value="我的出生地是？">我的出生地是？</option>
																						<option value="我就读过的大学名称是？">我就读过的大学名称是？</option>
																						<option value="我的生日是？">我的生日是？</option>
																						<option value="我最喜欢的休闲运动是？">我最喜欢的休闲运动是？</option>
																						<option value="我最喜欢的一部电影是？">我最喜欢的一部电影是？</option>
																						<option value="我现在居住的城市是？">我现在居住的城市是？</option>
																						<option value="我最喜欢的历史人物是？">我最喜欢的历史人物是？</option>
																				</select></td>
																			</tr>

																			<tr height="30">
																				<td align="right"><font face="隶书">答案：</font></td>
																				<td align="left"><input type="text" name="answer"
																					value="${customer.answer}"></td>
																			</tr>
																			</c:if>
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

																</c:if> <c:if test="${operatorStr=='delete'}">
																	<table align="center">
																		<tr height="30">
																			<td align="right">客户编号：</td>
																			<td align="left">${customer.cusID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户姓名：</td>
																			<td align="left">${customer.cusName}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户电话：</td>
																			<td align="left">${customer.cusphone}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户地址：</td>
																			<td align="left">${customer.cusadd}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">是否会员：</td>
																			<c:if test="${customer.vip==1}">
																				<td align="left">是</td>
																			</c:if>
																			<c:if test="${customer.vip==0}">
																				<td align="left">否</td>
																			</c:if>
																		</tr>
																		<tr height="30">
																			<td align="right">总消费：</td>
																			<td align="left">${customer.zongxiaofei}</td>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除客户"
																			onclick="window.location.href('Customer?operatorStr=${operatorStr}&&cusID=${customer.cusID}')"
																			style="height: 30px; width: 150px;">
																	</p>
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