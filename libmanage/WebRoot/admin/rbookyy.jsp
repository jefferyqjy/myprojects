<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Sreader"%>
<%@page import="com.cz.entity.Bookhj"%>
<%@page import="com.cz.entity.Bookyy"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
	<script type="text/javascript" src="/libmanage/js/popup.js"></script>
	<script type="text/javascript" src="/libmanage/js/calendar/WdatePicker.js"></script>
  </head>
  <%
  CommonDAO dao = Info.getDao(request);
     Sreader reader = (Sreader)request.getSession().getAttribute("reader"); 
  List<Books> lblist = dao.findByHql("from Books  ");
  List<Sreader> cbslist = dao.findByHql("from Sreader ");
  
   String readername = request.getParameter("readername")==null?"":request.getParameter("readername");
  String bookname = request.getParameter("bookname")==null?"":request.getParameter("bookname");
  String did = request.getParameter("did")==null?"":request.getParameter("did");
  
  if(!did.equals(""))
  {
  dao.delete(did,"Bookyy");
  }
  
  String url = "rbookyy.jsp?1=1";
  String sql = "from Bookyy  where     readername='"+reader.getUname()+"'  "; 
  
  if(!readername.equals(""))
  {
  url+="&readername="+readername;
  sql+=" and readername like '%"+readername+"%' ";
  }
  if(!bookname.equals("")){
  url+="&bookname="+bookname;
  sql+=" and bookname like '%"+bookname+"%' ";
  }
  sql+=" order by id desc";
  System.out.println(sql);
  PageManager pageManager = PageManager.getPage(url, 10, request);
  pageManager.doList(sql);
  PageManager bean = (PageManager) request.getAttribute("page");
  ArrayList<Bookyy> nlist = (ArrayList) bean.getCollection();
   %>
  <body>
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<form name="f1" method="post" action="/libmanage/admin/rbookyy.jsp" >
  	<table id="mainbody" border="0" width="100%" cellspacing="1"
					class="tableform">
			<tr>
     				 <td width=10% height="31" align="center" style="font-size: 12px">书名&nbsp;:</td>
	    <td width=14% style="font-size: 12px" align="left">
	    
	    <select name="bookname" id="bookname">
	    <option value="">不限</option>
      <%
        for(Books obj:lblist)
        {
         %>
      <option value="<%=obj.getBookname()%>"><%=obj.getBookname()%></option>
      <%} %>
    </select>
	    
	    </td>
					<td width=10% align="center" style="font-size: 12px"> </td>
					<td width=46% style="font-size: 12px" align="left">  
				 
					
					</td>
					<td width=20% style="font-size: 12px" align="right">
					<input type="submit" class="btn3_mouseup" value="查询">&nbsp; 
					
					<input type="button"  class="btn3_mouseup" onclick="add();" value="图书预约">&nbsp;
					 
					
					</td>
			</tr>
			</table>
  	
  	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
<tr align="center">
          <td colspan="6"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">信息列表</td>
        </tr>
        <tr align="center">
          <td  class="itemtitle" > 预约时间</td>
          <td class="itemtitle"> 还书时间</td>
          <td class="itemtitle"> 书名</td>
          <td class="itemtitle"> 读者</td>
          <td class="itemtitle">相关说明</td>
          <td class="itemtitle">审核状态</td>
           
  </tr>
        
         <%
 
  for(Bookyy map:nlist)
  {
    %>
        <tr align="center">
          <td align="center"> <%=map.getYytime().equals("")?"&nbsp;":map.getYytime() %> </td>
          <td align="center"> <%=map.getHtime().equals("")?"&nbsp;":map.getHtime() %>  </td>
          <td align="center">  <%=map.getBookname().equals("")?"&nbsp;":map.getBookname() %> </td>
          <td align="center">  <%=map.getReadername().equals("")?"&nbsp;":map.getReadername() %> </td>
           <td align="center"> <%=map.getBei().equals("")?"&nbsp;":map.getBei() %> </td>
           <td align="center"> <%=map.getStatus() %> </td>
         
  </tr>
        <%} %>
        
        
        <script type="text/javascript">
          function update(no)
          {
          pop('/libmanage/admin/updaterbookyy.jsp?id='+no,'修改信息',450,171);
          }
           
          
          function add()
          {
          pop('/libmanage/admin/addrbookyy.jsp','图书预约',450,171);
          }
          </script>
        
        <tr align="center">
          <td align="right" colspan="13"><%=bean.getInfo() %></td>
        </tr>
      </table>
      </form>
</body>
</html>
<%
if(request.getAttribute("suc")!=null)
{
 %>
 <script type="text/javascript">
<!--
alert("添加成功")
//-->
</script>
<%}%>