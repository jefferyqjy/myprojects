<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护会员卡信息</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function doDel(mId) {
	window.location.href = "Membercard?operatorStr=${operatorStr}&&mId=" + mId;
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
													class="STYLE4"> <c:if test="${operatorStr=='add'}"> 增加会员卡</c:if>
														<c:if test="${operatorStr=='modify'}">充值</c:if> <c:if
															test="${operatorStr=='delete'}">删除会员卡</c:if>
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
																	<form name="edit_Form" action="Membercard?operatorStr=${operatorStr}" method="post">
																		<table width="100%">
																			<c:if test="${operatorStr=='modify'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">会员卡ID：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="mId"
																						value="${membercard.mId}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">客户编号：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="cusId"
																						value="${membercard.cusId}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">客户姓名：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="cusName"
																						value="${membercard.cusName}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">会员卡号：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="cardNo"
																						value="${membercard.cardNo}"></td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">开通时间：</font></td>
																					<td align="left"><input type="text" readonly
																						style="background: #CCFFFF" name="createTime"
																						value="${membercard.createTime}"></td>
																				</tr>
																			</c:if>
																			<c:if test="${operatorStr=='add'}">
																				<tr height="30">
																					<td align="right"><font face="隶书">客户编号：</font></td>
																					<td align="left">
																						<input type="text" name="cusId" id="cusId">
																					</td>
																				</tr>
																				<tr height="30">
																					<td align="right"><font face="隶书">开通时间：</font></td>
																					<td align="left">
																						<input type="text" name="createTime" readonly onFocus="WdatePicker({minDate:'%y-%M-{%d} %H:%m:%s'})">
																					</td>
																				</tr>
																			</c:if>
																			<tr height="30">
																				<td align="right"><font face="隶书">金额：</font></td>
																				<td align="left">
																					<input type="text" name="amount" id="amount" value="${membercard.amount }" />
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
																			<td align="right">会员卡ID：</td>
																			<td align="left">${membercard.mId}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户编号：</td>
																			<td align="left">${membercard.cusId}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">客户姓名：</td>
																			<td align="left">${membercard.cusName}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">会员卡号：</td>
																			<td align="left">${membercard.cardNo}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">回访时间：</td>
																			<td align="left">${membercard.createTime}</td>
																		</tr>
																		<tr height="30">
																			<td align="right">金额：</td>
																			<td align="left">${membercard.amount }</td>
																		</tr>
																	</table>
																	<p align="center">
																		<input type=button name="delbut" value="请确认删除会员卡"
																			onclick="doDel('${membercard.mId}')"
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