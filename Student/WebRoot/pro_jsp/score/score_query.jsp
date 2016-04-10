<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html>
		<head>
			<base href="<%=basePath%>">
			<title>Query</title>
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">

		<script type="text/javascript">
			function toPage(pageNum){
				var url = "score/score_query.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：查询</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="score/score_query.do" method="post">
				科目：<input type="text" name="name">
				学号：<input type="text" name="stuno"> 
				班级：<input type="text" name="clazzname">
				院系：<input type="text" name="school">
				年度：<select name="year">
								<option value="2014">2014</option>
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
						</select>
				学期：<select name="type">
					<option value="">全部</option>
					<option value="上">上学期</option>
					<option value="下">下学期</option>
				</select>
				<input name="Submit" type="submit" class="bt" value="查询" />
			</form>
			<c:choose>
			<c:when test="${empty result}">
			</c:when>
			<c:otherwise>
			<table>
				<thead>
					<th width="25">
						<input class="select-all" name="" type="checkbox" value="" />
					</th>
					<th>
						课程名称
					</th>
					<th>
						成绩
					</th>
					<th>
						学号
					</th>
					<th>
						班级
					</th>
					<th>
						院系
					</th>
					<th>
						年度
					</th>
					<th>
						学期
					</th>
					<th>
						录入时间
					</th>
					<th>
						操作
					</th>
					</thead>
				<tbody>
				<c:forEach items="${result}" var="record">
				<tr>
					<td class="checkBox">
					<input name="id" type="checkbox" value="${record.id }" />
					</td>
					<td>
						${record.name }
					</td>
					<td>
						${record.score }
					</td>
					<td>
						${record.stuno }
					</td>
					<td>
						${record.clazzname }
					</td>
					<td>
						${record.school }
					</td>
					<td>
						${record.year }
					</td>
					<td>
						${record.type }
					</td>
					<td>
						${record.indate }
					</td>
						<td>
							<c:if test="${record.hasapply == 'N'}">
								<a onclick="mdy('<%=basePath%>/score/score_update2.do?id=${record.id}')">申请修改</a>
							</c:if>
							<c:if test="${record.hasapply == 'Y'}">
								<font color="red">正在审核修改要求</font>
							</c:if>
							<c:if test="${record.ismdy == 'Y'}">
								<img title="修改" src="<%=basePath%>/sys_css/icon/edit2.png" width="16" height="16" onclick="mdy('<%=basePath%>/score/score_modify.do?id=${record.id}')"/>
							</c:if>
							<img title="删除" src="<%=basePath%>/sys_css/icon/del.png" width="16" height="16" onclick="del('<%=basePath%>/score/score_del.do?id=${record.id}')"/>
						</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</c:otherwise>
			</c:choose>
			<div class="pageButton">
				${pageInfo }
			</div>
			<div>
				&nbsp;<img title="删除" src="<%=basePath%>/sys_css/icon/del.png" width="16" height="16" onclick="dels('<%=basePath%>/score/score_dels.do')"/>
			</div>
			</div></div></div>
		</body>
	</html>
