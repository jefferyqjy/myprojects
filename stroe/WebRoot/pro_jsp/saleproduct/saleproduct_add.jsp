<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/saleproduct/saleproduct.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="saleproduct"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="saleproduct"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>saleproduct/saleproduct_add.do" method="post" name="mainform">
					<table>
					<tr>
					    <td>
						    <public:i18n key="productname" module="saleproduct"></public:i18n>:
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
						   数量:
					    </td>
					    <td>
					    		
						    <input class="text-input small-input" type="text" id="amount" name="amount" rule="INTE_M"/>
						    
							<span id="amount_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					
					<tr>
					    <td>
						   金额:
					    </td>
					    <td>
					    		
						    <input class="text-input small-input" type="text" id="totalmoney" name="totalmoney" rule="FLOA_M"/>
						    
							<span id="totalmoney_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					
					<tr>
					    <td>
						      <public:i18n key="orderstatus" module="saleproduct"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="orderstatus" name="orderstatus">
						    <option value="配送中">配送中</option>
						    <option value="完成">完成</option>
						    <option value="不需配送">不需配送</option>
					        </select>
						    <span id="orderstatus_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="datesale" module="saleproduct"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="datesale" name="datesale" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#datesale").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="datesale_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <label><public:i18n key="remark" module="saleproduct"></public:i18n></label>
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
