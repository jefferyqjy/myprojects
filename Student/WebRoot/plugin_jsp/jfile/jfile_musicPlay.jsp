<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<!doctype html>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Add</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
	
</script>
	</head>
	<body id="page">
		<div id="contentWrap">
			<div id="widget table-widget">
				<div class="pageTitle">
					当前位置：试听/看
				</div>
				<c:if test="${fileType == 'audio'}">
					<audio src="<%=basePath%>/attachment/${fileName}" controls
						autoplay loop>
					HTML5 audio not supported
					</audio>
				</c:if>
				<c:if test="${fileType == 'video'}">
					<video  src="<%=basePath%>/attachment/${fileName}" controls
						autoplay loop>
					HTML5 video not supported
					</video>
				</c:if>
			</div>
		</div>
	</body>
</html>
