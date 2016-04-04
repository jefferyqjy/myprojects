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
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function onchange1(xx) {
		var time = new Date();
		$.ajax({
			url : "CustometInfoCusID",
			data : {
				"cusID" : xx,
				"opTime" : time.getTime()
			},
			type : "get",//get方法
			async : false, //同步执行
			dataType : "json",
			success : function(customer) {
				if (customer.error) {
					alert(customer.error);
					$("#cusID").val('');
					$("#cusName").val('');
					$("#cusphone").val('');
				} else {
					$("#cusID").val(customer.cusID);
					$("#cusName").val(customer.cusName);
					$("#cusphone").val(customer.cusphone);
				}
			},
			error : function() {
				alert(arguments[1]);
			}
		});
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加预约单</c:if>
														<c:if test="${operatorStr=='modify'}">修改预约单</c:if> <c:if
															test="${operatorStr=='delete'}">删除预约单</c:if>
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
																		action="CheckYuyue?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<c:if test="${operatorStr=='modify'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">预约单编号：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="yID"
																						value="${yuyue.yID}"></td>
																				</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户编号：</font></td>
																				<c:if test="${operatorStr=='modify'}">
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="cusID"
																						value="${yuyue.cusID}" id="cusID"></td>
																				</c:if>
																				<c:if test="${operatorStr=='add'}">
																					<td align="left"><input type="text"
																						name="cusID" value="${yuyue.cusID}"
																						onkeyup="this.value=this.value.replace(/\D/g,'')"
																						id="cusID" onblur="onchange1(this.value)"></td>
																				</c:if>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户姓名：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="cusName"
																					value="${yuyue.cusName}" id="cusName"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户电话：</font></td>
																				<td align="left"><input type="text"
																					name="cusphone" value="${yuyue.cusphone}"
																					id="cusphone"></td>

																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">车牌号：</font></td>
																				<td align="left"><input type="text"
																					name="carnum" value="${yuyue.carnum}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">车辆型号：</font></td>
																				<td align="left"><input type="text"
																					name="carmoder" value="${yuyue.carmoder}"></td>
																			</tr>

																			<tr height="30">
																				<td align="right"><font face="隶书">修理项目：</font></td>
																				<td align="left"><input type="text"
																					name="repairitem" value="${yuyue.repairitem}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">预约时间：</font></td>
																				<td align="left"><input type="text"
																					name="yuyuetime" value="${yuyue.yuyuetime}"
																					onFocus="WdatePicker({minDate:'%y-%M-{%d}'})"></td>
																			<tr height="30">
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
																			<td align="right">预约单编号：</td>
																			<td align="left">${yuyue.yID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户编号：</td>
																			<td align="left">${yuyue.cusID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户姓名：</td>
																			<td align="left">${yuyue.cusName}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户电话：</td>
																			<td align="left">${yuyue.cusphone}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">车牌号：</td>
																			<td align="left">${yuyue.carnum}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">车辆型号：</td>
																			<td align="left">${yuyue.carmoder}</td>
																		</tr>

																		<tr height="30">
																			<td align="right">修理项目：</td>
																			<td align="left">${yuyue.repairitem}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">预约时间：</td>
																			<td align="left">${yuyue.yuyuetime}</td>
																		</tr>

																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除预约单"
																			onclick="window.location.href('Yuyue?operatorStr=${operatorStr}&&yID=${yuyue.yID}')"
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