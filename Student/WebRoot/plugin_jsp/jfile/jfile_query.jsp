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
				var url = "jfile/jfile_query.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：查询</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="jfile/jfile_query.do" method="post">
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
						编号
					</th>
					<th>
						文件名
					</th>
					<th>
						上传时间
					</th>
					<th>
						类型
					</th>
					<th>
						大小(Byte)
					</th>
					<th>
						操作
					</th>
				<tbody>
				<c:forEach items="${result}" var="record">
				<tr>
					<td class="checkBox">
						<input name="id" type="checkbox" value="${record.id }" />
					</td>
					<td>
						${record.id }
					</td>
					<td>
						${record.oriname }
					</td>
					<td>
						${record.date }
					</td>
					<td>
						${record.type }
					</td>
					<td>
						${record.size }
					</td>
						<td>
						<c:if test="${record.type == 'image/jpeg' || record.type == 'image/gif' }">
							<a style="cursor: pointer;" onclick="openWin('<%=basePath %>/jfile/jfile_imageView.do?fileName=${record.newname }','',500,500,0)">预览</a>
						</c:if>
						<c:if test="${record.type == 'audio/mp3'}">
							<a style="cursor: pointer;" onclick="openWin('<%=basePath %>/jfile/jfile_musicPlay.do?fileName=${record.newname }&type=${record.type}&fileType=audio','',400,200,0)">试听</a>
						</c:if>
						<c:if test="${ record.type == 'nnot support yet'}">
							<a style="cursor: pointer;" onclick="openWin('<%=basePath %>/jfile/jfile_musicPlay.do?fileName=${record.newname }&type=${record.type}&fileType=video','',500,500,0)">试看</a>
						</c:if>
							<img title="下载" src="<%=basePath%>/sys_css/icon/download.png" width="16" height="16" onclick="actionDo('<%=basePath%>/jfile/jfile_download.do?id=${record.id}')"/>
							<img title="删除" src="<%=basePath%>/sys_css/icon/del.png" width="16" height="16" onclick="del('<%=basePath%>/jfile/jfile_del.do?id=${record.id}')"/>
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
				&nbsp;<img title="删除" src="<%=basePath%>/sys_css/icon/del.png" width="16" height="16" onclick="dels('<%=basePath%>/jfile/jfile_dels.do')"/>
			</div>
			</div></div></div>
		</body>
	</html>
