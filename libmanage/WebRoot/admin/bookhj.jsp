<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.dao.BookhjDAO"%>
<%@page import="com.cz.dao.BooksDAO"%>
<%@page import="com.cz.dao.SreaderDAO"%>
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
		<script type="text/javascript" src="/libmanage/admin/commfiles/js/calendar/WdatePicker.js"></script>
		<script type="text/javascript">
		function update(no) {
			pop('/libmanage/admin/updatebookhj.jsp?id='+no,'修改信息',450,195);
		}
		
		function hbook(no) {
			pop('/libmanage/admin/hbook.jsp?id='+no,'还书',450,272);
		}
		
		function shbook(no) {
			pop('/libmanage/admin/shbook.jsp?id='+no,'续借',400,72);
		}
		
		function add() {
			pop('/libmanage/admin/addbookhj.jsp','图书借出',450,195);
		}
		</script>
	</head>
	<%
		try {
			BooksDAO booksDAO = new BooksDAO();
			List<Books> lblist = booksDAO.findAll();
			SreaderDAO sreaderDAO = new SreaderDAO();
			List<Sreader> cbslist = sreaderDAO.findAll();

			String readername = request.getParameter("readername") == null ? "" : request.getParameter("readername");
			String bookname = request.getParameter("bookname") == null ? "" : request.getParameter("bookname");

			String jid = request.getParameter("jid") == null ? "" : request.getParameter("jid");
			if (!jid.equals("")) {
				BookhjDAO bookhjDAO = new BookhjDAO();
				Bookhj b = bookhjDAO.findById(Integer.valueOf(jid.trim()));
				b.setSjstatus("已拒绝");
				bookhjDAO = new BookhjDAO();
				bookhjDAO.update(b);
			}

			String tid = request.getParameter("tid") == null ? "" : request.getParameter("tid");
			if (!tid.equals("")) {
				BookhjDAO bookhjDAO = new BookhjDAO();
				Bookhj b = bookhjDAO.findById(Integer.valueOf(tid.trim()));
				b.setSjstatus("已通过");
				bookhjDAO = new BookhjDAO();
				bookhjDAO.update(b);
			}

			String did = request.getParameter("did") == null ? "" : request.getParameter("did");
			if (!did.equals("")) {
				BookhjDAO bookhjDAO = new BookhjDAO();
				bookhjDAO.deleteById(Integer.valueOf(did.trim()));
			}

			//String url = "bookhj.jsp?1=1";
			//String sql = "from Bookhj where (hbtime is  null or hbtime='') ";
			String sql = "select * from bookhj where 1=1";

			if (!readername.equals("")) {
				//url += "&readername=" + readername;
				sql += " and readername like '%" + readername + "%' ";
			}
			if (!bookname.equals("")) {
				//url += "&bookname=" + bookname;
				sql += " and bookname like '%" + bookname + "%' ";
			}
			sql += " order by id desc";
			//System.out.println(sql);
			//PageManager pageManager = PageManager.getPage(url, 10, request);
			//pageManager.doList(sql);
			//PageManager bean = (PageManager) request.getAttribute("page");
			//PageManager bean = (PageManager) request.getAttribute("page");

			BookhjDAO bookhjDAO = new BookhjDAO();
			List<Bookhj> nlist = bookhjDAO.findBySql(sql);
	%>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<form name="f1" method="post" action="/libmanage/admin/bookhj.jsp">
			<table id="mainbody" border="0" width="100%" cellspacing="1"
				class="tableform">
				<tr>
					<td width=10% height="31" align="center" style="font-size: 12px">书名&nbsp;:</td>
					<td width=14% style="font-size: 12px" align="left">
						<select name="bookname" id="bookname">
							<option value="">
								不限
							</option>
							<%
							for (Books obj : lblist) {
							%>
								<option value="<%=obj.getBookname()%>" ><%=obj.getBookname()%></option>
							<%
							}
							%>
						</select>

					</td>
					<td width=10% align="center" style="font-size: 12px">借书人&nbsp;:</td>
					<td width=46% style="font-size: 12px" align="left">
						<select name="readername" id="readername">
							<option value="">不限</option>
							<%
							for (Sreader obj : cbslist) {
							%>
								<option value="<%=obj.getUname()%>"><%=obj.getUname()%></option>
							<%
							}
							%>
						</select>
					</td>
					<td width=20% style="font-size: 12px" align="right">
						<input type="submit" class="btn3_mouseup" value="查询">&nbsp;
						<input type="button" class="btn3_mouseup" onclick="add();" value="图书借出">&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
				<tr align="center">
					<td colspan="10" background="/libmanage/admin/commfiles/images/bg.gif" bgcolor="#FFFFFF" class="STYLE3">信息列表</td>
				</tr>
				<tr align="center">
					<td class="itemtitle">借出时间</td>
					<td class="itemtitle">还书时间</td>
					<td class="itemtitle">书名</td>
					<td class="itemtitle">读者</td>
					<td class="itemtitle">押金</td>
					<td class="itemtitle">相关说明</td>
					<td class="itemtitle">续借至</td>
					<td class="itemtitle">续借申请状态</td>
					<td class="itemtitle">续借审批</td>
					<td class="itemtitle">操作</td>
				</tr>
				<%for (Bookhj map : nlist) {%>
					<tr align="center">
						<td align="center"><%=map.getJtime().equals("") ? "&nbsp;" : map.getJtime()%></td>
						<td align="center"><%=map.getHtime().equals("") ? "&nbsp;" : map.getHtime()%></td>
						<td align="center"><%=map.getBookname().equals("") ? "&nbsp;" : map.getBookname()%>
							<%-- 
	          					<%Books b = (Books)dao.findById(map.getId()+"","Books"); %> 
	          					<%=b.getBookname()%> 
	          
	          				--%>
						</td>
						<td align="center"><%=map.getReadername().equals("") ? "&nbsp;" : map.getReadername()%></td>
						<td align="center"><%=map.getYjin().equals("") ? "&nbsp;" : map.getYjin()%></td>
						<td align="center"><%=map.getBei().equals("") ? "&nbsp;" : map.getBei()%></td>
						<td align="center"><%=map.getSjtime() == null ? "&nbsp;" : map.getSjtime()%></td>
						<td align="center"><%=map.getSjstatus() == null ? "&nbsp;" : map.getSjstatus()%></td>
						<td align="center">
							<%if (map.getSjstatus() != null) {%>
								<a href="bookhj.jsp?tid=<%=map.getId()%>">通过</a> &nbsp;|&nbsp;
								<a href="bookhj.jsp?jid=<%=map.getId()%>">拒绝</a>
							<%} else {%>
								&nbsp;
							<%}%>
						</td>
						<td align="center">
							<a href="javascript:hbook('<%=map.getId()%>')">还书</a>
							&nbsp;|&nbsp;
							<a href="javascript:shbook('<%=map.getId()%>')">续借</a>
							&nbsp;|&nbsp;
							<a href="javascript:update('<%=map.getId()%>')">修改</a>
							&nbsp;|&nbsp;
							<a href="/libmanage/admin/bookhj.jsp?did=<%=map.getId()%>">删除</a>
						</td>
					</tr>
				<%}%>
				<tr><td align="right" colspan="13"><%--%><%=bean.getInfo()--%></td></tr>
				</table>
		</form>
	</body>
	<script type="text/javascript">
	<%
	if (request.getAttribute("suc") != null) {
	%>
		alert("添加成功")
	</script>
<%  }
} catch (Exception e) {
	e.printStackTrace();
}
%>
</html>