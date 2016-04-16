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
</head>
<body>
   <!--------------Header--------------->
<tags:header/>

<!--------------Navigation--------------->

<tags:nav/>

<!--------------Content--------------->
<section id="content">
	<div class="zerogrid">
		<div class="row block">
			<div class="main-content">
			
				<article>
					<div class="heading">
						<h2><a href="#" style="margin-left:25px">用户信息更新</a></h2>
					</div>
					<div class="content" style="margin-top:15px; height:600px">
						<div style="margin-left:200px">
						<form:form method="Post" action="/friend/member/update.spring" commandName="memberBean" id="memberBean_form" class="form-horizontal">
							<form:hidden path="id" />
							<form:hidden path="userPassword"/>
							<div style="width:500px; height:25px"> 
								<div style="width:100px;float:left; text-align:right">用户名： </div>
								<div style="width:400px;float:left"><form:input cssClass="input-xlarge focused" id="regUserName" path="userName" readonly="true" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">省份：</div>
								<div style="width:400px;float:left">
									<form:select path="province" cssClass="input-medium focused">
										<option value="">-请选择-</option>
										<c:forEach items="${ProvinceBeanList}" var="p" >
											<option value="${p.id }" <c:if test="${memberBean.province eq p.id }">selected="selected"</c:if> >${p.name }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">市： </div>
								<div style="width:400px;float:left">
									<form:select path="city" cssClass="input-medium focused">
										<option value="">-请选择-</option>
										<c:forEach items="${CityBeanList}" var="c" >
											<option value="${c.id }" <c:if test="${memberBean.city eq c.id }">selected="selected"</c:if> >${c.name }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">大学： </div>
								<div style="width:400px;float:left">
									<form:select path="universityId" cssClass="input-medium focused">
										<option value="">-请选择-</option>
										<c:forEach items="${UniversityBeanList}" var="u" >
											<option value="${u.id }" <c:if test="${memberBean.universityId eq u.id }">selected="selected"</c:if> >${u.name }</option>
										</c:forEach>
									</form:select>
					  			</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">专业： </div>
								<div style="width:400px;float:left">
									<form:select path="subject" cssClass="input-medium focused">
										<option value="">-请选择-</option>
										<c:forEach items="${SubjectBeanList}" var="s" >
											<option value="${s.id }" <c:if test="${memberBean.subject eq s.id }">selected="selected"</c:if> >${s.name }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">入学年份： </div>
								<div style="width:400px;float:left"><form:input path="year" cssClass="input-medium" readonly="true" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">学号： </div>
								<div style="width:400px;float:left"><form:input path="stuNo" cssClass="input-medium" readonly="true" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">性别： </div>
								<div style="width:400px;float:left">
									<form:select path="gender" id="gender" cssClass="input-medium focused" >
										<option value="" >请选择</option>
										<c:forEach items="${genders}" var="g" >
											<option value="${g.info }" <c:if test="${memberBean.gender eq g.info }">selected="selected"</c:if> >${g.info }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">年龄： </div>
								<div style="width:400px;float:left">
									<form:input path="age" id="age" cssClass="input-medium focused" />
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">地址： </div>
								<div style="width:400px;float:left">
									<form:input path="address" id="address" cssClass="input-xlarge focused" />
								</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">兴趣： </div>
								<div style="width:400px;float:left">
									<c:forEach var="myItem"  items="${InterestBeanList}" varStatus="status">
							  			<input type="checkbox" name="interestCheckBox" value="${myItem.id}" ${myItem.checked}/><label>${myItem.name}</label>
							  		</c:forEach>
							  	</div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">&nbsp;</div>
								<div style="width:400px;float:left"><input type="submit" value="更新" /></div>
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

<tags:copyright/>
</body>
</html>