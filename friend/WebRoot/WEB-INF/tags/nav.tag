<%@  tag pageEncoding="UTF-8"%>
<nav>
	<ul>
		<li><a href="/friend/home.jsp">首页</a></li>
		<li><a href="/friend/frd/preList.spring">好友</a></li>
		<li><a href="/friend/boke/preList.spring" >博客</a></li>
		<li><a href="/friend/photoBox/preList.spring">相册</a></li>
		<li><a href="#" onclick="userManager()">个人信息</a></li>
		<li><a href="/friend/member/preAdd.spring">注册</a></li>
		<%
		Object userObject = request.getSession().getAttribute("ONLINE_MEMBER");
		if (userObject != null) {
			String userName = (String)userObject;
			if (!userName.equals("")) {
				%>
			<li style="float:right"> <form action="/friend/member/logout.spring" method="POST">欢迎您: <%=userName %><input type="submit" value="退出" style="width:50px"/></form></li>	
				<%
			} else {
%>
		<li style="float:right"><form action="/friend/member/login.spring" method="POST">用户：<input type="text" name="userName" style="width:80px"/> 密码：<input type="password" name="password" style="width:80px"/> <input type="submit" value="登陆" style="width:50px"/></form></li>		
				<%
			}
		} else {
			%>
			<li style="float:right"><form action="/friend/member/login.spring" method="POST">用户：<input type="text" name="userName" style="width:80px"/> 密码：<input type="password" name="password" style="width:80px"/> <input type="submit" value="登陆" style="width:50px"/></form></li>		
					<%
				}
		%>
		
	</ul>
	<ul style="margin-bottom:15px;" >
		<li>${MESSAGE}</li>
	</ul>
</nav>
<script>
function checkUser() {
	var user = '${ONLINE_MEMBER}';
	if (user == null || user =="") {
		return 0;
	}
	return 1;
}


function friendsManager() {
	if (checkUser() > 0) {
		
	}
}

function bokeManager() {
	if (checkUser() > 0) {
		
	}
}

function photoManager() {
	if (checkUser() > 0) {
		
	}
}

function userManager() {
	if (checkUser() > 0) {
		window.location.href='/friend/member/preUpdate.spring';
	}
}
</script>