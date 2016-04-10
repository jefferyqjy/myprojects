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
			<div class="pageTitle">当前位置：添加学生</div>
			<div class="pageInput">
			<form action="student/student_add.do" method="post">
				<table>
					<tr>
						<td>学号</td>
						<td>
							<input type="text" name="stuid" required/>
						</td>
						<td>姓名</td>
						<td>
							<input type="text" name="name" required/>
						</td>
					</tr>
					<tr>
						<td>所属班级</td>
						<td>
						    <input type="text" name="clazzname" id="clazzname" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="40"/>
						    <input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/clazz/clazz_query2.do','',500,500,1)">
						</td>
						<td>是否缴费</td>
						<td>
							<input type="radio" name="ispayment" value="Y"/>是
							<input type="radio" name="ispayment" value="N" checked="checked"/>否
						</td>
					</tr>
					<tr>
						<td>身份证</td>
						<td>
							<input type="text" name="idno" required/>
						</td>
						<td>性别</td>
						<td>
							<input type="radio" name="gender" value="男" checked="checked">男
							<input type="radio" name="gender" value="女">女
						</td>
					</tr>
					<tr>
						<td>年龄</td>
						<td>
							<input type="text" name="age" />
						</td>
						<td>联系地址</td>
						<td>
							<input type="text" name="address" />
						</td>
					</tr>
					<tr>
						<td>政治面貌</td>
						<td>
							<select name="party">
								<option value="群众">群众</option>
								<option value="团员">团员</option>
								<option value="党员">党员</option>
							</select>
						</td>
						<td>联系电话</td>
						<td>
							<input type="text" name="phone" />
						</td>
					</tr>
					<tr>
						<td>能否享受补助</td>
						<td>
							<input type="radio" name="hasbonous" value="Y">是
							<input type="radio" name="hasbonous" value="N" checked="checked">否
						</td>
						<td>能否享受贷款</td>
						<td>
							<input type="radio" name="hasdebit" value="Y">是
							<input type="radio" name="hasdebit" value="N" checked="checked">否
						</td>
					</tr>
					<tr>
						<td>入学时间</td>
						<td>
							<input type="text" name="indate" readonly="readonly" onfocus="calendar.show(this)"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							家庭成员
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<table>
								<tr>
									<td>姓名</td>
									<td>关系</td>
									<td>单位</td>
								</tr>
								<tr>
									<td><input type="text" name="faname1" /></td>
									<td><input type="text" name="farelation1" /></td>
									<td><input type="text" name="fajob1" /></td>
								</tr>
								<tr>
									<td><input type="text" name="faname2" /></td>
									<td><input type="text" name="farelation2" /></td>
									<td><input type="text" name="fajob2" /></td>
								</tr>
								<tr>
									<td><input type="text" name="faname3" /></td>
									<td><input type="text" name="farelation3" /></td>
									<td><input type="text" name="fajob3" /></td>
								</tr>
							</table>
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
