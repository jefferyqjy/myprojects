
<%
	if (request.getAttribute("pageNotificationSuccess") != null) {
%>
<!-- 
<div class="notification success png_bg">
	<a href="#" class="close"><img
			src="resources/images/icons/cross_grey_small.png"
			title="Close this notification" alt="close" />
	</a>
	<div>
		${pageNotificationSuccess }
	</div>
</div>
 -->

<%
	}
%>
<%
	if (request.getAttribute("pageNotificationError") != null) {
%>
<div class="notification error png_bg">
	<a href="#" class="close"><img
			src="resources/images/icons/cross_grey_small.png"
			title="Close this notification" alt="close" />
	</a>
	<div>
		${pageNotificationError }
	</div>
</div>
<%
	}else{
	if (request.getAttribute("pageNotificationSuccess") != null) {
%>

<div class="notification success png_bg">
	<a href="#" class="close"><img
			src="resources/images/icons/cross_grey_small.png"
			title="Close this notification" alt="close" />
	</a>
	<div>
		${pageNotificationSuccess }
	</div>
</div>

<%} }%>
<%
	if (request.getAttribute("pageNotificationAttention") != null) {
%>
<div class="notification attention png_bg">
	<a href="#" class="close"><img
			src="resources/images/icons/cross_grey_small.png"
			title="Close this notification" alt="close" />
	</a>
	<div>
		${pageNotificationAttention }
	</div>
</div>
<%
	}
%>
<%
	if (request.getAttribute("pageNotificationInfo") != null) {
%>
<div class="notification information png_bg">
	<a href="#" class="close"><img
			src="resources/images/icons/cross_grey_small.png"
			title="Close this notification" alt="close" />
	</a>
	<div>
		${pageNotificationInfo }
	</div>
</div>
<%
	}
%>
<%
	if (request.getAttribute("pageNotificationNotice") != null) {
%>
<div class="notification notice png_bg">
	<a href="#" class="close"><img
			src="resources/images/icons/cross_grey_small.png"
			title="Close this notification" alt="close" />
	</a>
	<div>
		${pageNotificationNotice }
	</div>
</div>
<%
	}
%>