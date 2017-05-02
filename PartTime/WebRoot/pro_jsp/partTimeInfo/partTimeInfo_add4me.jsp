<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
				<h3><public:i18n key="function.add" module="partTimeInfo"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="partTimeInfo"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>partTimeInfo/partTimeInfo_add4me.do" method="post" name="mainform">
					<table>
					<tr>
					    <td>
						    <public:i18n key="types" module="partTimeInfo"></public:i18n>:
					    </td>
					    <td>
					    <public:showSelect hql="select parttype as newstype1, parttype as newstype2 from PartTimeType" name="types"></public:showSelect>
					    <!-- 
					        <input class="text-input small-input" type="text" id="types" name="types" rule="CHAR_M_120"/>
					     -->
						
						<span id="types_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="title" module="partTimeInfo"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="title" name="title" rule="CHAR_M_120"/>
						<span id="title_errorMsg" class="input-notification png_bg"></span>
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
						       <textarea class="text-input textarea wysiwyg" id="content" name="content" cols="60" rows="5" rule="CHAR_M_1024"></textarea>
						       <span id="content_errorMsg" class="input-notification png_bg"></span>
					     </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="date" module="partTimeInfo"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="date" name="date" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#date").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="date_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<!-- 
					<tr>
					    <td>
						    <public:i18n key="username" module="partTimeInfo"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="username" name="username" rule="CHAR_M"/>
						<span id="username_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					 -->
					
					<tr>
					    <td>
						      <public:i18n key="status" module="partTimeInfo"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="status" name="status">
						    <option value="正在招聘">正在招聘</option>
						    <option value="招聘结束">招聘结束</option>
					        </select>
						    <span id="status_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <label><public:i18n key="remark" module="partTimeInfo"></public:i18n></label>
					    </td>
					    <td>
						       <textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule="CHAR_N"></textarea>
						       <span id="remark_errorMsg" class="input-notification png_bg"></span>
					     </td>
					</tr>
					<tr>
					    <td>
					    </td>
					    <td>
							<input class="button" type="button" name="submitButton" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
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
