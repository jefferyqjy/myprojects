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
				var url = "course/course_query3.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
			function setParent(id) {
				opener.document.getElementById("name").value = id;
				window.opener = null;
				window.close();
			}
			
			function setParent2() {
				var length = $(".courseName").length; 
				if(length <= 0) {
					alert("请选择课程！");
					return;
				}
				var names = [];
				for(var i = 0; i<length; i++) {
					var checked = $(".courseName")[i].checked;
					if(checked) {
						var name = $(".courseName")[i].value;
						names.push(name);
					}
				}
				opener.document.getElementById("names").value = names;
				window.opener = null;
				window.close();
			}
			
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">&nbsp;当前位置：查询课程</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="course/course_query2.do" method="post">
				课程名称：<input type="text" name="name">
				<input name="Submit" type="submit" class="bt" value="查询" />
				<input name="select" type="button" class="bt" value="确认" onclick="setParent2()"/>
				<input type="hidden" name="names" />
			</form>
			<c:choose>
			<c:when test="${empty result}">
			</c:when>
			<c:otherwise>
			<table>
				<thead>
					<th>
						操作
					</th>
					<th>
						课程名称
					</th>
					<th>
						课程描述
					</th>
					</thead>
				<tbody>
				<c:forEach items="${result}" var="record">
				<tr>
					<td>
						<input type="checkbox" class="courseName" name="name-${record.id }" value="${record.name }" /> 
						<!--<a style="cursor: pointer;" onclick="setParent('${record.name}')" />选择</a>-->
					</td>
					<td>
						${record.name }
					</td>
					<td>
						${record.content }
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
