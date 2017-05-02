<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>plugin_js/bbss/title.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
			<h3><public:i18n key="function.detail" module="title"></public:i18n></h3>
			<ul class="content-box-tabs">
				<li><a href="#tab1" class="default-tab"><public:i18n key="function.detail" module="title"></public:i18n></a></li>
			</ul>
			<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
			<div class="tab-content default-tab" id="tab1">
				<fieldset>
					<p align="center">
						<!-- 
						<b><public:i18n key="title" module="title"></public:i18n>:</b>
						 -->
						<label><font size="26" style="font-weight: bold">${result.title}</font></label>
					</p>
					<p>
						<!-- 
						<b><public:i18n key="content" module="title"></public:i18n>:</b>
						 -->
						<label>${result.content}</label>
					</p>
					
					<p align="right">
						<b><public:i18n key="username" module="title"></public:i18n>:</b>
						<label>${result.username}</label>--
						<!-- 
						<b><public:i18n key="datetime" module="title"></public:i18n>:</b>
						 -->
						<label>${result.datetime}</label>
					</p>
					
					<c:forEach items="${result2}" var="record" varStatus="ind">
						
						<c:choose>
						<c:when test="${ind.index%2==0}">
						<div style="background-color:#f3f3f3;">
						</c:when>
						<c:otherwise>
						<div style="background-color:#FFFFFF">
						</c:otherwise>
						
						</c:choose>
							<p align="left">
								${record.username}:  ${record.content}
							</p>
							<p align="right">
								${ind.index+1}楼 --${record.datetimestr}
							</p>
						</div>
					</c:forEach>
					
					
					<form action="<%=basePath %>title/title_huifu.do" method="post" name="mainform">
					<input class="text-input small-input" type="hidden" id="title" name="title" rule="CHAR_M" value ="${result.id}"/>
					<table>
					
					<tr>
					    <td align="left" valign="top">
					    	<!-- 
						      <label><public:i18n key="content" module="message"></public:i18n></label>
						       -->
						      发表回复:
					    </td>
					    <td>
						       <textarea class="text-input textarea wysiwyg" id="content" name="content" cols="60" rows="5" rule="CHAR_M"></textarea>
						       <span id="content_errorMsg" class="input-notification png_bg"></span>
					     </td>
					</tr>
					<!-- 
					<tr>
					    <td>
					    	
						    <public:i18n key="username" module="message"></public:i18n>
						  
						    
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="username" name="username" rule="CHAR_M"/>
						<span id="username_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					 -->  
					<!-- 
					<tr>
					    <td>
						     <public:i18n key="datetime" module="message"></public:i18n>:
					    </td>
					    <td>
						      <input class="text-input small-input" type="text" id="datetime" name="datetime" rule="DATE_N" readonly="readonly"/>
						     <script>
							$("#datetime").datepicker({dateFormat:'yy-mm-dd'}); 
						    </script>
						     <span id="datetime_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					 -->
					<tr>
					    <td>
					    </td>
					    <td>
							<input class="button" type="button" name="submitButton" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
					    </td>
					 </tr>
					</table>
					<div class="clear"></div>
					</form>
					
					<!-- 
							<p>
								<input class="button" type="button" id="cancelButton" name="cancelButton" value='<public:i18n key="button.cancel" module="common"></public:i18n>' />
							</p>
							 -->
				</fieldset>
				<div class="clear"></div>
			</div>
			</div>
			</div>
			</div>
		</body>
	</html>
