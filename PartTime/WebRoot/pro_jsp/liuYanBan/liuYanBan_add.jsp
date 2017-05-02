<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/liuYanBan/liuYanBan.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="liuYanBan"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="liuYanBan"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>liuYanBan/liuYanBan_add.do" method="post" name="mainform">
					<table>
					<tr>
					    <td>
						    <public:i18n key="title" module="liuYanBan"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="title" name="title" rule="CHAR_M_1024"/>
						<span id="title_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="lianxiren" module="liuYanBan"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="lianxiren" name="lianxiren" rule="CHAR_M_1024"/>
						<span id="lianxiren_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="lianxidianhua" module="liuYanBan"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="lianxidianhua" name="lianxidianhua" rule="CHAR_M_1024"/>
						<span id="lianxidianhua_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="email" module="liuYanBan"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="email" name="email" rule="CHAR_M_1024"/>
						<span id="email_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <label><public:i18n key="content" module="liuYanBan"></public:i18n></label>
					    </td>
					    <td>
						       <textarea class="text-input textarea wysiwyg" id="content" name="content" cols="60" rows="5" rule="CHAR_M_1024"></textarea>
						       <span id="content_errorMsg" class="input-notification png_bg"></span>
					     </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="date" module="liuYanBan"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="date" name="date" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#date").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="date_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="username" module="liuYanBan"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="username" name="username" rule="CHAR_M"/>
						<span id="username_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <public:i18n key="status" module="liuYanBan"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="status" name="status">
						    <option value="未回复">未回复</option>
						    <option value="已回复">已回复</option>
					        </select>
						    <span id="status_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <label><public:i18n key="remark" module="liuYanBan"></public:i18n></label>
					    </td>
					    <td>
						       <textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule="CHAR_N_1024"></textarea>
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
