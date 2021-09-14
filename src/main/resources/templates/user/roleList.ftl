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
                          <h3>角色管理
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="角色昵称" name="description" id="description" class=".col-md-2">
                                  <button type="button" class="btn btn-primary" onclick="select()" id="showHide">搜索</button>
                                  <button type="button" id="addRole" style="float:right" class="btn btn-primary" onclick="addRole()">新增</button>
                                  <button type="button" id="returnUser" style="float:right" class="btn btn-primary" onclick="returnUser()">返回</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                  	  <th>角色名称</th>
                                      <th>角色昵称</th>
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
var _userId = "${userId!""}";
if(!_userId){
	$('#returnUser').hide();
}else{
	$("#description").hide();
	$("#showHide").hide();
	$("#addRole").text('添加角色'); // 只支持修改文本
}
var table = $('#datatable').DataTable({
	    "searching": false,
	    "ordering": true,
	    "processing": true,
	    "language": {
	        "url": '${ctx!}/assets/js/Chinese.json'
	    },
	    "serverSide": true,
	    "ajax": {
	        "url": "${ctx!}/user/getRoleData",
	        //默认为data,这里定义为空，则只需要传不带属性的数据
	        "data":{"userId":function(){
	        	return _userId;
	        }},
	    },
	    "columns": [
	    	{
	          "data": "name",	        	
	          "class":"text-center"
	        },
	        {
	          "data": "description",	        	
	          "class":"text-center"
	        },
	        {
	        	"data": "id",
	        	"class":"text-center",
	        	"render":function(data, type, row) { 
	        	  return otherButton(data); 
	        	 }
	        }
	    ],
        "footerCallback": function ( row, data, start, end, display ) {
        	if(table.data().length != 0 && _userId != '' && _userId != null){
        		$('#addRole').hide();
        	}
        }
	});

 	function delRole(roleId){
		bootbox.confirm("确认删除该条数据?", function(result){
			if(result) {
				var id = $(this).attr('data-id');
				$.get('${ctx!}/user/delRole?roleId='+roleId+'&userId='+_userId+'', function(result){
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
 	
 	
 	function select() {
        var description = $("#description").val();
        var param = {
            "description": description
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    }
 	
 	function returnUser(){
 		window.location.href = "${ctx!}/user/userMenu";
 	}
 	
 	function addRole(){
 		window.location.href = "${ctx!}/user/addRole?userId="+_userId+"";
 	}
 	
 	function editRole(data){
 		window.location.href = "${ctx!}/user/editRole?roleId="+data+"&userId="+_userId+"";
 	}
 	
 	
 	function otherButton(data){
 		var str = '';
 		if(!_userId){
 			if(data == 1){
 	 			str = '<a class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" href="${ctx!}/user/forwardMenuList?roleId='+data+'">权限</a> <span class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" onclick="editRole('+data+')">编辑</span>';
 			}else{
 				str = '<a class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" href="${ctx!}/user/forwardMenuList?roleId='+data+'">权限</a> <span class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" onclick="delRole('+data+')">删除</span> <span class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" onclick="editRole('+data+')">编辑</span>';
 			}
 		}else{
 			str = '<span class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" onclick="delRole('+data+')">删除</span> <span class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" onclick="editRole('+data+')">编辑</span>';
 		}
 		return str;
 	}
</script>
</html>
