<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/finance/finance.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="finance"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="finance"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>finance/finance_add.do" method="post" name="mainform">
					<table>
					<tr>
					    <td>
						      <public:i18n key="financetype" module="finance"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="financetype" name="financetype">
						    <option value="付供货商欠款">付供货商欠款</option>
						    <option value="欠供货商货款">欠供货商货款</option>
						    <option value="客户还款">客户还款</option>
						    <option value="客户欠款">客户欠款</option>
						    <option value="其他支出">其他支出</option>
						    <option value="其他收入">其他收入</option>
					        </select>
						    <span id="financetype_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="supname" module="finance"></public:i18n>:
					    </td>
					    <td>
					    	<!-- 
						    <input class="text-input small-input" type="text" id="supname" name="supname" rule=""/>
						     -->
						      <select class="select" type="text" id="supname" name="supname">
						      <option value=""></option>
						    <c:forEach items="${result}" var="record">
						    	<option value="${record.id}">${record.name }</option>
						    </c:forEach>
					        </select>
						<span id="supname_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="custname" module="finance"></public:i18n>:
					    </td>
					    <td>
					      <select class="select" type="text" id="custname" name="custname">
					      <option value=""></option>
						    <c:forEach items="${resultcust}" var="record">
						    	<option value="${record.id}">${record.name }</option>
						    </c:forEach>
					        </select>
					    
					    	<!--  
						    <input class="text-input small-input" type="text" id="custname" name="custname" rule=""/>
						     -->
						<span id="custname_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						     <public:i18n key="datefin" module="finance"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="datefin" name="datefin" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#datefin").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="datefin_errorMsg" class="input-notification png_bg"></span>
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
						      <label><public:i18n key="remark" module="finance"></public:i18n></label>
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
