<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="com.model.TZulin"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
    String path = request.getContextPath();
    int kk=0;
    List zulinList=(List)request.getAttribute("zulinList");
    for(int i=0;i<zulinList.size();i++)
    {
         TZulin zulin=(TZulin)zulinList.get(i);
         kk=kk+zulin.getFeiyong();
    }
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
        </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="42" background="<%=path %>/img/tbg.gif">&nbsp;租车记录&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					
					<td width="10%">车辆信息</td>
					<td width="10%">客户姓名</td>
					<td width="10%">身份证号</td>
					<td width="10%">驾照编号</td>
					
					<td width="10%">起租时间</td>
					<td width="10%">押金信息</td>
					<td width="10%">备注信息</td>
					<td width="10%">还车时间</td>
					
					<td width="10%">租车费用</td>
		        </tr>	
				<c:forEach items="${requestScope.zulinList}" var="zulin" varStatus="sta">
				<tr align='center' bgcolor="#FFFFFF">
					<td bgcolor="#FFFFFF" align="center">${sta.index+1}</td>
					
					<td bgcolor="#FFFFFF" align="center">${zulin.che.chexing}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.kehuming}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.shenfenzheng}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.jiazhaohao}</td>
					
					<td bgcolor="#FFFFFF" align="center">${zulin.kaishishijian}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.yajin}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.beizhu}</td>
					<td bgcolor="#FFFFFF" align="center">${zulin.jieshushijian}</td>
					
					<td bgcolor="#FFFFFF" align="center">
					   <c:if test="${zulin.feiyong==0}">
					       &nbsp;
					   </c:if>
					   <c:if test="${zulin.feiyong !=0}">
					       ${zulin.feiyong}
					   </c:if>
					</td>
				</tr>
				</c:forEach>
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="44" align="center" background="<%=path %>/img/tbg.gif">消费总额：<font color="red"><%=kk %></font></td>
				</tr>
			</table>
	</body>
</html>
