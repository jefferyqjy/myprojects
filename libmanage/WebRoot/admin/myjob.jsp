<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" /> 
  </head>
  
  <body>
  	<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
        <tr align="center">
          <td colspan="2"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">待办事项</td>
        </tr>
        <tr align="center">
          <td width="23%" align="center"> 专家审核 </td>
          <td width="77%" align="left"><a href="#"><span class="style1">
            <input type="text" name="textfield2" style="width:150px;" />
          </span></a> </td>
        </tr>
        <tr align="center">
          <td align="center"> 专家审核 </td>
          <td align="left"><a href="#"><span class="style1">
            <input type="text" name="textfield" style="width:150px;" />
          </span></a> </td>
        </tr>
        <tr align="center">
          <td align="center"> 专家审核 </td>
          <td align="left"><a href="#"><span class="style1">
            <input type="text" name="textfield3" style="width:150px;" />
          </span></a> </td>
        </tr>
        <tr align="center">
          <td colspan="2" align="center"><form name="form1" method="post" action="">
            <label>
              <input type="submit" name="button" id="button" value="提交">
            </label>
          </form>          </td>
        </tr>
      </table>
</body>
</html>

