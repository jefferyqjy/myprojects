<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/employee/employee.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="employee"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="employee"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>employee/employee_add.do" method="post" name="mainform">
					<table>
					<tr>
					    <td>
						    <public:i18n key="nameid" module="employee"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="nameid" name="nameid" rule="CHAR_M"/>
						<span id="nameid_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="name" module="employee"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="name" name="name" rule="CHAR_M"/>
						<span id="name_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <public:i18n key="sex" module="employee"></public:i18n>:
					    </td>
					    <td>
						<input type="radio" name="sex" value="男" rule="RADI_M;男;女" />男
						<input type="radio" name="sex" value="女" rule="RADI_M;男;女" />女
						    <span id="sex_errorMsg" class="input-notification png_bg"></span>
					    </td>
					 </tr>
					<tr>
					    <td>
						    <public:i18n key="datebirth" module="employee"></public:i18n>:
					    </td>
					    <td>
						    
						    
						     <input class="text-input small-input" type="text" id="datebirth" name="datebirth" rule="DATE_M" readonly="readonly"/>
						     <script>
							$("#datebirth").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="datebirth_errorMsg" class="input-notification png_bg"></span>
						    
						<span id="datebirth_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="entityid" module="employee"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="entityid" name="entityid" rule="CHAR_M"/>
						<span id="entityid_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="address" module="employee"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="address" name="address" rule=""/>
						<span id="address_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="tel" module="employee"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="tel" name="tel" rule="INTE_M"/>
						<span id="tel_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="datehire" module="employee"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="datehire" name="datehire" rule=""/>
						<span id="datehire_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <public:i18n key="type" module="employee"></public:i18n>:
					    </td>
					    <td>
						<input type="checkbox" name="type" value="采购员" rule="CHEC_M;采购员;送货员;员工;其他" />采购员
						<input type="checkbox" name="type" value="送货员" rule="CHEC_M;采购员;送货员;员工;其他" />送货员
						<input type="checkbox" name="type" value="员工" rule="CHEC_M;采购员;送货员;员工;其他" />员工
						<input type="checkbox" name="type" value="其他" rule="CHEC_M;采购员;送货员;员工;其他" />其他
						    <span id="type_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <label><public:i18n key="remark" module="employee"></public:i18n></label>
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
