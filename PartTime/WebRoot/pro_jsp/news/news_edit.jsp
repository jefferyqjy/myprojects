<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/news/news.js"></script>
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
			<h3><public:i18n key="function.edit" module="news"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.edit" module="news"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
			<form action="news/news_update.do" method="post" name="mainform">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
					   <td>
						   <public:i18n key="title" module="news"></public:i18n>:
					   </td>
					   <td>
						   ${result.title}
					   </td>
					</tr>
					<tr>
					   <td>
						    <label><public:i18n key="content" module="news"></public:i18n></label>
					   </td>
					   <td>
						   
					   </td>
					</tr>
					
					<tr>
					 
					   <td colspan="2">
						   <textarea class="text-input textarea wysiwyg" id="content" name="content" cols="60" rows="5" rule="CHAR_M">${result.content}</textarea>
						   <span id="content_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<!-- 
					<tr>
					   <td>
						   <public:i18n key="username" module="news"></public:i18n>:
					   </td>
					   <td>
						   ${result.username}
					   </td>
					</tr>
					 -->
					
					<tr>
					   <td>
						   <public:i18n key="datetime" module="news"></public:i18n>:
					   </td>
					   <td>
						   ${result.datetime}
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
