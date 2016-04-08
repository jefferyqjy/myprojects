<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护留言信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function doDel() {
	window.location.href = "MessageQuery?operatorStr=${operatorStr}&mId=${msg.mId}";
}
</script>
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
													class="STYLE4"><c:if test="${operatorStr=='add'}">添加留言</c:if>
														<c:if test="${operatorStr=='modify'}">修改留言</c:if> <c:if
															test="${operatorStr=='delete'}">删除留言</c:if>
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
															<td><c:if
																	test="${operatorStr=='modify' || operatorStr=='add'}">
																	<form name="edit_Form"
																		action="MessageQuery?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<c:if test="${operatorStr=='modify'}">
																				<tr height="30">
																					<td align="right">留言编号：</td>
																					<td align="left">
																						<input type="text" readonly style="background: #CCFFFF" name="mId" value="${msg.mId}">
																					</td>
																				</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户编号：</font></td>
																				<td align="left">
																					<input type="text" readonly style="background: #CCFFFF" name="cusId" value="${msg.cusId}" id="cusID">
																				</td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">留言内容：</font></td>
																				<c:if test="${operatorStr=='modify'}">
																					<td align="left">
																						<textarea rows="10" cols="30" name="content">${msg.content }</textarea>
																					</td>
																				</c:if>
																				<c:if test="${operatorStr=='add'}">
																					<td align="left">
																						<textarea rows="10" cols="30" name="content" ></textarea>
																					</td>
																				</c:if>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">留言时间：</font></td>
																				<td align="left">
																					<c:if test="${operaotrStr == 'modify' }">
																						<input type="text" name="createTime" readonly value="${msg.createTime}" >
																					</c:if>
																					<c:if test="${operatorStr == 'add' }">
																						<input type="text" name="createTime" readonly onFocus="WdatePicker({minDate:'%y-%M-{%d} %H:%m:%s'})">
																					</c:if>
																				</td>
																			<tr>
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
																			<td align="right">留言编号：</td>
																			<td align="left">${msg.mId}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户编号：</td>
																			<td align="left">${msg.cusId}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">留言内容：</td>
																			<td align="left">${msg.content}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">创建时间：</td>
																			<td align="left">${msg.createTime}</td>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除预约单"
																			onclick="doDel()"
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