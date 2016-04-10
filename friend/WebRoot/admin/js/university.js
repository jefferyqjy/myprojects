function initializeEvents() {
	$('.datatable').dataTable({
		"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
		"bServerSide" : true,
		"sAjaxSource" : "/friend/admin/university/list.spring",
	    "sServerMethod" : "POST" ,
	    "bProcessing" : false,
	    "bPaginate": true,
		"bLengthChange" : true,
		"iDisplayLength" : 10,
		"fnAdjustColumnSizing" : false,
		"bStateSave": false,
		"bSort":false,
		"bFilter":false,
		"aoColumnDefs" : makeCollumnDef(),
		"aoColumns" : makeCollomns(),
		"sPaginationType": "bootstrap",
		"oLanguage": {
		"sLengthMenu": "_MENU_ records per page"
		},
		fnDrawCallback : function() {
		var imgs = $('.datatable tbody tr td img');
		$("#imgArea").html('');
		var htmls = "";
		for(var i=0; i < imgs.length;i++) {
		htmls = "";
		var name = imgs[i].name;
		var path = imgs[i].src; 
		htmls += "<li id='" + name+"' class='thumbnail'>";
		htmls += "<a style='background:url("+path+")' title='"+name+"' href='"+path+"' class='cboxElement'><img class='grayscale' src='"+path+"' alt='"+name+"'></a>";
		htmls += "</li>";
		$("#imgArea").append(htmls);
		}
		},
		fnInitComplete : function() {
		var imgs = $('.datatable tbody tr td img');
		$("#imgArea").html('');
		var htmls = "";
		for(var i=0; i < imgs.length;i++) {
		htmls = "";
		var name = imgs[i].name;
		var path = imgs[i].src; 
		htmls += "<li id='" + name+"' class='thumbnail'>";
		htmls += "<a style='background:url("+path+")' title='"+name+"' href='"+path+"' class='cboxElement'><img class='grayscale' src='"+path+"' alt='"+name+"'></a>";
		htmls += "</li>";
		$("#imgArea").append(htmls);
		}
		}
	} );
}
function makeCollumnDef() {
	return [
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.id;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 0 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.name;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 1 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.city.name;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 2 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return createAction(oObj.aData.id);
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 3 ]
	        }]; 
}
function makeCollomns(){
	return [{ "mDataProp" : "id", "sHeight":"15px"}, 
        	{ "mDataProp" : "name"},
        	{ "mDataProp" : "city"},
        	{ "mDataProp" : "checked"}];
}
function createAction(id) {
	var inhtml = '<a class="btn btn-success" href="/friend/admin/university/view.spring?id=' + id + '">';
	inhtml += '<i class="icon-zoom-in icon-white"></i>View</a> ';
	inhtml += '<a class="btn btn-info" href="/friend/admin/university/preUpdate.spring?id=' + id + '">';
	inhtml += '<i class="icon-edit icon-white"></i>Edit</a> ';
	inhtml += '<a class="btn btn-danger" href="/friend/admin/university/delete.spring?id=' + id + '">';
	inhtml += '<i class="icon-trash icon-white"></i>Delete</a>';
	return inhtml;
}
