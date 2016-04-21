//用户登录
function login(path,closed){
	$("body").append("<div id='dlg_login' style='padding:20px;'></div>");
	$('#dlg_login').dialog({
		href:path+'/admin/login.jsp',
		modal:true,
		closed:closed,
	    title:'用户登录',
	    width:350,
	    height:210,
	    closable:false,
	    buttons:[{
	        text:'登录',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_login').form('submit',{
	            	url:path+'/AdminLoginServlet',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "1"){
				    		$.messager.alert('系统消息','用户名或密码错误','error');
				    	}else{
				    		//window.navigate(path+'/index.jsp');
				    		$.messager.alert('系统消息','登录成功','info',function(){
					    		$('#dlg_login').dialog('refresh');
					    		$('#dlg_login').dialog('close');
					    		location.href = path+'/admin/index.jsp';				    			
				    		},false);
				    	} 
				    }
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
			handler:function(){
				$('#dlg_login').dialog('refresh');
	        }
	    }]
	});
}
//修改密码
function editpass(path,type){
	$("body").append("<div id='dlg_update_pwd' style='padding:20px;'></div>");
	$('#dlg_update_pwd').dialog({
		href:path+'/admin/pwd_update.jsp',
		modal:true,
		closed:false,
		title:'修改密码',
		width:310,
		height:200,
		buttons:[{
			text:'提交',
			iconCls:'icon-ok',
			handler:function(){
				$('#form_pwd_update').form('submit',{
					url:path+'/PwdUpdateServlet?type='+type,
					onSubmit:function(){
						return $(this).form('validate');
					},
					success:function(data){
						if(data == "-1"){
							$.messager.alert('系统消息','修改密码失败','error');
						}else{
							$.messager.alert('系统消息','密码修改成功','info');
							$('#dlg_update_pwd').dialog('refresh');
							$('#dlg_update_pwd').dialog('close');
						} 
					}  
				});
			}
		},{
			text:'重置',
			iconCls:'icon-reload',
			handler:function(){
				$('#dlg_update_pwd').dialog('refresh');
			}
		}]
	});
}
//添加管理员
function adminAdd(path){
	$("body").append("<div id='dlg_systemuser_add' style='padding:20px;'></div>");
	$('#dlg_systemuser_add').dialog({
		href:path+'/admin/systemuser_add.jsp',
		modal:true,
		closed:false,
	    title:'添加系统用户',
	    width:300,
	    height:190,
	    buttons:[{
	        text:'提交',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_systemuser_add').form('submit',{
	            	url:path+'/AdminAddServlet?type=admin',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','用户名已存在','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_login').dialog('refresh');
					    		$('#dlg_login').dialog('close');
					    		location.href = path+'/admin/systemuser.jsp';				    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_systemuser_add').dialog('refresh');
	        }
	    }]
	});
}
//添加普通用户
function usersadd(path){
	$("body").append("<div id='dlg_reg' style='padding:20px;'></div>");
	$('#dlg_reg').dialog({
		href:path+'/admin/users_add.jsp',
		modal:true,
		closed:false,
	    title:'添加普通用户',
	    width:480,
	    height:480,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_reg').form('submit',{
	            	url:path+'/AdminAddServlet?type=users',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','用户名已存在','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_reg').dialog('refresh');
					    		$('#dlg_reg').dialog('close');
					    		location.href = path+'/admin/users.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_reg').dialog('refresh');
	        }
	    }]
	});
}
//修改信息
function fodify_information(path,id,users,web){
	$("body").append("<div id='dlg_fodify_information' style='padding:20px;'></div>");
	$('#dlg_fodify_information').dialog({
	    onLoad:function(){
	    	$("#form_reg tr:lt(7)").detach();
	    	$("#form_reg tr:last").detach();
	    	$('#form_reg').form('load',{
	    		phone:users[7],
	    		address:users[8],
	    		postalcode:users[9],
	    		email:users[10]
			});
	    },
	    onBeforeOpen:function(){
	    	$('#dlg_fodify_information').dialog('refresh');
	    },
		href:path+'/admin/users_add.jsp',
		modal:true,
		closed:false,
	    title:'修改信息',
	    width:360,
	    height:280,
	    buttons:[{
	        text:'提交',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_reg').form('submit',{
	            	url:path+'/InforUpdateServlet?id='+id,
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','修改信息失败！','error');
				    	}else{
				    		$.messager.alert('系统消息','修改信息成功！','info',function(){
				    			$('#dlg_fodify_information').dialog('refresh');
				    			$('#dlg_fodify_information').dialog('close');
				    			location.href = path+'/admin/'+web;
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_fodify_information').dialog('refresh');
	        }
	    }]
	});
}
//用户注销
function logout(path){
	$.ajax({
   		type: 'POST',
   		url: path+'/RemoveServlet',
   		data: 'mark=admin',
	   	success: function(msg){
	     window.location.href(path+'/admin/index.jsp');
	   }
	});
}
//解析JSON
function parseJson (text) {
	//extract JSON string
	var match;
	if ((match = /\{[\s\S]*\}|\[[\s\S]*\]/.exec(text))) {
		text = match[0];
	}
	var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
	cx.lastIndex = 0;
	if (cx.test(text)) {
		text = text.replace(cx, function (a) {
			return '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
		});
	}
	if (/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
		return eval('(' + text + ')');
	}
	throw 'JSON parse error';
}
//上传文件
function upload(path){
	$("body").append("<div id='dlg_upload' style='padding:20px;'></div>");
	$('#dlg_upload').dialog({
		href:path+'/admin/upload_file.jsp',
		modal:true,
		closed:false,
	    title:'上传文件',
	    width:450,
	    height:150,
	    closable:true,
	    buttons:[{
	        text:'上传',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#upload_file').form('submit',{
	            	url:path+'/admin/upload_json.jsp',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	var json = parseJson(data);
				    	if(json.error === 1){
				    		$.messager.alert('系统消息',json.message,'error');
				    	}else{
				    		//window.navigate(path+'/index.jsp');
				    		$.messager.alert('系统消息','上传成功','info',function(){
					    		$('#dlg_upload').dialog('refresh');
					    		$('#dlg_upload').dialog('close');
					    		$('#paths').val(json.url);				    			
				    		},false);
				    	} 
				    }
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_upload').dialog('refresh');
	        }
	    }]
	});
}
//添加病房
function wardadd(path){
	$("body").append("<div id='dlg_ward' style='padding:20px;'></div>");
	$('#dlg_ward').dialog({
		href:path+'/admin/ward_add.jsp',
		modal:true,
		closed:false,
	    title:'添加病房',
	    width:300,
	    height:240,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_ward').form('submit',{
	            	url:path+'/WardSickbedServlet?type=ward',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','病房编号重复','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_ward').dialog('refresh');
					    		$('#dlg_ward').dialog('close');
					    		location.href = path+'/admin/ward.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_ward').dialog('refresh');
	        }
	    }]
	});
}
//添加病床
function sickbedadd(path,ward){
	$("body").append("<div id='dlg_sickbed' style='padding:20px;'></div>");
	$('#dlg_sickbed').dialog({
		href:path+'/admin/sickbed_add.jsp',
		modal:true,
		closed:false,
	    title:'添加病床',
	    width:300,
	    height:150,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_sickbed').form('submit',{
	            	url:path+'/WardSickbedServlet?type=sickbed&ward='+ward,
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','病床编号重复','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_sickbed').dialog('refresh');
					    		$('#dlg_sickbed').dialog('close');
					    		location.href = path+'/admin/ward.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_sickbed').dialog('refresh');
	        }
	    }]
	});
}
//添加药品信息
function drugadd(path){
	$("body").append("<div id='dlg_drug' style='padding:20px;'></div>");
	$('#dlg_drug').dialog({
		href:path+'/admin/drug_add.jsp',
		modal:true,
		closed:false,
	    title:'添加药品信息',
	    width:460,
	    height:480,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_drug').form('submit',{
	            	url:path+'/DrugServlet?type=add',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','用户名已存在','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_drug').dialog('refresh');
					    		$('#dlg_drug').dialog('close');
					    		location.href = path+'/admin/drug.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_drug').dialog('refresh');
	        }
	    }]
	});
}
//修改药品信息
function fodify_drug(path,id,drug){
	$("body").append("<div id='dlg_fodify_drug' style='padding:20px;'></div>");
	$('#dlg_fodify_drug').dialog({
	    onLoad:function(){
	    	$('#form_drug').form('load',{
	    		name:drug[1],
	    		norms:drug[2],
	    		approval:drug[3],
	    		factory:drug[4],
	    		ingredient:drug[5],
	    		treat:drug[6],
	    		dosage:drug[7],
	    		price:drug[8]
			});
	    },
	    onBeforeOpen:function(){
	    	$('#dlg_fodify_drug').dialog('refresh');
	    },
		href:path+'/admin/drug_add.jsp',
		modal:true,
		closed:false,
	    title:'修改药品信息',
	    width:440,
	    height:480,
	    buttons:[{
	        text:'提交',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_drug').form('submit',{
	            	url:path+'/DrugServlet?type=update&id='+id,
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','修改信息失败！','error');
				    	}else{
				    		$.messager.alert('系统消息','修改信息成功！','info',function(){
				    			$('#dlg_fodify_drug').dialog('refresh');
				    			$('#dlg_fodify_drug').dialog('close');
				    			location.href = path+'/admin/drug.jsp';	    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_fodify_drug').dialog('refresh');
	        }
	    }]
	});
}
//查看药品信息
function show_drug(path,id,drug){
	$("body").append("<div id='dlg_show_drug' style='padding:20px;'></div>");
	$('#dlg_show_drug').dialog({
	    onLoad:function(){
	    	$('#form_drug_show').form('load',{
	    		name:drug[1],
	    		norms:drug[2],
	    		approval:drug[3],
	    		factory:drug[4],
	    		ingredient:drug[5],
	    		treat:drug[6],
	    		dosage:drug[7],
	    		price:drug[8]
			});
	    },
	    onBeforeOpen:function(){
	    	$('#dlg_show_drug').dialog('refresh');
	    },
		href:path+'/admin/drug_show.jsp',
		modal:true,
		closed:false,
	    title:'查看药品信息',
	    width:440,
	    height:480
	});
}
//挂号
function patientadd(path){
	$("body").append("<div id='dlg_patient' style='padding:20px;'></div>");
	$('#dlg_patient').dialog({
		href:path+'/admin/patient_add.jsp',
		modal:true,
		closed:false,
	    title:'挂号',
	    width:340,
	    height:380,
	    buttons:[{
	        text:'挂号',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_patient').form('submit',{
	            	url:path+'/PatientServlet',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','挂号失败','error');
				    	}else{
				    		$.messager.alert('系统消息','挂号成功','info',function(){
					    		$('#dlg_patient').dialog('refresh');
					    		$('#dlg_patient').dialog('close');
					    		location.href = path+'/admin/patient.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_patient').dialog('refresh');
	        }
	    }]
	});
}
//分配床位
function assignadd(path,sickbed){
	$("body").append("<div id='dlg_assign' style='padding:20px;'></div>");
	$('#dlg_assign').dialog({
		href:path+'/admin/assign_add.jsp',
	    onBeforeOpen:function(){
	    	$('#dlg_assign').dialog('refresh');
	    },
		modal:true,
		closed:false,
	    title:'分配床位',
	    width:300,
	    height:150,
	    buttons:[{
	        text:'分配',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_assign').form('submit',{
	            	url:path+'/AssignServlet?sickbed='+sickbed,
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','分配床位失败','error');
				    	}else{
				    		$.messager.alert('系统消息','分配床位成功','info',function(){
					    		$('#dlg_assign').dialog('refresh');
					    		$('#dlg_assign').dialog('close');
					    		location.href = path+'/admin/assign.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_assign').dialog('refresh');
	        }
	    }]
	});
}
//药品入库
function stockadd(path){
	$("body").append("<div id='dlg_stock' style='padding:20px;'></div>");
	$('#dlg_stock').dialog({
		href:path+'/admin/stock_add.jsp',
		modal:true,
		closed:false,
	    title:'药品入库',
	    width:340,
	    height:280,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_stock').form('submit',{
	            	url:path+'/StockServlet',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','添加失败','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_stock').dialog('refresh');
					    		$('#dlg_stock').dialog('close');
					    		location.href = path+'/admin/stock.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_stock').dialog('refresh');
	        }
	    }]
	});
}
//添加新病状
function symptomadd(path){
	$("body").append("<div id='dlg_symptom' style='padding:20px;'></div>");
	$('#dlg_symptom').dialog({
		href:path+'/admin/symptom_add.jsp',
		modal:true,
		closed:false,
	    title:'添加新病状',
	    width:440,
	    height:280,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_symptom').form('submit',{
	            	url:path+'/SymptomServlet',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','添加失败','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_symptom').dialog('refresh');
					    		$('#dlg_symptom').dialog('close');
					    		location.href = path+'/admin/symptom.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_symptom').dialog('refresh');
	        }
	    }]
	});
}
//患者开药
function prescribeadd(path){
	$("body").append("<div id='dlg_prescribe' style='padding:20px;'></div>");
	$('#dlg_prescribe').dialog({
		href:path+'/admin/prescribe_add.jsp',
		modal:true,
		closed:false,
	    title:'患者开药',
	    width:340,
	    height:280,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_prescribe').form('submit',{
	            	url:path+'/PrescribeServlet',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','库存量不足','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_prescribe').dialog('refresh');
					    		$('#dlg_prescribe').dialog('close');
					    		location.href = path+'/admin/prescribe.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_prescribe').dialog('refresh');
	        }
	    }]
	});
}
//患者检查
function inspectadd(path){
	$("body").append("<div id='dlg_inspect' style='padding:20px;'></div>");
	$('#dlg_inspect').dialog({
		href:path+'/admin/inspect_add.jsp',
		modal:true,
		closed:false,
	    title:'患者检查',
	    width:340,
	    height:280,
	    buttons:[{
	        text:'添加',
	        iconCls:'icon-ok',
	        handler:function(){
	            $('#form_inspect').form('submit',{
	            	url:path+'/InspectServlet',
	                onSubmit:function(){
				        return $(this).form('validate');
				    },
				    success:function(data){
				    	if(data == "-1"){
				    		$.messager.alert('系统消息','添加失败','error');
				    	}else{
				    		$.messager.alert('系统消息','添加成功','info',function(){
					    		$('#dlg_inspect').dialog('refresh');
					    		$('#dlg_inspect').dialog('close');
					    		location.href = path+'/admin/inspect.jsp';	  			    			
				    		},false);
				    	} 
				    }  
	            });
	        }
	    },{
	        text:'重置',
	        iconCls:'icon-reload',
	        handler:function(){
	            $('#dlg_inspect').dialog('refresh');
	        }
	    }]
	});
}



