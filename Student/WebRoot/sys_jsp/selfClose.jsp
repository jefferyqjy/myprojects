<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'base.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>/sys_style/style.css" rel="stylesheet" type="text/css" />
	<script>
	function selfClose() {
		window.opener = null;
		window.close();
	}
</script>
  </head>
<body id="page" onload="setTimeout('selfClose()',3000)">
<h2>系统信息</h2>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		    <th>
		    	<p>操作成功</p>
		    	<p>3秒后自动关闭！</p>
		    	<p>
		     	 <input name="Submit" type="button" class="bt" value="关闭" onclick="selfClose()"/>
		    	</p>
		    </th>
	  </tr>
</table>

</body>
</html>
