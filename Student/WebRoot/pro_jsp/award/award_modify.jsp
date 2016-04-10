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
			<form action="award/award_update.do" method="post">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
						<td>标题</td>
						<td>
							<input type="text" name="title" value="${result.title }" />
						</td>
					</tr>
					<tr>
						<td>内容</td>
						<td>
							<textarea rows="5" cols="50" name="content">${result.content }</textarea>
						</td>
					</tr>
					<tr>
						<td>学号</td>
						<td>
							${result.stuid }
						</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td>
							${result.stuname }
						</td>
					</tr>
					<tr>
						<td>类型</td>
						<td>
							<c:if test="${result.type == '惩罚'}">
								<input type="radio" name="type" value="奖励" />奖励
								<input type="radio" name="type" value="惩罚" checked="checked"/>惩罚
							</c:if>
							<c:if test="${result.type == '奖励'}">
								<input type="radio" name="type" value="奖励"  checked="checked"/>奖励
								<input type="radio" name="type" value="惩罚"/>惩罚
							</c:if>
						</td>
					</tr>
					<tr>
						<td>时间</td>
						<td>
							<input type="text" name="date" value="${result.date }" onfocus="calendar.show(this)"/>
						</td>
					</tr>
					<tr>
						<td>所在班级</td>
						<td>
							${result.clazzname }
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
