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
<section id="content" style="min-height:700px">
	<div class="zerogrid">
		<div class="row block">
			<div class="main-content">
				<font color="red"><b>${ERROR_MESSAGE}</b></font> 
			</div>
		</div>
	</div>
</section>

<tags:copyright/>
</body>
</html>