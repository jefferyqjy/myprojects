<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户注册</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function check() {

		if (register.cusName.value == "") {
			alert("请输入姓名！");
			return false;
		} else if (register.cuspwd.value == "") {
			alert("请输入密码！");
			return false;
		} else if (register.cusphone.value == "") {
			alert("请输入电话！");
			return false;
		} else if (register.question.value == "") {
			alert("请选择一个问题！");
			return false;
		} else if (register.answer.value == "") {
			alert("请输入答案！");
			return false;
		} else if (register.cusadd.value == "") {
			alert("请输入地址！");
			return false;
		}

		else {
			//设置跳转目的页面
			register.action = "CustomerAdd";
		}
	}
</script>
</head>
<body>
	<table id=toplogin cellSpacing=0 cellPadding=0 width="1000"
		align=center border=0>
		<tbody>
			<tr>
				<td valign="middle" align=left>

					<div class=jstime style="float: left; width: 70%">
						【<a href="login_cus.jsp">客户登录</a>】【<a href="register.jsp">免费注册</a>】【<a
							href="lost.jsp">忘记密码</a>】 &nbsp;&nbsp;&nbsp;
					</div>
					<div class=jstime
						style="float: right; width: 30%; text-align: right">
						<!--****************时间日历开始****************-->
						<script>
							setInterval(
									"clock.innerHTML=new Date().toLocaleString()+'&nbsp;&nbsp;星期'+'日一二三四五六'.charAt(new Date().getDay());",
									1000)
						</script>
						<span id=clock></span>
						<!--****************时间日历结束****************-->
						&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<table width=100% height="100%" border="0" cellpadding=0 cellspacing=0
		bgcolor="#FFFFFF">
		<tr>
			<td width="50%" rowspan="3" bgcolor="#1254A2">&nbsp;
			<td><object width="780" height="259">
					<embed src="flash/0354-ih.swf" type="application/x-shockwave-flash"
						width="780" height="259"></embed>
				</object></td>
			<td width="1" bgcolor="#000000" rowspan="3"><img
				src="img/spacer.gif" width="1" height="1"></td>
			<td width="50%" rowspan="3" bgcolor="#1254A2">&nbsp;</td>
		</tr>
		<tr height="30">
			<td align=center>
				<!--****************主菜单开始****************--> <a href="index.jsp"
				target="">首 页</a> | <a href="company.jsp" target="">企业概况</a> | <a
				href="login_cus.jsp" target="">客户中心</a> | <a href="login.jsp"
				target="">管理登陆</a> <!--****************主菜单结束****************-->
			</td>

		</tr>
		<tr>
			<td>

				<h2 align="center">客户注册表单</h2>
				<form name="register" method="post" onSubmit="check()">
					<table align="center">
						<tr bgcolor="#CCFF99">
							<td><font face="隶书">客户姓名：</font></td>
							<td><input type="text" name="cusName"
								value="${customer.cusName}"></td>
						</tr>

						<tr bgcolor="#CCFF99">
							<td><font face="隶书">客户密码：</font></td>
							<td><input type="text" name="cuspwd"
								value="${customer.cuspwd}"></td>
						</tr>

						<tr bgcolor="#CCFF99">
							<td><font face="隶书">客户电话：</font></td>
							<td><input type="text" name="cusphone"
								value="${customer.cusphone}"></td>
						</tr>
						<tr bgcolor="#CCFF99">
							<td><font face="隶书">问题：</font></td>
							<td><select name="question">
									<option value="" selected>请选择一个问题</option>
									<option value="我的出生地是？">我的出生地是？</option>
									<option value="我就读过的大学名称是？">我就读过的大学名称是？</option>
									<option value="我的生日是？">我的生日是？</option>
									<option value="我最喜欢的休闲运动是？">我最喜欢的休闲运动是？</option>
									<option value="我最喜欢的一部电影是？">我最喜欢的一部电影是？</option>
									<option value="我现在居住的城市是？">我现在居住的城市是？</option>
									<option value="我最喜欢的历史人物是？">我最喜欢的历史人物是？</option>
							</select></td>
						</tr>

						<tr bgcolor="#CCFF99">
							<td><font face="隶书">答案：</font></td>
							<td><input type="text" name="answer"
								value="${customer.answer}"></td>
						</tr>

						<tr bgcolor="#CCFF99">
							<td><font face="隶书">客户地址：</font></td>
							<td><input type="text" name="cusadd"
								value="${customer.cusadd}"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="submit" value="确定">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="reset" value="重填">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=button  value="返回首页"
								onclick="window.location.href('index.jsp')"
								style=" width: 80px;"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td background="img/bgdown.gif"></td>
			<td height="36" background="img/bgdown.gif" align="center"
				valign="top">
				<div style="padding-top: 6; color: DDEEFF" class="ver10">
					版本2015.v1</div>
			</td>
			<td background="img/bgdown.gif"></td>
			<td background="img/bgdown.gif"></td>
		</tr>
	</table>

</body>
</html>
