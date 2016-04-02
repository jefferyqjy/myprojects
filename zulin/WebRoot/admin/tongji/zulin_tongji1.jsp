<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
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
		<script type="text/javascript" src="<%=path %>/js/popup.js"></script>
		<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
        <script language="javascript">
              function check()
           {
               document.formAdd.submit();
           }
            </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
	<form action="<%=path %>/zulin_tongji1.action" name="formAdd" method="post">
	<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr align="center">
					<td>客户名：</td>
					<td align="left"><input type="text" name="kehuming" style="width: 200px;"/></td>
					</tr>
					<tr align="center">
					<td>联系电话：</td>
					<td align="left"><input type="text" name="kaishishijian" style="width: 200px;"/></td>
				</tr>
					<tr align="center">
					<td><input type="button" value="统计" onclick="check()"/></td>
				</tr>
	
	</form>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="10" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					<td width="10%">租房信息</td>
					<td width="6%">客户姓名</td>
					<td width="6%">联系电话</td>				
					<td width="10%">起租时间</td>	
					<td width="7%">租房费用</td>				
					<td width="6%">房东</td>
					<td width="6%">房东电话</td>
					<td width="6%">状态</td>		
					<td width="6%">停租时间</td>					
		        </tr>	
				<c:forEach items="${requestScope.zulinList}" var="zulin" varStatus="sta">
				<tr align='center' bgcolor="#FFFFFF">
					<td bgcolor="#FFFFFF" align="center">${sta.index+1}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.che.chexing}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.kehuming}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.shenfenzheng}</td>					
					<td bgcolor="#FFFFFF" align="center">${zulin.kaishishijian}</td>	
					<td bgcolor="#FFFFFF" align="center">
					   <c:if test="${zulin.feiyong==0}">
					       &nbsp;
					   </c:if>
					   <c:if test="${zulin.feiyong !=0}">
					       ${zulin.feiyong}
					   </c:if>
					</td>				
					<td bgcolor="#FFFFFF" align="center">${zulin.che.linkman}</td>			
					<td bgcolor="#FFFFFF" align="center">${zulin.che.linkphone}</td>	
					<td bgcolor="#FFFFFF" align="center">${zulin.shifouhuan}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.jieshushijian}</td>
				</tr>
				</c:forEach>
			</table>
			
		<!-- 	
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <input type="button" value="添加租赁信息" style="width: 80px;" onclick="zulinAdd()" />
			    </td>
			  </tr>
		    </table> -->
	</body>
</html>
