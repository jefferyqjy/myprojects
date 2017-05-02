<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/takeOffice/takeOffice.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.edit" module="takeOffice"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.edit" module="takeOffice"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
			<form action="takeOffice/takeOffice_update.do" method="post" name="mainform">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
					   <td>
						   <public:i18n key="parttimetitle" module="takeOffice"></public:i18n>:
					   </td>
					   <td>
						   ${result.parttimetitle}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="stuname" module="takeOffice"></public:i18n>:
					   </td>
					   <td>
						   ${result.stuname}
					   </td>
					</tr>
					<tr>
					    <td>
						      <public:i18n key="status" module="takeOffice"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="status" name="status" value="${result.status }">
						    <option value="兼职中">兼职中</option>
						    <option value="兼职结束">兼职结束</option>
					        </select>
					        
					        <script>
							//获得后台的值,把相应的select的option选为默认的值
							$(document).ready(function(){
							selectCheckPayStats("status","${result.status}");
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
						    <span id="status_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<!-- 
					<tr>
					   <td>
						   <public:i18n key="relname" module="takeOffice"></public:i18n>:
					   </td>
					   <td>
						   ${result.relname}
					   </td>
					</tr>
					 -->
					
					<tr>
					   <td>
						   <public:i18n key="phone" module="takeOffice"></public:i18n>:
					   </td>
					   <td>
						   ${result.phone}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="datefrom" module="takeOffice"></public:i18n>:
					   </td>
					   <td>
						   <input class="text-input small-input" type="text" id="datefrom" name="datefrom" rule="DATE_N" value="${result.datefrom }"/>
						   <script>
							$("#datefrom").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						    <span id="datefrom_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="dateto" module="takeOffice"></public:i18n>:
					   </td>
					   <td>
						   <input class="text-input small-input" type="text" id="dateto" name="dateto" rule="DATE_N" value="${result.dateto }"/>
						   <script>
							$("#dateto").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						    <span id="dateto_errorMsg" class="input-notification png_bg"></span>
					   </td>
					</tr>
					<tr>
					   <td>
						    <label><public:i18n key="remark" module="takeOffice"></public:i18n></label>
					   </td>
					   <td>
						   <textarea class="text-input textarea wysiwyg" id="remark" name="remark" cols="60" rows="5" rule="CHAR_N">${result.remark}</textarea>
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
