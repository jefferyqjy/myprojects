<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户中心</title>
<link href="Style/style.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	//判断输入的用户名和密码是否合法
	function check() {

		if (login_form.userID.value == "") {
			alert("请输入用户名！");
			return false;
		} else if (login_form.userPassword.value == "") {
			alert("请输入密码！");
			return false;
		} else if (login_form.imageCode.value == "") {
			alert("请输入验证码！");
			return false;
		} else if (login_form.userType.value == "") {

			return false;
		}

		else {
			//设置跳转目的页面
			login_form.action = "LoginCheck";
		}
	}

	function loadimage() {
		//生产随机验证码
		var rand = Math.random();
		//IE存在缓存,需要new Date()实现更换路径的作用
		document.getElementById("randImage").src = "image.jsp?" + rand;

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
			<td height="100%">
				<form name="login_form" method="post" onSubmit="check()">
					<table align=center border="0">
						<tr>
							<td width="50%" align="right">身份：</td>
							<td align="left"><select name="userType">
									<option value="customer">客户</option>
							</select></td>
						</tr>
						<tr>
							<td width="50%" align="right">用户名：</td>
							<td width="50%" align="left"><input type="text"
								name="userID" onMouseOver="this.style.background='#F0DAF3';"
								onMouseOut="this.style.background='#FFFFFF'"></td>
						</tr>
						<tr>
							<td width="50%" align="right">密码：</td>
							<td width="50%" align="left"><input type="password"
								name="userPassword"
								onMouseOver="this.style.background='#F0DAF3';"
								onMouseOut="this.style.background='#FFFFFF'"></td>
						</tr>
						<tr>
							<td width="50%" align="right">验证码:</td>
							<td width="50%" align="left"><input type="text"
								name="imageCode" id="imageCode" size="10"
								onMouseOver="this.style.background='#F0DAF3';"
								onMouseOut="this.style.background='#FFFFFF'" /> &nbsp; <img
								onclick="javascript:loadimage();" title="点击更换图片" name="rand"
								id="rand" src="image.jsp" width="58" height="20" border="1"
								align="middle"></td>
						</tr>
						<tr>
							<td width="50%" align="center" colspan="2"><br> <input
								name="Submit" type="Submit" class="submit1" value="登录"
								style="background-color: #FC6"> &nbsp; <input
								name="Submit2" type="reset" class="submit1" value="重置"
								style="background-color: #FC6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
								href="register.jsp">注册</a></td>
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