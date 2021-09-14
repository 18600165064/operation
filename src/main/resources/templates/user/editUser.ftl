<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx!}/assets/bootstrap-validator/css/bootstrapValidator.min.css" />
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine-zh_CN.js"></script>
  <script src="${ctx!}/assets/js/jquery.validationEngine.js"></script>
   <script src="${ctx!}/assets/js/jquery.validationEngine.min.js"></script>
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
                          <h3>编辑用户
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <!-- 搜索 -->
                              </div>
                          </div>
                          <div class="x_content">
                             <!-- 正文 -->
                              <form id="form" class="form-horizontal">
						            <table id="datatable" class="table table-bordered">
						              <tbody>
						              <tr>
						              	<input type="text" id="id" name="id" hidden="hidden"/>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>登录名称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="username" name="username" placeholder="登录名称" data-errormessage-value-missing="登录名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>真实名称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="nick_name" name="nick_name" placeholder="真实名称" data-errormessage-value-missing="真实名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>手机号</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control" id="phone" name="phone" placeholder="手机号" data-errormessage-value-missing="手机号不能为空"/>
						                </td>
						              </tr>
						              </tbody>
						            </table>
						            <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
						                <input class="btn btn-primary submit" type="button" onclick="update()" value="保存" />
						                <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>
						              </div>
						              <!--操作按钮 开始-->
						            </div>
						          </form>
                              <!-- 正文 -->
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

$(function(){
	var srhData = {"id" : _userId};
	tableFun("${ctx!}/user/getUserById",srhData);
	
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
})


function tableFun(tdUrl,tbData){
	$.get(tdUrl,tbData, function(result){ 
			var menuJson = JSON.parse(result);
			$("#id").val(menuJson.id);
			$("#username").val(menuJson.username);
			$("#nick_name").val(menuJson.nick_name);
			$("#phone").val(menuJson.phone);
	})
}


function update(){
	
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	$.ajax({
		url:"${ctx!}/user/updateUser",
		data : $("#form").serialize(),
		dataType : "json",
		async : false,
		success : function(data) {
					bootbox.alert(data.error,function(){
						window.location.href = "${ctx!}/user/userMenu";
					});
		}
	})
};


</script>
</html>
