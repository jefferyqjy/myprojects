<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.entity.Syspros"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Sreader"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
		<script type="text/javascript" src="/libmanage/js/My97DatePicker/WdatePicker.js"></script>
  </head>
  <%
  CommonDAO dao = Info.getDao(request);
  List<Books> lblist = dao.findByHql("from Books  ");
  List<Sreader> cbslist = dao.findByHql("from Sreader ");
   %>
  <body>
  <form name="f1" method="post" action="control!addbookhj"  >
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
        <tr align="center"  style="display: none">
          <td colspan="3"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3"> </td>
        </tr>
        <tr align="center">
          <td width="25%" height="25" align="center"> 借出时间</td>
        <td width="75%" height="25" align="left"><span class="style1">
      <input name="jtime" id="jtime"  type="text" size="12" onclick="WdatePicker();" /> &nbsp;</span></td>
      </tr>
        <tr align="center">
          <td height="25" align="center">还书时间</td>
    <td height="25" align="left"><span class="style1">
            <input name="htime" id="htime"  type="text" size="12"  onclick="WdatePicker();" />
          </span> </td>
        </tr>
        <tr align="center">
          <td height="25" align="center">书名</td>
    <td height="25" align="left"><select name="bookname" id="bookname">
      <%
        for(Books obj:lblist)
        {
         %>
      <option value="<%=obj.getId()%>"><%=obj.getBookname()%></option>
      <%} %>
    </select></td>
      </tr>
         <tr align="center">
          <td height="25" align="center">读者</td>
    <td height="25" align="left">
        <select name="readername" id="readername">
        <%
        for(Sreader obj:cbslist)
        {
         %>
        <option value="<%=obj.getUname()%>"><%=obj.getUname()%></option>
        <%} %>
        </select>        </td>
        </tr>
         <tr align="center">
          <td height="25" align="center">押金</td>
<td height="25" colspan="2" align="left"><label><span class="style1">
  <input name="yjin" id="yjin"  type="text" size="20"  />
  </span></label></td>
      </tr>
        
          <tr align="center">
          <td height="25" align="center">相关说明</td>
    <td height="25" colspan="2" align="left"><span class="style1">
            <input name="bei" type="text" id="bei" value="" size="50">
          </span> </td>
        </tr>
       
        

        <tr align="center">
          <td height="29" colspan="3" align="center">
            <label>
              <input type="submit" name="button" id="button" value="提交信息">
            </label> &nbsp;&nbsp;
            <input  type="reset" name="button2" id="button2" value="重新填写">                   </td>
        </tr>
      </table>
  </form> 
</body>
</html>
<script type="text/javascript" src="/libmanage/admin/commfiles/js/ajax.js"></script>

<script type="text/javascript"  >
<%
if(request.getAttribute("kjnum")!=null)
{
%>
alert("该用户借书数量超出可借数量");
<%}%>

<%
if(request.getAttribute("htime")!=null)
{
%>
alert("该用户有逾期未还图书");
<%}%>

</script>