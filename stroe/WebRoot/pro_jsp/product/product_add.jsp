<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/product/product.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="product"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="product"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>product/product_add.do" method="post" name="mainform">
					<table>
					<tr>
					    <td>
						    <public:i18n key="nameid" module="product"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="nameid" name="nameid" rule="CHAR_M"/>
						<span id="nameid_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="name" module="product"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="name" name="name" rule="CHAR_M"/>
						<span id="name_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="suppliernaem" module="product"></public:i18n>:
					    </td>
					    <td>
					    	<!--  
						    <input class="text-input small-input" type="text" id="suppliernaem" name="suppliernaem" rule=""/>
						    -->
						    
						    <select class="select" type="text" id="suppliernaem" name="suppliernaem">
						    <option value=""></option>
						    <c:forEach items="${result}" var="record">
						    	<option value="${record.id}">${record.name }</option>
						    </c:forEach>
					        </select>
						<span id="suppliernaem_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <label><public:i18n key="remark" module="product"></public:i18n></label>
					    </td>
					    <td>
						       <textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule=""></textarea>
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
