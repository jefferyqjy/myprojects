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
            function c()
            {
                if(document.formAdd.name.value=="")
                {
                    alert("请输入网站名称");
                    return false;
                }
                
                document.formAdd.submit();
            }
            
            function backToList() {
            	window.location.href="<%=path %>/contractMana.action";
            }
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/img/allbg.gif'>
			<form action="<%=path %>/contractAddSave.action" name="formAdd" method="post">
					<input type="hidden" name="adminId" value="${admin.userId }" />
					<input type="hidden" name="adminName" value="${admin.userName }" />
					<input type="hidden" name="id" value="${id }" />
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#E7E7E7">
							<td height="14" colspan="4" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 租户信息：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<select name="zulinId">
						    		<option value="">-请选择-</option>
						    		<c:forEach items="${zulinList }" var="z" >
							    		<option value="${z.id }" <c:if test="${zulinId eq z.id }">selected="selected"</c:if> >${z.kehuming }</option>
							    	</c:forEach>
						    	</select>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 合同内容：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<FCK:editor instanceName="content" basePath="/fckeditor" width="300" height="400" value="${content }" toolbarSet="Basic">
	                            </FCK:editor>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 生效时间：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<input type="text" name="startDateStr" value="${startDate }" /><a><font color="red">（* 格式为：yyyy-MM-dd ）</font></a>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 失效时间：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<input type="text" name="endDateStr" value="${endDate }" /><a><font color="red">（* 格式为：yyyy-MM-dd ）</font></a>
						    </td>
						</tr>
						<c:if test="${not empty id}">	
							<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						                 状态：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						    	<select name="status">
						    		<option value="">请选择</option>
						    		<option value="0" <c:if test="${status eq 0}">selected="selected"</c:if> >未签约</option>
						    		<option value="1" <c:if test="${status eq 1}">selected="selected"</c:if> >已签约</option>
						    	</select>
						    </td>
						</tr>
						</c:if>
						
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
