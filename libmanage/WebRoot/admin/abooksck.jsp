<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.dao.SreaderDAO"%>
<%@page import="com.cz.dao.SysprosDAO"%>
<%@page import="com.cz.dao.BooksDAO"%>
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
		SysprosDAO sysprosdao = new SysprosDAO();
		String sql = "select * from syspros where infoa = '出版社'";
		List<Syspros> cbslist = sysprosdao.findBySql(sql);
		String bookname = request.getParameter("bookname") == null ? "" : request.getParameter("bookname");
		String cbs = request.getParameter("cbs") == null ? "" : request.getParameter("cbs");
		String isbn = request.getParameter("isbn") == null ? "" : request.getParameter("isbn");

		String sid = request.getParameter("sid") == null ? "" : request.getParameter("sid");

		if (!sid.equals("")) {
			Sreader map = (Sreader) request.getSession().getAttribute("reader");
			SreaderDAO sreaderdao = new SreaderDAO();
			map = sreaderdao.findById(map.getId());
			String sc = map.getSc();
			sc += "-" + sid + "-";
			map.setSc(sc.replaceAll("null", ""));
			sreaderdao = new SreaderDAO();
			sreaderdao.update(map);
		}

		String did = request.getParameter("did") == null ? "" : request.getParameter("did");
		if (!did.equals("")) {
			BooksDAO booksdao = new BooksDAO();
			booksdao.deleteById(Integer.valueOf(did.trim()));
		}

		String url = "abooksck.jsp?1=1";
		sql = "select * from books where 1=1 ";

		if (!bookname.equals("")) {
			url += "&bookname=" + bookname;
			sql += " and bookname like '%" + bookname + "%' ";
		}

		if (!cbs.equals("")) {
			url += "&cbs=" + cbs;
			sql += " and cbs like '%" + cbs + "%' ";
		}

		if (!isbn.equals("")) {
			url += "&isbn=" + isbn;
			sql += " and isbn like '%" + isbn + "%' ";
		}
		HashMap m = new HashMap();
		m.put("cbs", cbs);
		if (!isbn.equals("")) {
			url += "&isbn=" + isbn;
			sql += " and isbn like '%" + isbn + "%' ";
		}
		sql += " order by id desc";
		System.out.println(sql);
		//PageManager pageManager = PageManager.getPage(url, 5, request);
		//pageManager.doList(sql);
		//PageManager bean = (PageManager) request.getAttribute("page");
		BooksDAO booksdao = new BooksDAO();
		List<Books> nlist = booksdao.findBySql(sql);
		//ArrayList<Books> nlist = (ArrayList) bean.getCollection();
	%>
	<body>
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<form name="f1" method="post" action="/libmanage/admin/abooksck.jsp" >
  	<table id="mainbody" border="0" width="100%" cellspacing="1"
					class="tableform">
			<tr>
     				 <td width=10% height="31" align="center" style="font-size: 12px">书名&nbsp;:</td>
	    <td width=14% style="font-size: 12px" align="left"><input name="bookname" value="<%=bookname%>" type="text"></td>
	     <td width=10% height="31" align="center" style="font-size: 12px">出版社&nbsp;:</td>
	    <td width=14% style="font-size: 12px" align="left">
	    
	     <select name="cbs" id="cbs">
	     <option value="">不限</option>
   <%
   	for (Syspros obj : cbslist) {
   %>
        <option value="<%=obj.getProname()%>"><%=obj.getProname()%></option>
        <%
        	}
        %>
  </select>
	    
	    </td>
					<td width="10%" align="center" style="font-size: 12px">ISBN :</td>
					<td width="" style="font-size: 12px" align="left"><input value="<%=isbn%>"  name="issn" type="text"></td>
					<td width="20%" style="font-size: 12px" align="right">
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
          
  </tr>
        
         <%
                 	for (Books map : nlist) {
                 %>
        <tr align="center">
         <td align="center"> <img src="/libmanage/upfile/<%=map.getFilename()%>" height="80" /></td>
          <td align="center"> <%=map.getIsbn().equals("") ? "&nbsp;" : map.getIsbn()%> </td>
          <td align="center"> <%=map.getBookname().equals("") ? "&nbsp;" : map
						.getBookname()%>  </td>
          <td align="center">  <%=map.getPrice().equals("") ? "&nbsp;" : map
								.getPrice()%> </td>
          <td align="center">  <%=map.getTslb().equals("") ? "&nbsp;" : map.getTslb()%> </td>
          <td align="center">  <%=map.getCbs().equals("") ? "&nbsp;" : map.getCbs()%> </td>
          <td align="center"> <%=map.getKucun().equals("") ? "&nbsp;" : map
								.getKucun()%> </td>
          
  </tr>
        <%
        	}
        %>
        
      </table>
      </form>
</body>
<%
	if (request.getAttribute("suc") != null) {
%>
		<script type="text/javascript">
		alert("添加成功")
		</script>
<%
	}
%>
</html>