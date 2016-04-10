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
			<div class="pageTitle">当前位置：修改成绩</div>
			<div class="pageInput">
			<form action="score/score_update.do" method="post">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
						<td>科目</td>
						<td>
							${result.name }
						</td>
					</tr>
					<tr>
						<td>成绩</td>
						<td>
							<input type="text" name="score" value="${result.score }" required/>
						</td>
					</tr>
					<tr>
						<td>学号</td>
						<td>
							${result.stuno }
						</td>
					</tr>
					<tr>
						<td>所在班级</td>
						<td>
							${result.clazzname }
						</td>
					</tr>
					<tr>
						<td>年度</td>
						<td>
							${result.year }
						</td>
					</tr>
					<tr>
						<td>学期</td>
						<td>
							${result.type }学期
						</td>
					</tr>
					<tr>
						<td>录入时间</td>
						<td>
							${result.indate }
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
