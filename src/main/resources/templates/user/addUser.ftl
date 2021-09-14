<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
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
                          <h3>新增用户
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
						            <table class="table table-bordered">
						              <tbody>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>用户类型</td>
						                <td width="30%" >
							                  <select class="form-control"  id="types" name="types" onchange="typeSelect()">
							                  		<option value="2" selected="selected">非供应商</option>
							                  		<option value="1">供应商</option>
							                  </select>
						                </td>
						              </tr>
						              <tr class="supplier">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供应商姓名</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required]" id="s_name" name="s_name" placeholder="供应商姓名" data-errormessage-value-missing="供应商姓名不能为空"/>
						                </td>
						               </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>登录名(联系人姓名)</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="s_manager" name="s_manager" placeholder="登录名" data-errormessage-value-missing="登录名不能为空"/>
						                </td>
						              </tr>
						              <tr class="supplier">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>联系人手机</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="s_phone" name="s_phone" placeholder="联系人手机" data-errormessage-value-missing="联系人手机不能为空"/>
						                </td>
						              </tr>
						              <tr class="supplier">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>账户类型</td>
						                <td width="30%" >
							                  <select class="form-control"  id="public_or_private" name="public_or_private">
							                  		<option value="0">公户</option>
							                  		<option value="1">私户</option>
							                  </select>
						                </td>
						              </tr>
						              </tbody>
						            </table>
						            <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
						                <input class="btn btn-primary submit" type="button" onclick="save()" value="保存" />
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
$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	$(".supplier").hide();
 });

function typeSelect(){
	var _type = $("#types").val();
	if(_type == 1){
		$(".supplier").show();
	}else{
		$(".supplier").hide();
	}
}


function save(){
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	$.ajax({
		url:"${ctx!}/user/addUserData",
		data : $("#form").serialize(),
		dataType : "json",
		async : false,
		success : function(data) {
				if (data.draw == 1) {
					bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/user/userMenu";
					});
				}else{
					bootbox.alert(data.error);
				}
		}
	})
};
</script>
</html>