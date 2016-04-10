$(function(){
		$('tbody tr:odd').addClass("trLight");
		
		$(".select-all").click(function(){
			if($(this).attr("checked")){
				$(".checkBox input[type=checkbox]").each(function(){
					$(this).attr("checked", true);  
					});
				}else{
					$(".checkBox input[type=checkbox]").each(function(){
					$(this).attr("checked", false);  
					});
				}
			});
		
		})

function actionDel(url) {
	if(confirm("Are you sure to delete?")) {
		self.location.href = encodeURI(url);
	}
}

function toModify(url) {
	openWin(url,'mdy',500,500,0);
}

function viewDetail(url) {
	openWin(url,'detail',500,500,0);
}

function actionDo(url) {
	self.location.href = url;
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
 /*
	 * open delete dialog
	 */
 function openDeleteDialog(url,confirmString){
	 var c = confirmString;
	 if(c == null || c == '') {
		 c = "Are you sure to delete?";
	 }
	 if(confirm(c)){
		 window.showModalDialog(url,"dialogHeight:24px;dialogWidth:21px;resizable:no;help:yes;status:no");
		 return true;
	 }
	 return false;
 }
 /*
	 * delete
	 */
 function del(url,info) {
	 if(openDeleteDialog(url,info)){
		 window.location.reload(true);
	 }
 }
 
 function dels(url){
		var ids = document.getElementsByName("id");
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
		del(url);
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
 
 function windowClose(){
	 window.returnValue = true;
	 window.close();
 }
 
 function isChinese(value){
	 var reg = "/^[u4E00-u9FA5]+$/";
	 if(!reg.test(value)) {
		 return false;
	 }
	return true;
 }
 
 function toAdd(url) {
		openWin(url,'Add',500,500,0);
	}
 
 function checkNumber(obj) {
	 if(!isfloat(obj.value)){
		 alert("该字段必须是数值类型");
		 obj.value = "0";
	 };
 }
 
 function isfloat(oNum){

	  if(!oNum) return false;

	  var strP=/^\d+(\.\d+)?$/;

	  if(!strP.test(oNum)) return false;

	  try{

	  	if(parseFloat(oNum)!=oNum) return false;

	  }catch(ex){

	    return false;

	  }

	  return true;

	}

 function createXMLHttpRequest(){
	 	var xmlhttp = null;
		if(window.ActiveXObject){
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
		}
		return xmlhttp;
	}
 
 function sendRequestByAjaxPost(url,mode,callback,sSucJsFuncName,sFailJsFuncName){
		var xmlhttp=createXMLHttpRequest();
		if(!xmlhttp)
		{
			alert("Your browser don't support XMLHTTP!!");
			return;
		}
		var vUrl = url.substr(0,url.indexOf('?'));
		var temp = url.substr((url.indexOf('?')+1),(url.length-1));
		xmlhttp.open("POST",vUrl,mode);
		// for firefox, if mode is true, it means asynchronouse, if mode is
		// false, firefox willnot call onreadystatechange
		if(mode){
		xmlhttp.onreadystatechange=function() {
			if(xmlhttp.readyState==4)
			{
				if(xmlhttp.status==200)
				{
					try{return sendRequestByAjaxPost_Handler(xmlhttp,mode,callback,sSucJsFuncName,sFailJsFuncName);}finally{xmlhttp=null;}
				}else
				{
					alert("sendRequestByAjaxPost error"+xmlhttp.status+"("+xmlhttp.statusText+")");
				}
			}
		}}
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
		xmlhttp.send(temp);
		if(!mode){
			try{return sendRequestByAjaxPost_Handler(xmlhttp,mode,callback,sSucJsFuncName,sFailJsFuncName);}finally{xmlhttp=null;}
		}
	}
 
 function sendRequestByAjaxPost_Handler(xmlhttp,mode,callback,sSucJsFuncName,sFailJsFuncName){
		var xml = xmlhttp.responseText;
		var resHeader = xmlhttp.getResponseHeader("Content-Type");
		if(typeof(xml) == "string"&&xml.length>0&&resHeader.indexOf("text/script") != -1){
			var firstChar=xml.substr(0,1);
			if(firstChar=="E"||firstChar=="N"){
				xml=xml.substr(1,xml.length);
					if(firstChar=="N"){
					xml=null;	
				}else{
					eval(xml);
				}
			}else{
				eval(xml);
			}
			return null;
		}
		var typeofCallback=typeof callback;
		if(typeofCallback =="function"||typeofCallback=='object'){
			return callback(xmlhttp,mode,sSucJsFuncName,sFailJsFuncName);
		}else if(typeofCallback=="string"){
			return eval(callback)(xmlhttp,mode,sSucJsFuncName,sFailJsFuncName);
		}else return null;
	}
 
 function showMessageBox(data){
	 if(typeof data != "string"){
			var ndRoot = data.getElementsByTagName("root")[0];
			var isSuccess = ndRoot.getAttribute("hasError");
			if("N" == isSuccess) {
				$("#message-box").hide();
			}else {
				var ndRoot = data.getElementsByTagName("root")[0];
				var errMsg = ndRoot.getElementsByTagName("errMsg")[0];
				if(document.all){
					$("#message-box").empty().html(errMsg.text);
				}else {
					$("#message-box").empty().html(errMsg.firstChild.nodeValue);
				}
				$("#message-box").show();
			}
		}
 }
 
 function comptime(beginTime, endTime) {
	    var beginTimes = beginTime.substring(0, 10).split('-');
	    var endTimes = endTime.substring(0, 10).split('-');

	    beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0];
	    endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0];

	    var a = (Date.parse(endTime) - Date.parse(beginTime)) / 3600 / 1000;
	    if (a < 0) {
	        return 1;
	    } else if (a > 0) {
	        return -1;
	    } else if (a == 0) {
	        return 0;
	    } else {
	        return 'exception'
	    }
	}  