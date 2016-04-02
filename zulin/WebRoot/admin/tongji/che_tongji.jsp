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
			<form action="<%=path %>/che_tongji.action" name="formAdd" method="post">
	<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
			  <tr>
						                <td align="right">
						              	区域
						                </td>
						                <td align="left">
						              	<select name="quyu" >
						    <option value="">--选择区域--</option>
							<option value="鼓楼区">鼓楼区</option>
							<option value="建邺区">建邺区</option>
							<option value="栖霞区">栖霞区</option>
							<option value="下关区">下关区</option>
							<option value="六合区">六合区</option>
							<option value="秦淮区">秦淮区</option>
							<option value="江宁区">江宁区</option>
							<option value="浦口区">浦口区</option>
							</select>
						                </td>
						            </tr>
						             <tr align="center">
						                <td align="right">
						              	户型
						                </td>
						                <td align="left">
						                <select name="pinpai" >
						                <option value="">--选择户型--</option>
							<option value="一室一厅">一室一厅</option>
							<option value="两室一厅">两室一厅</option>
							<option value="三室一厅">三室一厅</option>
							<option value="三室两厅">三室两厅</option>
							<option value="四室一厅">四室一厅</option>
							<option value="四室两厅">四室两厅</option>
							<option value="五室两厅">五室两厅</option>
							<option value="其他">其他</option>
							</select>
						                </td>
						            </tr>
						             <tr>
						                <td align="right">
						              	 类型
						                </td>
						                <td align="left">
						                      <select name="ttype" >
						                       <option value="">--选择类型--</option>
							<option value="公寓">公寓</option>
							<option value="牌楼">牌楼</option>
							<option value="别墅">别墅</option>
							<option value="农居房">农居房</option>
							<option value="其他">其他</option>
							</select>
						                </td>
						            </tr>
						               <tr>
						                <td align="right">
						              	 装修
						                </td>
						                <td align="left">
						          <select name="zhxiu" >
						          <option value="">--选择装修--</option>
							<option value="普通">普通</option>
							<option value="中等">中等</option>
							<option value="精装">精装</option>
							<option value="豪华装">豪华装</option>
							<option value="其他">其他</option>
							</select>
						                </td>
						            </tr>
						            <tr>
						                <td align="right">
						                    标题
						                </td>
						                <td align="left"><input type="text" name="chexing" size="14"/>
						                </td>
						            </tr>
						                  <tr>
						                <td align="right">
						                    房东
						                </td>
						                <td align="left"><input type="text" name="linkman" size="14"/>
						                </td>
						            </tr>
					<tr align="center">
					<td><input type="button" value="统计" onclick="check()"/></td>
				</tr>
	
	</form>
		
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="11" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">序号</td>
					<td width="14%">租房信息</td>
					<td width="6%">户型</td>
					<td width="6%">区域</td>
					<td width="6%">类型</td>
					<td width="6%">装修</td>
					<td width="6%">联系人</td>
					<td width="6%">联系电话</td>
					<td width="6%">面积</td>
					<td width="6%">租金</td>
					<td width="6%">状态</td>
		        </tr>	
				<c:forEach items="${requestScope.cheList}" var="che" varStatus="sta">
				<tr align='center' bgcolor="#FFFFFF">
					<td bgcolor="#FFFFFF" align="center">${sta.index+1}</td>
					<td bgcolor="#FFFFFF" align="center">${che.chexing}</td>
					<td bgcolor="#FFFFFF" align="center">${che.pinpai}</td>
					
					<td bgcolor="#FFFFFF" align="center">${che.quyu}</td>
					<td bgcolor="#FFFFFF" align="center">${che.ttype}</td>
					<td bgcolor="#FFFFFF" align="center">${che.zhxiu}</td>
					<td bgcolor="#FFFFFF" align="center">${che.linkman}</td>
					<td bgcolor="#FFFFFF" align="center">${che.linkphone}</td>
					<td bgcolor="#FFFFFF" align="center">${che.mianji}</td>
					<td bgcolor="#FFFFFF" align="center">${che.rizu}</td>
					<td bgcolor="#FFFFFF" align="center">
					    <c:if test="${che.zt=='空闲中'}">
					        <span style="color: red">空闲中</span>
					    </c:if>
					    <c:if test="${che.zt=='已租出'}">
					        <span style="color: green">已租出</span>
					    </c:if>
					    <c:if test="${che.zt=='已预约'}">
					        <span style="color: green">已预约</span>
					    </c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		    
		    
		    <div id="tip" style="position:absolute;display:none;border:0px;width:80px; height:80px;">
				<TABLE id="tipTable" border="0" bgcolor="#ffffee">
					<TR align="center">
						<TD><img id="photo" src="" height="150" width="150"></TD>
					</TR>
				</TABLE>
			</div>
	</body>
</html>
