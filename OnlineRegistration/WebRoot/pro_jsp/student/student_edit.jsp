<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/student/student.js"></script>
			<script type="text/javascript">
				//function tijiao(){
					//var selectValue = document.getElementById("shenhe").value;
					//var url = "student/student_update.do?selectValue="+selectValue;
					//self.location.href = encodeURI(url);
				//}
			</script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.edit" module="student"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.edit" module="student"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
			<form action="student/student_update.do" method="post" name="mainform">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
					   <td>
						   <public:i18n key="number" module="student"></public:i18n>:
					   </td>
					   <td>
						   ${result.number}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="name" module="student"></public:i18n>:
					   </td>
					   <td>
						   ${result.name}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="age" module="student"></public:i18n>:
					   </td>
					   <td>
					   		<input name="age" type="text" value="${result.age }" />
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="sex" module="student"></public:i18n>:
					   </td>
					   <td>
					   		<input name="sex" type="text" value="${result.sex }" />
					   </td>
					</tr>
					<tr>
					   <td>
						  <public:i18n key="collage" module="student"></public:i18n>:
					   </td>
					   <td>
					   		<input name="collage" type="text" value="${result.collage }" />
					   </td>
					   <!-- 
					   <td>
						  <input class="text-input small-input" type="text" id="collage" name="collage" rule="CHAR_M_20" value="${result.collage }"/>
						   <span id="collage_errorMsg" class="input-notification png_bg"></span>
					   </td> -->
					</tr>
					<tr>
					   <td>
						  <public:i18n key="className" module="student"></public:i18n>:
					   </td>
					   <td>
					   		<input name="className" type="text" value="${result.className }" />
					   </td>
					   <!-- 
					   <td>
						  <input class="text-input small-input" type="text" id="className" name="className" rule="CHAR_M_20" value="${result.className }"/>
						   <span id="className_errorMsg" class="input-notification png_bg"></span>
					   </td>-->
					</tr>
					<tr>
					   <td>
						  <public:i18n key="examId" module="student"></public:i18n>:
					   </td>
					   <td>
					   		<public:showSelect hql="select e.number, e.number from Exam e" name="examId" empty="true" event="onchange=\"chargeOnchange()\"">
					    	</public:showSelect>
					    	<script>
							//获得后台的值,把相应的select的option选为默认的值
							$(document).ready(function(){
							selectCheckPayStats("examId","${result.examId}");
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
					   </td>
					</tr>
					<!-- 
					<tr>
					   <td>
						  <public:i18n key="examId" module="student"></public:i18n>:
					   </td>
					   <td>
						  <input class="text-input small-input" type="text" id="examId" name="examId" rule="" value="${result.examId }"/>
						   <span id="examId_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr> -->
					<tr>
					   <td>
						   <public:i18n key="money" module="student"></public:i18n>:
					   </td>
					   <td>
						   ${result.money}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="shenhe" module="student"></public:i18n>:
					   </td>
					   <td>
					   		<select class="select" type="text" id="shenhe" name="shenhe">
						    	<option value="P"><public:i18n key="shenhe.pass" module="student"></public:i18n></option>
								<option value="F"><public:i18n key="shenhe.fail" module="student"></public:i18n></option>
					        </select>
					   </td>
					</tr>
					<tr>
					   <td>
								<input class="button" type="button" id="submitButton" name="submitButton" value='<public:i18n key="button.submit" module="common"></public:i18n>'/>
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
