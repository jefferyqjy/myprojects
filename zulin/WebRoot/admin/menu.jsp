<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   

	<STYLE type=text/css>
		BODY {PADDING-RIGHT: 0px; PADDING-LEFT: 5px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; BACKGROUND-COLOR: #2a8dc8}
		BODY {FONT-SIZE: 11px; COLOR: #003366; FONT-FAMILY: Verdana}
		TD {FONT-SIZE: 11px; COLOR: #003366; FONT-FAMILY: Verdana}
		DIV {FONT-SIZE: 11px; COLOR: #003366; FONT-FAMILY: Verdana}
		P {FONT-SIZE: 11px; COLOR: #003366; FONT-FAMILY: Verdana}
		.mainMenu {FONT-WEIGHT: bold; FONT-SIZE: 18px; CURSOR: hand; COLOR: #000000;font-weight: 700;font-family: 楷体;}
A.style2:link {PADDING-LEFT: 4px; COLOR: #0055bb; TEXT-DECORATION: none;font-family: 楷体;font-size: 14px;}
A.style2:visited {PADDING-LEFT: 4px; COLOR: #0055bb; TEXT-DECORATION: none;font-family: 楷体;font-size: 14px;}
A.style2:hover {PADDING-LEFT: 4px; COLOR: #ff0000; TEXT-DECORATION: none;font-family: 楷体;font-size: 14px;}
A.active {PADDING-LEFT: 4px; COLOR: #ff0000; TEXT-DECORATION: none;font-family: 楷体;font-size: 14px;}
		.span {COLOR: #ff0000;font-family:"宋体";font-size: 15px;}
	</STYLE>

    <script type="text/javascript">
	    function logout()
		{
		   if(confirm("确定要退出本系统吗??"))
		   {
			   window.parent.location="<%=path %>/login.jsp";
		   }
		}
		
		function displayAll(){
		
		
		
		}
	</script>
  </head>
  
  
  
<body onload="displayAll()">
 
<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
  <TR>
    <TD width=15><IMG src="<%=path %>/img/new_005.jpg" border=0></TD>
    <TD align=middle width=180 background=<%=path %>/img/new_006.jpg height=35><B></B> </TD>
    <TD width=15><IMG src="<%=path %>/img/new_007.jpg" border=0></TD>
  </TR>
</TABLE>

<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
  <TR>
    <TD width=15 background=<%=path %>/img/new_008.jpg></TD>
    <TD vAlign=top width=180 bgColor=#ffffff>
      <TABLE cellSpacing=0 cellPadding=3 width=165 align=center border=0>
        <!--  -->
        <TR><TD class=mainMenu>修改个人密码</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/admin/userinfo/userPw.jsp" target=dmMain>－ 修改个人密码</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
        
        <% String userType=request.getSession().getAttribute("userType").toString(); 
        
        System.out.print("================="+userType);
        if("2".equals(userType)){//管理员
        %>
        <!--  -->
        <TR><TD class=mainMenu>租房信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/cheMana.action" target=dmMain>－ 租房信息管理</A></TD></TR>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/admin/che/cheAdd.jsp" target=dmMain>－ 租房信息添加</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->        
        
        <!--  -->
        <TR><TD class=mainMenu>租赁信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/zulinMana.action" target=dmMain>－ 租赁信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
                      <!--  -->
        <TR><TD class=mainMenu>评价信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/yuyueMana.action?isType=pj" target=dmMain>－ 评价信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
        <!--  -->
        <TR><TD class=mainMenu>留言板--管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/liuyanMana.action" target=dmMain>－ 留言板--管理</A></TD></TR>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/userMana.action" target=dmMain>－ 会员信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
                          <!--  -->
        <TR><TD class=mainMenu>租房统计</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
               <TR><TD class=menuSmall><A class=style2 href="<%=path %>/zulin_tongji1.action" target=dmMain>－ 租赁信息统计</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
		<TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
               <TR><TD class=menuSmall><A class=style2 href="<%=path %>/che_tongji.action" target=dmMain>－ 租房信息统计</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  --> 
       <%}else if("1".equals(userType)){//房东 %>
       
        <!--  -->
        <TR><TD class=mainMenu>租房信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/cheMana.action" target=dmMain>－ 租房信息管理</A></TD></TR>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/admin/che/cheAdd.jsp" target=dmMain>－ 租房信息添加</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
            <!--  -->
        <TR><TD class=mainMenu>评价信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/yuyueMana.action?isType=pj" target=dmMain>－ 评价信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
                  <!--  -->
        <TR><TD class=mainMenu>留言信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/yuyueMana.action?isType=ly" target=dmMain>－ 留言信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
        <!--  -->
        <TR><TD class=mainMenu>租赁信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/zulinMana.action" target=dmMain>－ 租赁信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  --> 
        <!-- contract begin -->
        <TR><TD class=mainMenu>合同信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/contractMana.action" target=dmMain>－ 合同信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!-- contract end --> 
        <!-- fund begin -->
        <TR><TD class=mainMenu>资金信息管理</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 href="<%=path %>/fundMana.action" target=dmMain>－ 资金信息管理</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!-- fund end --> 
        <!--  -->
        <TR><TD class=mainMenu>租房统计</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
               <TR><TD class=menuSmall><A class=style2 href="<%=path %>/zulin_tongji1.action" target=dmMain>－ 租赁信息统计</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  --> 
       <%} %>
        <!--  -->
        <TR><TD class=mainMenu>安全退出系统</TD></TR>
        <TR>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 width=155 align=center border=0>
              <TR><TD class=menuSmall><A class=style2 onclick="logout()" target=dmMain style="font-family: 楷体;font-size: 14px;">－ 安全退出系统</A></TD></TR>
			</TABLE>
		  </TD>
		</TR>
        <TR><TD background=<%=path %>/img/new_027.jpg height=1></TD></TR><TR>  
        <!--  -->  
        
      </TABLE>
    </TD>
    <TD width=15 background=<%=path %>/img/new_009.jpg></TD></TR>
</TABLE>
</body>


</html>
