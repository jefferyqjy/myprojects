<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>plugin_js/announce/announce.js"></script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="announce"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="announce"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>announce/announce_add.do" method="post" name="mainform">
					<fieldset>
					<p>
						<b><public:i18n key="title" module="announce"></public:i18n>:</b>
						<input class="text-input small-input" type="text" id="title" name="title" rule="CHAR_M_120"/>
						<span id="title_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<label><public:i18n key="content" module="announce"></public:i18n></label>
						<textarea class="text-input textarea wysiwyg" id="content" name="content" cols="60" rows="5" rule="CHAR_M_1024"></textarea>
						<span id="content_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<b><public:i18n key="date" module="announce"></public:i18n>:</b>
						<input class="text-input small-input" type="text" id="date" name="date" rule="DATE_M" readonly="readonly"/>
						<script>
							$("#date").datepicker({dateFormat:'yy-mm-dd'}); 
						</script>
						<span id="date_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<b><public:i18n key="level" module="announce"></public:i18n>:</b>
						<input type="radio" name="level" value="高级" rule="RADI_M_高级;中级;低级" />高级
						<input type="radio" name="level" value="中级" rule="RADI_M_高级;中级;低级" />中级
						<input type="radio" name="level" value="低级" rule="RADI_M_高级;中级;低级" />低级
						<span id="level_errorMsg" class="input-notification png_bg"></span>
					</p>
					<p>
						<b><public:i18n key="sync" module="announce"></public:i18n>:</b>
						<input type="checkbox" name="sync" value="是" rule="CHEC_M_是;否" />是
						<input type="checkbox" name="sync" value="否" rule="CHEC_M_是;否" />否
						<span id="sync_errorMsg" class="input-notification png_bg"></span>
					</p>
						<p>
							<input class="button" type="button" name="submitButton" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
						</p>
					</fieldset>
					<div class="clear"></div>
					</form>
				</div>
				</div>
				</div>
			</div>
		</body>
	</html>
