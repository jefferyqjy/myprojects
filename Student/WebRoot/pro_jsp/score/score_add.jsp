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
			<div class="pageTitle">当前位置：录入成绩</div>
			<div class="pageInput">
			<form action="score/score_add.do" method="post">
				<table>
					<tr>
						<td>课程名称</td>
						<td>
						    <input type="text" required name="name" id="name" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="20"/>
						    <input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/course/course_query2.do','',500,500,1)">
						</td>
					</tr>
					<tr>
						<td>成绩</td>
						<td>
							<input type="text" name="score" required/>
						</td>
					</tr>
					<tr>
						<td>学号</td>
						<td>
						    <input type="text" required name="stuno" id="stuid" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="20"/>
						    <input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/student/student_query2.do','',500,500,1)">
						</td>
					</tr>
					<tr>
						<td>年度</td>
						<td>
							<select name="year">
								<option value="2014">2014</option>
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>学期</td>
						<td>
							<input type="radio" name="type" value="上" checked="checked">上学期
							<input type="radio" name="type" value="下">下学期
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
