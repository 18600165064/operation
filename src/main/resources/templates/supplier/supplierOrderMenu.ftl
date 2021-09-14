<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
<link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
<link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>
<script src="${ctx!}/assets/js/fileinput.js"></script>

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
                          <h3>供应商订单管理
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
								  <input type="text" placeholder="最小数量" name="smallNum" id="smallNum" class=".col-md-2">
								  <input type="text" placeholder="最大数量" name="bigNum" id="bigNum" class=".col-md-2">
								  <select class=".col-md-2" id="status" name="status" style="width: 100px;height: 25px">
									  <option value="" selected="selected">请选择</option>
									  <option value="dfk">待付款</option>
									  <option value="ptz">拼团中</option>
									  <option value="dfh">待发货</option>
									  <option value="dsh">待收货</option>
									  <option value="ywc">已完成</option>
									  <option value="close">关闭</option>
								  </select>
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
								  <button type="button" id="withdrawApply" style="float:right" class="btn btn-primary" onclick="export2()">导出</button>
								  <button type="button" id="withdrawApply" style="float:right" class="btn btn-primary" onclick="importExcel()">导入</button>
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
														添加物流
													</h4>
												</div>
												<div class="modal-body">
													<form id="form" class="form-horizontal">
														<table class="table table-bordered">
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>物流公司</td>
												                <td width="30%" >
												                	  <input type="hidden"  class="form-control" id="ids" name="ids"/>
													                  <select class="form-control"  id="trans_com" name="trans_com">
													                  		
													                  </select>
												                </td>
												              </tr>
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>物流单号</td>
												                <td width="30%" >
													                  <input type="text"  class="form-control" id="trans_id" name="trans_id" placeholder="物流单号"/>
												                </td>
												             </tr>
															<tr>
																<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>联系人姓名</td>
																<td width="30%" >
																	<input type="text"  class="form-control" id="userName" name="userName" placeholder="联系人姓名"/>
																</td>
															</tr>
															<tr>
																<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>联系人电话</td>
																<td width="30%" >
																	<input type="text"  class="form-control" id="userPhone" name="userPhone" placeholder="联系人电话"/>
																</td>
															</tr>
															<tr>
																<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>联系人地址</td>
																<td width="30%" >
																	<input type="text"  class="form-control" id="userAddress" name="userAddress" placeholder="联系人地址"/>
																</td>
															</tr>
												            </table>
												           </form>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" onclick="submitAll()" class="btn btn-primary">
														提交更改
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
                          
                          <!-- 模态框（Modal） -->
									<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
										<div class="modal-dialog" style="width:1100px">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="myModalLabel2">
														查看物流
													</h4>
												</div>
												<div class="modal-body">
													<form id="form" class="form-horizontal">
														<table class="table table-bordered">
															<tr>
												             	 <td><span style="text-align: center;display:block;">产品信息</span></td>
												             </tr>
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供应商名称</td>
												                <td width="30%" colspan="9">
													                  <input type="text"  class="form-control" id="s_name2" name="s_name2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>商品名</td>
												                <td width="30%" colspan="9">
													                  <input type="text"  class="form-control" id="sku_name2" name="sku_name2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>进价</td>
												                <td width="20%" >
													                  <input type="text"  class="form-control" id="supply_price2" name="supply_price2" readonly="readonly" />
												                </td>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>单价</td>
												                <td width="20%" >
													                  <input type="text"  class="form-control" id="price2" name="price2" readonly="readonly" />
												                </td>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>数量</td>
												                <td width="20%" >
													                  <input type="text"  class="form-control" id="num2" name="num2" readonly="readonly" />
												                </td>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>小计</td>
												                <td width="20%" >
													                  <input type="text"  class="form-control" id="subtotal2" name="subtotal2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												             	 <td><span style="text-align: center;display:block;">订单信息</span></td>
												             </tr>
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>订单编号</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="order_no2" name="order_no2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>订单状态</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="order_status2" name="order_status2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>创建时间</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="create_time2" name="create_time2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>订单金额</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="c_amount2" name="c_amount2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>支付方式</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="pay_type2" name="pay_type2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                 <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>支付时间</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="pay_time2" name="pay_time2" readonly="readonly" />
												                </td>
												             </tr>
												             <tr>
												             	 <td><span style="text-align: center;display:block;">收货信息</span></td>
												             </tr>
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>姓名</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="addr_name2" name="addr_name2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>电话</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="addr_mobile2" name="addr_mobile2" readonly="readonly" />
												                </td>
											                </tr>
											                <tr>
												                 <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>地址</td>
												                <td width="30%"  colspan="9">
													                  <input type="text"  class="form-control" id="addr_detail2" name="addr_detail2" readonly="readonly" />
												                </td>
												             </tr>
												             <tr>
												             	 <td><span style="text-align: center;display:block;">物流信息</span></td>
												             </tr>
												         </table>
														<table id="datatable3" class="table table-bordered">
															<thead>
							                                  <tr>
							                                      <th>序号</th>
							                                      <th>时间</th>
							                                      <th>物流信息</th>
							                                  </tr>
							                                  </thead>
							                                  <tbody> 
                						         						
                											  </tbody>
												         </table>
												     </form>
												</div>
<!-- 												<div class="modal-footer"> -->
<!-- 													<button type="button" class="btn btn-default" data-dismiss="modal">关闭 -->
<!-- 													</button> -->
<!-- 													<button type="button" onclick="submitRefund()" class="btn btn-primary"> -->
<!-- 														提交更改 -->
<!-- 													</button> -->
<!-- 												</div> -->
											</div>
										</div>
									</div>



							  <!-- 模态框（Modal） -->
							  <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
									  <div class="modal-content">
										  <div class="modal-header">
											  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
												  &times;
											  </button>
											  <h4 class="modal-title" id="myModalLabel">
												  选择文件
											  </h4>
										  </div>
										  <div class="modal-body">
											  <form id="form3" class="form-horizontal">
												  <table class="table table-bordered">
													  <tr>
														  <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>选择文件</td>
														  <td width="30%" >
															  <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
															  <input type="file" class="file" id="img2" name="excelFile">
															  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
														  </td>
													  </tr>
												  </table>
											  </form>
										  </div>
										  <div class="modal-footer">
											  <button type="button" class="btn btn-default" data-dismiss="modal">关闭
											  </button>
											  <button type="button" onclick="submitAll3()" class="btn btn-primary">
												  提交更改
											  </button>
										  </div>
									  </div><!-- /.modal-content -->
								  </div><!-- /.modal -->
							  </div>



							  <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
									  <div class="modal-content">
										  <div class="modal-header">
											  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
												  &times;
											  </button>
											  <h4 class="modal-title" id="myModalLabel">
												  导出数据为待发货状态的订单
											  </h4>
										  </div>
										  <div class="modal-body">
											  <form id="form4" class="form-horizontal">
												  <table class="table table-bordered">
													  <tr>
														  <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>产品名称</td>
														  <td width="30%" >
															  <select class="form-control" id="spuid" name="spuid" >

															  </select>
														  </td>
													  </tr>
												  </table>
											  </form>
										  </div>
										  <div class="modal-footer">
											  <button type="button" class="btn btn-default" data-dismiss="modal">关闭
											  </button>
											  <button type="button" onclick="submitAll4()" class="btn btn-primary">
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
                                      <th>物流单号</th>
                                      <th>用户ID</th>
                                      <th>供应商ID</th>
                                      <th>支付方式</th>
                                      <th>订单价格</th>
                                      <th>订单状态</th>
                                      <th>售后状态</th>
                                      <th>联系人</th>
                                      <th>联系人电话</th>
                                      <th>运费</th>
                                      <th>数量</th>
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
var _sid = "${sid!""}";
var table = $('#datatable').DataTable({
	    "searching": false,
	    "ordering": false,
	    "processing": true,
	    "language": {
	        "url": '${ctx!}/assets/js/Chinese.json'
	    },
	    "serverSide": true,
	    "ajax": {
	        "url": "${ctx!}/order/getOrderData?supplier_id="+_sid+"",
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
				"data": "trans_id",
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
					return dataHandle3(data);
				}
	        },
	        {
	          "data": "c_amount",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "order_status",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle2(data); 
	        	 	}
	        },
			{
				"data": "after_sale_status",
				"class":"text-center",
				"render":function(data, type, row) {
					return dataHandle4(data);
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
	          "data": "dis_fee",	        	
	          "class":"text-center"
	        },
			{
				"data": "num",
				"class":"text-center"
			},
	        {
	        	"data": "id",
	        	"class":"text-center",
	        	"render":function(data, type, row) {
	        		if(row.order_status != 'dfk'){
	        			if(row.order_status == 'dsh'){
	        				return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">添加物流</button> <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide2('+data+')">查看物流</button>';
	        			}if(row.order_status == 'ywc'){
	        				return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">添加物流</button> <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide2('+data+')">查看物流</button>';
	        			}if(row.order_status == 'ptz'){
	        				return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide2('+data+')">查看物流</button>';
	        			}if(row.order_status == 'close'){
	        				return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide2('+data+')">查看物流</button>';
	        			}if(row.order_status == 'ytk'){
							return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">添加物流</button> <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide2('+data+')">查看物流</button>';
						}else{
	        				return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="selfMention('+data+')">自提</button> <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">添加物流</button> <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide2('+data+')">查看物流</button>';
	        			}
	        		}else{
	        			return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide2('+data+')">查看物流</button>';
	        		}
	        	 }
	        }
	    ]
	});

$(function () {
	tableFun("${ctx!}/expressCompany/getData");
	tableFun3("${ctx!}/yMComment/getSpuData");
 });


function dataHandle4(data){
	var _status = "";
	if(data == 'tzclz'){
		_status = "团长处理中";
	}if(data == 'tzwtg'){
		_status = "团长未通过";
	} if(data == 'kfclz'){
		_status = "客服处理中";
	}if(data == 'kfwtg'){
		_status = "客服未通过";
	}if(data == 'cwclz'){
		_status = "财务处理中";
	}if(data == 'cwwtg'){
		_status = "财务未通过";
	}if(data == 'cwytg'){
		_status = "财务已通过";
	}
	return _status;
}

function dataHandle2(data){
	var _status = "";
    if(data == 'ptz'){
    	_status = "拼团中";
    }else if(data == 'dfh'){
    	_status = "待发货";
    }else if(data == 'dsh'){
    	_status = "待收货";
    }else if(data == 'ywc'){
    	_status = "已完成";
    }else if(data == 'ytk'){
    	_status = "已退款";
    }else if(data == 'close'){
    	_status = "已关闭";
    }else{
    	_status = "待付款";
    }
    return _status;
}


function dataHandle3(data){
	var _paytype = "";
	if(data == 'wxsmall'){
		_paytype = "微信";
	}
	return _paytype;
}


function tableFun(tdUrl){
	 $.get(tdUrl, function(result){ 
			var menuJson = JSON.parse(result);
	　　　　for (var i = 0; i < menuJson.length; i++) {
	　　　　	$("#trans_com").append("<option value='"+menuJson[i].express_name+"'>"+menuJson[i].express_name+"</option>");
		　　}
	})
}

function tableFun3(tdUrl){
	$.get(tdUrl, function(result){
		var menuJson = JSON.parse(result);
		for (var i = 0; i < menuJson.length; i++) {
			$("#spuid").append("<option value='"+menuJson[i].spuid+"'>"+menuJson[i].spu_name+"</option>");
		}
	})
}


<!-- 自提 -->
	function selfMention(id){
		bootbox.confirm("确认自提?", function(result){
		if(result) {
		var srhData = {"id" : id,"if_self":0,"order_status":"ywc"};
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
	
	
	function showAndHide2(id){
 		$('#myModal2').modal('show')
 		
 		$.ajax({
			url:"${ctx!}/order/getOtherData",
			data:{"id":id},
			dataType:"json",
			async : false,
			success : function(data) {
					var _status = "";
				    if(data.order_status == 'ptz'){
				    	_status = "拼团中";
				    }else if(data.order_status == 'dfh'){
				    	_status = "待发货";
				    }else if(data.order_status == 'dsh'){
				    	_status = "待收货";
				    }else if(data.order_status == 'ywc'){
				    	_status = "已完成";
				    }else if(data.order_status == 'ytk'){
				    	_status = "已退款";
				    }else if(data.order_status == 'close'){
				    	_status = "已关闭";
				    }else{
				    	_status = "待付款";
				    }
				    
				    var _payStatus = "";
				    if(data.pay_type == 'wxsmall'){
				    	_payStatus = "微信";
				    }if(data.pay_type == 2){
				    	_payStatus = "支付宝";
				    }if(data.pay_type == 3){
				    	_payStatus = "银联";
				    }if(data.pay_type == 7){
				    	_payStatus = "线下支付";
				    }
				    
					$("#s_name2").val(data.s_name);
					$("#sku_name2").val(data.sku_name);
					$("#supply_price2").val(data.supply_price);
					$("#price2").val(data.price);
					$("#num2").val(data.num);
					$("#subtotal2").val(data.subtotal);
					$("#order_no2").val(data.order_no);
					$("#order_status2").val(_status);
					$("#create_time2").val(formatDateTime2Date(data.create_time));
					$("#c_amount2").val(data.c_amount);
					$("#pay_type2").val(_payStatus);
					$("#pay_time2").val(formatDateTime2Date(data.pay_time));
					$("#addr_name2").val(data.addr_name);
					$("#addr_mobile2").val(data.addr_mobile);
					$("#addr_detail2").val(data.addr_detail);
					var _descr = eval(data.details);
					var _table = $('#datatable3'), tableBodyHtml = '';
					$.each(_descr,function(k, v) {
								tableBodyHtml += '<tr>';
								tableBodyHtml += '<td>' + k + '</td>';
								tableBodyHtml += '<td>' + v.time+ '</td>';
								tableBodyHtml += '<td>' + v.context+ '</td>';
								tableBodyHtml += '</tr>';
							});
					_table.find("tbody").html(tableBodyHtml);
			},
			error:function(data){
				
			}
		})
 		
 	}
	
	
	


 	function submitAll(){
 		var _id = $("#ids").val();
 		var _trans_com = $("#trans_com").val();
 		var _trans_id = $("#trans_id").val();
 		var _userName = $("#userName").val();
		var _userPhone = $("#userPhone").val();
		var _userAddress = $("#userAddress").val();

		// if(_trans_com != null && _trans_com != '' && _trans_com != 'undefind' && _trans_id != null && _trans_id != '' && _trans_id != 'udefind'){
			var srhData = {"id" : _id,"trans_com":_trans_com,"trans_id":_trans_id,"order_status":"dsh","addr_name":_userName,"addr_mobile":_userPhone,"addr_detail":_userAddress};
			$.ajax({
				url:"${ctx!}/order/editOrderById",
				data : srhData,
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error,function(){
							$('#myModal').modal('hide')
							window.location.reload();
						});
					}else{
						bootbox.alert(data.error);
					}
				}
			})
		// }else{
		// 	bootbox.alert("请填写内容再确定");
		// }

 	}

 	function showAndHide(id){
 		$("#ids").val(id);
 		$('#myModal').modal('show')
 		tableFun2(id);
 	}
 	
 	
 	function tableFun2(id){
 		var tdUrl = "${ctx!}/order/selectById";
 	    var srhData = {"id" : id};
 		$.get(tdUrl,srhData, function(result){ 
			var menuJson = JSON.parse(result);
	　　　　	  $("#trans_com").val(menuJson.trans_com);
	　　　　	  $("#trans_id").val(menuJson.trans_id);
			  $("#userName").val(menuJson.addr_name);
			  $("#userPhone").val(menuJson.addr_mobile);
			  $("#userAddress").val(menuJson.addr_detail);

	})
 	}


/**
 * 导入
 */
function importExcel(){
	$("#img2").fileinput('destroy');
	$("#img2").fileinput({
		language : 'zh',
		uploadUrl : "#",
		showPreview : false, //是否显示预览
		showCaption: true,//是否显示标题
		dropZoneEnabled: false,//是否显示拖拽区域
		autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
		minFileCount: 0,
		uploadAsync: false,
		showUpload:false,
		showRemove:false,
		maxFileCount: 1,//最大上传数量
		browseOnZoneClick: true,
		msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
		enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
// 		allowedFileExtensions : ["xls","xlsx"],
		browseClass : "btn btn-primary", //按钮样式
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	})
	$('#myModal3').modal('show')
}


function submitAll3(){
	$('#myModal3').modal('hide')
	var formdata = new FormData(document.getElementById("form3"));
	$.ajax({
		url:"${ctx!}/order/uploadExcel",
		data : formdata,
		type:"POST",
		dataType : "json",
		async : false,
		processData: false,
		contentType: false,
		success : function(data) {
			if (data.draw == 1) {
				bootbox.alert(data.error, function() {
					window.location.reload();
				});
			}else{
				bootbox.alert(data.error);
			}
		}
	})
}


/**
 * 导出
 */
function export2(){
	<#--;-->
	$('#myModal4').modal('show')
}
function submitAll4(){
	$('#myModal4').modal('hide')
	var spuid = $("#spuid").val();
	window.location.href = "${ctx!}/order/exportPrem?spuid="+spuid+"";
}



 	
 	function select() {
		var order_no = $("#order_no").val();
		var sub_order_no = $("#sub_order_no").val();
		var user_id = $("#user_id").val();
		var addr_name = $("#addr_name").val();
		var addr_mobile = $("#addr_mobile").val();
		var status = $("#status").val();
		var bigNum = $("#bigNum").val();
		var smallNum = $("#smallNum").val();
		var param = {
			"order_no": order_no,
			"sub_order_no": sub_order_no,
			"user_id": user_id,
			"addr_name": addr_name,
			"addr_mobile": addr_mobile,
			"order_status":status,
			"bigNum":bigNum,
			"smallNum":smallNum
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
