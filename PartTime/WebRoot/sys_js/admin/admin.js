function toPage(pageNum){
	var uName = $('form[name="searchForm"] input[name="username"]').val();
	var url = "admin/admin_query.do?curPage="+pageNum;
	self.location.href = encodeURI(url);
}

$(document).ready(function(){
	$('form[name="addForm"] input[name="username"]').blur(function(){
		var key = this.name+"_"+"errorMsg";
		var value = this.value;
		if(typeof value == "undefined" || value == null || ""==value) {
			$("#"+key).html("请填写该字段");
			$("#"+key).addClass("error");
			return　false;
		} else {
			$("#"+key).removeClass("error");
		}
		$("#"+key).empty();
		$("#"+key).addClass("success");
	});
	
	$('form[name="addForm"] input[name="password"]').blur(function(){
		var key = this.name+"_"+"errorMsg";
		var value = this.value;
		if(typeof value == "undefined" || value == null || ""==value) {
			$("#"+key).html("请填写该字段");
			$("#"+key).addClass("error");
			return　false;
		} else {
			$("#"+key).removeClass("error");
		}
		$("#"+key).empty();
		$("#"+key).addClass("success");
	});
	
	$('form[name="addForm"] input[name="cpassword"]').blur(function(){
		var key = this.name+"_"+"errorMsg";
		var value = this.value;
		if(typeof value == "undefined" || value == null || ""==value) {
			$("#"+key).html("请填写该字段");
			$("#"+key).addClass("error");
			return　false;
		} else if(this.value != $('form[name="addForm"] input[name="password"]').val()) {
			$("#"+key).html("确认密码要与密码一致");
			$("#"+key).addClass("error");
			return　false;
		} else {
			$("#"+key).removeClass("error");
		}
		$("#"+key).empty();
		$("#"+key).addClass("success");
	});
	
	$('form[name="addForm"] input[name="adminAddButton"]').click(function(){
		var hasError = false;
		$('form[name="addForm"] input[type="text"]').each(function(){
			var rule = $(this).attr("rule");
			hasError = checkInputTextHasError(this, rule);
		});
		$('form[name="addForm"] input[type="password"]').each(function(){
			var rule = $(this).attr("rule");
			hasError = checkInputTextHasError(this, rule);
		});
		if(!hasError) {
			document.addForm.submit();
		}
	});
	
	$('form[name="changeForm"] input[name="commit"]').click(function(){
		var oldpassword = $("#oldpassword").val();
		var username = $("#username").val();
		var newPassword = $("#newpassword").val();
		var cPassword = $("#cpassword").val();
		if(isEmpty(oldpassword)){
			$("#oldpassword_errorMsg").html("原始密码不能为空");
			$("#oldpassword_errorMsg").addClass("error");
		} else {
			$.post("admin/admin_checkOldPassword.do",{
				username:username,oldpassword:oldpassword
			},function(data){
				if(typeof data != "undefined" && data != null){
					var ndRoot = data.getElementsByTagName("root")[0];
					var isSuccess = ndRoot.getAttribute("hasError");
					if("N" == isSuccess) {
						$("#oldpassword_errorMsg").removeClass("error");
						if(isEmpty(newPassword)) {
							$("#newpassword_errorMsg").html("新密码不能为空");
							$("#newpassword_errorMsg").addClass("error");
						} else if(isEmpty(cPassword)) {
							$("#cpassword_errorMsg").html("确认密码不能为空");
							$("#cpassword_errorMsg").addClass("error");
						} else if(newPassword != cPassword) {
							$("#cpassword_errorMsg").html("新密码与确认密码不一致");
							$("#cpassword_errorMsg").addClass("error");
						} else {
							document.changeForm.submit();
						}
					}else {
						var ndRoot = data.getElementsByTagName("root")[0];
						var errMsg = ndRoot.getElementsByTagName("errMsg")[0];
						if(document.all){
							$("#oldpassword_errorMsg").html(errMsg.text);
							$("#oldpassword_errorMsg").addClass("error");
						}else {
							$("#oldpassword_errorMsg").html(errMsg.firstChild.nodeValue);
							$("#oldpassword_errorMsg").addClass("error");
						}
					}
				}
			});
			
		}
		
	});
	
	$('form[name="addForm"] input[name="adminCancelButton"]').click(function(){
		self.location.href = "admin/admin_query.do?username=";
	});
	
	$('#applyButton').click(function(){
		var actionName = $('select[name="dropdown"]').val();
		if("D" == actionName) {
			actionDels('admin/admin_dels.do');
		} else {
			alert("没有选择对应操作");
		}
	});
	
});