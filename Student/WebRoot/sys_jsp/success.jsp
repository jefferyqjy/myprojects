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
  </head>
<body id="page">
<div id="contentWrap">
  	<div id="widget table-widget">
  	<div class="pageTitle">系统信息</div>
  	<div class="pageColumn">
<form name="mainform" />
<input type="hidden" name="forwardLocation" value="${forwardLocation }"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <c:choose>
	  	<c:when test="${empty forwardLocation }">
	  	<thead>
	  		<th>
		    	操作成功
		    </th>
	  	</thead>
	  	</c:when>
	  	<c:otherwise>
	  		<script type="text/javascript">
	  			alert("操作成功");
	  			window.location.href=document.mainform.forwardLocation.value;
	  		</script>
	  	</c:otherwise>
	  </c:choose>	
	</table>
	<div class="pageButton" align="center">
		     	 <input name="Submit" type="button" value="点击此处返回" onclick="history.go(-1);"/>
	</div>
</form>
</div></div></div>
</body>
</html>
