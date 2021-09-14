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
                          <h3>订单-清算
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
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                                  <button type="button" id="withdrawApply" style="float:right" class="btn btn-primary" onclick="withdrawApply()">一键提现</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                  	  <th>主订单号</th>
                                      <th>子订单号</th>
                                      <th>供应商ID</th>
                                      <th>SKU主图</th>
                                      <th>SKU编号</th>
                                      <th>SKU名称</th>
                                      <th>数量</th>
                                      <th>退款金额</th>
                                      <th>清算金额</th>
                                      <th>清算状态</th>
                                      <th>创建时间</th>
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
	        "url": "${ctx!}/orderSettle/getOrderSettleData",
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
	          "data": "supplier_id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "sku_image",	        	
	          "render": function (data, type, row, meta) {
                  return updateImg(data);
              }
	        },
	        {
	          "data": "skuid",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "sku_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "num",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "refund_amt",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "settle_amt",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "settle_status",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataUpdate(data); 
	        	 	}	
	        },
	        {
	          "data": "create_time",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
        	 	 return formatDateTime2Date(data); 
        	 	}	
		     }
	    ]
	});

	function withdrawApply(){
		$.ajax({
			url:"${ctx!}/supplierWithdraw/apply",
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
	
 	function select() {
        var order_no = $("#order_no").val();
        var sub_order_no = $("#sub_order_no").val();
        var param = {
            "order_no": order_no,
            "sub_order_no": sub_order_no
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    }
 	
 	function updateImg(data){
	    if(data != null && data != "" && data.length > 0){
            return "<img src="+data+" height=\"30\" width=\"30\" />";
	    }else{
		        return "";
	    }
	}
 	
 	function dataUpdate(data){
 		var status = '';
 		if(data == '0'){
 			status = '未清算';
 		}if(data == '1'){
 			status = '已清算';
 		}if(data == '2'){
 			status = '处理中';
 		}
 		return status;
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
