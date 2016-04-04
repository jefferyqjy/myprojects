<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护工资信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function account() {
		var a = document.all.a.value;
		var b = document.all.b.value;
		var c = Number(a) + Number(b);
		document.all.c.value = c;
	}

	function chanage() {

		var x = document.getElementById("empID").value;
		jsSelectItemByValue(document.getElementById("empName"), x);
	}
	function jsSelectItemByValue(objSelect, objItemText) {
		var j = 0;
		for ( var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == objItemText) {
				objSelect.options[i].selected = true;
				j += 1;
				break;
			}
		}
		if (j < 1) {
			alert("ID不存在");
		}
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加工资单</c:if>
														<c:if test="${operatorStr=='modify'}">修改工资单</c:if> <c:if
															test="${operatorStr=='delete'}">删除工资单</c:if>
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
																		action="CheckWage?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<c:if test="${operatorStr=='modify'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">工资单编号：</font>
																					</td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="wID"
																						value="${wage.wID}"></td>
																				</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right" width="300"><font face="隶书">员工编号：</font>
																				</td>
																				<td align="left" width="50"><input type="text"
																					name="empID" value="${wage.empID}" id="empID"
																					onblur="chanage()"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">员工姓名：</font></td>
																				<td align="left"><c:if
																						test="${operatorStr=='modify' || operatorStr=='add'}">
																						<select name="empID" id="empName">
																							<c:forEach var="item" items="${employees}"
																								varStatus="employee">
																								<c:if test="${operatorStr=='add'}">
																									<option value="${item.empID}">${item.empName}</option>
																								</c:if>
																								<c:if test="${operatorStr=='modify'}">
																									<c:if test="${item.empID==wage.empID}">
																										<option value="${item.empID}"
																											selected="selected">${item.empName}</option>
																									</c:if>
																									<c:if test="${item.empID!=wage.empID}">
																										<option value="${item.empID}">${item.empName}</option>
																									</c:if>
																								</c:if>
																							</c:forEach>
																						</select>
																					</c:if></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">月份：</font></td>
																				<td align="left"><input type="text"
																					name="month" value="${wage.month}"
																					onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"><span
																					style="color: red;">例如：2014-11</span></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">每月基本工资：</font>
																				</td>
																				<td align="left"><input type="text"
																					name="basepay" value="${wage.basepay}" id="a"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">提成：</font></td>
																				<td align="left"><input type="text"
																					name="commission" value="${wage.commission}" id="b"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">员工工资：</font></td>
																				<td align="left"><input type="text"
																					name="salary" value="${wage.salary}" id="c"
																					readOnly="readonly"></td>
																				<td align="left"><input type="button"
																					onclick="account()" value="计算"></td>
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
																			<td align="right">工资单编号</td>
																			<td align="left">${wage.wID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">员工编号：</td>
																			<td align="left">${wage.empID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">员工姓名：</td>
																			<td align="left">${wage.empName}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">月份：</td>
																			<td align="left">${wage.month}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">每月基本工资：</td>
																			<td align="left">${wage.basepay}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">提成：</td>
																			<td align="left">${wage.commission}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">员工工资：</td>
																			<td align="left">${wage.salary}</td>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除零件员工工资单"
																			onclick="window.location.href('Wage?operatorStr=${operatorStr}&&wID=${wage.wID}')"
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