<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.*"%>
<jsp:directive.page import="java.text.SimpleDateFormat" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护销售出库入库单</title>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jstable.js"></script>
<script language="JavaScript">
	function doSearchKind() {
		if (document.all.empID.value == "") {
			alert("请选择员工!");
		} else if (document.all.cusID.value == "") {
			alert("请选择客户!");
		} else {
			window.location.href = "MaintainPartss?operatorStr=1&&empID="
					+ document.all.empID.value + "&&cusID="
					+ document.all.cusID.value + "&&ssdate="
					+ document.all.ssdate.value;
		}
	}
</script>
<script language="JavaScript">
	function doSearchPart() {
		if (document.all.kindID.value == "") {
			alert("请选择零件零件类别!");
		} else {
			window.location.href = "MaintainPartss?operatorStr=2&&kindID="
					+ document.all.kindID.value;
		}
	}
</script>
<script language="JavaScript">
	function doSearchItems() {
		var num = parseInt(document.getElementById("num").value);
		if (document.all.partID.value == "") {
			alert("请选择零件!");
		} else if (document.all.partssprice.value == "") {
			alert("请输入销售出库单价!");
		} else if (num == "NaN") {
			alert("请输入正确的价格!");
		} else if (num <= 0) {
			alert("销售出库单价不能是负数或零!");
		} else if (document.all.partssnum.value == "") {
			alert("请输入销售出库数量!");
		} else if (num == "NaN") {
			alert("请输入正确的数量!");
		} else if (num <= 0) {
			alert("销售出库数量不能是负数或零!");
		} else {
			window.location.href = "MaintainPartss?operatorStr=3&&partID="
					+ document.all.partID.value + "&&partssnum="
					+ document.all.partssnum.value + "&&partssprice="
					+ document.all.partssprice.value;
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
													class="STYLE4"> <c:if test="${p_operatorStr=='add'}">销售出库单</c:if>
														<c:if test="${p_operatorStr=='modify'}">查看销售出库单，销售单编号为:${partrk.partrkID}</c:if>
														<c:if test="${p_operatorStr=='delete'}">销售出库，销售编号为:${partrk.partrkID}</c:if>
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
														cellspacing="1" bgcolor="#6699CC">
														<tr bgcolor="#f3ffe3">
															<td><c:if test="${p_operatorStr=='add'}">
																	<table border="0" align="center" width="1000"
																		cellpadding="0" cellspacing="1" bgcolor="#6699CC">
																		<tr align="center" height="30" bgcolor="#FFFFFF">
																			<td width="360" align="right">请选择员工： <select
																				name="empID" class="select">
																					<c:forEach var="item" items="${employees}"
																						varStatus="employee">
																						<c:if test="${p_operatorStr=='add'}">
																							<option value="${item.empID}">${item.empName}</option>
																						</c:if>
																						<c:if test="${p_operatorStr=='modify'}">
																							<c:if test="${item.empID==partss.empID}">
																								<option value="${item.empID}"
																									selected="selected">${item.empName}</option>
																							</c:if>
																							<c:if test="${item.empID!=partss.empID}">
																								<option value="${item.empID}">${item.empName}</option>
																							</c:if>
																						</c:if>
																					</c:forEach>
																			</select>
																			</td>
																			<td align="right">请选择客户： <select name="cusID" class="select">
																					<c:forEach var="item" items="${customers}"
																						varStatus="customers">
																						<c:if test="${p_operatorStr=='add'}">
																							<option value="${item.cusID}">${item.cusName}</option>
																						</c:if>
																						<c:if test="${p_operatorStr=='modify'}">
																							<c:if test="${item.cusID==partss.cusID}">
																								<option value="${item.cusID}"
																									selected="selected">${item.cusName}</option>
																							</c:if>
																							<c:if test="${item.cusID!=partss.cusID}">
																								<option value="${item.cusID}">${item.cusName}</option>
																							</c:if>
																						</c:if>
																					</c:forEach>
																			</select>
																			</td>
																			<td width="370" align="left" style="color: red;">
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="ext_btn ext_btn_submit"
																				value="确定" onClick="doSearchKind();">请选择员工和客户
																			</td>
																		</tr>

																		<tr height="30" bgcolor="#FFFFFF">
																			<td align="right">请选择零件类别：<select name="kindID"
																				class="select">
																					<c:forEach var="item" items="${categorys}"
																						varStatus="categorys">
																						<option value="${item.kindID}">${item.kindName}</option>
																					</c:forEach>
																			</select>
																			</td>
																			<td align="right">请选择零件：<select
																				name="partID" class="select">
																					<c:forEach var="item" items="${parts}"
																						varStatus="parts">
																						<option value="${item.partID}">${item.partname}</option>
																					</c:forEach>
																			</select>
																			</td>
																			<td align="left" style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																			<input
																				type="button" value="确定"
																				class="ext_btn ext_btn_submit"
																				onClick="doSearchPart();"> 选择零件类别后点击确定，再选择零件</td>
																		</tr>
																		<tr align="center" bgcolor="#FFFFFF" height="40">
																			<td align="right">请输入销售出库单价：<input type="text"
																				id="partssprice" name="partssprice"
																				class="input-text lh25" value="" size="10" />
																			</td>
																			<td align="right">请输入销售出库数量：<input type="text" id="num"
																				name="partssnum" value="" class="input-text lh25"
																				size="10" />
																			</td>
																			<td align="left" style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																				type="button" class="ext_btn ext_btn_submit"
																				value="确定" onClick="doSearchItems();">
																				请输入零件销售出库单价和数量</td>
																		</tr>

																	</table>
																</c:if>
																<p></p> <c:if test="${p_operatorStr=='add'}">
																	<table border="0" align="center" width="1000">
																	<tr height="25" bgcolor="#FFFFFF">
																			<td align="right" width="320"><font face="隶书">员工：</font></td>
																			<td align="left">${partss.empName}</td>
																			<td align="right"><font face="隶书">客户：</font></td>
																			<td align="left" width="400">${partss.cusName}</td>
																		</tr>
																		<tr height="25" bgcolor="#FFFFFF">
																			<td align="right"><font face="隶书">销售出库总金额：</font></td>
																			<td align="left">${partss.partsstotal}（元）</td>
																			<td align="right"><font face="隶书">销售出库时间：</font></td>
																			<td align="left"><input name="ssdate" readonly style="background: #CCFFFF" type="text"
																				value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
																			</td>
																		</tr>
																	</table>
																</c:if> <c:if
																	test="${p_operatorStr=='modify' || p_operatorStr=='delete'}">
																	<table border="0" align="center" width="1000">
																		<tr height="30" bgcolor="#FFFFFF">
																			<td>员工：${partss.empName}</td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td>客户：${partss.cusName}</td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td>销售出库总金额：${partss.partsstotal}（元）</td>
																		</tr>
																		<tr height="30" bgcolor="#FFFFFF">
																			<td>销售出库日期：${partss.ssdate}</td>
																		</tr>
																	</table>
																</c:if>


																<p></p>
																<p align="center">
																	<font size=6>销售出库零件详单</font>
																</p>

																<table border="1" cellspacing="0" align="center" width="100%">

																	<thead>
																		<tr>
																			<th background="img/tab_14.gif">零件编号</th>
																			<th background="img/tab_14.gif">零件名称</th>
																			<th background="img/tab_14.gif">零件规格</th>
																			<th background="img/tab_14.gif">零件包装</th>
																			<th background="img/tab_14.gif">零件类别</th>
																			<th background="img/tab_14.gif">销售出库单价</th>
																			<th background="img/tab_14.gif">销售出库数量</th>
																			<th background="img/tab_14.gif">金额小计</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach var="item" items="${partssmingxis}"
																			varStatus="partssmingxis">
																			<tr>
																				<td>${item.partID}</td>
																				<td>${item.partname}</td>
																				<td>${item.partstandard}</td>
																				<td>${item.partpackaging}</td>
																				<td>${item.kindName}</td>
																				<td>${item.partssprice}</td>
																				<td>${item.partssnum}</td>
																				<td>${item.partsstotalmoney}</td>
																			</tr>
																		</c:forEach>

																		<tr align="right">
																			<td colspan="9">总金额:${partss.partsstotal}元&nbsp;</td>
																		</tr>
																		<c:if
																			test="${p_operatorStr=='add' || p_operatorStr=='delete'}">
																		<tr align="left">
																			<td colspan="9"><c:if
																					test="${p_operatorStr=='add'}">
																					<a href="MaintainPartss?operatorStr=5">销售单审核出库(请注意审核,一旦提交不可更改)</a>
																				</c:if> <c:if test="${p_operatorStr=='delete'}">
																					<a href="MaintainPartss?operatorStr=7">提交需要退货的销售出库单(请注意审核,一旦提交不可更改)</a>
																				</c:if></td>
																		</tr>
																		</c:if>
																	</tbody>
																</table>
																<p align="center">
																	<font color="red"><b>${message}</b></font>
																</p>
																<p align="center">
																	<a href="partss.jsp">返回零件销售管理</a>
																</p>
																</td>
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