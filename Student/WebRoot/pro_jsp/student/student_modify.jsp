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
			<form action="student/student_update.do" method="post">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
						<td>学号</td>
						<td>
							${result.stuid }
						</td>
						<td>姓名</td>
						<td>
							<input type="text" name="name" value="${result.name }" required/>
						</td>
					</tr>
					<tr>
						<td>所属班级</td>
						<td>
						    <input type="text" value="${result.clazzname }" name="clazzname" id="clazzname" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="40"/>
						    <input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/clazz/clazz_query2.do','',500,500,1)">
						</td>
						<td>是否缴费</td>
						<td>
							<c:if test="${result.ispayment == 'Y' }">
								<input type="radio" name="ispayment" value="Y" checked="checked"/>是
								<input type="radio" name="ispayment" value="N"/>否
							</c:if>
							<c:if test="${result.ispayment == 'N' }">
								<input type="radio" name="ispayment" value="Y"/>是
								<input type="radio" name="ispayment" value="N" checked="checked"/>否
							</c:if>
						</td>
					</tr>
					<tr>
						<td>身份证</td>
						<td>
							${result.idno }
						</td>
						<td>性别</td>
						<td>
							<c:if test="${result.gender == '男' }">
								<input type="radio" name="gender" value="男" checked="checked">男
								<input type="radio" name="gender" value="女">女
							</c:if>
							<c:if test="${result.gender == '女' }">
								<input type="radio" name="gender" value="男">男
								<input type="radio" name="gender" value="女" checked="checked">女
							</c:if>
						</td>
					</tr>
					<tr>
						<td>年龄</td>
						<td>
							<input type="text" name="age" value="${result.age }"/>
						</td>
						<td>联系地址</td>
						<td>
							<input type="text" name="address" value="${result.address }"/>
						</td>
					</tr>
					<tr>
						<td>政治面貌</td>
						<td>
							<select name="party">
								<c:if test="${result.party == '群众' }">
									<option value="群众" selected="selected">群众</option>
									<option value="团员">团员</option>
									<option value="党员">党员</option>
								</c:if>
								<c:if test="${result.party == '团员' }">
									<option value="群众">群众</option>
									<option value="团员" selected="selected">团员</option>
									<option value="党员">党员</option>
								</c:if>
								<c:if test="${result.party == '党员' }">
									<option value="群众">群众</option>
									<option value="团员">团员</option>
									<option value="党员" selected="selected">党员</option>
								</c:if>
							</select>
						</td>
						<td>联系电话</td>
						<td>
							<input type="text" name="phone" value="${result.phone }"/>
						</td>
					</tr>
					<tr>
						<td>能否享受补助</td>
						<td>
							<c:if test="${result.hasbonous == 'Y' }">
								<input type="radio" name="hasbonous" value="Y" checked="checked">是
								<input type="radio" name="hasbonous" value="N">否
							</c:if>
							<c:if test="${result.hasbonous == 'N' }">
								<input type="radio" name="hasbonous" value="Y">是
								<input type="radio" name="hasbonous" value="N" checked="checked">否
							</c:if>
						</td>
						<td>能否享受贷款</td>
						<td>
							<c:if test="${result.hasdebit == 'Y' }">
								<input type="radio" name="hasdebit" value="Y" checked="checked">是
								<input type="radio" name="hasdebit" value="N">否
							</c:if>
							<c:if test="${result.hasdebit == 'N' }">
								<input type="radio" name="hasdebit" value="Y">是
								<input type="radio" name="hasdebit" value="N" checked="checked">否
							</c:if>
						</td>
					</tr>
					<tr>
						<td>入学时间</td>
						<td>
							<input type="text" name="indate" readonly="readonly" onfocus="calendar.show(this)" value="${result.indate }"/>
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
									<td><input type="text" name="faname1" value="${result.faname1 }"/></td>
									<td><input type="text" name="farelation1" value="${result.farelation1 }"/></td>
									<td><input type="text" name="fajob1" value="${result.fajob1 }"/></td>
								</tr>
								<tr>
									<td><input type="text" name="faname2" value="${result.faname2 }"/></td>
									<td><input type="text" name="farelation2" value="${result.farelation2 }"/></td>
									<td><input type="text" name="fajob2" value="${result.fajob2 }"/></td>
								</tr>
								<tr>
									<td><input type="text" name="faname3" value="${result.faname3 }"/></td>
									<td><input type="text" name="farelation3" value="${result.farelation3 }"/></td>
									<td><input type="text" name="fajob3" value="${result.fajob3 }"/></td>
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
