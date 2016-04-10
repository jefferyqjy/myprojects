<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>System</title>
    
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
  	<div class="pageTitle">当前位置：公告详情</div>
  	<div class="pageInput">
    <table id="normalT">
    	<tr>
    		<td>标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
    		<td>
    			${result.title }
    		</td>
    	</tr>
    	<tr>
    		<td>内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
    		<td>
    			<textarea rows="10" cols="60" readonly="readonly" style="background-color: #EAEAEA">${result.content }</textarea>
    		</td>
    	</tr>
    	<tr>
    		<td>发布日期：</td>
    		<td>
    			${result.createTime }
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<input type="button" value="关闭" onclick="javascript:windowClose();">
    		</td>
    	</tr>
    </table>
    </div></div></div>
  </body>
</html>
