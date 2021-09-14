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
                          <h3>SPU
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="SPU编号" name="spuid" id="spuid" class=".col-md-2">
                                  <input type="text" placeholder="SPU名称" name="spu_name" id="spu_name" class=".col-md-2">
                                  <input type="text" placeholder="供应商关键字" name="userName" id="userName" class=".col-md-2">
                                  <input type="text" placeholder="一级分类" name="first_category_name" id="first_category_name" class=".col-md-2">
                                  <input type="text" placeholder="二级分类" name="second_category_name" id="second_category_name" class=".col-md-2">
                                  <input type="text" placeholder="三级分类" name="third_category_name" id="third_category_name" class=".col-md-2">
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                                   <button type="button" id="addSpu" style="float:right" class="btn btn-primary" onclick="addSpu()">新增</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                  	  <th width="6%">产品主图</th>
                                      <th width="7%">spu编号</th>
                                      <th width="7%">spu名称</th>
                                      <th width="7%">品牌名称</th>
                                      <th width="7%">一级分类</th>
                                      <th width="7%">二级分类</th>
                                      <th width="7%">三级分类</th>
                                      <th width="31%">规格</th>
                                      <th width="4%">维护状态</th>
                                      <th width="4%">上下架</th>
                                      <th width="13%">操作</th>
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
	        "url": "${ctx!}/spuCommodity/getSpuData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "image",	        	
	          "render": function (data, type, row, meta) {
                  return updateImg(data);
              }
	        },
	        {
	          "data": "spuid",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "spu_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "brand_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "first_category_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "second_category_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "third_category_name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "spu_spec",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "maintain_status",	        	
	          "class":"text-center",
              "render":function(data, type, row) {
                  return dataHandle(data);
                  }
	        },
	        {
	          "data": "on_sale",	        	
	          "class":"text-center",
              "render":function(data, type, row) {
                  return dataHandle2(data);
                  }
	        },
	        {
	        	"data": "id",
	        	"class":"text-center",
	        	"render":function(data, type, row) {
                	return '<a class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" href=\"${ctx!}/product/addProduct?spuid='+data+'\">添加SKU</a> <a class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" href=\"${ctx!}/spuCommodity/editSupplierSpu?id='+data+'\">查看</a> <a class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" href=\"${ctx!}/materail/addMaterail?spuid='+data+'\">添加素材</a> <a class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" href=\"${ctx!}/materail/materailMenu?spuid='+data+'\">素材列表</a>';
	        	 }
	        }
	    ]
	});
 	
 	
 	function dataHandle(data){
 		var _status = '';
        if(data == 0){
        	_status = '未维护';
        }else if(data == 1){
        	_status = '开始维护';
        }else{
        	_status = '维护中';
        }
        return _status;
    }
 	
 	function dataHandle2(data){
 		var _status = '';
        if(data == 0){
        	_status = '上架';
        }else{
        	_status = '下架';
        }
        return _status;
    }
 	
 	
function updateImg(data){
	    if(data != null && data != "" && data.length > 0){
        var url = data.substr(0,7);
        if(url != null && url != '' && url == '/upload'){
            var realUrl = "http://img.yuemee.com"+data;
            return "<img src="+realUrl+" height=\"30\" width=\"30\" />";
        }else{
            return "<img src="+data+" height=\"30\" width=\"30\" />";
        }
    }else{
	        return "";
    }
}
 	
 	
 	function select() {
        var spuid = $("#spuid").val();
        var spu_name = $("#spu_name").val();
        var first_category_name = $("#first_category_name").val();
        var second_category_name = $("#second_category_name").val();
        var third_category_name = $("#third_category_name").val();
        var param = {
            "spuid": spuid,
            "spu_name":spu_name,
            "first_category_name":first_category_name,
            "second_category_name":second_category_name,
            "third_category_name":third_category_name
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
    
    function addSpu(){
 		window.location.href = "${ctx!}/spuCommodity/addSupplierSpu";
 	}
    
    function delSpu(id){
    	bootbox.confirm("确认删除该条数据?", function(result){
			if(result) {
				$.get('${ctx!}/spuCommodity/delSpu?id='+id+'', function(result){
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
    
    function maintainSpu(id){
    	bootbox.confirm("选择维护当前SPU及下属所有SKU在拼团时长未结束前都处于不能编辑状态,您确定要维护吗?", function(result){
			if(result) {
				$.get('${ctx!}/spuCommodity/maintainSpu?id='+id+'', function(result){
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
    
</script>
</html>