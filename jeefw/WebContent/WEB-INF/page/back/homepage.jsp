<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>
		控制台
		<small>
			<i class="ace-icon fa fa-angle-double-right"></i>
			概述&统计
		</small>
	</h1>
</div><!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="alert alert-block alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<i class="ace-icon fa fa-times"></i>
			</button>
			<strong class="green">
				候选人投票系统
			</strong>
   			————毕业设计作品
		</div>

		<div class="row">
			<div class="space-6"></div> 
			<div class="vspace-12-sm"></div>

			<div class="col-sm-5"> 
			</div><!-- /.col -->
		</div><!-- /.row -->

 
	</div><!-- /.col -->
</div><!-- /.row -->

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${contextPath}/static/assets/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">
		var scripts = [ null, "${contextPath}/static/assets/js/jquery-ui.custom.js", "${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/jquery.easypiechart.js", "${contextPath}/static/assets/js/jquery.sparkline.js",
        		"${contextPath}/static/assets/js/flot/jquery.flot.js", "${contextPath}/static/assets/js/flot/jquery.flot.pie.js", "${contextPath}/static/assets/js/flot/jquery.flot.resize.js", "${contextPath}/static/assets/js/activities-serverload.js", null ]
        $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
        	// inline scripts related to this page
        	jQuery(function($) {
        		$('.easy-pie-chart.percentage').each(function() {
        			var $box = $(this).closest('.infobox');
        			var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
        			var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
        			var size = parseInt($(this).data('size')) || 50;
        			$(this).easyPieChart({
        				barColor : barColor,
        				trackColor : trackColor,
        				scaleColor : false,
        				lineCap : 'butt',
        				lineWidth : parseInt(size / 10),
        				animate : /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
        				size : size
        			});
        		})

        		$('.sparkline').each(function() {
        			var $box = $(this).closest('.infobox');
        			var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
        			$(this).sparkline('html', {
        				tagValuesAttribute : 'data-values',
        				type : 'bar',
        				barColor : barColor,
        				chartRangeMin : $(this).data('min') || 0
        			});
        		});

        		// flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
        		// but sometimes it brings up errors with normal resize event handlers
        		$.resize.throttleWindow = false;

        		

        		// Android's default browser somehow is confused when tapping on label which will lead to dragging the task
        		// so disable dragging when clicking on label
        		var agent = navigator.userAgent.toLowerCase();
        		if ("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
        			$('#tasks').on('touchstart', function(e) {
        				var li = $(e.target).closest('#tasks li');
        				if (li.length == 0)
        					return;
        				var label = li.find('label.inline').get(0);
        				if (label == e.target || $.contains(label, e.target))
        					e.stopImmediatePropagation();
        			});

        		$('#tasks').sortable({
        			opacity : 0.8,
        			revert : true,
        			forceHelperSize : true,
        			placeholder : 'draggable-placeholder',
        			forcePlaceholderSize : true,
        			tolerance : 'pointer',
        			stop : function(event, ui) {
        				// just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
        				$(ui.item).css('z-index', 'auto');
        			}
        		});
        		$('#tasks').disableSelection();
        		$('#tasks input:checkbox').removeAttr('checked').on('click', function() {
        			if (this.checked)
        				$(this).closest('li').addClass('selected');
        			else
        				$(this).closest('li').removeClass('selected');
        		});

        		// show the dropdowns on top or bottom depending on window height and menu position
        		$('#task-tab .dropdown-hover').on('mouseenter', function(e) {
        			var offset = $(this).offset();

        			var $w = $(window)
        			if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
        				$(this).addClass('dropup');
        			else
        				$(this).removeClass('dropup');
        		});
        		
        		Index.initCharts(); // init activities-serverload.js's method
        		
        	})
        });
		
</script>
