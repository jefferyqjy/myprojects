function initializeEvents() {
	$('.datatable').dataTable({
		"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
		"bServerSide" : true,
		"sAjaxSource" : "/friend/admin/member/list.spring",
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
	      	        return oObj.aData.userName;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 1 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.userPassword;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 2 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.gender;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 3 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.age;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 4 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.address;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 5 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return oObj.aData.university.name;
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 6 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
      	        return oObj.aData.status;
           },
           "bVisible" : true ,
           "aTargets" : [ 7 ]
	        },
	        { "fnRender" : function (oObj, sVal) {
	      	        return createAction(oObj.aData.id, oObj.aData.status);
	           },
	           "bVisible" : true ,
	           "aTargets" : [ 8 ]
	        }]; 
}
function makeCollomns(){
	return [{ "mDataProp" : "id", "sHeight":"15px"}, 
        	{ "mDataProp" : "userName"},
        	{ "mDataProp" : "userPassword"},
        	{ "mDataProp" : "gender"},
        	{ "mDataProp" : "age"},
        	{ "mDataProp" : "address"},
        	{ "mDataProp" : "university"},
        	{ "mDataProp" : "status"},
        	{ "mDataProp" : "checked"}];
}
function createAction(id, status) {
	var inhtml = '<a class="btn btn-success" href="/friend/admin/member/view.spring?id=' + id + '">';
	inhtml += '<i class="icon-zoom-in icon-white"></i>查看</a> ';
	inhtml += '<a class="btn btn-info" href="/friend/admin/member/preUpdate.spring?id=' + id + '">';
	inhtml += '<i class="icon-edit icon-white"></i>修改</a> ';
	inhtml += '<a class="btn btn-danger" href="/friend/admin/member/delete.spring?id=' + id + '">';
	inhtml += '<i class="icon-trash icon-white"></i>删除</a>&nbsp;';
	if (status ===1){
		inhtml += '<a class="btn btn-info" href="/friend/admin/member/invalid.spring?id=' + id + '">';
		inhtml += '<i class="icon-trash icon-white"></i>冻结</a>&nbsp;';
	} else if (status ===0) {
		inhtml += '<a class="btn btn-success" href="/friend/admin/member/valid.spring?id=' + id + '">';
		inhtml += '<i class="icon-edit icon-white"></i>恢复</a>';
	}
	
	return inhtml;
}
