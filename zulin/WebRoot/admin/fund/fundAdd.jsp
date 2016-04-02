<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
        
        <script language="javascript">
            function c() {
                document.formAdd.submit();
            }
            
            // landlord can only enter fund creating page through contract list page, so here we directly return to contract list page
            function backToList() {
            	window.location.href="<%=path %>/contractMana.action";
            }
            
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/img/allbg.gif'>
			<form action="<%=path %>/fundAddSave.action" name="formAdd" method="post">
					<input type="hidden" name="contractId" value="${contractId }">
					<input type="hidden" name="id" value="${id }">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#E7E7E7">
							<td height="14" colspan="4" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 合同编号：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<label>${contractId }</label>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 类型：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<select name="type" id="type" >
						    		<option value=""  <c:if test="${empty fund.type }">selected="selected"</c:if>>-请选择-</option>
						    		<option value="0" <c:if test="${fund.type eq 0}">selected="selected"</c:if>>定金</option>
						    		<option value="1" <c:if test="${fund.type eq 1}">selected="selected"</c:if>>押金</option>
						    		<option value="2" <c:if test="${fund.type eq 2}">selected="selected"</c:if>>租金</option>
						    	</select>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 金额：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<input type="text" name="money" value="${fund.money }" />
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 是否退款：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<select name="payType" >
						    		<option value=""  <c:if test="${empty fund.payType }">selected="selected"</c:if>>-请选择-</option>
						    		<option value="0" <c:if test="${fund.payType eq 0}">selected="selected"</c:if>>否</option>
						    		<option value="1" <c:if test="${fund.payType eq 1}">selected="selected"</c:if>>是</option>
						    	</select>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 状态：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<select name="status" >
						    		<option value=""  <c:if test="${empty fund.status }">selected="selected"</c:if>>-请选择-</option>
						    		<option value="0" <c:if test="${fund.status eq 0}">selected="selected"</c:if>>未缴纳</option>
						    		<option value="1" <c:if test="${fund.status eq 1}">selected="selected"</c:if>>已缴纳</option>
						    	</select>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 发票号：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<input type="text" name="receiptNumber" value="${fund.receiptNumber }" />
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						       <input type="button" value="提交" onclick="c()"/>&nbsp; 
						       <input type="reset" value="重置"/>&nbsp;
						       <input type="button" value="返回" onclick="backToList()"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
