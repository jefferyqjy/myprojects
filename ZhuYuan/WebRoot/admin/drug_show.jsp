<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_drug_show" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">ҩƷ���ƣ�</td>
      <td><input class="easyui-validatebox" type="text" name="name" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">���</td>
      <td><input class="easyui-validatebox" type="text" name="norms" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��׼�ĺţ�</td>
      <td><input class="easyui-validatebox" type="text" name="approval" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">�������ң�</td>
      <td><input class="easyui-validatebox" type="text" name="factory" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">��Ҫ�ɷݣ�</td>
      <td><textarea class="easyui-validatebox" name="ingredient" required="true" rows="4" cols="40"></textarea></td>
    </tr>
    <tr>
      <td align="right">��Ӧ֢״��</td>
      <td><textarea class="easyui-validatebox" name="treat" required="true" rows="4" cols="40"></textarea></td>
    </tr>
    <tr>
      <td align="right">�÷�������</td>
      <td><textarea class="easyui-validatebox" name="dosage" required="true" rows="4" cols="40"></textarea></td>
    </tr>
    <tr>
      <td align="right">�ۼۣ�</td>
      <td><input class="easyui-numberbox" type="text" name="price" required="true" missingMessage="��������ֻ����������" customize="Customize" /></td>
    </tr>
  </table>
</form>
