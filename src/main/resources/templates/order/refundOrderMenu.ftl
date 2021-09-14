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
                          <h3>退款审核
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
								  <input type="text" placeholder="主订单号" name="order_no" id="order_no" class=".col-md-2">
								  <input type="text" placeholder="子订单号" name="sub_order_no" id="sub_order_no" class=".col-md-2">
								  <input type="text" placeholder="用户ID" name="user_id" id="user_id" class=".col-md-2">
								  <input type="text" placeholder="收货人姓名" name="addr_name" id="addr_name" class=".col-md-2">
								  <input type="text" placeholder="收货人电话" name="addr_mobile" id="addr_mobile" class=".col-md-2">
								  <input type="text" placeholder="物流单号" name="trans_id" id="trans_id" class=".col-md-2">
								  <select class=".col-md-2" id="status" name="status" style="width: 100px;height: 25px">
									  <option value="" selected="selected">请选择</option>
									  <option value="dfk">待付款</option>
									  <option value="ptz">拼团中</option>
									  <option value="dfh">待发货</option>
									  <option value="dsh">待收货</option>
									  <option value="ywc">已完成</option>
									  <option value="close">关闭</option>
								  </select>
								  <select class=".col-md-2" id="after_sale_status" name="after_sale_status" style="width: 100px;height: 25px">
									  <option value="" selected="selected">请选择</option>
									  <option value="tzclz">团长处理中</option>
									  <option value="tzwtg">团长未通过</option>
									  <option value="kfclz">客服处理中</option>
									  <option value="kfwtg">客服未通过</option>
									  <option value="cwclz">财务处理中</option>
									  <option value="cwytg">财务已通过</option>
								  </select>
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                                  <button type="button" class="btn btn-primary" id="export" onclick="exportRefund()">导出</button>
                              </div>
                          </div>
                          <div class="x_content">
                          
                          		<!-- 模态框（Modal） -->
									<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="myModalLabel2">
														退款金额
													</h4>
												</div>
												<div class="modal-body">
													<form id="form" class="form-horizontal">
														<table class="table table-bordered">
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>退款金额</td>
												                <td width="30%" >
													                  <input type="text"  class="form-control" id="refund_value" name="refund_value" placeholder="退款金额"/>
												                </td>
												             </tr>
												            </table>
												     </form>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" onclick="submitRefund()" class="btn btn-primary">
														提交更改
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>		
                          		
                          
                          
                          
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                  	  <th>主订单号</th>
                                      <th>子订单号</th>
                                      <th>用户ID</th>
                                      <th>供应商ID</th>
                                      <th>支付方式</th>
                                      <th>订单状态</th>
                                      <th>订单价格</th>
                                      <th>运费</th>
                                      <th>售后状态</th>
                                      <th>联系人</th>
                                      <th>联系人电话</th>
									  <th>申请退款时间</th>
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
  </body>
    <script src="${ctx!}/assets/js/custom.js"></script>
<script>
var _status = "${status!""}";
var _url;
if(_status == 2){
	_url = "${ctx!}/order/getOrderByAfterStatus";
}if(_status == 1){
    $("#export").hide();
	_url = "${ctx!}/order/getOrderByAfterStatus2";
}
var table = $('#datatable').DataTable({
	    "searching": false,
	    "ordering": false,
	    "processing": true,
	    "language": {
	        "url": '${ctx!}/assets/js/Chinese.json'
	    },
	    "serverSide": true,
	    "ajax": {
	        "url":_url,
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "order_no",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "sub_order_no",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "user_id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "supplier_id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "pay_type",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle(data); 
	        	 	}
	        },
	        {
	          "data": "order_status",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle2(data); 
	        	 	}
	        },
	        {
	          "data": "c_amount",	        	
	          "class":"text-center"
	        },
			{
				"data": "dis_fee",
				"class":"text-center"
			},
	        {
	          "data": "after_sale_status",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle3(data); 
	        	 	}
	        },
	        {
	          "data": "addr_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "addr_mobile",	        	
	          "class":"text-center"
	        },
			{
				"data": "apply_time",
				"class":"text-center",
				"render":function(data, type, row) {
					return formatDateTime2Date(data);
				}
			},
	        {
	        	"data": "id",
	        	"class":"text-center",
	        	"render":function(data, type, row) {
	        		if(_status == 1){
	        			if(row.after_sale_status == 'cwclz'){
		        			return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="refund('+data+')">退款</button>  <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="cwrefuse('+data+')">驳回</button>';
		        		}else{
		        			return '';
		        		}
	        		}
	        		if(_status == 2){
	        			if(row.after_sale_status == 'kfclz' || row.after_sale_status == 'tzclz' || row.after_sale_status == 'tzwtg'){
		        			return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="adopt('+data+')">审核</button>  <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="kfrefuse('+data+')">驳回</button>';
		        		}else{
		        			return '';
		        		}
	        		}
	        	 }
	        }
	    ]
	});

function dataHandle(data){
	var _status = "";
	if(data == "wxsmall"){
		_status = "微信";
	}
	return _status;
}

function dataHandle2(data){
	var _status = "";
    if(data == 'dfk'){
    	_status = "待付款";
    }if(data == 'ptz'){
    	_status = "拼团中";
    }if(data == 'dfh'){
    	_status = "待发货";
    }if(data == 'dsh'){
    	_status = "待收货";
    }if(data == 'ywc'){
    	_status = "已完成";
    }if(data == 'ytk'){
    	_status = "已退款";
    }if(data == 'close'){
    	_status = "已关闭";
    }
    return _status;
}

function dataHandle3(data){
	var _status = "";
	 if(data == 'tzclz'){
    	_status = "团长处理中";
    }else if(data == 'tzwtg'){
    	_status = "团长未通过";
    }else if(data == 'kfclz'){
   		_status = "客服处理中";
    }else if(data == 'kfwtg'){
    	_status = "客服未通过";
    }else if(data == 'cwclz'){
    	_status = "财务处理中";
    }else if(data == 'cwwtg'){
    	_status = "财务未通过";
    }else if(data == 'cwytg'){
    	_status = "财务已通过";
    }else{
    	_status = "暂无";
    }
    return _status;
}

	<!-- 客服通过处理 -->
 	function adopt(id){
 		bootbox.confirm("确认要通过审核吗?", function(result){
			if(result) {
 		var srhData = {"id":id,"after_sale_status":"cwclz"};
		 		 $.ajax({
		 			url:"${ctx!}/order/editOrderById",
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
		})
 	}
 	
 	<!-- 客服未通过处理 -->
	function kfrefuse(id){
		bootbox.confirm("确认驳回?", function(result){
			if(result) {
 		var srhData = {"id":id,"after_sale_status":"kfwtg"};
	 		 $.ajax({
	 			url:"${ctx!}/order/editOrderById",
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
		})
 	}
	
	<!-- 财务未通过处理 -->
	function cwrefuse(id){
		bootbox.confirm("确认驳回?", function(result){
			if(result) {
 		var srhData = {"id":id,"after_sale_status":"cwwtg"};
 		 $.ajax({
 			url:"${ctx!}/order/editOrderById",
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
		})
 	}
 	
	var _id;
	<!-- 财务通过处理 -->
	function refund(id){
		_id = id;
		var srhData = {"id":_id};
		$.ajax({
			url:"${ctx!}/order/selectRefundPrice",
			data:srhData,
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.draw == 1) {
					$("#refund_value").val(data.data);
					$('#myModal2').modal('show')
				}else{
					bootbox.alert(data.error);
				}
			}
		})

 	}
	
	
	function submitRefund(id){
		$('#myModal2').modal('hide')
		var _amount = $("#refund_value").val();
 		var srhData = {"id":_id,"amount":_amount,"after_sale_status":"cwytg"};
 		 $.ajax({
 			url:"${ctx!}/order/submitRefund",
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
	
	
 	function select() {
		var order_no = $("#order_no").val();
		var sub_order_no = $("#sub_order_no").val();
		var user_id = $("#user_id").val();
		var addr_name = $("#addr_name").val();
		var addr_mobile = $("#addr_mobile").val();
		var trans_id = $("#trans_id").val();
		var status = $("#status").val();
		var saleStatus = $("#after_sale_status").val();
		var param = {
			"order_no": order_no,
			"sub_order_no": sub_order_no,
			"user_id": user_id,
			"addr_name": addr_name,
			"addr_mobile": addr_mobile,
			"trans_id": trans_id,
			"order_status":status,
			"after_sale_status":saleStatus
		};
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    }

    function exportRefund(){
        window.location.href = "${ctx!}/order/exportRefund";
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