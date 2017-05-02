$(document).ready(function(){
	$(".nav-top-item").each(function() {
		$(this).click(function() {
			$(".nav-top-item").each(function() {
				$(this).removeClass("current");
			});
			$(this).addClass("current");
		});
	});
	$('a[name="subLink"]').each(function() {
		$(this).click(function() {
			$('a[name="subLink"]').each(function() {
				$(this).removeClass("current");
			});
			$(this).addClass("current");
		});
	});
	$("#main-nav li a.no-submenu").click(function() {
		$('iframe[name="right"]').attr("src", this.href);
		return false;
	}); 
});