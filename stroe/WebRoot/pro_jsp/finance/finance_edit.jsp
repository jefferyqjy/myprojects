<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/finance/finance.js"></script>
			
			<script type="text/javascript">
				
				//获得后台的值,把相应的select的option选为默认的值
			$(document).ready(function(){
			 selectCheckPayStats("financetype","${result.financetype}");
			
			 
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
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.edit" module="finance"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.edit" module="finance"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
			<form action="finance/finance_update.do" method="post" name="mainform">
				<table>
				<input type='hidden' name='id' value='${result.id }' />
					<tr>
					    <td>
						      <public:i18n key="financetype" module="finance"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="financetype" name="financetype" value="${result.financetype }">
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
						   ${result.supname}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="custname" module="finance"></public:i18n>:
					   </td>
					   <td>
						   ${result.custname}
					   </td>
					</tr>
					<tr>
					   <td>
						   <public:i18n key="datefin" module="finance"></public:i18n>:
					   </td>
					   <td>
						   ${result.datefin}
					   </td>
					</tr>
					<tr>
					    <td>
						   金额:
					    </td>
					    <td>
					    		
						    <input class="text-input small-input" type="text" id="totalmoney" name="totalmoney" value="${result.totalmoney }" rule="FLOA_M"/>
						    
							<span id="totalmoney_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					   <td>
						    <label><public:i18n key="remark" module="finance"></public:i18n></label>
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
