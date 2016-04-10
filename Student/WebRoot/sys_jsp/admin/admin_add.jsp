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
  </head>
  
  <body id="page">
  <div id="contentWrap">
  	<div id="widget table-widget">
  	<div class="pageTitle">当前位置：添加教学管理员</div>
  	<div class="pageInput">
		  <form action="admin/admin_add.do" method="post">
		    <table>
		    	<tr>
		    		<td>用&nbsp;户&nbsp;名：</td>
		    		<td>
		    			<input type="text" name="username" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
		    		<td>
		    			<input type="password" name="password" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
		    		<td>
		    			<input type="radio" name="userType" value="1" checked="checked"/>教学管理员
		    		</td>
		    	</tr>
		    	
		    	<tr>
		    		<td colspan="2">
		    			<input type="submit" value="提交" />
		    		</td>
		    	</tr>
		    </table>
		    </form>
		    </div>
		   </div>
	    </div>
  </body>
</html>
