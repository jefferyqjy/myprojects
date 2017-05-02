function actionDel(url) {
	var confirmMsg = "Are you sure to delete?";
	if(confirm(confirmMsg)) {
		window.location.href=encodeURI(url);
	}
}



function gotoEdit(url) {
	window.location.href=encodeURI(url);
}



function actionDo(url) {
	window.location.href=encodeURI(url);
}

function gotoSelectPage(url) {
	openWin(url,"SelectorPage",500,500,0);
}

function actionDels(url){
	var confirmMsg = "Are you sure to delete?";
	if(confirm(confirmMsg)) {
		var ids = document.getElementsByName("primaryKey");
		var temp = "?ids=";
		var isEmpty = true;
		if(ids){
			for(var i=0;i<ids.length;i++){
				var id = ids[i]; 
				if(id.type == "checkbox" && id.checked == true){
					isEmpty = false;
					temp+=id.value + ","
				}
			}
		}
		url = url +　temp;
		if(isEmpty){
			alert("请至少选择一笔记录进行删除");
			return;
		}
		window.location.href=encodeURI(url);
	}
}


function isNotEmpty(value){
	 if(typeof value != "undefined" && value != null && value.length > 0){
		 return true;
	 } else {
		 return false;
	 }
 }
		 
 function isEmpty(value) {
	 return !isNotEmpty(value);
 } 

function checkRadioHasError(input, clazz) {
	if(isNotEmpty(clazz)) {
		var option = clazz.substr(5,1);
		var name = input.name;
		var id = name + "_errorMsg";
		if("M" == option){
			var hasChecked = false;
			$('input[name="'+name+'"]').each(function(){
				if(this.checked == true || this.checked == "checked"){
					hasChecked = true;
				}
			});
			if(!hasChecked) {
				 $("#"+id).html("请选择该项");
				 $("#"+id).addClass("error");
				 return true;
			} else {
				 $("#"+id).empty();
				 $("#"+id).removeClass("error");
				 return false;
			}
		} else {
			 $("#"+id).empty();
			 $("#"+id).removeClass("error");
			return false;  
		}
	}
}

function checkCheckboxHasError(input, clazz) {
	if(isNotEmpty(clazz)) {
		var option = clazz.substr(5,1);
		var name = input.name;
		var id = name + "_errorMsg";
		if("M" == option){
			var hasChecked = false;
			$('input[name="'+name+'"]').each(function(){
				if(this.checked == true || this.checked == "checked"){
					hasChecked = true;
				}
			});
			if(!hasChecked) {
				 $("#"+id).html("请勾选该项");
				 $("#"+id).addClass("error");
				 return true;
			} else {
				 $("#"+id).empty();
				 $("#"+id).removeClass("error");
				 return false;
			}
		} else {
			 $("#"+id).empty();
			 $("#"+id).removeClass("error");
			return false;  
		}
	}
}
 
function checkInputTextHasError(input,clazz) {
	 if(isNotEmpty(clazz)) {
		 var type = clazz.substr(0,4);
		 var option = clazz.substr(5,1);
		 var inputValue = input.value;
		 if("INTE" == type && isNotEmpty(inputValue)) {
			 if(parseInt(inputValue) == inputValue) { 
				 $("#"+id).empty();
				 $("#"+id).removeClass("error");
				 return false;
			 } else {
				 var id = input.name + "_errorMsg";
				 $("#"+id).html("必须是整数类型");
				 $("#"+id).addClass("error");
				 return true;
			 }
		 }
		 if("FLOA" == type && isNotEmpty(inputValue)){
			 var reg = /^(-?\d+)(\.\d+)?$/;
			 if(reg.test(inputValue)){
				 $("#"+id).empty();
				 $("#"+id).removeClass("error");
				 return false;
			 } else {
				 var id = input.name + "_errorMsg";
				 $("#"+id).html("必须是数字类型");
				 $("#"+id).addClass("error");
				 return true;
			 }
		 }
		 if(isNotEmpty(option)) {
			 if("M" == option) {
				 var id = input.name + "_errorMsg";
				 if(isEmpty(inputValue)) {
					 $("#"+id).html("请填写该字段");
					 $("#"+id).addClass("error");
					 return true;
				 }  else {
					 $("#"+id).empty();
					 $("#"+id).removeClass("error");
					 return false;
				 }
			 }
		 }
		 var maxLength = clazz.substr(7,4);
		 if(isNotEmpty(maxLength)) {
			 if(inputValue.length > maxLength) {
				 var id = input.name + "_errorMsg";
				 $("#"+id).html("该字段超出长度"+maxLength);
				 return true;
			 }
		 } else {
			 return false;
		 }
	 }
 }

function mdy(url) {
	if(openModifyDialog(url)){
		window.location.reload(true);
	}
}
 
 function openModifyDialog(url){
	window.showModalDialog(url,"dialogHeight:500px;dialogWidth:500px;resizable:no;help:yes;status:no");
	return true;
 }

/*
 * 打开新窗口 f:链接地址 n：窗口的名称 w:窗口的宽度 h:窗口的高度 s:窗口是否有滚动条 1有 0没有
 */
 function openWin(f,n,w,h,s) {
	 var newf = encodeURI(f);
	 sb = s == "1" ? "1" : "0";
	 l = (screen.width - w) / 2;
	 t = (screen.height - h) / 2;
	 sFeatures = "left=" + l + ",top=" + t + ",height=" + h + ",width=" + w +
	 	",center=1,scrollbars=" + sb + ",status=0,directories=0,channelmode=0";
	 openwin = window.open(newf,n,sFeatures);
	 if(!openwin.opener) {
		 openwin.opener = self;
		 openwin.focus();
		 return openwin;
	 }
 }
 
 function showCities(){
	 var province = $("#province").val();
	 $.post('admin/admin_getCities.do',{pid:province},
			 function(data){
				if(typeof data != "undefined" && data != null){	
					$("#city").empty();
					$("#city").html(data);
				}
	 		}
	 );
 }