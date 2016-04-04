<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护系统用户信息</title>
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加系统用户</c:if>
														<c:if test="${operatorStr=='modify'}">修改系统用户</c:if> <c:if
															test="${operatorStr=='delete'}">删除系统用户</c:if>
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
												<td bgcolor="#f3ffe3" height="390" valign="top"><table width="99%" border="0"
														align="center" cellpadding="0" cellspacing="1"
														bgcolor="#6699CC" onmouseover="changeto()"
														onmouseout="changeback()">
														<tr bgcolor="#f3ffe3">
															<td><c:if test="${operatorStr=='add'}">
																	<form name="edit_Form"
																		action="CheckAdmin?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<tr height="30">
																				<td align="right">
																				<font face="隶书">系统用户姓名：</font>
																				</td>
																				<td align="left">
																				<input type="text" name="admName" value="${admin.admName}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right">
																				<font face="隶书">系统用户密码：</font>
																				</td>
																				<td align="left">
																				<input type="text" name="admpwd" value="${admin.admpwd}">
																				</td>
																			</tr>
																			<tr height="30">
																				<td align="right">
																				<font face="隶书">性别：</font>
																				</td>
																				<td align="left">
																				<select name="sex" class="select"> 
                       															 <option value="男">男</option> 
                       															 <option value="女">女</option> 
                        														</select>
																				</td>
																			</tr>
																			<tr height="30">
																				<td align="right">
																				<font face="隶书">联系电话：</font>
																				</td>
																				<td align="left">
																				<input type="text" name="admphone" value="${admin.admphone}">
																				</td>
																			</tr>
																			<tr height="30">
																				<td align="right">
																				<font face="隶书">家庭地址：</font>
																				</td>
																				<td align="left">
																				<input type="text" name="admadd" value="${admin.admadd}">
																				</td>
																			</tr>
																			<tr height="30">
																				<td align="right">
																				<input type="submit" name="sub" class="btn btn82 btn_save2" value="确定">
																				</td>
																				<td align="left">
																				<input type="reset" name="res" class="btn btn82 btn_res" value="重填">
																				</td>
																			</tr>
																		</table>
																	</form>

																</c:if> <c:if test="${operatorStr=='modify'}">
																	<form name="edit_Form"
																		action="CheckAdmin?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<tr height="30">
																				<td align="right"><font face="隶书">系统用户编号：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="admID"
																					value="${admin.admID}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">系统用户姓名：</font></td>
																				<td align="left"><input type="text" name="admName"
																					value="${admin.admName}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">性别：</font></td>
																				<td align="left"><select name="sex" class="select"> 
                       															 <option value="男">男</option> 
                       															 <option value="女">女</option> 
                        														</select></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">联系电话：</font></td>
																				<td align="left"><input type="text" name="admphone"
																					value="${admin.admphone}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">家庭地址：</font></td>
																				<td align="left"><input type="text" name="admadd"
																					value="${admin.admadd}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><input type="submit" class="btn btn82 btn_save2" name="sub" value="确定"></td>
																				<td align="left"><input type="reset" name="res" class="btn btn82 btn_res" value="重填"></td>
																			</tr>
																		</table>
																</form>

																</c:if> <c:if test="${operatorStr=='delete'}">
																	<table align="center">
																		<tr height="30">
																			<td align="right">系统用户编号：</td>
																			<td align="left">${admin.admID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">系统用户姓名：</td>
																			<td align="left">${admin.admName}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">性别：</td>
																			<td align="left">${admin.sex}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">联系电话：</td>
																			<td align="left">${admin.admphone}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">家庭地址：</td>
																			<td align="left">${admin.admadd}</td>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除系统用户"
																			onclick="window.location.href('Admin?operatorStr=${operatorStr}&&admID=${admin.admID}')"
																			style="height: 30px; width: 150px;">
																	</p>
																</c:if><p align="left">
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