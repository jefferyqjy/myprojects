<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
.table-padding td {padding:5px;}
</style>
<form id="form_login" method="post">
  <table class="table-padding">
    <tr>
      <td align="right">用户名：</td>
      <td><input class="easyui-validatebox" type="text" name="name" required="true" validType="pattern" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">密码：</td>
      <td><input class="easyui-validatebox" type="password" name="pwd" required="true" validType="pattern" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">身份：</td>
      <td><select id="cc" class="easyui-combobox" name="status" editable="false" required="true">
          <option value="0">管理员</option>
          <option value="1">普通用户</option>
          <option value="2">患者</option>
        </select></td>
    </tr>
    <tr>
      <td colspan="2"><span style="color:#F00">注：如患者登录，用户名为挂号编号，密码为111111</span></td>
    </tr>
  </table>
</form>
