$(document).ready(function(){
		$('form[name="mainform"] input[name="submitButton"]').click(function(){
			var hasError = false;
			var hasError1 = false;
			var hasError2 = false;
 		$('form[name="mainform"] input[type="text"]').each(function(){
				var rule = $(this).attr("rule");
				if(checkInputTextHasError(this, rule)){
					hasError = true;
				}
			});
 		$('form[name="mainform"] input[type="radio"]').each(function(){
				var rule = $(this).attr("rule");
				if(checkRadioHasError(this, rule)) {
					hasError1 = true;
				}
			});
 		$('form[name="mainform"] input[type="checkbox"]').each(function(){
				var rule = $(this).attr("rule");
				if(checkCheckboxHasError(this, rule)) {
					hasError2 = true;
				}
			});
			if(!hasError && !hasError1 && !hasError2) {
				document.mainform.submit();
			}
		});
		$('#cancelButton').click(function(){
			self.location.href = "jfile/jfile_query.do";
		});
		$('#applyButton').click(function(){
			var actionName = $('select[name="dropdown"]').val();
			if("D" == actionName) {
				actionDels('jfile/jfile_dels.do');
			} else {
				alert("没有选择对应操作");
			}
		});
		$('#downloadButton').click(function(){
			var id = $('#id').val();
			self.location.href = "jfile/jfile_download.do?id="+id;
		});
});
function newUploadRow() {
	var table = document.getElementById("normalT");
	var tr = table.insertRow(-1);
	var td1 = null;
	if(document.all){
		td1 = tr.insertCell();
	}else {
		td1 = document.createElement("td");
		tr.appendChild(td1);
	}
	td1.innerHTML = "<input class=\"text-input medium-input\" name=\"attach\" id=\"attach\" type=\"file\" />";
}
