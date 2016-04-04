<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<link href="Style/main.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	function check() {

		if (passwordupdate_form.userPassword.value == "") {
			alert("请输入原密码！");
			return false;
		} else if (passwordupdate_form.userPassword2.value == "") {
			alert("请输入新密码！");
			return false;
		} else if (passwordupdate_form.userPassword3.value == "") {
			alert("请输入重复密码！");
			return false;
		} else if (document.passwordupdate_form.userPassword2.value != document.passwordupdate_form.userPassword3.value) {
			alert("您两次输入的新密码不一致！请重新输入！");
			return false;
		}

		else {
			//设置跳转目的页面
			passwordupdate_form.action = "Passwordupdate";
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
													class="STYLE4"> 修改密码 </span></td>
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
															<td><form name="passwordupdate_form" method="post"
																	onSubmit="check()">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="30" align="right"><span
																				style="color: red;">*</span>请输入原密码：</td>
																			<td align="left"><input name="userPassword" type="password"
																				class="text2" id="userPassword"></td>
																		</tr>
																		<tr>
																			<td height="30" align="right"><span
																				style="color: red;">*</span>请输入新密码：</td>
																			<td align="left"><input name="userPassword2" type="password"
																				class="text2" id="userPassword2"></td>
																		</tr>
																		<tr>
																			<td height="30" align="right"><span
																				style="color: red;">*</span>请重复新密码：</td>
																			<td align="left"><input name="userPassword3" type="password"
																				class="text2" id="userPassword3"></td>
																		</tr>
																		<tr>
																			<td height="30" align="center" colspan="2">&nbsp;
																				<br> <input type="submit" name="button"
																				id="button" value="修改密码"
																				style="height: 30px; width: 90px;"> &nbsp;
																				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <input
																				type="reset" name="button" id="button" value="重置密码"
																				style="height: 30px; width: 90px;">
																			</td>
																		</tr>
																	</table>
																</form></td>
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