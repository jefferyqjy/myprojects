<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/partTimeInfo/partTimeInfo.js"></script>
		</head>
<!-- fckeditor -->
<script type="text/javascript" src="<%=basePath%>fckeditor/fckeditor.js"></script>
<script type="text/javascript">
window.onload = function()
{
var sBasePath = "<%=request.getScheme() + "://" + request.getServerName() + ":" + 
request.getServerPort() + request.getContextPath() + "/fckeditor/" %>"
var oFCKeditor = new FCKeditor( 'content' ) ;//content是textarea的name
oFCKeditor.BasePath = sBasePath ;
oFCKeditor.ReplaceTextarea() ;


//var oFCKeditor2 = new FCKeditor( 'remark' ) ;//remark是textarea的name
//oFCKeditor2.BasePath = sBasePath ;
//oFCKeditor2.ReplaceTextarea() ;
}
</script>
		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.edit" module="partTimeInfo"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.edit" module="partTimeInfo"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
			<form action="partTimeInfo/partTimeInfo_update.do" method="post" name="mainform">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
					   <td>
						   <public:i18n key="types" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						   ${result.types}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="title" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						   ${result.title}
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="workadd" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="workadd" name="workadd" rule="CHAR_M_120" value="${result.workadd }"/>
						   <span id="workadd_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="money" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="money" name="money" rule="CHAR_M_120" value="${result.money }"/>
						   <span id="money_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="linkname" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="linkname" name="linkname" rule="CHAR_M_120" value="${result.linkname }"/>
						   <span id="linkname_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="phone" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="phone" name="phone" rule="CHAR_M_120" value="${result.phone }"/>
						   <span id="phone_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						    <label><public:i18n key="content" module="partTimeInfo"></public:i18n></label>
					   </td>
					   <td>
						   
					   </td>
					</tr>
					<tr>
					   
					   <td colspan="2">
						   <textarea class="text-input textarea wysiwyg" id="content" name="content" cols="60" rows="5" rule="CHAR_M_1024">${result.content}</textarea>
						   <span id="content_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="date" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						   <input class="text-input small-input" type="text" id="date" name="date" rule="DATE_M" value="${result.date }"/>
						   <script>
							$("#date").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						    <span id="date_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="username" module="partTimeInfo"></public:i18n>:
					   </td>
					   <td>
						   ${result.username}
					   </td>
					</tr>
					<tr>
					    <td>
						      <public:i18n key="status" module="partTimeInfo"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="status" name="status" value="${result.status }">
						    <option value="正在招聘">正在招聘</option>
						    <option value="招聘结束">招聘结束</option>
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
						    <label><public:i18n key="remark" module="partTimeInfo"></public:i18n></label>
					   </td>
					   <td>
						   <textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule="CHAR_N">${result.remark}</textarea>
						   <span id="remark_errorMsg" class="input-notification png_bg"></span>
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
