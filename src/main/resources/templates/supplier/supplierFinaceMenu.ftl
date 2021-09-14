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
                          <h3>供应商账户管理
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
														提现金额
													</h4>
												</div>
												<div class="modal-body">
													<form id="form" class="form-horizontal">
														<table class="table table-bordered">
															<tr>
												                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>提现金额</td>
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
                                      <th>供应商ID</th>
                                  	  <th>保证金</th>
                                      <th>总收入</th>
                                      <th>冻结金额</th>
                                      <th>可提现金额</th>
                                      <th>退款金额</th>
                                      <th>累计提现金额</th>
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
	        "url": "${ctx!}/supplier/getSupplierFinaceData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "sid",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "bond",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "income",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "frozen",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "can_withdraw",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "refund",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "already_withdraw",	        	
	          "class":"text-center"
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
	        		return '';
	        		// return '<button type="button" class="btn btn-danger btn-xs ml-5 js-delete" onclick="showAndHide('+data+')">申请提现</button>';
	        	 }
	        }
	    ]
	});

		var _id;

		function showAndHide(id){
			_id = id;
			$('#myModal').modal('show')
		}	
		
		
		function submit(){
			$('#myModal').modal('hide')
			var _confirm = $("#confirm_amt_total").val();
			$.ajax({
				url:"${ctx!}/supplier/withdrawal",
				data:{"id":_id,"deduction_money":_confirm},
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
