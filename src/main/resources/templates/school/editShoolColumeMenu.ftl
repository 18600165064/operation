<#include "../common/header.html">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>
<script src="${ctx!}/assets/js/fileinput.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/locales/zh.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine-zh_CN.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.min.js"></script>

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
                          <h3>商学院栏目
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
                                          <input type="text" id="id" name="id" hidden="hidden"/>
										  <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>标题</td>
										  <td width="30%" >
											  <input type="text"  class="form-control validate[required]" id="colume_name" name="colume_name" placeholder="标题" data-errormessage-value-missing="标题不能为空"/>
										  </td>
									  </tr>
                                      <tr>
                                          <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>是否显示</td>
                                          <td width="30%" >
                                              <select class="form-control"  id="is_show" name="is_show">
                                                  <option value="0">显示</option>
                                                  <option value="1">不显示</option>
                                              </select>
                                          </td>
                                      </tr>
						              </tbody>
						             </table>

						            <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
						              </div>
						              <!--操作按钮 开始-->
						              	<input class="btn btn-primary submit" type="button" onclick="save()" value="保存" />
						                <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>
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
var id = "${id!""}";

$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
    var srhData = {"id" : id};
    tableFun("${ctx!}/shoolColume/selectById",srhData);
 });


function tableFun(tdUrl,tbData){
    $.ajax({
        url:tdUrl,
        data : tbData,
        dataType : "json",
        async : false,
        success : function(menuJson) {
            $("#id").val(menuJson.id);
            $("#colume_name").val(menuJson.colume_name);
            $("#is_show").val(menuJson.is_show);
        }
    })
}



function save(){
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }

	$.ajax({
		url:"${ctx!}/shoolColume/editShoolColumeData",
		data : $("#form").serialize(),
		dataType : "json",
		async : false,
		success : function(data) {
				if (data.draw == 1) {
					bootbox.alert(data.error, function() {
						window.location.href = "${ctx!}/shoolColume/schoolColumeMenu";
					});
				}else{
					bootbox.alert(data.error);
				}
		}
	})
};


</script>
</html>