<#include "../common/header.html">
<link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
<link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
<link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx!}/assets/bootstrap-validator/css/bootstrapValidator.min.css" />
<script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine-zh_CN.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>

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
                          <h3>供应商打款
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
<!--                                   <input type="text" placeholder="主订单号" name="userName" id="userName" class=".col-md-2"> -->
<!--                                   <input type="text" placeholder="子订单号" name="userName" id="userName" class=".col-md-2"> -->
<!--                                   <button type="button" class="btn btn-primary" onclick="select()">搜索</button> -->
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
														确认清算金额
													</h4>
												</div>
												<div class="modal-body">
													<form id="form" class="form-horizontal">
														<table class="table table-bordered">
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>确认清算金额</td>
												                <td width="30%" >
													                  <input type="text"  class="form-control" id="confirm_amt_total" name="confirm_amt_total" placeholder="确认清算金额"/>
												                </td>
												             </tr>
												            </table>
												     </form>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" onclick="submit()" class="btn btn-primary">
														确认
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
                          
                          
                          
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>批次号</th>
                                  	  <th>供应商名称</th>
                                      <th>联系人</th>
                                      <th>办公电话</th>
                                      <th>币种</th>
                                      <th>银行信息</th>
                                      <th>账户持有人</th>
                                      <th>清算金额</th>
                                      <th>确认清算金额</th>
                                      <th>清算状态</th>
                                      <th>清算时间</th>
                                      <th>创建时间</th>
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
	        "url": "${ctx!}/supplierWithdraw/getSupplierWithdrawData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "batch_no",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "s_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "s_manager",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "office_phone",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "currency",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "bank_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "account_holder",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "settle_amt_total",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "confirm_amt_total",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHan(data); 
	        	 	}
	          
	        },
	        {
	          "data": "settle_status",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle(data); 
	        	 	}	
	        },
	        {
	          "data": "settle_time",	        	
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
	        	"data": "id",
	        	"class":"text-center",
	        	"render":function(data, type, row) {
	        		if(row.settle_status == 1){
	        			return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">明细导出</button>';
	        		}else{
	        			return '<a type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="liquidationAgo('+data+')">已经清算</a>  <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">确认清算</button>  <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">明细导出</button>';
	        		}
	        	 }
	        }
	    ]
	});
	var _id;

	function showAndHide(id){
		_id = id;
		$('#myModal').modal('show')
	}	

	function dataHan(data){
		if(data != null && data != '' && data != 'undefind'){
			return data;
		}else{
			return '';
		}
	}
	
	function submit(){
		$('#myModal').modal('hide')
		var _confirm = $("#confirm_amt_total").val();
		$.ajax({
			url:"${ctx!}/supplierWithdraw/submit",
			data:{"id":_id,"confirm_amt_total":_confirm},
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
			},
			error: function (data) {
				bootbox.alert(data.error);
	        }
		})
	}


	function liquidationAgo(id){
		$.ajax({
			url:"${ctx!}/supplierWithdraw/liquidationAgo",
			data:{"id":id},
			dataType:"json",
			async : false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error,function(){
							window.location.reload();
						});
					}else{
						bootbox.alert(data.error);
					}
			},
			error:function(data){
				bootbox.alert(data);
			}
		})
	}


	function dataHandle(data){
		var _status = "";
	    if(data == 0){
	    	_status = "待清算";
	    }if(data == 1){
	    	_status = "已清算";
	    }if(data == 2){
	    	_status = "预付款";
	    }
	    return _status;
	}

 	function select() {
        var userName = $("#userName").val();
        var param = {
            "userName": userName
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
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
 	
</script>
</html>
