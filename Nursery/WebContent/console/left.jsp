<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�����׶�԰����ϵͳ</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE3 {
	font-size: 12px;
	color: #435255;
}
.STYLE4 {font-size: 12px}
.STYLE5 {font-size: 12px; font-weight: bold; }
-->
</style></head>

<body>
<%
String userRole = (String)request.getSession().getAttribute("PRO_USER_ROLE");
%>
<table width="147" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="images/main_34.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9%">&nbsp;</td>
        <td width="83%" onClick="document.getElementById('umTr').style.display = document.getElementById('umTr').style.display == 'none'? '' : 'none'"><div align="center" class="STYLE5"><a style="cursor:pointer">�û�����</a></div></td>
        <td width="8%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr id="umTr" style="display:none">
    <td valign="top"><div align="center">
      <table width="82%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="images/main_40.gif" width="28" height="28"></td>
              <td width="99"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'"><a href="/Nursery/console/updatepassword.jsp" target="rightFrame">�޸�����</a></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>

        <tr>
          <td height="5">&nbsp;</td>
        </tr>
      </table>
    </div></td>
  </tr>

  <tr>
    <td height="23" background="images/main_34_1.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9%">&nbsp;</td>
        <td width="83%" onClick="document.getElementById('xjTr').style.display = document.getElementById('xjTr').style.display == 'none'? '' : 'none'"><div align="center" class="STYLE4"><a style="cursor:pointer">ѧ����Ϣ</a></div></td>
        <td width="8%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
   <tr id="xjTr" style="display:none">
    <td valign="top"><div align="center">
      <table width="82%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="images/main_40.gif" width="28" height="28"></td>
              <td width="99"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'"><a href="/Nursery/console/inqstudent.jsp" target="rightFrame">��ѯѧ��</a></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>

        	<%
	if ("admin".equals(userRole)) {
		%>
        <tr>
          <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="images/main_46.gif" width="28" height="28"></td>
              <td width="99"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'"><a href="/Nursery/console/addstudent.jsp" target="rightFrame">���ѧ��</a></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
        <%
}
%>
        <tr>
          <td height="5">&nbsp;</td>
        </tr>
      </table>
    </div></td>
  </tr>
<%
if ("admin".equals(userRole) || "teacher".equals(userRole)){

	%>
	 <tr>
    <td height="23" background="images/main_34_1.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9%">&nbsp;</td>
        <td width="83%" onClick="document.getElementById('tcTr').style.display = document.getElementById('tcTr').style.display == 'none'? '' : 'none'"><div align="center" class="STYLE4"><a style="cursor:pointer">��ʦ����</a></div></td>
        <td width="8%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr id="tcTr" style="display:none">
    <td valign="top"><div align="center">
      <table width="82%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="38"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="images/main_40.gif" width="28" height="28"></td>
              <td width="99"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'"><a href="/Nursery/console/inqteacher.jsp" target="rightFrame">��ʦ��ѯ</a></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
	<%
	if ("admin".equals(userRole)) {
	%>
        <tr>
          <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="images/main_46.gif" width="28" height="28"></td>
              <td width="99"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'"><a href="/Nursery/console/addteacher.jsp" target="rightFrame">��ӽ�ʦ</a></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="images/main_46.gif" width="28" height="28"></td>
              <td width="99"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'"><a href="/Nursery/console/viewEvaluate.jsp" target="rightFrame">��ʦ����</a></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="5">&nbsp;</td>
        </tr>
	<%
	}
	%>
      </table>
    </div></td>
  </tr>
	<%
}
%>

  <tr>
    <td height="19" background="images/main_69.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="24%">&nbsp;</td>
        <td width="76%" valign="bottom"><span class="STYLE3">�汾��2011 v1.0</span></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
