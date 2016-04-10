
function actionDel(url) {
	if(confirm("Are you sure to delete?")) {
		self.location.href = encodeURI(url);
	}
}

function actionDo(url) {
	self.location.href = encodeURI(url);
}
/*
 * 打开新窗口
 * f:链接地址
 * n：窗口的名称
 * w:窗口的宽度
 * h:窗口的高度
 * s:窗口是否有滚动条 1有 0没有
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
  *open delete dialog 
  */
 function openDeleteDialog(url,confirmString){
	 var c = confirmString;
	 if(c == null || c == '') {
		 c = "are you sure to delete?";
	 }
	 if(confirm(c)){
		 return window.showModalDialog(url,"dialogHeight:234px;dialogWidth:271px;resizable:no;help:yes;status:no");
	 }
	 return false;
 }
 /*
  *delete 
  */
 function del(url,info) {
	 if(openDeleteDialog(url,info)){
		 window.location.reload(true);
	 }
	 
 }
 
 function windowClose(){
	 window.returnValue = true;
	 window.close();
 }
