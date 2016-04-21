<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Syspros"%>
<%@page import="com.cz.entity.Sreader"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
	<script type="text/javascript" src="/libmanage/js/popup.js"></script>
  </head>
  
  	 <%
String admin = "";
String utype = "";
if(session.getAttribute("admin")!=null)
{
Sysuser map = (Sysuser)request.getSession().getAttribute("admin"); 
 admin = "pupdatesysusers.jsp";
 utype="管理员";
}

if(session.getAttribute("reader")!=null)
{
Sreader map = (Sreader)request.getSession().getAttribute("reader"); 
 admin = "rupdatesreader.jsp";
 utype="读者";
}
%>
  
  <%
  CommonDAO dao = Info.getDao(request);
    List<Syspros> cbslist = dao.findByHql("from Syspros where infoa='出版社'");
   String bookname = request.getParameter("bookname")==null?"":request.getParameter("bookname");
  String cbs = request.getParameter("cbs")==null?"":request.getParameter("cbs");
  String ibsn = request.getParameter("ibsn")==null?"":request.getParameter("ibsn");
  
    String sid = request.getParameter("sid")==null?"":request.getParameter("sid");
  
  if(!sid.equals(""))
  {
  Sreader map = (Sreader)request.getSession().getAttribute("reader"); 
  map = (Sreader)dao.load(map.getId()+"","Sreader");
  String sc = map.getSc();
  sc += "-"+sid+"-";
  map.setSc(sc.replaceAll("null",""));
  dao.update(map);
  }
  
  
  String did = request.getParameter("did")==null?"":request.getParameter("did");
  
  if(!did.equals(""))
  {
  dao.delete(did,"Books");
  }
  
  String url = "booksck.jsp?1=1";
  String sql = "from Books  where 1=1 ";
  
  if(!bookname.equals(""))
  {
  url+="&bookname="+bookname;
  sql+=" and bookname like '%"+bookname+"%' ";
  }
  
  
  
  if(!cbs.equals(""))
  {
  url+="&cbs="+cbs;
  sql+=" and cbs like '%"+cbs+"%' ";
  }
  
  if(!ibsn.equals(""))
  {
  url+="&ibsn="+ibsn;
  sql+=" and ibsn like '%"+ibsn+"%' ";
  }
  HashMap m = new HashMap();
  m.put("cbs",cbs);
  if(!ibsn.equals("")){
  url+="&ibsn="+ibsn;
  sql+=" and ibsn like '%"+ibsn+"%' ";
  }
  sql+=" order by id desc";
  System.out.println(sql);
  PageManager pageManager = PageManager.getPage(url, 5, request);
  pageManager.doList(sql);
  PageManager bean = (PageManager) request.getAttribute("page");
  ArrayList<Books> nlist = (ArrayList) bean.getCollection();
   %>
  <body>
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<form name="f1" method="post" action="/libmanage/admin/booksck.jsp" >
  	<table id="mainbody" border="0" width="100%" cellspacing="1"
					class="tableform">
			<tr>
     				 <td width=10% height="31" align="center" style="font-size: 12px">书名&nbsp;:</td>
	    <td width=14% style="font-size: 12px" align="left"><input name="bookname" value="<%=bookname %>" type="text"></td>
	     <td width=10% height="31" align="center" style="font-size: 12px">出版社&nbsp;:</td>
	    <td width=14% style="font-size: 12px" align="left">
	    
	     <select name="cbs" id="cbs">
	     <option value="">不限</option>
   <%
        for(Syspros obj:cbslist)
        {
        
         %>
        <option value="<%=obj.getProname()%>"><%=obj.getProname()%></option>
        <%} %>
  </select>
	    
	    </td>
					<td width=10% align="center" style="font-size: 12px">ISBN :</td>
					<td width= style="font-size: 12px" align="left"><input value="<%=ibsn %>"  name="issn" type="text"></td>
					<td width=20% style="font-size: 12px" align="right">
					<input type="submit" class="btn3_mouseup" value="查询">
					&nbsp;
					 
					</td>
			</tr>
			</table>
  	
  	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
<tr align="center">
          <td colspan="8"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">信息列表</td>
        </tr>
        <tr align="center">
        <td  class="itemtitle" > 封面</td>
          <td  class="itemtitle" > ISBN</td>
          <td class="itemtitle"> 书名</td>
          <td class="itemtitle"> 价格</td>
          <td class="itemtitle"> 图书类别</td>
          <td class="itemtitle"> 出版社</td>
          <td class="itemtitle">库存</td>
          <%
          if(utype.equals("读者")){
           %>
          <td class="itemtitle">收藏</td>
         <%} %>
  </tr>
        
         <%
 
  for(Books map:nlist)
  {
   Sreader read = (Sreader)request.getSession().getAttribute("reader"); 
 read = (Sreader)dao.load(read.getId()+"","Sreader");
 if(read.getSc()==null)read.setSc("");
 
        boolean b = false;
        if(read.getSc().indexOf("-"+map.getId()+"-")>-1)
        b = true;
    %>
        <tr align="center">
         <td align="center"> <img src="/libmanage/upfile/<%=map.getFilename() %>" height="80" /></td>
          <td align="center"> <%=map.getIsbn().equals("")?"&nbsp;":map.getIsbn() %> </td>
          <td align="center"> <%=map.getBookname().equals("")?"&nbsp;":map.getBookname() %>  </td>
          <td align="center">  <%=map.getPrice().equals("")?"&nbsp;":map.getPrice() %> </td>
          <td align="center">  <%=map.getTslb().equals("")?"&nbsp;":map.getTslb() %> </td>
          <td align="center">  <%=map.getCbs().equals("")?"&nbsp;":map.getCbs() %> </td>
          <td align="center"> <%=map.getKucun().equals("")?"&nbsp;":map.getKucun() %> </td>
          
          <td align="center"> 
          
          <%
          if(utype.equals("读者")){
          if(b==false){
           %>
          <a href="booksck.jsp?sid=<%=map.getId()%>">收藏</a>
          <%}else{ %>
      <font color="orange">已收藏</font> 
          
          <%}} %>
          
           </td>
          
  </tr>
        <%} %>
        
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

<%=Info.tform(m)%>