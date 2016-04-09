<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护回访信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function doDel(rId) {
	window.location.href = "ReturnVisit?operatorStr=${operatorStr}&&rId=" + rId;
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加回访记录</c:if>
														<c:if test="${operatorStr=='modify'}">修改回访记录</c:if> <c:if
															test="${operatorStr=='delete'}">删除回访记录</c:if>
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
															<td>
																<c:if test="${operatorStr=='modify' || operatorStr=='add'}">
																	<form name="edit_Form" action="ReturnVisit?operatorStr=${operatorStr}" method="post">
																		<table width="100%">
																			<c:if test="${operatorStr=='modify'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">回访记录编号：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="rId"
																						value="${returnvisit.rId}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">员工编号：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="empId"
																						value="${returnvisit.empId}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">员工姓名：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="empName"
																						value="${returnvisit.empName}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">维修单编号：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="cId"
																						value="${returnvisit.cId}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">回访时间：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="cId"
																						value="${returnvisit.createTime}"></td>
																				</tr>
																			</c:if>
																			<c:if test="${operatorStr=='add'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">员工编号：</font></td>
																					<td align="left">
																						<input type="text" name="empId" id="empId">
																					</td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">维修单编号：</font></td>
																					<td align="left">
																						<input type="text" name="cId" value="${cId }" readonly>
																					</td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">回访时间：</font></td>
																					<td align="left">
																						<input type="text" name="createTime" readonly onFocus="WdatePicker({minDate:'%y-%M-{%d} %H:%m:%s'})">
																					</td>
																				</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right"><font face="隶书">回访内容：</font></td>
																				<td align="left">
																					<textarea rows="10" cols="30" name="content">${returnvisit.content }</textarea>
																				</td>
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
																<c:if test="${operatorStr=='delete'}">
																	<table align="center">
																		<tr height="30">
																			<td align="right">回访记录编号：</td>
																			<td align="left">${returnvisit.rId}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">回访员工编号：</td>
																			<td align="left">${returnvisit.empId}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">员工姓名：</td>
																			<td align="left">${returnvisit.empName}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">维修单编号：</td>
																			<td align="left">${returnvisit.cId}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">回访时间：</td>
																			<td align="left">${returnvisit.createTime}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">回访内容：</td>
																			<td align="left">${returnvisit.content}</td>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除回访记录"
																			onclick="doDel('${returnvisit.rId}')"
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