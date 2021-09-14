<#include "../common/header.html">
<link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
<link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
<link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
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
                          <h3>团关系查询
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="用户ID" name="participant_id" id="participant_id" class=".col-md-2">
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                              </div>
                          </div>
                          <div class="x_content">
                          
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                  	  <th>团长</th>
                                      <th>参与者</th>
                                      <th>开始时间</th>
                                      <th>结束时间</th>
                                      <th>订单号</th>
                                      <th>团号</th>
                                      <th>规模</th>
                                      <th>参与人数</th>
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
var table = $('#datatable').DataTable({
	    "searching": false,
	    "ordering": false,
	    "processing": false,
		"bProcessing":true,
	    "language": {
	        "url": '${ctx!}/assets/js/Chinese.json'
	    },
	    "serverSide": true,
	    "ajax": {
	        "url": "${ctx!}/assemble/getAssembleData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "originator_name",
	          "class":"text-center"
	        },
	        {
	          "data": "participant_name",
	          "class":"text-center"
	        },
			{
				"data": "start_time",
				"class":"text-center",
				"render":function(data, type, row) {
					return formatDateTime2Date(data);
				}
			},
	        {
	          "data": "end_time",
	          "class":"text-center",
				"render":function(data, type, row) {
					return formatDateTime2Date(data);
				}
			},
	        {
	          "data": "order_no",
	          "class":"text-center"
	        },
	        {
	          "data": "group_num",
	          "class":"text-center"
	        },
	        {
	          "data": "scale",
	          "class":"text-center"
	        },
	        {
	          "data": "participants_num",
	          "class":"text-center"
	        }
	    ]
	});

 	function select() {
        var participant_id = $("#participant_id").val();
        var param = {
			"participant_id":participant_id
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