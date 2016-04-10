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
  <h2>当前位置：会员购物</h2>
  <form action="admin/admin_buy.do" method="post">
    <table id="normalT">
    	<tr>
    		<td>会&nbsp;员&nbsp;号：</td>
    		<td>
    			<input type="text" name="username" />
    		</td>
    	</tr>
    	<tr>
    		<td>购物金额：</td>
    		<td>
    			<input type="text" name="doPrice" />
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
