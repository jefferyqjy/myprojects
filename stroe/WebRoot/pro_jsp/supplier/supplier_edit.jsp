<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/supplier/supplier.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.edit" module="supplier"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.edit" module="supplier"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
			<form action="supplier/supplier_update.do" method="post" name="mainform">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
					   <td>
						   <public:i18n key="nameid" module="supplier"></public:i18n>:
					   </td>
					   <td>
						   ${result.nameid}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="name" module="supplier"></public:i18n>:
					   </td>
					   <td>
						   ${result.name}
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="address" module="supplier"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="address" name="address" rule="" value="${result.address }"/>
						   <span id="address_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="tel" module="supplier"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="tel" name="tel" rule="CHAR_M" value="${result.tel }"/>
						   <span id="tel_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="type" module="supplier"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="type" name="type" rule="" value="${result.type }"/>
						   <span id="type_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						    <label><public:i18n key="remark" module="supplier"></public:i18n></label>
					   </td>
					   <td>
						   <textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule="">${result.remark}</textarea>
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
