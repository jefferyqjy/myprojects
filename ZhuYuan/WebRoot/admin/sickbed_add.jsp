<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_sickbed" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">²¡´²±àºÅ£º</td>
      <td><input class="easyui-validatebox" type="text" name="number" required="true" customize="Customize" /></td>
    </tr>
  </table>
</form>
