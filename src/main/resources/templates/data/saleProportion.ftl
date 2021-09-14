<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
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
                          <h3>销售占比
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" name="strDate" id="strDate" class=".col-md-3" placeholder="时间"/>
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                                  <button type="button" class="btn btn-primary" onclick="sortByAmount()">按订单金额排序</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>商品名称</th>
                                      <th>订单数量</th>
                                      <th>订单金额</th>
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
	$('#strDate').datepicker('setDate', new Date());
	
    var table = $('#datatable').DataTable({
        "searching": false,
        "processing": true,
        "paging": false,
        "ordering": false,
        "language": {
            "url": '${ctx!}/assets/js/Chinese.json'
        },
//         "columnDefs" : [{
//             "targets" : 0,
//             "orderable" : false
//         }],
//         "columnDefs" : [{
//             "targets" : 2,
//             "orderable" : false
//         }],
        //"order": [[1,'desc']],
        //"serverSide": true,
        "ajax": {
            "url": "${ctx!}/data/getSaleProportion",
        },
        "columns": [
            {"data": "skuName"},
            {"data": "count"},
            {"data": "amount"}
        ]
    });

    function select() {
        var strDate = $("#strDate").val();
        var param = {
            "strDate": strDate
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    }
    
    function sortByAmount() {
    	var strDate = $("#strDate").val();
        var param = {
            "status": "1",
            "strDate": strDate
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
        return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
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
