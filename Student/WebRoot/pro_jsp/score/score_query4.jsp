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
				var url = "score/score_query4.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：成绩排序</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="score/score_query4.do" method="post">
				<fieldset>
					<legend>科目</legend>
					<input type="text" name="name" id="name" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="40"  required/>
					<input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/course/course_query2.do','',500,500,1)">
				</fieldset>
				<fieldset>
					<legend>班级</legend>
					<input type="text" name="clazzname" id="clazzname" readonly="readonly" style="background-color: #EAEAEA" size="20" maxlength="40"  required/>
					<input type="button" class="bt" value="选择..." onclick="openWin('<%=basePath%>/clazz/clazz_query2.do','',500,500,1)">
				</fieldset>
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
					<option value="上">上学期</option>
					<option value="下">下学期</option>
				</select>
				<select name="orderType">
					<option value="desc">降序</option>
					<option value="asc">升序</option>
				</select>
				<input name="Submit" type="submit" class="bt" value="统计" />
				<input type="button" value="打印" onclick="window.print()">
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
						年度
					</th>
					<th>
						学期
					</th>
					<th>
						录入时间
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
						${record.year }
					</td>
					<td>
						${record.type }
					</td>
					<td>
						${record.indate }
					</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</c:otherwise>
			</c:choose>
			</div>
			</div></div>
		</body>
	</html>
