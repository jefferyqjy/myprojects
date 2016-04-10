<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html>
		<head>
			<base href="<%=basePath%>">
			<title>Modify</title>
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：修改课程信息</div>
			<div class="pageInput">
			<form action="course/course_update.do" method="post">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
						<td>课程名称</td>
						<td>
							${result.name }
						</td>
					</tr>
					<tr>
						<td>所占学分</td>
						<td>
							<input type="text" name="number" size="4" required value="${result.number }"/>
						</td>
					</tr>
					<tr>
						<td>及格分数线</td> 
						<td>
							<input type="text" name="pass" size="4" required value="${result.pass }"/>
						</td>
					</tr>
					<tr>
						<td>课程描述</td>
						<td>
							<textarea rows="5" cols="50" name="content">${result.content }</textarea>
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
