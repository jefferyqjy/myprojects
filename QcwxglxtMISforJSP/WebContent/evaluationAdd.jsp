<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.*"%>
<jsp:directive.page import="java.text.SimpleDateFormat" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护结账管理信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

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
													class="STYLE4">添加评价
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
																		action="CheckoutsQuery?operatorStr=${operatorStr}"
																		method="post">
																		<table width="100%">
																		<tr height="30">
																			<td align="right"><font face="隶书">结账单编号：</font></td>
																			<td align="left"><input type="text" name="cID"
																				value="${checkouts.cID}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">消费：</font></td>
																			<td align="left"><input type="text"
																				name="xiaofei" id="xiaofei" readonly
																				style="background: #CCFFFF"
																				value="${checkouts.xiaofei}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">消费时间：</font></td>
																			<td align="left"><input type="text"
																				name="checkoutsdate" readonly
																				style="background: #CCFFFF" id="checkoutsdate"
																				value="${checkouts.checkoutsdate}"></td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">评价时间：</font></td>
																			<td align="left"><input name="evaluatime" readonly
																				style="background: #CCFFFF" type="text"
																				value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
																			</td>
																		</tr>
																		<tr height="30">
																			<td align="right"><font face="隶书">评价内容：</font></td>
																			<td align="left"><input type="radio"
																				name="evaluation" value="很好" checked>很好 <input
																				type="radio" name="evaluation" value="好">好 <input
																				type="radio" name="evaluation" value="一般">一般
																				<input type="radio" name="evaluation" value="差">差
																				<input type="radio" name="evaluation" value="极差">极差</td>
																		</tr>
																		<tr height="30">
																			<td align="right"><input type="submit"
																				class="btn btn82 btn_save2" name="sub" value="确定"></td>
																			<td align="left"><input type="reset" name="res"
																				class="btn btn82 btn_res" value="重填"></td>
																		</tr>
																	</table>
																	</form>
																	</c:if></td>
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