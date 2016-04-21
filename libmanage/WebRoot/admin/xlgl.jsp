<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Syspros"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
	<script type="text/javascript" src="/libmanage/js/popup.js"></script>
  </head>
  <%
  CommonDAO dao = Info.getDao(request);
   String key = request.getParameter("key")==null?"":request.getParameter("key");
  String did = request.getParameter("did")==null?"":request.getParameter("did");
  
  if(!did.equals(""))
  {
  dao.delete(did,"Syspros");
  }
  
  String url = "xlgl.jsp?1=1";
  String sql = "from Syspros  where infoa='学历' ";
  
  if(!key.equals(""))
  {
  sql+=" and proname like '%"+key+"%' ";
  }
  
  sql+=" order by id desc";
  System.out.println(sql);
  PageManager pageManager = PageManager.getPage(url, 100, request);
  pageManager.doList(sql);
  PageManager bean = (PageManager) request.getAttribute("page");
  ArrayList<Syspros> nlist = (ArrayList) bean.getCollection();
   %>
  <body>
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<form name="f1" method="post" action="/libmanage/admin/xlgl.jsp" >
  	<table id="mainbody" border="0" width="100%" cellspacing="1"
					class="tableform">
			<tr>
     				 <td height="31" align="left" style="font-size: 12px">  
                     &nbsp;&nbsp;相关信息&nbsp; :&nbsp; 
   				       <input name="key" type="text" value="<%=key %>" size="35"> &nbsp;
   				     <input type="submit" class="btn3_mouseup" value="查询"> &nbsp;
   				     <input type="button" onclick="add();" class="btn3_mouseup" value="添加">                     </td>
	    </tr>
			</table>
  	
  	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
<tr align="center">
          <td colspan="7"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">信息列表</td>
        </tr>
        <tr align="center">
          <td class="itemtitle" width="37%" > 学历名称<br></td>
          <td class="itemtitle" width="13%" > 操作</td>
          <td class="itemtitle" width="37%" > 学历名称</td>
          <td class="itemtitle" width="13%" > 操作</td>
          
  </tr>
        
    
        <tr align="center">
        
        <%
        int i=0;
  for(Syspros data:nlist)
  {
  i++;
    %>
          <td align="center"> <%=data.getProname().equals("")?"&nbsp;":data.getProname() %> </td>
          <td align="center"> 
          <a href="javascript:update('<%=data.getId()%>')">修改</a>
          &nbsp;|&nbsp;
          <a href="/libmanage/admin/xlgl.jsp?did=<%=data.getId()%>">删除</a> </td>
          
          <%
          if(i%2==0)out.print("</tr><tr align='center'>");
           %>
          
          <%} %>
          
  </tr>
        
        
        <script type="text/javascript">
          function update(no)
          {
          pop('/libmanage/admin/updatepros.jsp?id='+no,'修改学历',300,84);
          }
          
          function add()
          {
          pop('/libmanage/admin/addpros.jsp?type=3','添加学历',300,84);
          }
          </script>
      </table>
      </form>
</body>
</html>
