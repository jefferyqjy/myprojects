<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css">

<!-- jQuery validation engine CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/validate/template.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/validate/validationEngine.jquery.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>

<!-- jQuery validate engine -->
<script src="${pageContext.request.contextPath }/js/validate/jquery.validationEngine.js"></script>
<script src="${pageContext.request.contextPath }/js/validate/jquery.validationEngine-zh_CN.js"></script>
<script src="${pageContext.request.contextPath }/js/validate/other-validations.js"></script>


<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>启蒙幼儿园管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {font-size: 12px; color: #adc9d9; }
-->
</style>

<script type="text/javascript">
$(function() {
	
	// 后台验证若不成功，则获取错误信息并提示
	var error = "${Error}";
	if(error != null && error != "") {
		alert(error);
	}
	
	// 前台简单验证
	$("#submit").on("click", function() {
		var userName = $("input[name='user_name']").val();
		var password = $("input[name='user_password']").val();
		var userType = $("select[name='user_type']").val();
		
		var message = "";
		if(userName == null || userName == "") {
			message = "用户名为空！\n";
		}
		if(password == null || password == "") {
			message += "密码为空！\n";
		}
		if(userType == null || userType == "") {
			message += "身份为空！\n";
		}
		if(message == "") {
			$("button[type='submit']").click();
		} else {
			alert(message);
		}
		return false;
	})	
});
</script>
</head>

<body>
<form name="LoginForm" action="/Nursery/login" method="post">
<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
	<!-- <tr>
		<td bgcolor="#1075b1">&nbsp;</td>
	</tr> -->
	<tr>
		<td height="608" background="images/login_15.jpg" style="background-repeat: no-repeat;">
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<div class="row">
				<div class="col-lg-5"></div>
				<div class="col-lg-2">
					<div class="row" style="margin-top:40%">
			    	<div class="col-lg-4"><label><font color="black">用户名</font></label></div>
				    <div class="col-lg-8">
				    	<input type="text" name="user_name" id="textfield" class="validate[required]">
			    	</div>            
		  		</div>
			  	<br>
			  	<div class="row">
				    <div class="col-lg-4"><label><font color="black">密码</font></label></div>
				    <div class="col-lg-8">
				    	<input type="text" name="user_password" id="textfield2">
			    	</div>            
			  	</div>
			  	<br>
			  	<div class="row">
				    <div class="col-lg-4"><label><font color="black">身份</font></label></div>
				    <div class="col-lg-8">
				    	<select name="user_type">
				    		<option value="">-请选择-</option>
				    		<option value="1">管理员</option>
				    		<option value="2">教师</option>
				    		<option value="3">家长</option>
				    	</select>
			    	</div>            
			  	</div>
		  		<br>
			  	<div class="row" style="margin-bottom:40%">
				    <div class="col-lg-3">
				    	<button class="btn btn-warning" id="submit" >提交</button>
				    	<button class="btn btn-warning" type="submit" style="display:none" ></button>
			    	</div>
				    <div class="col-lg-3">
				    	<button class="btn btn-warning" type="reset" >重置</button>
			    	</div>
			    	<div class="col-lg-6"></div>            
			  	</div>
				</div>
				<div class="col-lg-5"></div>
			</div>
		</td>
	</tr>
	<!-- <tr>
		<td bgcolor="#152753">&nbsp;</td>
	</tr> -->
</table>
</form>
</body>
</html>
