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
			<div class="pageTitle">当前位置：添加</div>
			<div class="pageInput">
			<form action="clazz/clazz_add.do" method="post">
				<table>
					<tr>
						<td>班级名称</td>
						<td>
							<input type="text" name="name" required/>
						</td>
					</tr>
					<tr>
						<td>教师</td>
						<td>
							<input type="text" name="username" id="username" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="40"/>
						    <input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/admin/admin_query3.do','',500,500,1)">
						</td>
					</tr>
					<tr>
						<td>班级描述</td>
						<td>
							<textarea rows="5" cols="50" name="desc"></textarea>
						</td>
					</tr>
					<tr>
						<td>所属院系</td>
						<td>
						    <input type="text" name="school" id="school" required readonly="readonly" style="background-color: #EAEAEA" size="40" maxlength="40"/>
						    <input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/school/school_query2.do','',500,500,1)">
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
