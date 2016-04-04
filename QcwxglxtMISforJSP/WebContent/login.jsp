<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车维修管理登录界面</title>
<style type="text/css">
body {
	background-color: #DAD6D7;
}
</style>


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
			alert("请选择登录身份!");
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

	<table width="708" height="435" border="0" align="center"
		cellpadding="0" cellspacing="0" background="img/a.jpg">
		<tr>
			<td>
				<div align="center"
					style="FONT-WEIGHT: bold; FONT-SIZE: 40pt; font-style:italic;color: #0F0;">汽车维修管理系统</div>
			</td>
		</tr>
		<tr>
			<td width="708" height="321">
				<form name="login_form" method="post" onSubmit="check()">
					<table width="410" height="198" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td width="50%" align="right">身份：</td>
							<td align="left"><select name="userType">
									<option value="">请选择登录身份</option>
									<option value="admin">系统用户</option>
									<option value="employee">员工</option>

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
								style="background-color: #FC6"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>