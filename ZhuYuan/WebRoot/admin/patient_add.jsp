<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_patient" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">����������</td>
      <td><input class="easyui-validatebox" type="text" name="truename" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">�Ա�</td>
      <td><input name="sex" type="radio" value="��" checked><span>��</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="Ů"><span>Ů</span></td>
    </tr>
    <tr>
      <td align="right">���䣺</td>
      <td><input class="easyui-numberbox" type="text" name="age" required="true" missingMessage="��������ֻ����������" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">�������ڣ�</td>
      <td><input class="easyui-datebox" type="text" name="birthday" required="true" panelWidth="190" editable="false" /></td>
    </tr>
    <tr>
      <td align="right">���壺</td>
      <td><input class="easyui-validatebox" type="text" name="nation" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">���֤���룺</td>
      <td><input class="easyui-validatebox" type="text" name="idcard" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��ϵ�绰��</td>
      <td><input class="easyui-numberbox" type="text" name="phone" required="true" missingMessage="��������ֻ����������" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��ͥסַ��</td>
      <td><input class="easyui-validatebox" type="text" name="address" required="true" customize="Customize" /></td>
    </tr>
  </table>
</form>
