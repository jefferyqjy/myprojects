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
			<div class="pageTitle">当前位置：学生详情</div>
			<div class="pageInput">
				<table>
					<tr>
						<td>学号</td>
						<td>
							${result.stuid }
						</td>
						<td>姓名</td>
						<td>
							${result.name }
						</td>
					</tr>
					<tr>
						<td>所属班级</td>
						<td>
						   ${result.clazzname }
						</td>
						<td>是否缴费</td>
						<td>
							${result.ispayment }
						</td>
					</tr>
					<tr>
						<td>身份证</td>
						<td>
							${result.idno }
						</td>
						<td>性别</td>
						<td>
							${result.gender }
						</td>
					</tr>
					<tr>
						<td>年龄</td>
						<td>
							${result.age }
						</td>
						<td>联系地址</td>
						<td>
							${result.address }
						</td>
					</tr>
					<tr>
						<td>政治面貌</td>
						<td>
							${result.party }
						</td>
						<td>联系电话</td>
						<td>
							${result.phone }
						</td>
					</tr>
					<tr>
						<td>能否享受补助</td>
						<td>
							${result.hasbonous }
						</td>
						<td>能否享受贷款</td>
						<td>
							${result.hasdebit }
						</td>
					</tr>
					<tr>
						<td>入学时间</td>
						<td>
							${result.indate }
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
									<td>${result.faname1 }</td>
									<td>${result.farelation1 }</td>
									<td>${result.fajob1 }</td>
								</tr>
								<tr>
									<td>${result.faname2 }</td>
									<td>${result.farelation2 }</td>
									<td>${result.fajob2 }</td>
								</tr>
								<tr>
									<td>${result.faname3 }</td>
									<td>${result.farelation3 }</td>
									<td>${result.fajob3 }</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			</div>
			</div>
		</body>
	</html>
