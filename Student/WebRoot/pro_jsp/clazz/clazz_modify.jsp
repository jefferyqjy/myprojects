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
			<div class="pageTitle">当前位置：修改</div>
			<div class="pageInput">
			<form action="clazz/clazz_update.do" method="post">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
						<td>班级名称</td>
						<td>
							<input type="hidden" name="name" value="${result.name }">
							${result.name }
						</td>
					</tr>
					<tr>
						<td>描述信息</td>
						<td>
							<textarea rows="5" cols="50" name="desc">${result.desc }</textarea>
						</td>
					</tr>
					<tr>
						<td>所属院系</td>
						<td>
						    <input type="text" name="school" id="school" value="${result.school }" required readonly="readonly" style="background-color: #EAEAEA" size="40" maxlength="40"/>
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
