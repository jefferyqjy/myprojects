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
				var url = "score/score_query6.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：查询成绩修改申请</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="score/score_query6.do" method="post">
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
							<a onclick="mdy('<%=basePath%>/score/score_update3.do?id=${record.id}')">通过</a>
							/<a onclick="mdy('<%=basePath%>/score/score_update4.do?id=${record.id}')">拒绝</a>
						</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</c:otherwise>
			</c:choose>
			</div></div></div>
		</body>
	</html>
