<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html>
		<head>
			<base href="<%=basePath%>">
			<title>Add</title>
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：添加课程</div>
			<div class="pageInput">
			<form action="course/course_add.do" method="post">
				<table>
					<tr>
						<td>课程名称</td>
						<td>
							<input type="text" name="name" required/>
						</td>
					</tr>
					<tr>
						<td>所占学分</td>
						<td>
							<input type="text" name="number" size="4" required/>
						</td>
					</tr>
					<tr>
						<td>及格分数线</td> 
						<td>
							<input type="text" name="pass" size="4" required/>
						</td>
					</tr>
					<tr>
						<td>是否必修</td>
						<td>
							<input type="radio" name="mandatory" value="是" checked="checked">是
							<input type="radio" name="mandatory" value="否">否
						</td>
					</tr>
					<tr>
						<td>课程描述</td>
						<td>
							<textarea rows="5" cols="50" name="content"></textarea>
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
