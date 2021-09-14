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
                          <h3>新增角色
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
						              <tr id="roleDescription">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>角色名称</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required]" id="name1" name="name" placeholder="角色名称" data-errormessage-value-missing="角色名称不能为空"/>
						                </td>
						              <tr id="roleDescription2">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>已有角色名称</td>
						                <td width="30%" >
							                  <select class="form-control"  id="name2" name="name2">
							                  </select>
						                </td>
						              </tr>
						              <tr id="des">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>角色职称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="description" name="description" placeholder="角色职称" data-errormessage-value-missing="角色职称不能为空"/>
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
var _userId = "${userId!""}";
if(_userId){
	$('#des').hide();
	$('#roleDescription').hide();
}else{
	$('#roleDescription2').hide();
}

$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	tableFun("${ctx!}/user/getAllData");
 });


function tableFun(tdUrl){
	 $.get(tdUrl, function(result){ 
			var menuJson = JSON.parse(result);
				$("#name2").append("<option value = '' selected='selected'>请选择</option>");
	　　　　for (var i = 0; i < menuJson.length; i++) {
	　　　　	$("#name2").append("<option value='"+menuJson[i].name+"'>"+menuJson[i].description+"</option>");
		　　}
	})
}

function save(){
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	var _select = $("#name2").val();
	if(_select != null && _select != ""){
		 $('#name1').val(_select);
	}
	
	$.ajax({
		url:"${ctx!}/user/addRoleData?userId="+_userId+"",
		data : $("#form").serialize(),
		dataType : "json",
		async : false,
		success : function(data) {
				if (data.draw == 1) {
					bootbox.alert(data.error, function() {
						if (_userId) {
							window.location.href = "${ctx!}/user/roleList?userId=" + _userId + "";
						} else {
							window.location.href = "${ctx!}/user/roleList";
						}
					});
				}else{
					bootbox.alert(data.error);
				}
		}
	})
};


</script>
</html>