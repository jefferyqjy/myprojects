<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="form_ward" method="post">
  <table cellpadding="5">
    <tr>
      <td align="right">������ţ�</td>
      <td><input class="easyui-validatebox" type="text" name="number" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">�������ң�</td>
      <td><input class="easyui-validatebox" type="text" name="offices" required="true" customize="Customize" /></td>
    </tr>
    <tr>
      <td align="right">���Σ�</td>
      <td><input name="grade" type="radio" value="�ߵ�" checked><span>�ߵ�</span>&nbsp;&nbsp;<input type="radio" name="grade" value="�е�"><span>�е�</span>&nbsp;&nbsp;<input type="radio" name="grade" value="�͵�"><span>�͵�</span></td>
    </tr>
    <tr>
      <td align="right">�۸�</td>
      <td><input class="easyui-numberbox" type="text" name="price" required="true" missingMessage="��������ֻ����������" customize="Customize" /></td>
    </tr>
  </table>
</form>
