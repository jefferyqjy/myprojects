<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Modify</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function checkAndSubmit() {
			var title = document.mainform.title.value;
			if(title = null || title.trim().length == 0){
				alert("公告标题不能为空");
				return;
			}
			var content = document.mainform.content.value;
			if(content = null || content.trim().length == 0){
				alert("公告内容不能为空");
				return;
			}
			document.mainform.submit();
		}
	</script>
  </head>
  
  <body id="page">
  <div id="contentWrap">
  	<div id="widget table-widget">
  	<div class="pageTitle">当前位置：修改公告</div>
  	<div class="pageInput">
		  <form action="announce/announce_update.do" method="post" name="mainform">
		    <table>
		    	<tr>
		    		<td>标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
		    		<td>
		    			<input type="hidden" name="id" value="${result.id }">
		    			<input type="text" name="title" size="60" required value="${result.title }"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
		    		<td>
		    			<textarea rows="8" cols="40" name="content" required>${result.content }</textarea>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>级&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
		    		<td>
			    		<c:if test="${result.level == 0}">
				    		<input type="radio" name="level" value="0" checked="checked">紧急
			    			<input type="radio" name="level" value="1">中等
			    			<input type="radio" name="level" value="2">低级
			    		</c:if>
		    			<c:if test="${result.level == 1}">
				    		<input type="radio" name="level" value="0">紧急
			    			<input type="radio" name="level" value="1" checked="checked">中等
			    			<input type="radio" name="level" value="2">低级
			    		</c:if>
			    		<c:if test="${result.level == 2}">
				    		<input type="radio" name="level" value="0">紧急
			    			<input type="radio" name="level" value="1">中等
			    			<input type="radio" name="level" value="2" checked="checked">低级
			    		</c:if>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td colspan="2">
		    			<input type="button" onclick="checkAndSubmit()" value="提交" />
		    		</td>
		    	</tr>
		    </table>
		    </form>
		    </div>
		   </div>
	    </div>
  </body>
</html>
