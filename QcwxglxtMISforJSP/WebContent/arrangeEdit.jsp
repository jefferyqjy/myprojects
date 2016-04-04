<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护维修安排信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function onchange1(xx) {
		var time = new Date();
		
		$.ajax({
			url : "YuyueInfo",
			data : {
				"yID" : xx,
				"opTime" : time.getTime()
			},
			type : "get",//get方法
			async : false, //同步执行
			dataType : "json",
			success : function(yuyue) {
				if (yuyue.error) {
					alert(yuyue.error);
					$("#cusID").val('');
					$("#cusName").val('');
					$("#carnum").val('');
					$("#carmoder").val('');
					$("#cusphone").val('');
					$("#repairitem").val('');
					$("#yuyuetime").val('');
				} else {
					$("#cusID").val(yuyue.cusID);
					$("#cusName").val(yuyue.cusName);
					$("#carnum").val(yuyue.carnum);
					$("#carmoder").val(yuyue.carmoder);
					$("#cusphone").val(yuyue.cusphone);
					$("#repairitem").val(yuyue.repairitem);
					$("#yuyuetime").val(yuyue.yuyuetime);
				}
			},
			error : function() {
				alert(arguments[1]);
			}
		});
		$.ajax({
			url : "ArrangeInfoyID",
			data : {
				"yID" : xx,
				"opTime" : time.getTime()
			},
			type : "get",//get方法
			async : false, //同步执行
			dataType : "json",
			success : function(yuyue) {
				if (yuyue.error) {
					alert(yuyue.error);
					$("#cusID").val('');
					$("#cusName").val('');
					$("#carnum").val('');
					$("#carmoder").val('');
					$("#cusphone").val('');
					$("#repairitem").val('');
					$("#yuyuetime").val('');
				}
			},
			error : function() {
				alert(arguments[1]);
			}
		});
	}

	function onchange2(xx) {
		var time = new Date();
		$.ajax({
			url : "PartInfo",
			data : {
				"partname" : xx,
				"opTime" : time.getTime()
			},
			type : "post",//get方法
			async : false, //同步执行
			dataType : "json",
			success : function(part) {
				if (part.error) {
					alert(part.error);
					$("#partID").val('');

				} else {
					$("#partID").val(part.partID);
				}
			},
			error : function() {
				alert(arguments[1]);
			}
		});
	}

	function chanage() {

		var x = document.getElementById("empID").value;
		jsSelectItemByValue(document.getElementById("empName"), x);
		jsSelectItemByValue(document.getElementById("empphone"), x);
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
			alert("员工编号不存在");
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加维修安排</c:if>
														<c:if test="${operatorStr=='modify'}">修改维修安排</c:if> <c:if
															test="${operatorStr=='delete'}">删除维修安排</c:if>
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
																		action="CheckArrange?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%" border="0" cellpadding="0"
																			cellspacing="1" bgcolor="#6699CC">
																			<c:if test="${operatorStr=='modify'}">
																				<tr height="30" bgcolor="#FFFFFF">
																					<td align="right"><font face="隶书">维修编号：</font>
																					</td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="admID"
																						value="${arrange.aID}"></td>
																					<td></td>
																					<td></td>
																					<td></td>
																					<td></td>
																				</tr>
																			</c:if>
																			<tr height="30" bgcolor="#FFFFFF">
																				<td align="right"><font face="隶书">员工编号：</font>
																				</td>
																				<td align="left"><input type="text"
																					name="empID"
																					onkeyup="this.value=this.value.replace(/\D/g,'')"
																					value="${arrange.empID}" id="empID"
																					onblur="chanage()"></td>
																				<td align="right"><font face="隶书">员工姓名：</font>
																				</td>
																				<td align="left"><c:if
																						test="${operatorStr=='modify' || operatorStr=='add'}">
																						<select name="empID" id="empName">
																							<c:forEach var="item" items="${employees}"
																								varStatus="employee">
																								<c:if test="${operatorStr=='add'}">
																									<option value="${item.empID}">${item.empName}</option>
																								</c:if>
																								<c:if test="${operatorStr=='modify'}">
																									<c:if test="${item.empID==arrange.empID}">
																										<option value="${item.empID}"
																											selected="selected">${item.empName}</option>
																									</c:if>
																									<c:if test="${item.empID!=arrange.empID}">
																										<option value="${item.empID}">${item.empName}</option>
																									</c:if>
																								</c:if>
																							</c:forEach>
																						</select>
																					</c:if></td>
																				<td align="right"><font face="隶书">员工电话：</font></td>
																				<td align="left"><c:if
																						test="${operatorStr=='modify' || operatorStr=='add'}">
																						<select name="empID" id="empphone">
																							<c:forEach var="item" items="${employees}"
																								varStatus="employee">
																								<c:if test="${operatorStr=='add'}">
																									<option value="${item.empID}">${item.empphone}</option>
																								</c:if>
																								<c:if test="${operatorStr=='modify'}">
																									<c:if test="${item.empID==arrange.empID}">
																										<option value="${item.empID}"
																											selected="selected">${item.empphone}</option>
																									</c:if>
																									<c:if test="${item.empID!=arrange.empID}">
																										<option value="${item.empID}">${item.empphone}</option>
																									</c:if>
																								</c:if>
																							</c:forEach>

																						</select>
																					</c:if></td>
																			</tr>
																			<tr height="30" bgcolor="#FFFFFF">
																				<td align="right"><font face="隶书">预约单编号：</font></td>
																				<td align="left"><input type="text" name="yID"
																					onkeyup="this.value=this.value.replace(/\D/g,'')"
																					value="${arrange.yID}"
																					onblur="onchange1(this.value)"></td>

																				<td align="right"><font face="隶书">客户编号：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="cusID"
																					value="${arrange.cusID}" id="cusID"></td>

																				<td align="right"><font face="隶书">客户姓名：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="cusName"
																					value="${arrange.cusName}" id="cusName"></td>
																			</tr>
																			<tr height="30" bgcolor="#FFFFFF">
																				<td align="right"><font face="隶书">客户电话：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="cusphone"
																					value="${arrange.cusphone}" id="cusphone"></td>

																				<td align="right"><font face="隶书">车牌号：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="carnum"
																					value="${arrange.carnum}" id="carnum"></td>

																				<td align="right"><font face="隶书">车辆类型：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="carmoder"
																					value="${arrange.carmoder}" id="carmoder"></td>
																			</tr>
																			<tr height="30" bgcolor="#FFFFFF">
																				<td align="right"><font face="隶书">修理项目：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="repairitem"
																					value="${arrange.repairitem}" id="repairitem"></td>
																				<td align="right"><font face="隶书">预约时间：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="yuyuetime"
																					value="${arrange.yuyuetime}" id="yuyuetime"></td>
																				<td></td>
																				<td></td>
																			</tr>
																			<tr height="30" bgcolor="#FFFFFF">
																				<td align="right"><font face="隶书">安排时间：</font></td>
																				<td align="left"><input type="text"
																					name="anpaitime" value="${arrange.anpaitime}"
																					onFocus="WdatePicker({minDate:'%y-%M-{%d}'})"
																					id="yuyuetime"></td>
																				<td align="right"><font face="隶书">预计完工时间：</font></td>
																				<td align="left"><input type="text"
																					name="wangongtime" value="${arrange.wangongtime}"
																					onFocus="WdatePicker({minDate:'%y-%M-{%d}'})"
																					id="yuyuetime"></td>
																				<td></td>
																				<td></td>
																			</tr>
																			<tr height="30" bgcolor="#FFFFFF">
																				<td align="right"><font face="隶书">所需零件名称：</font></td>
																				<td align="left"><input type="text"
																					name="partname" value="${arrange.partname}"
																					id="partname" onblur="onchange2(this.value)"></td>
																				<td align="right"><font face="隶书">所需零件编号：</font></td>
																				<td align="left"><input type="text" readonly
																					style="background: #CCFFFF" name="partID"
																					value="${arrange.partID}" id="partID"></td>
																				<td align="right"><font face="隶书">所需零件数量：</font></td>
																				<td align="left"><input type="text"
																					onkeyup="this.value=this.value.replace(/\D/g,'')"
																					name="checknum" value="${arrange.checknum}"
																					id="checknum"></td>
																			</tr>
																			<tr height="30" bgcolor="#FFFFFF">
																				<td></td>
																				<td></td>
																				<td align="right"><input type="submit"
																					class="btn btn82 btn_save2" name="sub" value="确定"></td>
																				<td align="left"><input type="reset" name="res"
																					class="btn btn82 btn_res" value="重填"></td>

																				<td></td>
																				<td></td>
																			</tr>
																		</table>
																	</form>

																</c:if> <c:if test="${operatorStr=='delete'}">
																	<table align="center" border="0" cellpadding="0"
																		cellspacing="1" bgcolor="#6699CC">
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">维修单编号：</td>
																			<td align="left">${arrange.aID}</td>

																			<td align="right">员工编号：</td>
																			<td align="left">${arrange.empID}</td>

																			<td align="right">员工姓名：</td>
																			<td align="left">${arrange.empName}</td>

																			<td align="right">员工电话：</td>
																			<td align="left">${arrange.empphone}</td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">预约单编号：</td>
																			<td align="left">${arrange.yID}</td>

																			<td align="right">客户编号：</td>
																			<td align="left">${arrange.cusID}</td>

																			<td align="right">客户姓名：</td>
																			<td align="left">${arrange.cusName}</td>

																			<td align="right">客户电话：</td>
																			<td align="left">${arrange.cusphone}</td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">车牌号：</td>
																			<td align="left">${arrange.carnum}</td>

																			<td align="right">车辆类型：</td>
																			<td align="left">${arrange.carmoder}</td>

																			<td align="right">修理项目：</td>
																			<td align="left">${arrange.repairitem}</td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">预约时间：</td>
																			<td align="left">${arrange.yuyuetime}</td>

																			<td align="right">安排时间：</td>
																			<td align="left">${arrange.anpaitime}</td>

																			<td align="right">预计完工时间：</td>
																			<td align="left">${arrange.wangongtime}</td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">所需零件编号：</td>
																			<td align="left">${arrange.partID}</td>

																			<td align="right">所需零件名称：</td>
																			<td align="left">${arrange.partname}</td>

																			<td align="right">所需零件数量：</td>
																			<td align="left">${arrange.checknum}</td>
																			<td></td>
																			<td></td>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除维修单"
																			onclick="window.location.href('Arrange?operatorStr=${operatorStr}&&aID=${arrange.aID}')"
																			style="height: 30px; width: 150px;">
																	</p>
																</c:if> <c:if test="${operatorStr=='queryarrange'}">
																	<table align="center" border="0" cellpadding="0"
																		cellspacing="1" bgcolor="#6699CC">
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">维修单编号：</td>
																			<td align="left">${arrange.aID}</td>

																			<td align="right">员工编号：</td>
																			<td align="left">${arrange.empID}</td>

																			<td align="right">员工姓名：</td>
																			<td align="left">${arrange.empName}</td>

																			<td align="right">员工电话：</td>
																			<td align="left">${arrange.empphone}</td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">预约单编号：</td>
																			<td align="left">${arrange.yID}</td>

																			<td align="right">客户编号：</td>
																			<td align="left">${arrange.cusID}</td>

																			<td align="right">客户姓名：</td>
																			<td align="left">${arrange.cusName}</td>

																			<td align="right">客户电话：</td>
																			<td align="left">${arrange.cusphone}</td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">车牌号：</td>
																			<td align="left">${arrange.carnum}</td>

																			<td align="right">车辆类型：</td>
																			<td align="left">${arrange.carmoder}</td>

																			<td align="right">修理项目：</td>
																			<td align="left">${arrange.repairitem}</td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">预约时间：</td>
																			<td align="left">${arrange.yuyuetime}</td>

																			<td align="right">安排时间：</td>
																			<td align="left">${arrange.anpaitime}</td>

																			<td align="right">预计完工时间：</td>
																			<td align="left">${arrange.wangongtime}</td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">所需零件编号：</td>
																			<td align="left">${arrange.partID}</td>

																			<td align="right">所需零件名称：</td>
																			<td align="left">${arrange.partname}</td>

																			<td align="right">所需零件数量：</td>
																			<td align="left">${arrange.checknum}</td>
																			<td></td>
																			<td></td>
																		</tr>
																	</table>
																	<p align="center">
																		<a href="arrange.jsp">返回维修安排管理</a>
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