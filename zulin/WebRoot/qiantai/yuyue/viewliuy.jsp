<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		
		<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
		
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="6" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="5%">序号</td>
				    <td width="10%">租房信息</td>		
					<td width="10%">留言者ID</td>
					<td width="10%">留言内容</td>					
					<td width="10%">留言时间</td>
					<td width="10%">回复内容</td>					
					<td width="10%">回复时间</td>
		        </tr>	
				<s:iterator value="#request.yuyueList" id="yuyue" status="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#ss.index+1"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#yuyue.che.chexing"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#yuyue.user.userRealname"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#yuyue.liuyanremark"/>
					</td>					
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#yuyue.liushij"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#yuyue.huifuremark"/>
					</td>					
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#yuyue.huifushij"/>
					</td>
				</tr>
				</s:iterator>
			</table>
	</body>
</html>
