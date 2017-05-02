<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/public.tld" prefix="public" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<title>Simpla Admin | Sign In</title>
		<!-- 
		<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=basePath %>resources/css/invalid.css" type="text/css" media="screen" />	
		 -->
		<meta content="MSHTML 6.00.2800.1611" name="GENERATOR"/>
		<link rel="stylesheet" href="<%=basePath %>images/css1.css" type="text/css" rel="stylesheet"/>
		<link rel="stylesheet" href="<%=basePath %>images/newhead.css" type="text/css" rel="stylesheet"/>
		
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>resources/scripts/simpla.jquery.configuration.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>resources/scripts/facebox.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>sys_js/login/login.js"></script>
		
		
		<style type="text/css">
		.img22 {border:blue solid 0px;}
		
		.img22:hover{border:red solid 0px; background-color: red; }
</style>
	</head>
		
		
<BODY bgColor="#eef8e0" leftMargin="0" topMargin="0" MARGINWIDTH="0" MARGINHEIGHT="0">
<form name="mainform" method="post" action="<%=basePath %>admin/admin_login.do">
<TABLE cellSpacing="0" cellPadding="0" width="1004" border="0">
  <TBODY>
  <TR>
    <TD colSpan="6"><IMG height="92" alt="" src="images/crm_1.gif" 
    width="345"/></TD>
    <TD colSpan="4"><IMG height="92" alt="" src="images/crm_2.gif" 
    width="452"/></TD>
    <TD><IMG height="92" alt="" src="images/crm_3.gif" width="207"/></TD></TR>
  <TR>
    <TD colSpan="6"><IMG height="98" alt="" src="images/crm_4.gif" 
    width="345"/></TD>
    <TD colSpan="4"><IMG height="98" alt="" src="images/crm_5.gif" 
    width="452"/></TD>
    <TD><IMG height="98" alt="" src="images/crm_6.gif" width="207"/></TD></TR>
  <TR>
    <TD rowSpan="5"><IMG height="370" alt="" src="images/crm_7.gif" 
    width="59"/></TD>
    <TD colSpan="5"><IMG height="80" alt="" src="images/crm_8.gif" 
    width="286"/></TD>
    <TD colSpan="4"><IMG height="80" alt="" src="images/crm_9.gif" 
    width="452"/></TD>
    <TD><IMG height="80" alt="" src="images/crm_10.gif" width="207"/></TD></TR>
  <TR>
    <TD><IMG height="110" alt="" src="images/crm_11.gif" width="127"/></TD>
    <TD background="images/crm_12.gif" colSpan="6">
      <TABLE id="table1" cellSpacing="0" cellPadding="0" width="98%" border="0">
        <TBODY>
        <TR>
          <TD>
            <TABLE id="table2" cellSpacing="1" cellPadding="0" width="100%" 
              border="0"><TBODY>
              <TR>
                
                <TD colspan="2">
                	 <div class="notification information png_bg" style="display: none;" id="login_error_message"></div>
                </TD>
                </TR>
             
              
              <TR>
                <TD align="middle" width="81">
                
                	<FONT color="#ffffff">用户名：</FONT>
                	
                	</TD>
                <TD align="left">
                	<input class="text-input" type="text" name="username" id="username"/>
                </TD>
                </TR>
                <tr style="height: 8px">
                <td></td>
                <td></td>
                </tr>
              <TR align="left">
                <TD align="middle" width="81"><FONT color="#ffffff">密&nbsp; 
                码：</FONT></TD>
                <TD align="left">
                	<input class="text-input" type="password" name="password" id="password"/>
                </TD>
                </TR>
                 <tr style="height: 8px">
                <td></td>
                <td></td>
                </tr>
              <TR>
                <TD align="middle" width="81"><FONT color="#ffffff">类&nbsp; 
                型：</FONT></TD>
                <TD align="left">
                	<select name="usertype" id="usertype" class="medium-input" style="width: 150px">
							<option value="0">超级管理员</option>
							<option value="1">企业用户</option>
						</select>
                </TD>
                </TR>
                </TBODY>
                </TABLE>
               </TD>
               </TR>
               </TBODY>
               </TABLE>
               </TD>
    <TD colSpan="2" rowSpan="2"><IMG height="158" alt=""  src="images/crm_13.gif"  style="cursor:pointer" width="295"/></TD>
    <TD rowSpan="2"><IMG height="158" alt="" src="images/crm_14.gif"   width="207"/></TD></TR>
  <TR>
    <TD rowSpan="3"><IMG height="180" alt="" src="images/crm_15.gif" width="127"/></TD>
    <TD rowSpan="3"><IMG height="180" alt="" src="images/crm_16.gif" 
    width="24"/></TD>
    <TD>
    <!-- 
    <INPUT title=登录后台 type=image height=48 alt="" width=86 
      src="images/crm_17.gif" name=image>
        <input  type="image" height="48" alt="" width="86" id="signIn" value=""  src="images/crm_17.gif"/>
        <input  type="button" id="signIn" value="" style="background-image:images/crm_17.gif"/>
       -->
      <img src="images/crm_17.gif" alt=""  class ="img22"  style="cursor:pointer" id="signIn"/>
      
      </TD>
    <TD><IMG height="48" alt="" src="images/crm_18.gif" width="21"/></TD>
    <TD colSpan="2"><A href="#"><IMG 
      title="返回首页" height="48" alt="" src="images/crm_19.gif" class ="img22" style="cursor:pointer"  width="84"
      border="0"/></A></TD>
    <TD><IMG height="48" alt="" src="images/crm_20.gif" width="101"/></TD></TR>
  <TR>
    <TD colSpan="5" rowSpan="2"><IMG height="132" alt="" 
      src="images/crm_21.gif" width="292"/></TD>
    <TD rowSpan="2"><IMG height="132" alt="" src="images/crm_22.gif" 
      width="170"/></TD>
    <TD colSpan="2"><IMG height="75" alt="" src="images/crm_23.gif" 
    width="332"/></TD></TR>
  <TR>
    <TD colSpan="2"><IMG height="57" alt="" src="images/crm_24.gif" 
    width="332"/></TD></TR>
  <TR>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="59"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="127"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="24"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="86"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="21"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="28"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="56"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="101"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="170"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" width="125"/></TD>
    <TD><IMG height="1" alt="" src="images/spacer.gif" 
  width="207"/></TD></TR></TBODY></TABLE></FORM></BODY></HTML>
