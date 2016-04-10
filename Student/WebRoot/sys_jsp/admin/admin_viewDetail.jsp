<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp" %>
<!DOCTYPE html>
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
			var username = document.mainform.username.value;
			if(username == null || "" == username){
				alert("用户名不能为空");
				return;
			}
			var password = document.mainform.password.value;
			if(password == null || "" == password){
				alert("密码不能为空");
				return;
			}
			var cpassword = document.mainform.cpassword.value;
			if(cpassword == null || "" == cpassword) {
				alert("确认密码不能为空");
				return;
			}
			if(password != cpassword) {
				alert("密码和确认密码不一致");
				return;
			}
			document.mainform.submit();
		}
	</script>
  </head>
  
  <body id="page">
  <h2>当前位置：个人信息</h2>
  <form action="admin/admin_add.do" method="post" name="mainform">
    <table id="normalT">
    	<tr>
    		<td>用&nbsp;户&nbsp;名：</td>
    		<td>
    			${result.username }
    		</td>
    	</tr>
    	<tr>
    		<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
    		<td>
    			${result.password }
    		</td>
    	</tr>
    	<tr>
    		<td>真实姓名：</td>
    		<td>
    			${result.name }
    		</td>
    	</tr>
    	<tr>
    		<td>类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
    		<td>
    			学生
    		</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
