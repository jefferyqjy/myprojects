<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_ward" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">病房编号：</td>
      <td><input class="easyui-validatebox" type="text" name="number" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">所属科室：</td>
      <td><input class="easyui-validatebox" type="text" name="offices" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">档次：</td>
      <td><input name="grade" type="radio" value="高档" checked><span>高档</span>&nbsp;&nbsp;<input type="radio" name="grade" value="中档"><span>中档</span>&nbsp;&nbsp;<input type="radio" name="grade" value="低档"><span>低档</span></td>
    </tr>
    <tr>
      <td align="right">价格：</td>
      <td><input class="easyui-numberbox" type="text" name="price" required="true" missingMessage="该输入项只能输入数字" customize="Customize" /></td>
    </tr>
  </table>
</form>
