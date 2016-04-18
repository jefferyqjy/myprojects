<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/orderproduct/orderproduct.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="orderproduct"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="orderproduct"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>orderproduct/orderproduct_add.do" method="post" name="mainform">
					<table>
					<tr>
					    <td>
						    <public:i18n key="orderid" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="orderid" name="orderid" rule="CHAR_M"/>
						<span id="orderid_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <public:i18n key="orderstatus" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="orderstatus" name="orderstatus">
						    <option value="下订单">下订单</option>
						    <option value="订单完成">订单完成</option>
						    <option value="订单作废">订单作废</option>
					        </select>
						    <span id="orderstatus_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="dateorder" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="dateorder" name="dateorder" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#dateorder").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="dateorder_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="datefinished" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="datefinished" name="datefinished" readonly="readonly"/>
						     <script>
							$("#datefinished").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="datefinished_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="productname" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
					    	<!-- 
						    <input class="text-input small-input" type="text" id="productname" name="productname" rule="CHAR_M"/>
						     -->
						     <select class="select" type="text" id="productname" name="productname">
						    <c:forEach items="${resultpro}" var="record">
						    	<option value="${record.id}">${record.name }</option>
						    </c:forEach>
					        </select>
						<span id="productname_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="suppliernaem" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
					    <!-- 
						    <input class="text-input small-input" type="text" id="suppliernaem" name="suppliernaem" rule=""/>
						   -->
						     <select class="select" type="text" id="suppliernaem" name="suppliernaem">
						    <c:forEach items="${result}" var="record">
						    	<option value="${record.id}">${record.name }</option>
						    </c:forEach>
					        </select>
						<span id="suppliernaem_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="datebirth" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="datebirth" name="datebirth" readonly="readonly"/>
						     <script>
							$("#datebirth").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="datebirth_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="datequality" module="orderproduct"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="datequality" name="datequality" readonly="readonly"/>
						     <script>
							$("#datequality").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="datequality_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					
					
					<tr>
					    <td>
						   数量:
					    </td>
					    <td>
					    		
						    <input class="text-input small-input" type="text" id="amount" name="amount" rule="INTE_M"/>
						    
							<span id="amount_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					
					
					<tr>
					    <td>
						      <label><public:i18n key="remark" module="orderproduct"></public:i18n></label>
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
