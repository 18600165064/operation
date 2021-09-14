<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine-zh_CN.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.min.js"></script>

<style tyle="text/css">
 ul li{
 	list-style:none;
 }
</style>


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
                          <h3>顶级账户
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix" style="margin-top:0;padding-right:17px;">
                                  <!-- 搜索 -->
                              </div>
                          </div>
                          <div class="x_content">
                             <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                  	  <th>  </th>
                                  	  <th>ID</th>
                                      <th>唯一标识</th>
                                      <th>小程序open_id</th>
                                      <th>手机号</th>
                                      <th>昵称</th>
                                      <th>头像</th>
                                      <th>性别</th>
                                      <th>生日</th>
                                      <th>创建时间</th>
                                      <th>身份</th>
                                  </tr>
                                  </thead>
                              </table>
                              <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
						                <input class="btn btn-primary submit" type="button" onclick="save()" value="保存" />
						                <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>
						              </div>
						              <!--操作按钮 开始-->
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
var _spuid = "${spuid!""}";

var table = $('#datatable').DataTable({
    "searching": false,
    "ordering": false,
    "processing": true,
    "language": {
        "url": '${ctx!}/assets/js/Chinese.json'
    },
    "serverSide": true,
    "ajax": {
        "url": "${ctx!}/topLevel/getTopLevelData?usage_status=0",
        //默认为data,这里定义为空，则只需要传不带属性的数据
    },
    "columns": [
    	 {
            "data": "user_id",	        	
            "class":"text-center",
            "render":function(data, type, row) {
                return '<input type="checkbox" id="'+data+'" value="'+data+'" name="check">';
    	 }
          },
        {
          "data": "user_id",	        	
          "class":"text-center"
        },
        {
          "data": "unionid",	        	
          "class":"text-center",
          "render":function(data, type, row) {
                return dataHandle(data);
                }
        },
        {
          "data": "open_id_small",	        	
          "class":"text-center",
          "render":function(data, type, row) {
                return dataHandle(data);
                }
        },
        {
          "data": "phone",	        	
          "class":"text-center",
          "render":function(data, type, row) {
            return dataHandle(data);
            }
        },
        {
          "data": "nick_name",	        	
          "class":"text-center",
          "render":function(data, type, row) {
            return dataHandle(data);
            }
        },
        {
            "data": "head_icon",
            "render": function (data, type, row, meta) {
                return updateImg(data);
            }
        },
        {
            "data": "sex",
            "class":"text-center",
            "render":function(data, type, row) {
                return dataHandleSex(data);
                }
        },
        {
            "data": "birthday",
            "class":"text-center",
            "render":function(data, type, row) {
                return formatDateTime2Date(data);
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
            "data": "usage_status",
            "class":"text-center",
            "render":function(data, type, row) {
                return dataHandleUsage(data);
                }
        }
    ],
    "drawCallback": function() {
    	var srhData = {"spuid" : _spuid};
    	tableFun("${ctx!}/topLevel/selectBySpuid",srhData);
    }
});

function tableFun(tdUrl,tdData){
	 $.ajax({
			url:tdUrl,
			data:tdData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
		　　　　for(var i = 0; i < menuJson.length; i++){
						var inp = document.getElementById(""+menuJson[i].user_id+"");
							  inp.checked="checked";
		　　　　}
			}
		})
}

function dataHandleUsage(data){
	var _usage = '禁用';
	if(data == 0){
		_usage = '启用';
	}
	return _usage;
}	
	
	
function dataHandleSex(data){
	var _sex = '男';
	if(data == 1){
		_sex = '女';
	}
	return _sex;
}


	function save(){
		obj = document.getElementsByName("check");
    	check_val = [];
   	    for(k in obj){
   	        if(obj[k].checked)
   	            check_val.push(obj[k].value);
   	    }

   	    if(check_val.length > 0){
	   	  	$.ajax({
		 		url:"${ctx!}/topLevel/addTopLevel",
		 		data : {"userIds":check_val.join(','),"spuid":_spuid},
		 		dataType : "json",
		 		async : false,
		 		success : function(data) {
			 			if (data.draw == 1) {
							bootbox.alert(data.error,function(){
								window.location.href = "${ctx!}/spuCommodity/spuMenu";
							});
						}else{
							bootbox.alert(data.error);
						}
		 		}
		 	})
   	    }else{
   	    	bootbox.alert("勾选后顶级账户才能保存");
   	    }
	}


	
	function select() {
    var userName = $("#userName").val();
    var param = {
        "userName": userName
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload();
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


function dataHandle(data){
    if(data != null && data != '' && data != 'undefined'){
        data = data;
    }else{
        data = '';
    }
    return data;
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