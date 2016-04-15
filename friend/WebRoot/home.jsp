<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<section id="content" style="padding-bottom:20px;">
	<div class="zerogrid">
		<div class="row block">
			<div class="main-content col11" style="width:100%; min-height：600px">
				<article>
					<div class="content" style="margin-top:35px; margin-left:400px; height:500px">
						WELCOME TO OUR WEB SITE
					</div>
				</article>
			</div>
		</div>
	</div>
</section>
<tags:copyright/>
<script type="text/javascript">
var message = "${MESSAGE}";
if(message != null && message != "") {
	alert(message);	
}
</script>
</body>
</html>