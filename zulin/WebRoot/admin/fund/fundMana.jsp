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
			function fundAdd() {
				window.location.href = "<%=path %>/fundAddShow.action";
			}
           
           	function fundDel(id) {
           		window.location.href = "<%=path %>/fundDel.action?id=" + id;
           	}
           	
           	function fundDetail(id) {
           		window.location.href = "<%=path %>/fundAddShow.action?id=" + id;
           	}
           
           	function over(picPath) {
			  	if (picPath=="")picPath="/img/default.jpg";
			  	x = event.clientX;
			  	y = event.clientY;      
			  	document.all.tip.style.display = "block";
			  	document.all.tip.style.top = y;
			  	document.all.tip.style.left = x+10;
			  	document.all.photo.src = ".."+picPath; 
			}
			
		   	function out() {
			  	document.all.tip.style.display = "none";
		   	}
		   
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="13" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">序号</td>
					<td width="4%">合同编号</td>
					<td width="6%">类型</td>
					<td width="6%">金额</td>
					<td width="6%">状态</td>
					<td width="6%">是否退款</td>
					<td width="6%">发票号</td>
					<td width="16%">操作</td>
		        </tr>	
				<c:forEach items="${fundList}" var="fund" varStatus="sta">
				<tr align='center' bgcolor="#FFFFFF">
					<td bgcolor="#FFFFFF" align="center">${sta.index+1}</td>
					<td bgcolor="#FFFFFF" align="center">${fund.contractId}</td>
					<td bgcolor="#FFFFFF" align="center">
						<c:if test="${fund.type == '0'}">
					        <span>定金</span>
					    </c:if>
					    <c:if test="${fund.type == '1'}">
					        <span>押金</span>
					    </c:if>
					    <c:if test="${fund.type == '2'}">
					        <span>租金</span>
					    </c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">${fund.money}</td>
					<td bgcolor="#FFFFFF" align="center">
					    <c:if test="${fund.status == '0'}">
					        <span style="color: red">未缴纳</span>
					    </c:if>
					    <c:if test="${fund.status == '1'}">
					        <span style="color: green">已缴纳</span>
					    </c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <c:if test="${fund.payType == '0'}">
					        <span>否</span>
					    </c:if>
					    <c:if test="${fund.payType == '1'}">
					        <span>是</span>
					    </c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">${fund.receiptNumber}</td>
					<td bgcolor="#FFFFFF" align="center">
						<input type="button" value="修改" onclick="fundDetail(${fund.id})"/>
						<input type="button" value="删除" onclick="fundDel(${fund.id})"/>
					</td>
				</tr>
				</c:forEach>
			</table>
		    
		    
		    <!--<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="fundAdd()" />
			    </td>
			  </tr>
		    </table> 
		    
		    --><!-- <div id="tip" style="position:absolute;display:none;border:0px;width:80px; height:80px;">
				<TABLE id="tipTable" border="0" bgcolor="#ffffee">
					<TR align="center">
						<TD>
							<c:if test="${not empty msg}">
								${msg },请刷新页面！
							</c:if>
							<!-- <img id="photo" src="" height="150" width="150"> 
						</TD>
					</TR>
				</TABLE>
			</div> -->
	</body>
</html>
