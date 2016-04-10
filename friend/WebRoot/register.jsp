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
						<form:form method="Post" action="/friend/member/add.spring" commandName="memberBean" id="memberBean_form" class="form-horizontal">
							<div style="width:500px; height:25px"> 
								<div style="width:100px;float:left; text-align:right">用户名： </div>
								<div style="width:400px;float:left"> <form:input class="input-xlarge focused" id="userName" path="userName" type="text" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">密码： </div>
								<div style="width:400px;float:left"> <form:input class="input-xlarge focused" id="userPassword" path="userPassword" type="password" /></div>
							</div>
				
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">性别： </div>
								<div style="width:400px;float:left"><form:input class="input-xlarge focused" id="gender" path="gender" type="text" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">年龄： </div>
								<div style="width:400px;float:left"><form:input class="input-xlarge focused" id="age" path="age" type="text" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">出生地： </div>
								<div style="width:400px;float:left"><form:input class="input-xlarge focused" id="address" path="address" type="text" /></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">大学： </div>
								<div style="width:400px;float:left"><form:select path="university.id" data-rel="chosen">
									<c:forEach var="myItem"  items="${UniversityBeanList}">
									<option value="${myItem.id}">${myItem.name}</option>
									</c:forEach>
								  </form:select></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">兴趣： </div>
								<div style="width:400px;float:left"><c:forEach var="myItem"  items="${InterestBeanList}" varStatus="status">
							  		<input type="checkbox" name="interestCheckBox" value="${myItem.id}" ${myItem.checked}/> ${myItem.name}
							  	</label>
							  	</c:forEach></div>
							</div>
							<div style="width:500px; height:25px; margin-top:15px"> 
								<div style="width:100px;float:left; text-align:right">&nbsp;</div>
								<div style="width:400px;float:left"><input type="submit" value="注册" /></div>
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