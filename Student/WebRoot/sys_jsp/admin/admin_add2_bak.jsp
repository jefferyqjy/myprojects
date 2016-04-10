<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp" %>
<%@ page import="com.sys.web.refnum.util.*" %>
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
	$(function(){
		$('tbody tr:odd').addClass("trLight");
		
		$(".select-all").click(function(){
			if($(this).attr("checked")){
				$(".checkBox input[type=checkbox]").each(function(){
					$(this).attr("checked", true);  
					});
				}else{
					$(".checkBox input[type=checkbox]").each(function(){
					$(this).attr("checked", false);  
					});
				}
			});
		
		})
</script>
<style type="text/css">
	body {
		background:#FFF
	}
</style>
  </head>
  
  <body id="page">
  <h2>当前位置：注册会员</h2>
  <form action="admin/admin_add2.do" method="post">
    <table id="normalT">
    	<tr>
    		<td>会&nbsp;员&nbsp;号：</td>
    		<td>
    			<input type="text" name="username" value="<%=RefNumManager.getRefNum("user","id") %>" readonly="readonly"/>
    		</td>
    	</tr>
    	<tr>
    		<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
    		<td>
    			<input type="password" name="password" />
    		</td>
    	</tr>
    	<tr>
    		<td>消费金额：</td>
    		<td>
    		<input type="text" name="doPrice" />
    		</td>
    	</tr>
    	<tr>
    		<td>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
    		<td>
    			<input type="text" name="name" />
    		</td>
    	</tr>
    	<tr>
    		<td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
    		<td>
    		<select name="gender">
    			<option value="男" selected="selected">男</option>
    			<option value="女">女</option>
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
    		<td>
    			<input type="text" name="phone" />
    		</td>
    	</tr>
    	<tr>
    		<td>类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
    		<td>
    			<input type="radio" name="userType" value="2" checked="checked"/>会员
    		</td>
    	</tr>
    	
    	<tr>
    		<td colspan="2">
    			<input type="submit" value="提交" />
    		</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
