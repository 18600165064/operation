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
                          <h3>编辑供应商
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
						              	<input type="text" id="s_id" name="s_id" hidden="hidden"/>
						              	<input type="text" id="jd_user_id" name="jd_user_id" hidden="hidden"/>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供应商姓名</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="s_name" name="s_name" placeholder="供应商姓名" data-errormessage-value-missing="供应商姓名不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>城市名称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="city" name="city" placeholder="城市名称" data-errormessage-value-missing="城市名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>国家</td>
						                <td width="30%" >
						                  <select class="form-control"  id="country" name="country">
						                  			<option value="">请选择</option>
							                  		<option value="0">中国大陆</option>
							                  		<option value="1">中国香港</option>
							                  		<option value="2">中国澳门</option>
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>省份</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="province" name="province" placeholder="省份" data-errormessage-value-missing="省份不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>邮政编码</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="postal_code" name="postal_code" placeholder="邮政编码" data-errormessage-value-missing="邮政编码不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>注册地址</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="registered_address" name="registered_address" placeholder="注册地址" data-errormessage-value-missing="注册地址不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供应商账户组</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="s_account_group" name="s_account_group" placeholder="供应商账户组" data-errormessage-value-missing="供应商账户组不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>纳税人类型</td>
						                <td width="30%" >
											<select class="form-control"  id="taxpayer_type" name="taxpayer_type">
													<option value="">请选择</option>
							                  		<option value="0">一般纳税人</option>
							                  		<option value="1">非一般纳税人</option>
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>增值税号</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="val_added_tax_no" name="val_added_tax_no" placeholder="增值税号" data-errormessage-value-missing="增值税号不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>联系人名称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="s_manager" name="s_manager" placeholder="联系人名称" data-errormessage-value-missing="联系人名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>经营地址</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="business_address" name="business_address" placeholder="经营地址" data-errormessage-value-missing="经营地址不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>电子邮箱</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="email" name="email" placeholder="电子邮箱" data-errormessage-value-missing="电子邮箱不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>联系人办公电话</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="office_phone" name="office_phone" placeholder="联系人办公电话" data-errormessage-value-missing="联系人办公电话不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>联系人手机</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="s_phone" name="s_phone" placeholder="联系人手机" data-errormessage-value-missing="联系人手机不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>传真</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="fax" name="fax" placeholder="传真" data-errormessage-value-missing="传真不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>币种</td>
						                <td width="30%" >
											<select class="form-control"  id="currency" name="currency">
													<option value="">请选择</option>
							                  		<option value="0">人民币</option>
							                  		<option value="1">港元</option>
							                  		<option value="2">美元</option>
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>银行代码</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="bank_code" name="bank_code" placeholder="银行代码" data-errormessage-value-missing="银行代码不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>银行名称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="bank_name" name="bank_name" placeholder="银行名称" data-errormessage-value-missing="银行名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>银行账户号码</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="bank_account_no" name="bank_account_no" placeholder="银行账户号码" data-errormessage-value-missing="银行账户号码不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>账户持有人</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="account_holder" name="account_holder" placeholder="账户持有人" data-errormessage-value-missing="账户持有人不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>公司</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required]" id="company" name="company" placeholder="公司" data-errormessage-value-missing="公司不能为空"/>
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
var _id = "${id!""}";
$(function(){
	var srhData = {"id":_id};
	tableFun("${ctx!}/supplierInfo/selectById",srhData);
	
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
			$("#s_id").val(menuJson.s_id);
			$("#jd_user_id").val(menuJson.jd_user_id);
			$("#s_name").val(menuJson.s_name);
			$("#city").val(menuJson.city);
			$("#country").val(menuJson.country);
			$("#province").val(menuJson.province);
			$("#postal_code").val(menuJson.postal_code);
			$("#registered_address").val(menuJson.registered_address);
			$("#s_account_group").val(menuJson.s_account_group);
			$("#taxpayer_type").val(menuJson.taxpayer_type);
			$("#val_added_tax_no").val(menuJson.val_added_tax_no);
			$("#s_manager").val(menuJson.s_manager);
			$("#business_address").val(menuJson.business_address);
			$("#email").val(menuJson.email);
			$("#office_phone").val(menuJson.office_phone);
			$("#s_phone").val(menuJson.s_phone);
			$("#fax").val(menuJson.fax);
			$("#currency").val(menuJson.currency);
			$("#bank_code").val(menuJson.bank_code);
			$("#bank_name").val(menuJson.bank_name);
			$("#bank_account_no").val(menuJson.bank_account_no);
			$("#account_holder").val(menuJson.account_holder);
			$("#company").val(menuJson.company);
			$("#create_time").val(menuJson.create_time);
			$("#status").val(menuJson.status);
	})
}


function update(){
	
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	$.ajax({
		url:"${ctx!}/supplierInfo/updateSupplierInfo",
		data : $("#form").serialize(),
		dataType : "json",
		async : false,
		success : function(data) {
				if (data.draw == 1) {
					bootbox.alert(data.error,function(){
						window.location.href = "${ctx!}/supplierInfo/supplierInfoMenu";
					});
				}else{
					bootbox.alert(data.error);
				}
		}
	})
};


</script>
</html>
