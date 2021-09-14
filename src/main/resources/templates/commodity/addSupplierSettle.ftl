<#include "../common/header.html">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
  <link href="${ctx!}/assets/css/hsCheckData.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine-zh_CN.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>
<script src="${ctx!}/assets/js/fileinput.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/locales/zh.js"></script>
<script src="${ctx!}/assets/js/plugins/piexif.js"></script>
<script src="${ctx!}/assets/js/cityData.js"></script>
<script src="${ctx!}/assets/js/hsCheckData.js"></script>




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
                          <h3>新增结算运费模板
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
                              <form id="form" class="form-horizontal" method="post" enctype="multipart/form-data">
						            <table class="table table-bordered">
						              <tbody id="tbody">
						               <tr>
										<input type="hidden" name="nodel" id="nodel" />
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>不发货地区设置</td>
						                <td width="30%"  width="30%"  colspan="11">
							                  <div  data-cid="" id='no_delivery' style="width:100%;display: inline-block;"></div>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>模板名称</td>
						                <td width="30%"  width="30%"  colspan="11">
							                  <input type="text"  class="form-control validate[required]" id="templet_name" name="templet_name" placeholder="模板名称" data-errormessage-value-missing="模板名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>是否包邮</td>
						                <td width="30%"  width="30%"  colspan="11">
							                  <select class="form-control"  id="status" name="status">
							                  		<option id="0" value="0">不包邮</option>
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>默认运费</td>
						                <td width="30%" width="30%"  colspan="11" >
						                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="default_price" name="default_price" placeholder="默认运费" data-errormessage-value-missing="默认运费不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>默认件数</td>
						                <td width="30%"  width="30%"  colspan="11">
						                  <input type="text"  class="form-control validate[required,custom[onlyNumberSp],min[1]]" id="default_number" name="default_number" placeholder="默认件数" data-errormessage-value-missing="默认件数不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>默认续件数</td>
						                <td width="30%"  width="30%"  colspan="10">
						                  <input type="text"  class="form-control validate[required,custom[onlyNumberSp],min[1]]" id="increa_number" name="increa_number" placeholder="默认续件数" data-errormessage-value-missing="默认续件数不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>默认续费</td>
						                <td width="30%"  width="30%"  colspan="11">
						                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="increa_price" name="increa_price" placeholder="默认续费" data-errormessage-value-missing="默认续费不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>计价方式</td>
						                <td width="30%"  width="30%"  colspan="11">
						                	<select class="form-control"  id="type" name="type">
							                  		<option id="1" value="1">按件</option>
							                  </select>
							            </td>
						              </tr>
									   <tr>
										   <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>自定义设置(其他地区的运费才用默认运费)</td>
										   <td width="30%"  colspan="11">
											   <input class="btn btn-primary submit" type="button" onclick="createRow()" value="添加地区" />
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
var _id = "${id!""}";
var _int = 0;
var region;
var selectArray = new Array();
$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	
	tableFun("${ctx!}/region/allData");
	
 });


function tableFun(tdUrl){
		$.ajax({
			url:tdUrl,
			dataType : "json",
			async : false,
			success : function(menuJson) {
				region= menuJson;
			}
		})
	
	$("#no_delivery").hsCheckData({isShowCheckBox: true, data: region});
}


function createRow(){
	var editTable=document.getElementById("tbody");
	var tr=document.createElement("tr");
	tr.id="tr"+_int+"";
	var td0=document.createElement("td");
	td0.width="8%";
	td0.innerHTML='<div data-cid="cityDuoXuan" id="city'+_int+'" name="city'+_int+'"></div>';
	var td2=document.createElement("td");
	td2.width="8%";
	td2.innerHTML='<em style="color:red;"> * </em>首件数';
	var td3=document.createElement("td");
	td3.width="8%";
	td3.innerHTML='<input type="text"  class="form-control validate[required,custom[onlyNumberSp]]" id="first_tag'+_int+'" name="settleSettingsList['+_int+'].first_tag" placeholder="首件数" data-errormessage-value-missing="首件数不能为空"/>';
	var td4=document.createElement("td");
	td4.width="8%";
	td4.innerHTML='<em style="color:red;"> * </em>首费';
	var td5=document.createElement("td");
	td5.width="8%";
	td5.innerHTML='<input type="text"  class="form-control validate[required,custom[onlyNumberTwo]]" id="first_price'+_int+'" name="settleSettingsList['+_int+'].first_price" placeholder="首费" data-errormessage-value-missing="首费不能为空"/>';
	var td6=document.createElement("td");
	td6.width="8%";
	td6.innerHTML='<em style="color:red;"> * </em>续件数';
	var td7=document.createElement("td");
	td7.width="8%";
	td7.innerHTML='<input type="text"  class="form-control validate[required,custom[onlyNumberSp]]" id="second_tag'+_int+'" name="settleSettingsList['+_int+'].second_tag" placeholder="续件数" data-errormessage-value-missing="续件数不能为空"/>';
	var td8=document.createElement("td");
	td8.width="8%";
	td8.innerHTML='<em style="color:red;"> * </em>续费';
	var td9=document.createElement("td");
	td9.width="8%";
	td9.innerHTML='<input type="text"  class="form-control validate[required,custom[onlyNumberTwo]]" id="second_price'+_int+'" name="settleSettingsList['+_int+'].second_price" placeholder="续费" data-errormessage-value-missing="续费不能为空"/>';
	var td10=document.createElement("td");
	td10.width="8%";
	td10.innerHTML='<input class="btn btn-danger btn-xs ml-5 js-delete" type="button" id="'+_int+'" onclick="delRow('+_int+')" value="删除" />';
	var td11=document.createElement("td");
	td11.width="1%";
	td11.innerHTML='<input type="hidden" class="form-control" name="settleSettingsList['+_int+'].city" id="nodels'+_int+'"/>';
	
	tr.appendChild(td0);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tr.appendChild(td4);
	tr.appendChild(td5);
	tr.appendChild(td6);
	tr.appendChild(td7);
	tr.appendChild(td8);
	tr.appendChild(td9);
	tr.appendChild(td10);
	tr.appendChild(td11);
	
	editTable.appendChild(tr);
	_int ++;
	
	selectCity(_int);	
}

function selectCity(_int){
	var _in =_int-1;
	
	$("#city"+_in+"").hsCheckData({isShowCheckBox: true, data: region});
}

function delRow(num){
	var tbody = document.getElementById("tbody");
	var cell = document.getElementById("tr"+num+"");
	tbody.removeChild(cell);
}


function save(){
	var _in = _int-1;
	
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	 //不发货地区
    var noDel = $("#no_delivery").attr("data-id");
	
	 $("[id^='city']").each(function () {
         var cityId = $(this).attr("data-id");
         var cityIds = $(this).attr("id");
         var _it = cityIds.substring(4,cityIds.length);
         $("#nodels"+_it+"").val(cityId);
     });
	 
	
	 if(noDel != '' && noDel != null && noDel != 'undefind'){
		 $("#nodel").val(noDel);
	 }
	
	 
		$.ajax({
			url:"${ctx!}/supplierSettle/addSupplierSettleDate",
	 		data : $("#form").serialize(),
			dataType : "json",
			async : false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/supplierSettle/supplierSettleMenu?s_id="+_id+"";
						});
					}else{
						bootbox.alert(data.error);
					}
			}
		})
};
</script>
</html>