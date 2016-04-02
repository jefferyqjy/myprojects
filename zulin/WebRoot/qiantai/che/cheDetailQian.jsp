<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />

<style type="text/css">
.style12 {
	color: #FF6633;
	font-size: 14px;
}

td {
	FONT-SIZE: 9pt;
	LINE-HEIGHT: 150%;
	COLOR: #353535
}

body {
	background-image: url(<%=path%>/img/background.gif);
	FONT-SIZE: 9pt;
	LINE-HEIGHT: 150%;
	margin: 0px;
}

a:link {
	color: #333333;
	text-decoration: none
}

a:visited {
	color: #333333;
	text-decoration: none
}

a:active {
	color: #333333;
	text-decoration: none
}

a:hover {
	color: #ff0000;
	text-decoration: underline
}

A:unknown LINK {
	TEXT-DECORATION: none
}

.list_link {
	FONT-WEIGHT: bold;
	FONT-SIZE: 12px;
	COLOR: #8d1c1c;
	TEXT-DECORATION: none
}

.wenbenkuang {
	BORDER-RIGHT: #999999 1px solid;
	BORDER-TOP: #999999 1px solid;
	FONT-SIZE: 9pt;
	BORDER-LEFT: #999999 1px solid;
	COLOR: #333333;
	BORDER-BOTTOM: #999999 1px solid;
	FONT-FAMILY: "宋体"
}

.go-wenbenkuang {
	BORDER-RIGHT: #666666 1px solid;
	PADDING-RIGHT: 1px;
	BORDER-TOP: #ffffff 1px solid;
	PADDING-LEFT: 1px;
	FONT-SIZE: 9pt;
	PADDING-BOTTOM: 1px;
	BORDER-LEFT: #ffffff 1px solid;
	CURSOR: hand;
	COLOR: #333333;
	PADDING-TOP: 1px;
	BORDER-BOTTOM: #666666 1px solid;
	FONT-FAMILY: "宋体";
	HEIGHT: 19px;
	BACKGROUND-COLOR: #eeeeee
}

.table-zuoyou {
	BORDER-RIGHT: #A9A9A9 1px solid;
	BORDER-LEFT: #A9A9A9 1px solid
}

.table-shangxia {
	BORDER-TOP: #A9A9A9 1px solid;
	BORDER-BOTTOM: #A9A9A9 1px solid
}

.table-sxzy {
	BORDER-TOP: #A9A9A9 1px solid;
	BORDER-BOTTOM: #A9A9A9 1px solid; # A9A9A9 1px solid;
	BORDER-LEFT: #A9A9A9 1px solid;
	BORDER-RIGHT: #A9A9A9 1px solid
}

.table-you {
	BORDER-RIGHT: #A9A9A9 1px solid
}

.table-zuo {
	BORDER-LEFT: #A9A9A9 1px solid
}

.table-shang {
	BORDER-TOP: #A9A9A9 1px solid
}

.table-xia {
	BORDER-BOTTOM: #A9A9A9 1px solid
}

.table-xu {
	BORDER-BOTTOM: #A9A9A9 1px dotted
}

.matrix {
	FONT-SIZE: 12px
}

.matrix A {
	COLOR: #93393a
}

.matrix_sub {
	BORDER-RIGHT: #c9c9c9 1px solid;
	PADDING-RIGHT: 4px;
	BORDER-TOP: 0px;
	PADDING-LEFT: 4px;
	BACKGROUND-IMAGE: url(<%=path%>/img/botton2.gif);
	PADDING-BOTTOM: 4px;
	BORDER-LEFT: #c9c9c9 1px solid;
	COLOR: #000000;
	PADDING-TOP: 1px;
	BORDER-BOTTOM: #c9c9c9 1px solid;
	BACKGROUND-COLOR: #f7f7f7
}

.matrix_header {
	BORDER-RIGHT: #c9c9c9 1px solid;
	BORDER-TOP: #c9c9c9 1px solid;
	PADDING-LEFT: 5px;
	BACKGROUND-IMAGE: url(<%=path%>/img/botton2.gif);
	PADDING-BOTTOM: 6px;
	BORDER-LEFT: #c9c9c9 1px solid;
	COLOR: #00469e;
	PADDING-TOP: 6px;
	BORDER-BOTTOM: #838383 1px solid;
	BACKGROUND-COLOR: #ffffff
}

.matrix_info {
	BORDER-RIGHT: #c9c9c9 1px solid;
	PADDING-RIGHT: 4px;
	PADDING-LEFT: 4px;
	PADDING-BOTTOM: 4px;
	BORDER-LEFT: #c9c9c9 1px solid;
	PADDING-TOP: 4px;
	BORDER-BOTTOM: #eae9e9 1px solid;
	BACKGROUND-COLOR: #f7f7f7
}

.matrix_info_header {
	BORDER-RIGHT: #c9c9c9 1px solid;
	PADDING-RIGHT: 4px;
	BORDER-TOP: #c9c9c9 1px solid;
	PADDING-LEFT: 4px;
	PADDING-BOTTOM: 4px;
	BORDER-LEFT: #c9c9c9 1px solid;
	PADDING-TOP: 4px;
	BORDER-BOTTOM: #eae9e9 1px solid;
	BACKGROUND-COLOR: #ebeaea
}

.matrix_content {
	COLOR: #353535;
	BACKGROUND-COLOR: #ffffff
}

.matrix_left {
	BORDER-LEFT: #c9c9c9 1px solid
}

.error {
	BORDER-RIGHT: #ff0000 2px solid;
	BORDER-TOP: #ff0000 2px solid;
	BORDER-LEFT: #ff0000 2px solid;
	WIDTH: 95%;
	BORDER-BOTTOM: #ff0000 2px solid;
	BACKGROUND-COLOR: #ffdede
}

.error TD {
	FONT-SIZE: 12px;
	COLOR: #ff0000
}

.error .title {
	BACKGROUND-COLOR: #ff9393
}

.error .title TD {
	FONT-WEIGHT: bold;
	FONT-SIZE: 12px;
	COLOR: #ffffff
}

.select {
	BORDER-TOP: #c4c4c4 1px solid;
	BORDER-LEFT: #c4c4c4 1px solid
}

.tableBorder {
	WIDTH: 98%;
	BACKGROUND-COLOR: #ffffff;
	border: 1px solid #0066FF;
}

.edfour {
	BORDER-RIGHT: #000000 1px solid;
	BORDER-TOP: #000000 1px solid;
	BORDER-LEFT: #000000 1px solid;
	BORDER-BOTTOM: #000000 1px solid;
	BACKGROUND-COLOR: #ffffff
}

.color {
	FONT-SIZE: 12px;
	COLOR: #d7fd62;
	TEXT-DECORATION: none
}

.line {
	FONT-SIZE: 12px;
	LINE-HEIGHT: 25px;
	TEXT-DECORATION: none
}

.unnamed1 {
	FONT-SIZE: 12px;
	COLOR: #000000;
	TEXT-DECORATION: none
}

.unnamed2 {
	FONT-SIZE: 12px;
	LINE-HEIGHT: 25px;
	TEXT-DECORATION: none
}

.da {
	font-size: 14px;
	color: #FFFFFF;
	letter-spacing: 1px;
}
</style>

</head>

<BODY leftMargin=0 topMargin=0 bgColor=#ffffff>
	<jsp:include flush="true" page="/qiantai/inc/incTop.jsp"></jsp:include>

	<TABLE border=0 cellSpacing=0 cellPadding=0 width=772 bgColor=#ffffff
		align=center>
		<TR>
			<TD class=b vAlign=top width=178><jsp:include flush="true"
					page="/qiantai/inc/incLeft.jsp"></jsp:include></TD>


			<td width="1" bgcolor="#A9A9A9"></td>
			<td width="3" bgcolor="white"></td>
			<!-- 右部分-->
			<TD class=b vAlign=top width=591 align=left>
				<TABLE border=0 cellSpacing=0 cellPadding=0 width=590 align=center
					bgcolor="#CCCCCC" style="margin-top: 2px;">
					<TR>
						<TD height="25">&nbsp;&nbsp;&nbsp;</TD>
					</TR>
				</TABLE>
				<TABLE border=0 cellSpacing=0 cellPadding=2 width=590 align=left
					height=133>
					<TR>
						<TD height=129 vAlign=top align=left>
							<form action="" method="post" name="buy">
								<table width="100%" border="0" cellpadding="3" cellspacing="3">
									<tr>
										<td align="left"><img width="240" height="200"
											src="<%=path %>/${requestScope.che.fujian }"
											style="border:1px solid #ccc; padding:3px;" />
										</td>
									</tr>
									<tr>
										<td align="left">租房信息：${requestScope.che.chexing }</td>
									</tr>
									<tr>
										<td align="left">区域：<c:out
												value="${requestScope.che.quyu }" escapeXml="false"></c:out>
										</td>
									</tr>
									<tr>
										<td align="left">户型：<c:out
												value="${requestScope.che.pinpai }" escapeXml="false"></c:out>
										</td>
									</tr>
									<tr>
										<td align="left">类型：<c:out
												value="${requestScope.che.ttype }" escapeXml="false"></c:out>
										</td>
									</tr>
									<tr>
										<td align="left">装修：<c:out
												value="${requestScope.che.zhxiu }" escapeXml="false"></c:out>
										</td>
									</tr>
									<tr>
										<td align="left">面积：<c:out
												value="${requestScope.che.mianji }" escapeXml="false"></c:out>
										</td>
									</tr>
									<tr>
										<td align="left">租金：${requestScope.che.rizu }元/月</td>
									</tr>
									<tr>
										<td align="left">联系人：<c:out
												value="${requestScope.che.linkman }" escapeXml="false"></c:out>
										</td>
									</tr>
									<tr>
										<td align="left">联系电话：<font color="orange" size="6"><strong><c:out
														value="${requestScope.che.linkphone }" escapeXml="false"></c:out>
											</strong>
										</font>
										</td>
									</tr>
									<tr>
										<td align="left">房源信息：${requestScope.che.beizhu }</td>
									</tr>
									<tr>
										<td align="left"><font color="red">温馨提示:您看到的房源，请告诉朋友是在高校周边房屋出租系统中看到的...</font>
										</td>
									</tr>
									<tr>
										<td align="left" style="color: red">状态：${requestScope.che.zt
											}</td>
									</tr>
									<tr>
										<td align="left"><c:if
												test="${requestScope.che.zt eq '空闲中'}">
												<input type="button" id="vvvv" style="width: 80px;"
													value="求租" onclick="yuyueAdd(${requestScope.che.id })">
											</c:if> <c:if test="${requestScope.che.zt ne '空闲中'}">
												<input type="button" id="vvvv" style="width: 80px;"
													value="求租" onclick="yuyueAdd(${requestScope.che.id })">
											</c:if></td>
											
									</tr>
								</table>
							</form></TD>
					</TR>
				 <tr>
				 
				 <td><font color="orange" size="4"><strong>评价信息：</strong></font><br>
				 <TABLE border=0 cellSpacing=0 cellPadding=2 width=590 align=left
					>					
				  <c:forEach items="${requestScope.yuyueList}" var="che" varStatus="sta">
				     	  <tr>			
					     	  <td class="t">
					     		 <font color="orange"><strong>${che.xingming}</strong></font>&nbsp;&nbsp;
					     		  <span class='ico ntu'></span>
					     		  <i class="clear"></i>${che.pinjiaremark}<i class="clear"></i>
								  <span class="fl">评价时间：${che.pinjiashij}</span>
						          <span name="zaixian_56499070"></span>
						          <span class='fl ml5 biz'></span>
					     	  </td>
				     	  </tr>
				     	  </c:forEach></TABLE></td>
				 </tr>
				 
				 </TABLE></TD>
		</TR>
	</TABLE>


	<TABLE border=0 cellSpacing=0 cellPadding=0 width=772 bgColor=#a9a9a9
		align=center>
		<TR>
			<TD rowSpan=5 width=1></TD>
		</TR>
	</TABLE>


	<jsp:include flush="true" page="/qiantai/inc/incFoot.jsp"></jsp:include>

	<script language="javascript">
	       <c:if test="${requestScope.che.zt=='已租出' }">
	           document.getElementById("vvvv").disabled=true;
	       </c:if>
	       function yuyueAdd(cheId)
	       {
	           <s:if test="#session.userType !=3">
	                alert("请先登录");
	           </s:if>
	           <s:if test="#session.userType ==3">
	               <%--  //var url="<%=path%>/qiantai/yuyue/yuyueAdd.jsp?cheId="+cheId; --%>
	               // var ret = window.showModalDialog(url,"","dialogWidth:600px; dialogHeight:400px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
	                if(window.confirm("确定租此房吗？")){
	                var url="<%=path%>/qiuZhu.action?cheId="+cheId;
	                window.location=url;
	                }
	           </s:if>
	       }
         </script>
</BODY>
</html>
