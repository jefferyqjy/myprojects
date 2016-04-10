<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin_add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function checkAndSubmit(){
	var realname = document.regform.oldPwd.value;
	if(realname == null || realname == ""){
		alert("原始密码不能为空");
		return;
	}
	var idno = document.regform.newPwd.value;
	if(idno == null || "" == idno){
		alert("新密码不能为空");
		return;
	}
	var phone = document.regform.cnewPwd.value;
	if(phone == null || "" == phone){
		alert("确认密码不能为空");
		return;
	}
	if(idno != phone)  {
		alert("新密码和确认密码不一致");
		return;
	}
	
	
	document.regform.submit();
	}

	$(document).ready(function(){
			$("#oldPwd").blur(function(){
				if(this.value == "") {
					alert("原始密码不能为空");	
				}else {
					$.post("admin/admin_update.do",
							{oldPwd:$("#oldPwd").val(),callType:"ajax",type:"changePwd"},
							function(data){
								showMessageBox(data);
							});
				}
			});
	})
	</script>
  </head>
  
  <body id="page">
   <div id="contentWrap">
  	<div id="widget table-widget">
  	<div class="pageTitle">当前位置：修改密码</div>
  	<div class="pageInput">
  <form action="admin/admin_update.do" method="post" name="regform">
  <input type="hidden" name="type" value="changePwd">
    <table id="normalT">
    	<tr>
    		<td>用&nbsp;户&nbsp;名：</td>
    		<td>
    		<input type="hidden" name="username" value="${result.username }"/>
    		${result.username }
    		</td>
    	</tr>
    	<tr>
    		<td>原始密码：</td>
    		<td>
    			<input type="password" name="oldPwd" id="oldPwd"/>
    		</td>
    	</tr>
    	<tr>
    		<td>新&nbsp;密&nbsp;码：</td>
    		<td>
    			<input type="password" name="newPwd"/>
    		</td>
    	</tr>
    	<tr>
    		<td>确认密码：</td>
    		<td>
    			<input type="password" name="cnewPwd"/>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<input type="button" value="提交修改" onclick="checkAndSubmit()"/>
    			<span id="message-box"></span>
    		</td>
    	</tr>
    </table>
    </form>
    </div></div></div>
  </body>
</html>
