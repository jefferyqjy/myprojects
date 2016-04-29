<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><public:i18n key="system.name" module="system"></public:i18n></title>
			<script type="text/javascript" src="<%=basePath %>pro_js/student/student.js"></script>
			<script type="text/javascript">
				function batchInserts(){
					var url = "student/student_batchInsert.do";
					self.location.href = encodeURI(url);
				}
			</script>
		</head>

		<body>
			<div id="main-content">
			<div class="content-box">
			<div class="content-box-header">
				<h3><public:i18n key="function.add" module="student"></public:i18n></h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab"><public:i18n key="function.add" module="student"></public:i18n></a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="content-box-content">
			<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
				<div class="tab-content default-tab" id="tab1">
					<form action="<%=basePath %>student/student_add.do" method="post" name="mainform">
					<table>
					<tr>
						<td colspan="2" style="padding-left: 1100px">
							<input class="button" type="button" name="batchInsert" value="批量导入" onclick="batchInserts()"/>
						</td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="number" module="student"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="number" name="number" rule="CHAR_M_20"/>
						<span id="number_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="name" module="student"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="name" name="name" rule="CHAR_M_20"/>
						<span id="name_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="age" module="student"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="age" name="age" rule="CHAR_M_20"/>
						<span id="age_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						      <public:i18n key="sex" module="student"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="sex" name="sex">
						    <option value="男">男</option>
						    <option value="女">女</option>
					        </select>
						    <span id="sex_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="collage" module="student"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="collage" name="collage" rule="CHAR_M_20"/>
						<span id="collage_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<tr>
					    <td>
						    <public:i18n key="className" module="student"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="className" name="className" rule="CHAR_M_20"/>
						<span id="className_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
					<!-- 
					<tr>
					    <td>
						    <public:i18n key="examId" module="student"></public:i18n>:
					    </td>
					    <td>
						    <input class="text-input small-input" type="text" id="examId" name="examId" rule=""/>
						<span id="examId_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr> -->
					<tr>
					    <td>
						      <public:i18n key="money" module="student"></public:i18n>:
					    </td>
					    <td>
					        <select class="select" type="text" id="money" name="money">
						    <option value="未缴">未缴</option>
						    <option value="已缴">已缴</option>
					        </select>
						    <span id="money_errorMsg" class="input-notification png_bg"></span>
					    </td>
					</tr>
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
				</div>
				</div>
				</div>
			</div>
		</body>
	</html>
