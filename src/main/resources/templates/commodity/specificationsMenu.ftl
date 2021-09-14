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
                          <h3>规格
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                              	<span>
                              		<input type="text" placeholder="规格名称" name="spec_name" id="spec_name" >
								     <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
								     <button class="btn btn-primary" type="button" id="addSpec" style="float:right"  onclick="addClassBySpec()">绑定分类</button>
								     <button class="btn btn-primary" type="button" id="addSpec" style="float:right"  onclick="addSpec()">添加规格</button>
                              	</span>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
	                                  <tr>
	                                      <th>ID</th>
	                                  	  <th>规格名称</th>
	                                  	  <th>规格值</th>
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
	        "url": "${ctx!}/specifications/getData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "id",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "spec_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "spec_value",	        	
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
                    	return '<a class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" href=\"${ctx!}/specifications/editSpecifications?id='+data+'\">编辑</a>';
	        	 }
	        }
	    ]
	});

 	
function dataHandle(data){
	var sale = "";
    if(data == 1){
    	sale = "一级分类";
    }else if(data == 2){
    	sale = "二级分类";
    }else{
    	sale = "三级分类";
    }
    return sale;
}	
 
function dataHandle2(data){
	var sale = "";
    if(data == 0){
    	sale = "显示";
    }else{
    	sale = "不显示";
    }
    return sale;
}	
 	
function updateImg(data){
	    if(data != null && data != "" && data.length > 0){
            return "<img src="+data+" height=\"30\" width=\"30\" />";
	    }else{
		        return "";
	    }
}
 	
 	
 	function select() {
        var spec_name = $("#spec_name").val();
        var param = {
            "spec_name": spec_name
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
    
    function addSpec(){
 		window.location.href = "${ctx!}/specifications/addSpecifications";
 	}
    
    function delSku(id){
    	bootbox.confirm("确认删除该条数据?", function(result){
			if(result) {
				$.get('${ctx!}/product/deleteById?id='+id+'', function(result){
					var data = JSON.parse(result);
					if (data.draw == 1) {
						bootbox.alert(data.error,function(){
							window.location.reload();
						});
					}else{
						bootbox.alert(data.error);
					}
				})
			}

		})
    }
    
    
    function addClassBySpec(){
		window.location.href = "${ctx!}/specifications/addClassBySpec";
	}
</script>
</html>