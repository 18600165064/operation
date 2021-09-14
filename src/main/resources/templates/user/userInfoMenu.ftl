<#include "../common/header.html">
<link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
<link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
<link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
<link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
<script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine-zh_CN.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>
<script src="${ctx!}/assets/js/fileinput.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/locales/zh.js"></script>
<script src="${ctx!}/assets/js/plugins/piexif.js"></script>


  <body class="nav-md">
  <div class="container body">
      <div class="main_container">
          <div class="col-md-3 left_col">
              <div class="left_col scroll-view">
                  <div class="navbar nav_title" style="border: 0;">
                      <a href="/" class="site_title"><i class="fa fa-paw"></i><span>阅米</span></a>
                  </div>
                  <div class="clearfix"></div>
                  <!-- sidebar menu -->
           <#include "../common/left.ftl">
                  <!-- /sidebar menu -->
              </div>
          </div>
    <#include "../common/nav.ftl">
          <!-- page content -->
          <div class="right_col" role="main">
              <div class="">
                  <div class="page-title">
                      <div class="title_left">
                          <h3>顶级账户设置
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                   <input type="text" placeholder="用户ID" name="user_id" id="user_id" class=".col-md-2">
                                   <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                              </div>
                          </div>
                          <div class="x_content">
                          
                          	<!-- 模态框（Modal） -->
									<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="myModalLabel">
														选择用户权限
													</h4>
												</div>
												<div class="modal-body">
													<form id="form" class="form-horizontal">
														<table class="table table-bordered">
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em></td>
												                <td width="30%" >
												                	  <input type="hidden"  class="form-control" id="ids" name="ids"/>
													                  <select class="form-control"  id="other_status" name="other_status" onchange="selectType()">
													                  		<option value="">请选择</option>	
													                  		<option id="0" value="0">普通用户</option>	
													                  		<option id="1" value="1">特殊散户</option>	
													                  		<option id="2" value="2">直营</option>	
													                  		<option id="3" value="3">机构</option>	
													                  		<option id="4" value="4">顶级账户</option>	
													                  		<option id="5" value="5">微商</option>	
													                  		<option id="6" value="6">网红</option>
													                  		<option id="7" value="7">招商</option>
													                  		<option id="7" value="8">员工</option>
													                  		<option id="7" value="9">团队长</option>
													                  </select>
												                </td>
												              </tr>
															  <tr id="protectDay">
																  <td class="input-group-addon" width="20%"> 上架商品 </em></td>
																  <td width="30%" >
																	  <select class="form-control"  id="spuid" name="spuid">
																	  </select>
																  </td>
															  </tr>
															<tr id="groupTeam">
																<td class="input-group-addon" width="20%"> 保护时间 </em></td>
																<td width="30%" >
																	<input type="text"  class="form-control validate[required,custom[integer],min[1]]" id="protect_day" name="protect_day" placeholder="天" data-errormessage-value-missing="保护时间不能为空"/>
																</td>
															</tr>
												            </table>
												     </form>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" onclick="submitAll()" class="btn btn-primary">
														确认
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
                          
                          
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>ID</th>
                                      <th>唯一标识</th>
                                      <th>小程序open_id</th>
                                      <th>手机号</th>
                                      <th>昵称</th>
                                      <th>头像</th>
                                      <th>性别</th>
                                      <th>生日</th>
                                      <th>创建时间</th>
                                      <th>状态</th>
                                      <th>操作</th>
                                  </tr>
                                  </thead>
                              </table>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  </div>
  <!-- footer content -->
  <footer>
      <div class="pull-right">
      </div>
      <div class="clearfix"></div>
  </footer>
  <!-- /footer content -->
  </div>
  </div>
  </body>
    <script src="${ctx!}/assets/js/custom.js"></script>
<script>
var table = $('#datatable').DataTable({
	    "searching": false,
	    "ordering": false,
	    "processing": true,
	    "language": {
	        "url": '${ctx!}/assets/js/Chinese.json'
	    },
	    "serverSide": true,
	    "ajax": {
	        "url": "${ctx!}/userInfo/getUserInfoData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "user_id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "unionid",	        	
	          "class":"text-center",
	          "render":function(data, type, row) {
	                return dataHandle(data);
	                }
	        },
	        {
	          "data": "open_id_small",	        	
	          "class":"text-center",
	          "render":function(data, type, row) {
	                return dataHandle(data);
	                }
	        },
	        {
	          "data": "phone",	        	
	          "class":"text-center",
              "render":function(data, type, row) {
                return dataHandle(data);
                }
	        },
	        {
	          "data": "nick_name",	        	
	          "class":"text-center",
              "render":function(data, type, row) {
                return dataHandle(data);
                }
	        },
            {
                "data": "head_icon",
                "render": function (data, type, row, meta) {
                    return updateImg(data);
                }
            },
            {
                "data": "sex",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandleSex(data);
                    }
            },
            {
                "data": "birthday",
                "class":"text-center",
                "render":function(data, type, row) {
                    return formatDateTime2Date(data);
                    }
            },
            {
  	          "data": "create_time",	        	
  	          "class":"text-center",
  	          "render":function(data, type, row) { 
          	 	 return formatDateTime2Date(data); 
          	 	}	
  		     },
  		   {
  	            "data": "usage_status",
  	            "class":"text-center",
  	            "render":function(data, type, row) {
  	                return dataHandleUsage(data);
  	                }
  		   },
	        {
	        	"data": "user_id",
	        	"class":"text-center",
	        	"render":function(data, type, row) {
                        return '<button class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" onclick="updateId('+data+')">设置用户属性</button>';
	        	 }
	        }
	    ]
	});


$(function () {
	_errFm = $('#form');
	_errFm.validationEngine('attach', {
		maxErrorsPerField: 1,
		autoHidePrompt: true,
		autoHideDelay: 2000
	});
})

function dataHandleUsage(data){
	var _usage = '禁用';
	if(data == 0){
		_usage = '启用';
	}
	return _usage;
}	
	
	
function dataHandleSex(data){
	var _sex = '男';
	if(data == 1){
		_sex = '女';
	}
	return _sex;
}


 	function select() {
        var userId = $("#user_id").val();
        var param = {
            "user_id": userId
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    }
 	
 	function updateImg(data){
 	    if(data != null && data != "" && data.length > 0){
            var url = data.substr(0,7);
            if(url != null && url != '' && url == '/upload'){
                var realUrl = "http://img.yuemee.com"+data;
                return "<img src="+realUrl+" height=\"30\" width=\"30\" />";
            }else{
                return "<img src="+data+" height=\"30\" width=\"30\" />";
            }
        }else{
 	        return "";
        }
    }


    function dataHandle(data){
        if(data != null && data != '' && data != 'undefined'){
            data = data;
        }else{
            data = '';
        }
        return data;
    }
 	
 	/**
     * 时间戳转时间
     * */
    function formatDateTime2Date(value) {
        if (value == null || value == '') {
            return '';
        }
        var dt;
        if (value instanceof Date) {
            dt = value;
        } else {
            dt = new Date(value);
        }
        return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
    }

    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
        }
        if (/(y+)/.test(format))
            format = format.replace(RegExp.$1, (this.getFullYear() + "")
                    .substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(format))
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        return format;
    }
 	
    function updateId(id){
		$("#spuid").empty();
		$("#protect_day").val('');
 		$("#ids").val(id);
 		$.ajax({
    		url:"${ctx!}/userInfo/selectById",
    		data : {"user_id":id},
    		dataType : "json",
    		async : false,
    		success : function(data) {
    			$("#other_status").val(data.other_status);
    			$('#myModal').modal('show');
				if (data.other_status == 9) {
					$.get("${ctx!}/groupTeam/getSpuData",function(result){
						var menuJson = JSON.parse(result);
						for (var i = 0; i < menuJson.length; i++) {
							$("#spuid").append("<option value='"+menuJson[i].spuid+"' title='"+menuJson[i].spu_keyword+"'>"+menuJson[i].spu_keyword+"</option>");
						}
						$("#spuid").val(data.spuid);
						if (data.spuid != null && data.spuid != '' && data.spuid != 'undefind'){
							$("#spuid").attr("disabled",true);
						}
					})
					$('#protectDay').show();
					$('#groupTeam').show();
					$("#protect_day").val(data.protect_day);
				}else{
					$('#protectDay').hide();
					$('#groupTeam').hide();
				}

    		}
    	})
 		
 	}

 	function selectType(){
		var _status = $("#other_status").val();
		var _spuid = $("#spuid").val();
		if (_status == 9) {
			$('#protectDay').show();
			$('#groupTeam').show();
			$.get("${ctx!}/groupTeam/getSpuData",function(result){
				var menuJson = JSON.parse(result);
				for (var i = 0; i < menuJson.length; i++) {
					$("#spuid").append("<option value='"+menuJson[i].spuid+"' title='"+menuJson[i].spu_keyword+"'>"+menuJson[i].spu_keyword+"</option>");
				}
			})
			if (_spuid == null || _spuid == '' && _spuid == 'undefind'){
				$("#spuid").attr("disabled",false);
			}
		}else{
			$('#protectDay').hide();
			$('#groupTeam').hide();
		}
	}

    
    function submitAll(){
		if (!_errFm.validationEngine('validate')) {
			return false;
		}


		var _day = $("#protect_day").val();
		var _spuid = $("#spuid").val();
		var _trans = $("#other_status").val();
		if (_trans == 9){
			if (_day == null || _day == '' || _day == 'undefind'){
				bootbox.alert("请添加保护时长");
				return false;
			}
		}

    	$('#myModal').modal('hide');
    	var _id = $("#ids").val();

    	if(_trans != null && _trans != '' && _trans != 'undefind'){
    		var srhData;
    		if(_trans == 0){
    			srhData = {"updateId":_id,"user_id":_id,"type":0,"roster":0,"identity":0,"infinite":0,"other_status":0,"protect_day":"0"};
    		}if(_trans == 1){
    			srhData = {"updateId":_id,"user_id":_id,"type":1,"roster":0,"identity":0,"infinite":0,"other_status":1,"protect_day":"0"};
    		}if(_trans == 2){
    			srhData = {"updateId":_id,"user_id":_id,"type":2,"roster":0,"identity":1,"infinite":0,"other_status":2,"protect_day":"0"};
    		}if(_trans == 3){
    			srhData = {"updateId":_id,"user_id":_id,"type":3,"roster":0,"identity":1,"infinite":1,"other_status":3,"protect_day":"0"};
    		}if(_trans == 4){
    			srhData = {"updateId":_id,"user_id":_id,"type":4,"roster":0,"identity":1,"infinite":1,"other_status":4,"protect_day":"0"};
    		}if(_trans == 5){
    			srhData = {"updateId":_id,"user_id":_id,"type":5,"roster":0,"identity":1,"infinite":0,"other_status":5,"protect_day":"0"};
    		}if(_trans == 6){
				srhData = {"updateId":_id,"user_id":_id,"type":6,"roster":0,"identity":1,"infinite":0,"other_status":6,"protect_day":"0"};
			}if(_trans == 7){
				srhData = {"updateId":_id,"user_id":_id,"type":7,"roster":0,"identity":1,"infinite":2,"other_status":7,"protect_day":"0"};
			}if(_trans == 8){
				srhData = {"updateId":_id,"user_id":_id,"type":8,"roster":0,"identity":1,"infinite":3,"other_status":8,"protect_day":"0"};
			}if(_trans == 9){
				srhData = {"updateId":_id,"user_id":_id,"type":9,"roster":0,"identity":0,"infinite":1,"other_status":9,"protect_day":_day,"spuid":_spuid};
			}
    		$.ajax({
	    		url:"${ctx!}/userInfo/updateUserInfo",
	    		data : srhData,
	    		dataType : "json",
	    		async : false,
	    		success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error,function(){
							window.location.reload();
						});
					}else{
						bootbox.alert(data.error);
					}
	    		}
	    	})
    	}
    }
</script>
</html>
