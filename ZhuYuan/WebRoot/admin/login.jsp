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
      <td align="right">�û�����</td>
      <td><input class="easyui-validatebox" type="text" name="name" required="true" validType="pattern" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">���룺</td>
      <td><input class="easyui-validatebox" type="password" name="pwd" required="true" validType="pattern" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��ݣ�</td>
      <td><select id="cc" class="easyui-combobox" name="status" editable="false" required="true">
          <option value="0">����Ա</option>
          <option value="1">��ͨ�û�</option>
          <option value="2">����</option>
        </select></td>
    </tr>
    <tr>
      <td colspan="2"><span style="color:#F00">ע���综�ߵ�¼���û���Ϊ�Һű�ţ�����Ϊ111111</span></td>
    </tr>
  </table>
</form>
