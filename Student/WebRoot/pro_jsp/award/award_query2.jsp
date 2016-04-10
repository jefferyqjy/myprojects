<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sys.common.util.Const"%>
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
				var url = "award/award_query2.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：管理奖惩信息</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="award/award_query2.do" method="post">
				类型：<select name="type">
					<option value="">全部</option>
					<option value="奖励">奖励</option>
					<option value="惩罚">惩罚</option>
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
						标题
					</th>
					<th>
						内容
					</th>
					<th>
						学号
					</th>
					<th>
						姓名
					</th>
					<th>
						类型
					</th>
					<th>
						时间
					</th>
					<th>
						所在班级
					</th>
					</thead>
				<tbody>
				<c:forEach items="${result}" var="record">
				<tr>
					<td class="checkBox">
					<input name="id" type="checkbox" value="${record.id }" />
					</td>
					<td>
						${record.title }
					</td>
					<td>
						${record.content }
					</td>
					<td>
						${record.stuid }
					</td>
					<td>
						${record.stuname }
					</td>
					<td>
						${record.type }
					</td>
					<td>
						${record.date }
					</td>
					<td>
						${record.clazzname }
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
			</div></div></div>
		</body>
	</html>
