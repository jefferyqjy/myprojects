<%@  tag pageEncoding="UTF-8"%>
<div id="copyright">
	<p>Copyright © 2012 - Domain Name</p>
	<p>Copyright </p>
</div>

	<div class="modal hide fade" style="display:none" id="msgModal" style="z-index:99999">
	
	
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" onclick="$('#msgModal').hide();">&times;</button>
			<h3 id='msgTitle'>消息中心</h3>
		</div>
		<div class="modal-body" >
			<p id='msgBody' style="min-height:100px; height:auto">message here </p>
			<div class="modal-footer" style="height:20px">
				<a href="#" id="msgClose" data-dismiss="modal" class="btn" onclick="closeMsgModal();">关闭</a>
			</div>
		</div>
	</div>	
	
	<script>
	var closeMethod;
	function openMsg(title, content, callback) {
		$("#msgTitle").text(title);
		$("#msgBody").text(content);
		$('#msgModal').modal();
		closeMethod = callback;
	}
	
	function closeMsgModal() {
	if (closeMethod && typeof closeMethod === 'function') {
		closeMethod();
	}
	$('#msgModal').modal('hide');
	}
	</script>