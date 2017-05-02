$(document).ready(function(){
		$("#signIn").click(function(){
			$("#login_error_message").hide();
			var userType = $("#usertype").val();
			if(userType == null || ""==userType){
				$("#login_error_message").show();
				$("#login_error_message").html("<div>用户类型不能为空</div>")
				return false;
			}
			var username = $("#username").val();
			if(username == null || "" == username){
				$("#login_error_message").show();
				$("#login_error_message").html("<div>用户名不能为空</div>")
				return false;
			}
			var password = $("#password").val();
			if(password == null || ""==password){
				$("#login_error_message").show();
				$("#login_error_message").html("<div>密码不能为空</div>")
				return false;
			}
			
			$("#login_error_message").hide();
			$.post("admin/admin_loginCheck.do",
					{username:username,password:password,usertype:userType},
					function(data){
						if(typeof data != "undefined" && data != null){
							var ndRoot = data.getElementsByTagName("root")[0];
							var isSuccess = ndRoot.getAttribute("hasError");
							if("N" == isSuccess) {
								document.mainform.submit();
							}else {
								var ndRoot = data.getElementsByTagName("root")[0];
								var errMsg = ndRoot.getElementsByTagName("errMsg")[0];
								if(document.all){
									$("login_error_message").html("<div>"+errMsg.text+"</div>");
								}else {
									$("#login_error_message").html("<div>"+errMsg.firstChild.nodeValue+"</div>");
								}
								$("#login_error_message").show();
							}
						}
					}
				);
		});
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				$("#signIn").trigger("click");
			}
		}
});


