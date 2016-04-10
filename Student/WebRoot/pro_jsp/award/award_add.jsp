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
			<div class="pageTitle">当前位置：添加奖惩信息</div>
			<div class="pageInput">
			<form action="award/award_add.do" method="post">
				<table>
					<tr>
						<td>事件标题</td>
						<td>
							<input type="text" name="title" required/>
						</td>
					</tr>
					<tr>
						<td>事件内容</td>
						<td>
							<textarea rows="5" cols="50" name="content"></textarea>
						</td>
					</tr>
					<tr>
						<td>学号</td>
						<td>
						    <input required type="text" name="stuid" id="stuid" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="20"/>
						    <input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/student/student_query2.do','',500,500,1)">
						</td>
					</tr>
					<tr>
						<td>类型</td>
						<td>
							<input type="radio" name="type" value="奖励" checked="checked">奖励
							<input type="radio" name="type" value="惩罚">惩罚
						</td>
					</tr>
					<tr>
						<td>时间</td>
						<td>
							<input type="text" name="date" readonly="readonly" onfocus="calendar.show(this)"/>
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
