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
		<script type="text/javascript">
			function newUploadRow() {
				var table = document.getElementById("normalT");
				var tr = table.insertRow(-1);
				var td1 = null;
				if(document.all){
					td1 = tr.insertCell();
				}else {
					td1 = document.createElement("td");
					tr.appendChild(td1);
				}
				td1.innerHTML = "<input name=\"attach\" type=\"file\" />&nbsp;<input type=\"button\" value=\"继续添加\" onclick=\"newUploadRow()\">"
			}
		</script>
</head>
		<body id="page">
			<div id="contentWrap">
				<div id="widget table-widget">
					<div class="pageTitle">
						当前位置：添加
					</div>
					<div class="pageInput">
						<form action="jfile/jfile_add.do" enctype="multipart/form-data" method="post">
							<table id="normalT">
								<tr>
									<td>
										<input value="上传" type="submit" />
									</td>
								</tr>
								<tr>
									<td>
										<input name="attach" type="file" />
										<input type="button" value="继续添加" onclick="newUploadRow()">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</body>
</html>
