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
				var url = "courseStudent/courseStudent_query.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
			
			function edit(id) {
				self.location.href = '<%=basePath%>/pro_jsp/courseStudent/courseStudent_select.jsp';
			}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：我的选课</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="courseStudent/courseStudent_query.do" method="post">
				学年：<input type="text" name="year">
				学期：
				<select name="type" >
					<option value="" >-请选择-</option>
					<option value="上" >-上学期-</option>
					<option value="下" >-下学期-</option>
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
					<th>学号</th>
					<th>学年</th>
					<th>学期</th>
					<th>课程名称</th>
					<th>操作</th>
				</thead>
				<tbody>
				<c:forEach items="${result}" var="record">
				<tr>
					<td class="checkBox">
					<input name="id" type="checkbox" value="${record.id }" />
					</td>
					<td>
						${record.stuno }
					</td>
					<td>
						${record.year }
					</td>
					<td>
						${record.type }
					</td>
					<td>
						${record.name }
					</td>
						<td>
							<img title="重选" src="<%=basePath%>/sys_css/icon/edit2.png" width="16" height="16" onclick="edit('${record.id}')"/>
							<img title="删除" src="<%=basePath%>/sys_css/icon/del.png" width="16" height="16" onclick="del('<%=basePath%>/courseStudent/courseStudent_del.do?id=${record.id}')"/>
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
				&nbsp;<img title="删除" src="<%=basePath%>/sys_css/icon/del.png" width="16" height="16" onclick="dels('<%=basePath%>/courseStudent/courseStudent_dels.do')"/>
			</div>
			</div></div></div>
		</body>
	</html>
