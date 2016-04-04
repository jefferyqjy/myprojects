<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护结账管理信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function onchange1(xx) {
		var time = new Date();
		$.ajax({
			url : "CustomerInfo",
			data : {
				"aID" : xx,
				"opTime" : time.getTime()
			},
			type : "get",
			async : false, //同步执行
			dataType : "json",
			success : function(cus) {
				if (cus.error) {
					alert(cus.error);
					$("#cus").text('');
					$("#vip").text('');
					$("#disc").text('1');
				} else {
					$("#cus").text(cus.cusName);
					if (cus.vip == 1) {
						$("#vip").text("会员");
						$("#disc").text('0.95');
					} else {

						$("#vip").text("非会员");
						$("#disc").text('1');
					}

				}
			},
			error : function() {
				alert(arguments[1]);
			}
		});
		
		$.ajax({
			url : "ArrangeInfo",
			data : {
				"aID" : xx,
				"opTime" : time.getTime()
			},
			type : "get",
			async : false, //同步执行
			dataType : "json",
			success : function(arrange) {
				if (arrange.error) {
					alert(arrange.error);
					$("#partID").val('');
					$("#partname").val('');
					$("#checknum").val('');

				} else {
					$("#partID").val(arrange.partID);
					$("#partname").val(arrange.partname);
					$("#checknum").val(arrange.checknum);

				}
			},
			error : function() {
				alert(arguments[1]);
			}
		});
		$.ajax({
			url : "CheckoutsInfoaID",
			data : {
				"aID" : xx,
				"opTime" : time.getTime()
			},
			type : "get",
			async : false, //同步执行
			dataType : "json",
			success : function(checkouts) {
				if (checkouts.error) {
					alert(checkouts.error);
					$("#partID").val('');
					$("#partname").val('');
					$("#checknum").val('');
				}
			},
			error : function() {
				alert(arguments[1]);
			}
		});
	}
	function account() {
		var a = document.all.repaircost.value;
		var b = document.all.partprice.value;
		var c = Number(a) + Number(b);
		var disc = Number($("#disc").text());
		document.all.c.value = c * disc;
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加结账单</c:if>
														<c:if test="${operatorStr=='modify'}">修改结账单</c:if> <c:if
															test="${operatorStr=='delete'}">删除结账单</c:if>
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
																		action="CheckCheckouts?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																			<tr height="30">
																				<td align="right"><font face="隶书">维修单编号：</font>
																				</td>
																				<td align="left" width="50"><c:if
																						test="${operatorStr=='add'}">
																						<input type="text" name="aID"
																							value="${checkouts.aID}"
																							onkeyup="this.value=this.value.replace(/\D/g,'')"
																							onblur="onchange1(this.value)" />
																					</c:if> <c:if test="${operatorStr=='modify'}">
																						<input type="text" readonly
																							style="background: #CCFFFF" name="aID"
																							value="${checkouts.aID}">
																					</c:if></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">客户信息：</font>
																				</td>
																				<td align="left"><span id="cus"></span>|<span
																					id="vip"></span>|<span id="disc"></span></td>
																				<td align="left"><font color="red">客户姓名|是否会员|折扣</font></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">零件编号：</font></td>
																				<td align="left"><input id="partID" type="text"
																					name="partID" readOnly="readonly"
																					style="background: #CCFFFF"
																					value="${checkouts.partID}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">零件名称：</font></td>
																				<td align="left"><input id="partname"
																					type="text" name="partname" readOnly="readonly"
																					style="background: #CCFFFF"
																					value="${checkouts.partname}"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">零件数量：</font></td>
																				<td align="left"><input id="checknum"
																					type="text" name="checknum" readOnly="readonly"
																					style="background: #CCFFFF"
																					value="${checkouts.checknum}"></td>
																			</tr>
																			<c:if test="${operatorStr=='add'}">
																			<tr height="30">
																				<td align="right"><font face="隶书">零件费：</font></td>
																				<td align="left"><input type="text"
																					onkeyup="this.value=this.value.replace(/\D/g,'')"
																					name="partprice" value="${checkouts.partprice}"
																					id="partprice"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">维修费：</font></td>
																				<td align="left"><input type="text"
																					onkeyup="this.value=this.value.replace(/\D/g,'')"
																					name="repaircost" value="${checkouts.repaircost}"
																					id="repaircost"></td>
																			</tr>
																			</c:if>
																			<c:if test="${operatorStr=='modify'}">
																			<tr height="30">
																				<td align="right"><font face="隶书">零件费：</font></td>
																				<td align="left"><input type="text"
																					readOnly="readonly" style="background: #CCFFFF"
																					name="partprice" value="${checkouts.partprice}"
																					id="partprice"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">维修费：</font></td>
																				<td align="left"><input type="text"
																					readOnly="readonly" style="background: #CCFFFF"
																					name="repaircost" value="${checkouts.repaircost}"
																					id="repaircost"></td>
																			</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right"><font face="隶书">消费：</font></td>
																				<td align="left"><input type="text"
																					name="xiaofei" value="${checkouts.xiaofei}" id="c"
																					readOnly="readonly" style="background: #CCFFFF"></td>
																				<td align="left"><input type="button"
																					onclick="account()" value="计算"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">结账日期：</font></td>
																				<td align="left"><input type="text"
																					name="checkoutsdate"
																					value="${checkouts.checkoutsdate}"
																					onFocus="WdatePicker({minDate:'%y-%M-{%d}'})"></td>
																			</tr>
																			<tr height="30">
																				<td align="right"><font face="隶书">是否缴费：</font></td>
																				<td align="left"><select name="beizhu">
																						<option value="0" selected>未缴费</option>
																						<option value="1">已缴费</option>
																				</select></td>
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
																			<td align="right">结账单编号：</td>
																			<td align="left">${checkouts.cID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">维修单编号：</td>
																			<td align="left">${checkouts.aID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">零件编号：</td>
																			<td align="left">${checkouts.partID}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">零件名称：</td>
																			<td align="left">${checkouts.partname}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">零件数量：</td>
																			<td align="left">${checkouts.checknum}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">零件价格：</td>
																			<td align="left">${checkouts.partprice}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">修理费：</td>
																			<td align="left">${checkouts.repaircost}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">消费：</td>
																			<td align="left">${checkouts.xiaofei}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">结账日期：</td>
																			<td align="left">${checkouts.checkoutsdate}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">备注：</td>
																			<c:if test="${checkouts.beizhu==1}">
																				<td align="left">已缴费</td>
																			</c:if>
																			<c:if test="${checkouts.beizhu==0}">
																				<td align="left">未缴费</td>
																			</c:if>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除结账单"
																			onclick="window.location.href('Checkouts?operatorStr=${operatorStr}&&cID=${checkouts.cID}')"
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