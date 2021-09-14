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
                          <h3>消息
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
                                  <button type="button" id="addUser" style="float:right" class="btn btn-primary" onclick="addRich()">新增</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>标题</th>
                                      <th>浏览数量</th>
                                      <th>置顶</th>
                                      <th>类型</th>
                                      <th>创建时间</th>
                                      <th>更新时间</th>
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
	        "url": "${ctx!}/rich/getRichData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	    },
	    "columns": [
	        {
	          "data": "title",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "browse_num",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHan(data); 
	        	 	}	
	        },
	        {
	          "data": "is_top",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle(data); 
	        	 	}	
	        },
	        {
	          "data": "type",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return dataHandle2(data); 
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
	          "data": "update_time",	        	
	          "class":"text-center",
	          "render":function(data, type, row) { 
	        	 	 return formatDateTime2Date(data); 
	        	 	}	
	        },
	        {
	        	"data": "id",
	        	"class":"text-center",
	        	"render":function(data, type, row) {
	        		return '<a type="button" class="btn btn-danger btn-xs ml-5 js-delete" href=\"${ctx!}/rich/editRich?id='+data+'\">编辑</a>';
	        	 }
	        }
	    ]
	});

	function dataHan(data){
		if(data != null && data != '' && data != 'undefind'){
			return data;
		}else{
			return '';
		}
	}


	function dataHandle(data){
		var _isTop = '';
		if(data == 0){
			_isTop = '否';
		}else{
			_isTop = '是';
		}
		return _isTop;
	}
	
	
	function dataHandle2(data){
		var _isTop = '';
		if(data == 1){
			_isTop = '欢迎语';
		}else if(data == 2){
			_isTop = '常见问题';
		}else if(data == 3){
			_isTop = '首页弹窗海报';
		}else if(data == 4){
			_isTop = '系统公告';
		}else if(data == 5){
			_isTop = '拼团赚钱';
		}else if(data == 6){
			_isTop = '拼团规则';
		}else if(data == 7){
			_isTop = '他咋拼';
		}else if(data == 8){
			_isTop = '宣言';
		}else if(data == 9){
			_isTop = '我要开团海报';
		}else if(data == 10){
			_isTop = '页面公告';
		}else if(data == 11){
			_isTop = '龙虎榜海报';
		}else if(data == 12){
			_isTop = '首页海报';
		}else if(data == 13){
			_isTop = '我要参团海报';
		}else if(data == 14){
			_isTop = '商品中间页';
		}else{
			_isTop = '';
		}
		return _isTop;
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
    
    
    function addRich(){
 		window.location.href = "${ctx!}/rich/addRich";
 	}
 	
</script>
</html>
