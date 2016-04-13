<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基于Java web的大学生同城交友平台</title>
<link rel="stylesheet" href="/friend/css/zerogrid.css">
<link rel="stylesheet" href="/friend/css/style.css">

<script src='${pageContext.request.contextPath }/admin/js/jquery-1.7.2.min.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/jquery-ui-1.8.21.custom.min.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-transition.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-alert.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-modal.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-dropdown.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-scrollspy.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-tab.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-tooltip.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-popover.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-button.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-collapse.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-carousel.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-typeahead.js'></script>
<script src='${pageContext.request.contextPath }/admin/js/bootstrap-tour.js'></script>

<script type="text/javascript">
$(function() {
	// load cities
	$("select[name='province']").on("change", function() {
		var provinceId = $(this).val();
		if(provinceId != null && provinceId != "") {
			$("select[name='city']").empty();
			$.ajax({
				url: "${pageContext.request.contextPath}/resource/loadCities.spring",
				type: "get",
				data: {provinceId : provinceId},
				dataType: "json",
				success: function(data) {
					var defaultOption = "<option value='' >-请选择-</option>";
					$("select[name='city']").append(defaultOption);
					$.each(data, function(i, item) {
						var option = "<option value='"+item.id+"' >"+item.name+"</option>";
						$("select[name='city']").append(option);
					})
				}
			});
		}
	});

	// load universities
	$("select[name='city']").on("change", function() {
		var cityId = $(this).val();
		if(cityId != null && cityId != "") {
			$("select[name='university']").empty();
			$.ajax({
				url: "${pageContext.request.contextPath}/resource/loadUniversities.spring",
				type: "get",
				data: {cityId : cityId},
				dataType: "json",
				success: function(data) {
					var defaultOption = "<option value='' >-请选择-</option>";
					$("select[name='university']").append(defaultOption);
					$.each(data, function(i, item) {
						var option = "<option value='"+item.id+"' >"+item.name+"</option>";
						$("select[name='university']").append(option);
					})
				}
			});
		}
	});
	
	// load subjects
	$("select[name='university']").on("change", function() {
		var universityId = $(this).val();
		if(universityId != null && universityId != "") {
			$("select[name='subject']").empty();
			$.ajax({
				url: "${pageContext.request.contextPath}/resource/loadSubjects.spring",
				type: "get",
				data: {universityId : universityId},
				dataType: "json",
				success: function(data) {
					var defaultOption = "<option value='' >-请选择-</option>";
					$("select[name='subject']").append(defaultOption);
					$.each(data, function(i, item) {
						var option = "<option value='"+item.id+"' >"+item.name+"</option>";
						$("select[name='subject']").append(option);
					})
				}
			});
		}
	});

	// enable year of entering university.
	$("select[name='subject']").on("change", function() {
		var subjectId = $(this).val();
		if(subjectId != null && subjectId != '') {
			$("input[name='year']").removeAttr("readonly");
		}
	});

})
function submitForm() {
	var message = "";
	
	var userName = $("input[name='userName']").val();
	if(userName == null || userName == "") {
		message += "用户名不能为空！\n";
	}
	
	var userPassword = $("input[name='userPassword']").val();
	if(userPassword == null || userPassword == "") {
		message += "密码不能为空！\n";
	}
	
	var province = $("input[name='province']").val();
	if(province == null || province == "") {
		message += "省份不能为空！\n";
	}
	
	var city = $("input[name='city']").val();
	if(city == null || city == "") {
		message += "城市不能为空！\n";
	}
	
	var university = $("input[name='university']").val();
	if(university == null || university == "") {
		message += "学校不能为空！\n";
	}
	
	var subject = $("input[name='subject']").val();
	if(subject == null || subject == "") {
		message += "专业不能为空！\n";
	}

	var year = $("input[name='year']").val();
	if(year == null || year == "") {
		message += "入学年份不能为空！";
	}

	var stuNo = $("input[name='stuNo']").val();
	if(stuNo == null || stuNo = "") {
		message += "学号不能为空！";
	}

	if(ajaxValidate(userName, stuNo)) {
		
	}
	
}

function ajaxValidate(userName, stuNo) {
	$.ajax({
		url: "${pageContext.request.contextPath}/resource/ajaxValidate.spring",
		type: "get",
		data: {
			userName : userName,
			stuNo : stuNo
		},
		dataType: "json",
		success: function(data) {
			if(data.message != null && data.message != "") {
				alert(data.message);
				return false;
			} 
			return true;
		}
	});
}
</script>
</head>
<body>
   <!--------------Header--------------->
<tags:header/>

<!--------------Navigation--------------->

<tags:nav/>

<!--------------Content--------------->
<%try{ %>
<section id="content">
	<div class="zerogrid">
		<div class="row block">
			<div class="main-content">
			
				<article>
					<div class="heading">
						<h2><a href="#" style="margin-left:25px">注册新用户</a></h2>
					</div>
					<div class="content" style="margin-top:15px; height:450px">
						<div style="margin-left:200px">
						<form:form method="Post" action="/friend/member/add.spring" commandName="memberBean" id="memberBean_form" cssClass="form-horizontal">
							<div style="width:500px; height:25px"> 
								<div style="width:100px;float:left; text-align:right">用户名： </div>
								<div style="width:400px;float:left"> <form:input cssClass="input-xlarge focused" id="userName" path="userName" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">密码： </div>
								<div style="width:400px;float:left"><form:password cssClass="input-xlarge focused" id="userPassword" path="userPassword"/></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">省份： </div>
								<div style="width:400px;float:left">
									<form:select path="province" cssClass="input-medium focused">
										<option value="">-请选择-</option>
										<option value="1">江苏省</option>
										<option value="2">山东省</option>
										<option value="3">浙江省</option>
									</form:select>
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">市： </div>
								<div style="width:400px;float:left">
									<form:select path="city" cssClass="input-medium focused">
										<option value="">-请选择-</option>
									</form:select>
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">大学： </div>
								<div style="width:400px;float:left">
									<form:select path="university" cssClass="input-medium focused">
										<option value="">-请选择-</option>
									</form:select>
					  			</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">专业： </div>
								<div style="width:400px;float:left">
									<form:select path="subject" cssClass="input-medium focused">
										<option value="">-请选择-</option>
									</form:select>
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">入学年份： </div>
								<div style="width:400px;float:left"><form:input path="year" cssClass="input-medium" readonly="true" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">&nbsp;</div>
								<div style="width:400px;float:left"><input type="button" onclick="submitForm()" value="注册" /></div>
							</div>
							<B>${MESSAGE}</B>
							</form:form>
						</div>
					</div>
				</article>
			
			</div>
		
			
		</div>
	</div>
</section>
<%}catch(Exception e){ e.printStackTrace();} %>
<tags:copyright/>
</body>
</html>