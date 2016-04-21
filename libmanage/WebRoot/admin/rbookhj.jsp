<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Sreader"%>
<%@page import="com.cz.entity.Bookhj"%>
 
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
  
  
  String jid = request.getParameter("jid")==null?"":request.getParameter("jid");
  
  if(!jid.equals(""))
  {
  Bookhj b = (Bookhj)dao.load(jid,"Bookhj");
  b.setSjstatus("已拒绝");
  dao.update(b);
  }
  
  String tid = request.getParameter("tid")==null?"":request.getParameter("tid");
  
  if(!tid.equals(""))
  {
  Bookhj b = (Bookhj)dao.load(tid,"Bookhj");
  b.setSjstatus("已通过");
  dao.update(b);
  }
  
  
  String did = request.getParameter("did")==null?"":request.getParameter("did");
  
  if(!did.equals(""))
  {
  dao.delete(did,"Bookhj");
  }
  
  
  
  String url = "rbookhj.jsp?1=1";
  String sql = "from Bookhj  where (hbtime is  null or hbtime='') and  readername='"+reader.getUname()+"'"; 
  
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
  ArrayList<Bookhj> nlist = (ArrayList) bean.getCollection();
   %>
  <body>
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<form name="f1" method="post" action="/libmanage/admin/rbookhj.jsp" >
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
					<td width=10% align="center" style="font-size: 12px"></td>
					<td width=46% style="font-size: 12px" align="left">  
					
					
					
					</td>
					<td width=20% style="font-size: 12px" align="right">
					<input type="submit" class="btn3_mouseup" value="查询">&nbsp; 
					 
					
					</td>
			</tr>
			</table>
  	
  	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
<tr align="center">
          <td colspan="10"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">信息列表</td>
        </tr>
        <tr align="center">
          <td  class="itemtitle" > 借出时间</td>
          <td class="itemtitle"> 还书时间</td>
          <td class="itemtitle"> 书名</td>
          <td class="itemtitle"> 读者</td>
          <td class="itemtitle">押金</td>
          <td class="itemtitle">相关说明</td>
          
          <td class="itemtitle">续借至</td>
          <td class="itemtitle">续借申请状态</td>
         
          
          <td class="itemtitle">操作</td>
  </tr>
        
         <%
 
  for(Bookhj map:nlist)
  {
    %>
        <tr align="center">
          <td align="center"> <%=map.getJtime().equals("")?"&nbsp;":map.getJtime() %> </td>
          <td align="center"> <%=map.getHtime().equals("")?"&nbsp;":map.getHtime() %>  </td>
          <td align="center">  <%=map.getBookname().equals("")?"&nbsp;":map.getBookname() %> </td>
          <td align="center">  <%=map.getReadername().equals("")?"&nbsp;":map.getReadername() %> </td>
          <td align="center"> <%=map.getYjin().equals("")?"&nbsp;":map.getYjin() %> </td>
           <td align="center"> <%=map.getBei().equals("")?"&nbsp;":map.getBei() %> </td>
           
           <td align="center"> <%=map.getSjtime()==null?"&nbsp;":map.getSjtime() %> </td>
           <td align="center"> <%=map.getSjstatus()==null?"&nbsp;":map.getSjstatus() %> </td>
           
           
          <td align="center">
          
          
          <a href="javascript:shbook('<%=map.getId()%>')">续借</a>
          
          
          
          
          </td>
  </tr>
        <%} %>
        
        
        <script type="text/javascript">
          function update(no)
          {
          pop('/libmanage/admin/updaterbookhj.jsp?id='+no,'修改信息',450,195);
          }
          
          function hbook(no)
          {
          pop('/libmanage/admin/hbook.jsp?id='+no,'还书',450,272);
          }
          
          function shbook(no)
          {
          pop('/libmanage/admin/rshbook.jsp?id='+no,'续借',400,72);
          }
          
          function add()
          {
          pop('/libmanage/admin/addrbookhj.jsp','图书借出',450,195);
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