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
                          <h3>结算运费模板
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
<!--                                   <input type="text" placeholder="三级分类" name="third_category_name" id="third_category_name" class=".col-md-2"> -->
<!--                                   <button type="button" class="btn btn-primary" onclick="select()">搜索</button> -->
                                   <button type="button" id="historyGo" style="float:right" class="btn btn-primary" onclick="historyGo()">返回</button>
                                   <button type="button" id="addSupplierAccountShipping" style="float:right" class="btn btn-primary" onclick="addSupplierAccountShipping()">新增</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                  	  <th>模板ID</th>
                                      <th>模板名称</th>
                                      <th>供应商ID</th>
                                      <th>是否包邮</th>
                                      <th>创建时间</th>
                                      <th>默认件数</th>
                                      <th>默认首费</th>
                                      <th>默认续件数</th>
                                      <th>默认续费</th>
                                      <th>类型</th>
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
var _id = "${id!""}";
if(_id != null && _id != '' && _id != 'undefind'){
	$("#historyGo").show();
}else{
	$("#historyGo").hide();
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
	        "url": "${ctx!}/supplierSettle/getSupplierSettleData?s_id="+_id+"",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "sas_id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "templet_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "s_id",	        	
	          "class":"text-center",
				"render":function(data, type, row) {
					return handle2(data);
				}
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
	          "data": "default_number",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return handle(data); 
	        	 	}	
	        },
	        {
	          "data": "default_price",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return handle(data); 
	        	 	}	
	        },
	        {
	          "data": "increa_number",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return handle(data); 
	        	 	}	
	        },
	        {
	          "data": "increa_price",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return handle(data); 
	        	 	}	
	        },
	        {
	          "data": "type",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle2(data); 
	        	 	}	
	        }
	    ]
	});



function handle2(data){
	if(data != '' && data != null){
		return data;
	}
	return "";
}

function handle(data){
	var _status = '';
	if(data == '' || data == null){
		if(data == 0){
			return data;
		}
		return _status;
	}
	return data;
}
	
	function dataHandle(data){
		var _status = '';
		if(data == 1){
			_status = '包邮';
		}if(data == 0){
			_status = '不包邮';
		}
		return _status;
	}
	
	
	function dataHandle2(data){
		var _status = '';
		if(data == 1){
			_status = '按件数';
		}if(data == 2){
			_status = '按重量';
		}if(data == 3){
			_status = '按体积';
		}if(data == 4){
			_status = '固定运费';
		}
		return _status;
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
    
    function addSupplierAccountShipping(){
 		window.location.href = "${ctx!}/supplierSettle/addSupplierSettle?s_id="+_id+"";
 	}
    
    function historyGo(){
 		window.location.href = "${ctx!}/supplierInfo/supplierInfoMenu";
 	}
    
    
    
    function delSupplierAccountShipping(id){
    	bootbox.confirm("确认删除该条数据?", function(result){
			if(result) {
				$.get('${ctx!}/supplierAccountShipping/delSupplierAccountShipping?id='+id+'', function(result){
					var data = JSON.parse(result);
					if (data.draw == 1) {
						bootbox.alert(data.error,function(){
							window.location.reload();
						});
					}else{
						bootbox.alert(data.error);
						window.location.reload();
					}
				})
			}

		})
    }
</script>
</html>