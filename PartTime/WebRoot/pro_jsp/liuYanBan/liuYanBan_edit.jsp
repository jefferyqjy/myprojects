<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/liuYanBan/liuYanBan.js"></script>
		</head>
<!-- fckeditor -->
<script type="text/javascript" src="<%=basePath%>fckeditor/fckeditor.js"></script>
<script type="text/javascript">
window.onload = function()
{
var sBasePath = "<%=request.getScheme() + "://" + request.getServerName() + ":" + 
request.getServerPort() + request.getContextPath() + "/fckeditor/" %>"
//var oFCKeditor = new FCKeditor( 'content' ) ;//content是textarea的name
//oFCKeditor.BasePath = sBasePath ;
//oFCKeditor.ReplaceTextarea() ;


var oFCKeditor2 = new FCKeditor( 'remark' ) ;//remark是textarea的name
oFCKeditor2.BasePath = sBasePath ;
oFCKeditor2.ReplaceTextarea() ;
}
</script>
		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.edit" module="liuYanBan"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.edit" module="liuYanBan"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
			<form action="liuYanBan/liuYanBan_update.do" method="post" name="mainform">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
					   <td>
						   <public:i18n key="title" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   ${result.title}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="lianxiren" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   ${result.lianxiren}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="lianxidianhua" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   ${result.lianxidianhua}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="email" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   ${result.email}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="content" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   ${result.content}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="date" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   ${result.date }
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="username" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   ${result.username}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="status" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						   
						   <select class="select" type="text" id="status" name="status" value ="${result.status}">
						    <option value="未回复">未回复</option>
						    <option value="已回复">已回复</option>
					        </select>
					        <script>
							//获得后台的值,把相应的select的option选为默认的值
							$(document).ready(function(){
							selectCheckPayStats("status","${result.status}");
							});
							//修改select的option默认显示项的方法
							function selectCheckPayStats(id,value)
							{
								//获得下拉列表的id
								var select = document.getElementById(id);
								//获得下拉列表的所有option
								var options = select.options;
								//循环获得对应的节点
								for(var i=0;i<options.length;i++)
								{
									//获得节点的值和后台传来的值进行比较
										if (options[i].value == value)
											{
												//如果当前节点与后台传来的值一致，则将当前节点设置为选中状态，并跳出循环
 												options[i].selected = true;
													break;
											}
								}
							} 
    						</script>
					        
						    <span id="status_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="remark" module="liuYanBan"></public:i18n>:
					   </td>
					   <td>
						  <textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule="CHAR_N_1024"> ${result.remark}</textarea>
					   </td>
					</tr>
					<tr>
					   <td>
								<input class="button" type="button" id="submitButton" name="submitButton" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
					   </td>
					   <td>
								<input class="button" type="button" id="cancelButton" name="cancelButton" value='<public:i18n key="button.cancel" module="common"></public:i18n>' />
					   </td>
					</tr>
				</table>
				<div class="clear"></div>
			</form>
			</div>
			</div>
			</div>
			</div>
		</body>
	</html>
