<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/sys_jsp/base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><public:i18n key="system.name" module="system"></public:i18n></title>
		<script type="text/javascript" src="<%=basePath %>sys_js/admin/admin.js"></script>
	</head>
  
	<body>
		
		<div id="main-content"> <!-- Main Content Section with everything -->
			
			<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3><public:i18n key="function.manager" module="admin"></public:i18n></h3>
					
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab"><public:i18n key="function.list" module="admin"></public:i18n></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2"><public:i18n key="function.add" module="admin"></public:i18n></a></li>
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->
						
						<jsp:include page="/sys_jsp/notification.jsp"></jsp:include>
						<form id="searchForm" name="searchForm" action="admin/admin_query.do" method="post">
							<public:i18n key="username" module="admin"></public:i18n> ：<input type="text" name="username"/>
							<input name="Submit" type="submit" class="button" value='<public:i18n key="button.query" module="common"></public:i18n>' />
						</form>
						<c:choose>
							<c:when test="${empty result}">
							</c:when>
							<c:otherwise>
						<table>
							
							<thead>
								<tr>
								   <th><input class="check-all" type="checkbox" /></th>
								   <th><public:i18n key="username" module="admin"></public:i18n></th>
								   <th><public:i18n key="usertype" module="admin"></public:i18n></th>
								   <th><public:i18n key="action" module="common"></public:i18n></th>
								</tr>
								
							</thead>
						 
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="bulk-actions align-left">
											<select name="dropdown">
												<option value=""><public:i18n key="action.select" module="common"></public:i18n></option>
												<option value="D"><public:i18n key="label.delete" module="common"></public:i18n> </option>
											</select>
											<a class="button" id="applyButton" style="cursor: pointer;"><public:i18n key="button.apply" module="common"></public:i18n></a>
										</div>
										
										<div class="pagination">
											${pageInfo }
										</div> <!-- End .pagination -->
										<div class="clear"></div>
									</td>
								</tr>
							</tfoot>
							<tbody>
							<c:forEach items="${result}" var="user">
								<tr>
									<td><input name="primaryKey" type="checkbox" value="${user.username }"/></td>
									<td><a href="<%=basePath %>admin/admin_detail.do?username=${user.username }" title="title">${user.username }</a></td>
									<td>
										<c:if test="${user.usertype == '1'}">
											<public:i18n key="usertype.admin" module="system"></public:i18n>
										</c:if>
										<c:if test="${user.usertype == '2'}">
											<public:i18n key="usertype.user" module="system"></public:i18n>
										</c:if>
									</td>
									<td>
										<!-- Icons -->
										 <a style="cursor: pointer;" onclick="gotoEdit('<%=basePath %>admin/admin_edit.do?username=${user.username }')" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
										 <a style="cursor: pointer;" onclick="actionDel('<%=basePath %>admin/admin_del.do?username=${user.username }')" title="Delete"><img src="resources/images/icons/cross.png" alt="Delete" /></a> 
									</td>
								</tr>
								</c:forEach>
							</tbody>
							
						</table>
						</c:otherwise>
						</c:choose>
					</div> <!-- End #tab1 -->
					
					<div class="tab-content" id="tab2">
					
						<form action="<%=basePath %>admin/admin_add.do" method="post" name="addForm">
							
							<fieldset> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								<p>
									<label><public:i18n key="username" module="admin"></public:i18n></label>
										<input class="text-input small-input" type="text" id="username" name="username" rule="CHAR_M_120"/>
										<span id="username_errorMsg" class="input-notification png_bg"></span> <!-- Classes for input-notification: success, error, information, attention -->
								</p>
								<p>
									<label><public:i18n key="password" module="admin"></public:i18n></label>
										<input class="text-input small-input" type="password" id="password" name="password" rule="CHAR_M_120"/>
										<span id="password_errorMsg" class="input-notification png_bg"></span> <!-- Classes for input-notification: success, error, information, attention -->
								</p>
								<p>
									<label><public:i18n key="cpassword" module="admin"></public:i18n></label>
										<input class="text-input small-input" type="password" id="cpassword" name="cpassword" rule="CHAR_M_120"/>
										<span id="cpassword_errorMsg" class="input-notification png_bg"></span> <!-- Classes for input-notification: success, error, information, attention -->
								</p>
								<p>
									<label><public:i18n key="usertype" module="admin"></public:i18n></label>
									<input type="radio" name="usertype" checked="checked" value="1"/><public:i18n key="usertype.admin" module="system"></public:i18n>
									<input type="radio" name="usertype" value="2"/><public:i18n key="usertype.user" module="system"></public:i18n>
								</p>
								<p>
									<input class="button" type="button" name="adminAddButton" value='<public:i18n key="button.submit" module="common"></public:i18n>' />
								</p>
								
							</fieldset>
							
							<div class="clear"></div><!-- End .clear -->
							
						</form>
						
					</div> <!-- End #tab2 -->        
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
		</div> <!-- End #main-content -->
		
</body>
</html>
