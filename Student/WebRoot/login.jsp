<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="<%=basePath %>/sys_css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/javascript/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.captcha').focus(function(){
			$('.yzm-box').show();
			});
			
		$('.captcha').focusout(function(){
			$('.yzm-box').hide();
			});
		})
		
function checkAndSubmit(){
	//document.loginForm.submit();
}

	$(document).ready(function(){
		$(".login-btn").click(function(){
			$("#message-box").hide();
			var userType = $("#loginType").val();
			if(userType == null || ""==userType){
				alert("请选择登录类型");
				return;
			}
			var username = $(".username").val();
			if(username == null || "" == username){
				alert("用户名不能为空");
				return;
			}
			var password = $(".password").val();
			if(password == null || ""==password){
				alert("密码不能为空");
				return;
			}
			$.post("<%=basePath %>/admin/admin_login.do",
					{username:username,password:password,loginType:userType,callType:"ajax"},
					function(data){
						if(typeof data != "undefined" && data != null){
							var ndRoot = data.getElementsByTagName("root")[0];
							var isSuccess = ndRoot.getAttribute("hasError");
							if("N" == isSuccess) {
								document.loginForm.submit();
							}else {
								var ndRoot = data.getElementsByTagName("root")[0];
								var errMsg = ndRoot.getElementsByTagName("errMsg")[0];
								if(document.all){
									$("#message-box").empty().html(errMsg.text);
								}else {
									$("#message-box").empty().html(errMsg.firstChild.nodeValue);
								}
								$("#message-box").show();
								$("#message-box").hide(3000);
							}
						}
						}
					);
			});
		})
</script>

</head>

<body>
<div id="wrap">
	<div id="header"> </div>
    <div id="content-wrap">
    	<div class="space">
    	</div>
   	  <form action="<%=basePath %>/admin/admin_login.do" id="loginForm" method="post" name="loginForm">
   	  <div class="content">
        <div class="field"><label>账　户：</label><input class="username" name="username"  type="text" /></div>
		<div class="field"><label>密　码：</label><input class="password" name="password"  type="password" /><br /></div>
        <div class="field"><label>类     型：</label><select name="loginType" id="loginType">
																<option value=""></option>
																<option value="0">
																	超级管理员
																</option>
																<option value="1">
																	教学管理员
																</option>
																<option value="2">
																	教师
																</option>
																<option value="4">
																	学生用户
																</option>
															</select><br />
        <div class="yzm-box"></div>
        </div>
        <div class="btn"><input name="" type="submit" class="login-btn" value=""/></div>
        <div id="message-box"> 用户名或密码错误！ </div>
      </div></form>
    </div>
    <div id="footer"> </div>
</div>
</body>
</html>
