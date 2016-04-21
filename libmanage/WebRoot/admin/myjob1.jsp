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
  	
  	<table id="mainbody" border="0" width="100%" cellspacing="1"
					class="tableform">
			<tr>
     				 <td width=8% height="31" align="right" style="font-size: 12px">省(直辖市)&nbsp;&nbsp;</td>
	    <td width=8% style="font-size: 12px" align="left"><select name="select">
			          <option>北京市</option>
			          <option>上海市</option>
			          <option>山东省</option>
			          <option>河北省</option>
			          <option>湖南省</option>
			          <option>广东省</option>
			          <option>江苏省</option>
			          <option>黑龙江省</option>
			          <option>海南省</option>
			        </select></td>
					<td width=8% align="right" style="font-size: 12px">市（区）&nbsp;&nbsp;</td>
					<td width=8% style="font-size: 12px" align="left"><select name="select2">
			          <option>武汉</option>
			          <option>黄石</option>
			          <option>十堰</option>
			          <option>襄阳</option>
			          <option>孝感</option>
			          <option>咸宁</option>
			          <option>黄冈</option>
			          <option>随州</option>
			          <option>恩施</option>
			        </select></td>
					<td width=5% align="right" style="font-size: 12px">学校名称&nbsp;&nbsp;</td>
					<td width=15% style="font-size: 12px" align="left"><input name="text" type="text"></td>
					<td width=10% style="font-size: 12px" align="right"><input type="button" class="btn3_mouseup" value="查询">&nbsp;&nbsp;</td>
			</tr>
			</table>
  	
  	
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
        <tr align="center">
          <td colspan="4"  background="/libmanage/admin/commfiles/images/bg.gif"
				bgcolor="#FFFFFF" class="STYLE3">待办事项</td>
        </tr>
        <tr align="center">
          <td  class="itemtitle" > 事项名称 </td>
          <td class="itemtitle" width="45%"> 事项描述 </td>
          <td class="itemtitle" width="15%"> 申请人 </td>
          <td class="itemtitle"> 申请时间 </td>
        </tr>
        <tr align="center">
          <td align="left"> 教材审核 </td>
          <td align="left"><a href="#">九年义务教育小学三年级语文教材审核</a> </td>
          <td> 刘XX </td>
          <td align="center"> 2011-04-05&nbsp;18:23 </td>
        </tr>
        <tr align="center">
          <td align="left"> 专家审核 </td>
          <td align="left"><a href="#">推荐专家审核</a> </td>
          <td> 李XX </td>
          <td align="center"> 2011-04-15&nbsp;19:25 </td>
        </tr>
      </table>
  </body>
</html>

