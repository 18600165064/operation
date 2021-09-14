<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
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
                          <h3>商品服务商
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="订单号" name="order_no" id="order_no" class=".col-md-2">
                                  <input type="text" placeholder="用户ID" name="user_id" id="user_id" class=".col-md-2">
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>主订单号</th>
                                      <th>用户ID</th>
                                      <th>商品差额</th>
                                      <th>即时返现</th>
                                      <th>核销返现</th>
                                      <th>服务费</th>
                                      <th>运费</th>
                                      <th>供货价</th>
                                      <th>退款</th>
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
	        "url": "${ctx!}/proServiceBill/getProServiceBillData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
            {
                "data": "orderNo",
                "class":"text-center"
            },
            {
                "data": "userId",
                "class":"text-center"
            },
	        {
	            "data": "spce",
	            "class":"text-center",
                "render":function(data, type, row) {
                    return handle(data);
                }
	        },
            {
                "data": "jsfx",
                "class":"text-center",
                "render":function(data, type, row) {
                    return handle(data);
                }
            },
            {
                "data": "hxfx",
                "class":"text-center",
                "render":function(data, type, row) {
                    return handle(data);
                }
            },
            {
                "data": "fwf",
                "class":"text-center",
                "render":function(data, type, row) {
                    return handle(data);
                }
            },
            {
                "data": "yf",
                "class":"text-center",
                "render":function(data, type, row) {
                    return handle(data);
                }
            },
            {
                "data": "ghj",
                "class":"text-center",
                "render":function(data, type, row) {
                    return handle(data);
                }
            },
            {
                "data": "tk",
                "class":"text-center",
                "render":function(data, type, row) {
                    return handle(data);
                }
            },
            {
                "data": "createTime",
                "class":"text-center",
                "render":function(data, type, row) {
                    return formatDateTime2Date(data);
                }
            }
	    ]
	});


function handle(data) {
    var num = 0;
    if(data != null && data != '' && data != 'undefind'){
        num = data;
    }
    return num;
}


function select() {
        var order_no = $("#order_no").val();
        var user_id = $("#user_id").val();
        var param = {
            "order_no":order_no,
            "user_id":user_id
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