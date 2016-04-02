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
			function contractAdd() {
				window.location.href = "<%=path %>/contractAddShow.action";
			}
           
            // extension = create a new contract
           	function contractExtension(id) {
          		window.location.href = "<%=path %>/contractAddShow.action";
           	}
           	
           	function contractDel(id) {
           		window.location.href = "<%=path %>/contractDel.action?id=" + id;
           	}
           	
           	function contractDetail(id) {
           		window.location.href = "<%=path %>/contractAddShow.action?id=" + id;
           	}
           	
           	function fundAdd(contractId) {
           		window.location.href = "<%=path %>/fundAddShow.action?contractId=" + contractId;
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
					<td width="4%">房东</td>
					<td width="14%">客户</td>
					<td width="6%">合同生效时间</td>
					<td width="6%">合同失效时间</td>
					<td width="6%">状态</td>
					<td width="16%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.contractList}" var="contract" varStatus="sta">
				<tr align='center' bgcolor="#FFFFFF">
					<td bgcolor="#FFFFFF" align="center">${sta.index+1}</td>
					<td bgcolor="#FFFFFF" align="center">${contract.adminName}</td>
					<td bgcolor="#FFFFFF" align="center">${contract.userName}</td>
					<td bgcolor="#FFFFFF" align="center">${contract.startDate}</td>
					<td bgcolor="#FFFFFF" align="center">${contract.endDate}</td>
					<td bgcolor="#FFFFFF" align="center">
					    <c:if test="${contract.status=='0'}">
					        <span style="color: red">未签约</span>
					    </c:if>
					    <c:if test="${contract.status=='1'}">
					        <span style="color: green">已签约</span>
					    </c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<input type="button" value="修改" onclick="contractDetail(${contract.contractId})"/>
						<c:if test="${contract.expired=='1'}">
							<input type="button" value="续约" onclick="contractExtension()" />
						</c:if>
						<input type="button" value="删除" onclick="contractDel(${contract.contractId})"/>
						<input type="button" value="收款" onclick="fundAdd(${contract.contractId})"/>
					</td>
				</tr>
				</c:forEach>
			</table>
		    
		    
		    <table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="contractAdd()" />
			    </td>
			  </tr>
		    </table> 
		    
		    <div id="tip" style="position:absolute;display:none;border:0px;width:80px; height:80px;">
				<TABLE id="tipTable" border="0" bgcolor="#ffffee">
					<TR align="center">
						<TD>
							<c:if test="${not empty msg}">
								${msg },请刷新页面！
							</c:if>
							<!-- <img id="photo" src="" height="150" width="150"> -->
						</TD>
					</TR>
				</TABLE>
			</div>
	</body>
</html>
