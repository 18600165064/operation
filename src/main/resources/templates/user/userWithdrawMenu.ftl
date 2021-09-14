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
                          <h3>用户体现
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="用户ID" name="userId" id="userId" class=".col-md-2">
								  <input type="text" name="strDate" id="strDate" placeholder="开始时间" class=".col-md-3"/>
								  <input type="text" name="endDate" id="endDate" placeholder="结束时间" class=".col-md-3"/>
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>ID</th>
                                  	  <th>前台用户ID</th>
                                      <th>后台管理员编号</th>
                                      <th>订单号</th>
                                      <th>兑现总金额</th>
                                      <th>从余额部分提取金额</th>
                                      <th>请求状态</th>
                                      <th>创建时间</th>
                                      <th>处理时间</th>
                                      <th>手续费</th>
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
	$('#strDate').datepicker('setDate', null);
	$('#endDate').datepicker('setDate', null);


var table = $('#datatable').DataTable({
	    "searching": false,
	    "ordering": false,
	    "processing": true,
	    "language": {
	        "url": '${ctx!}/assets/js/Chinese.json'
	    },
	    "serverSide": true,
	    "ajax": {
	        "url": "${ctx!}/userWithdraw/getUserWithdrawData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "user_id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "back_user_id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "order_no",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "total",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "money",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "status",	        	
	          "class":"text-center",
	          "render":function(data, type, row) {
	                return dataHandle(data);
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
	          "data": "process_time",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return formatDateTime2Date(data); 
	        	 	}	
	        },
	        {
	          "data": "poundage",	        	
	          "class":"text-center"
	        },
	        {
	        	"data": "id",
	        	"class":"text-center",
	        	"render":function(data, type, row) {
	        		if(row.status == 0){
	        			return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="withdraw('+data+')">转账</button> <button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="rejected('+data+')">驳回</button>';
	        		}else{
	        			return '';
	        		}
	        	 }
	        }
	    ]
	});
	
	function withdraw(data){
		bootbox.confirm("确认转账吗?", function(result){
			if(result) {
				$.ajax({
					url:"${ctx!}/userWithdraw/withdraw",
					data:{"id":data},
					dataType : "json",
					async : false,
					success : function(data) {
							if (data.draw == 1) {
								bootbox.alert(data.error,function(){
									bootbox.alert(data.error);
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
		})
	}
	
	
	
	function rejected(data){
		bootbox.confirm("确认驳回吗?", function(result){
			if(result) {
					$.ajax({
						url:"${ctx!}/userWithdraw/rejected",
						data:{"id":data},
						dataType : "json",
						async : false,
						success : function(data) {
								if (data.draw == 1) {
									bootbox.alert(data.error,function(){
										bootbox.alert(data.error);
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
		})
	}
	
	
	
function dataHandle(data){
	var _status = "";
     if(data == 1){
    	_status = "审核中";
    }else if(data == 2){
    	_status = "转账成功";
    }else if(data == 3){
    	_status = "完成";
    }else if(data == 4){
    	_status = "拒绝";
    }else if(data == 5){
    	_status = "关闭";
    }else{
    	_status = "申请中";
    }
    return _status;
}

 	function select() {
        var userId = $("#userId").val();
        var strDate = $("#strDate").val();
        var endDate = $("#endDate").val();
        var param = {
            "user_id": userId,
			"strDate":strDate,
			"endDate":endDate
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
