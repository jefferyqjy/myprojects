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
			self.location.href = "partTimeType/partTimeType_query.do";
		});
		$('#applyButton').click(function(){
			var actionName = $('select[name="dropdown"]').val();
			if("D" == actionName) {
				actionDels('partTimeType/partTimeType_dels.do');
			} else {
				alert("没有选择对应操作");
			}
		});
});
function setParent(value) {
		opener.document.getElementById('uuid').value = value;
		window.opener = null;
		window.close();
}