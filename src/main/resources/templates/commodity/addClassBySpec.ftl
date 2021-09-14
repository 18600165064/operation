<#include "../common/header.html">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
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
<script src="${ctx!}/assets/js/ueditor/ueditor.config.js"></script>
<script src="${ctx!}/assets/js/ueditor/ueditor.all.js"></script>




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
                          <h3>绑定分类
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
						              <tbody>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>一级分类</td>
						                <td width="30%" >
						                	  <input type="hidden"  class="form-control" id="first_category_name" name="first_category_name"/>
							                  <select class="form-control"  id="first_category" name="first_category"  onchange="fun1()">
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>二级分类</td>
						                <td width="30%" >
						                	  <input type="hidden"  class="form-control" id="second_category_name" name="second_category_name"/>
							                  <select class="form-control"  id="second_category" name="second_category" onchange="fun2()">
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>三级分类</td>
						                <td width="30%" >
						                	  <input type="hidden"  class="form-control" id="third_category_name" name="third_category_name"/>
							                  <select class="form-control"  id="third_category" name="third_category" onchange="fun3()">
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SPU规格</td>
						                <td width="30%" >
						                	<input type="hidden"  class="form-control" id="spu_spec" name="spu_spec"/>
											<div id="specifications">
						                	
						                	</div>	
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
var _array = new Array();
$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	var srhData = {"cate_level" : 1,"uper_id":0};
	tableFun("${ctx!}/classIfication/getClassIficationData",srhData);
	tableFun2("${ctx!}/specifications/selSpecName");
	tableFun3("${ctx!}/specifications/getSpecificationsData");
 });

function tableFun(tdUrl,tbData){
	 $.get(tdUrl,tbData, function(result){ 
			var menuJson = JSON.parse(result);
				$("#first_category").append("<option value = '' selected='selected'>请选择</option>");
	　　　　for (var i = 0; i < menuJson.length; i++) {
	　　　　	$("#first_category").append("<option value='"+menuJson[i].id+"' title='"+menuJson[i].cate_name+"'>"+menuJson[i].cate_name+"</option>");
		　　}
	})
}

function tableFun2(tdUrl,tbData){        
	 $.get(tdUrl,tbData, function(result){ 
			var menuJson = JSON.parse(result);
			_type = menuJson;
	})
}

function tableFun3(tdUrl){        
	$.ajax({
		url:tdUrl,
		dataType : "json",
		async : false,
		success : function(menuJson) {
			_array = menuJson;
			console.log(_array);
		}
	})
}

function save(){
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
		var arr = new Array(); 
		$("input[type=checkbox]:checked").each(function(i){ 
			arr[i] = $(this).val(); 
		});
		var vals = arr.join(",");
		
		var _cid = $("#third_category").val();
		
		$("#spu_spec").val(vals);
		
		$.ajax({
			url:"${ctx!}/specifications/addspecByClassData",
			data : {"cid":_cid,"sids":vals},
			dataType : "json",
			async : false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/specifications/specificationsMenu";
						});
					}else{
						bootbox.alert(data.error);
					}
			}
		})
	
};

function fun1(){
	 $("#second_category").empty();
	 $("#third_category").empty();
	 $("#specifications").empty();
	 var _uper_id = $("#first_category").val();
	 var srhData = {"cate_level" : 2,"uper_id":_uper_id};
	 var _name = $("#first_category").find("option:selected").attr("title");
	 $("#first_category_name").val(_name);
	 $.ajax({
			url:"${ctx!}/classIfication/getClassIficationData",
			data : srhData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
		　　　　for (var i = 0; i < menuJson.length; i++) {
		　　　　	$("#second_category").append("<option value='"+menuJson[i].id+"' title='"+menuJson[i].cate_name+"'>"+menuJson[i].cate_name+"</option>");
			　　}
				if(menuJson.length > 0){
					fun2();
				}
			}
		})
}

function fun2(){
	$("#third_category").empty();
	$("#specifications").empty();
	 var _uper_id = $("#second_category").val();
	 var srhData = {"cate_level" : 3,"uper_id":_uper_id};
	 var _name = $("#second_category").find("option:selected").attr("title");
	 $("#second_category_name").val(_name);
	 $.ajax({
			url:"${ctx!}/classIfication/getClassIficationData",
			data : srhData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
		　　　　for (var i = 0; i < menuJson.length; i++) {
		　　　　	$("#third_category").append("<option value='"+menuJson[i].id+"' title='"+menuJson[i].cate_name+"'>"+menuJson[i].cate_name+"</option>");
			　　}
				if(menuJson.length > 0){
					fun3();
				}
			}
		})
}


function fun3(){
	$("#specifications").empty();
	var _third_category =  $("#third_category").val();
	 var _name = $("#third_category").find("option:selected").attr("title");
	 $("#third_category_name").val(_name);
	var srhData = {"cid" : _third_category};
	if(_third_category != null && _third_category != '' && _third_category != 'undefind'){
		$.ajax({
			url:"${ctx!}/specifications/getAllSpecificationsByCid",
			data : srhData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
					var executerDiv=$("#specifications");  
				  	  executerDiv.innerHTML="";  
				var ul=document.createElement("ul");
				  	  
				var type = eval(_type);
				  	  for(var j = 0; j< type.length; j++){
				  		var li=document.createElement("li");  
				  			  li.setAttribute("value",type[j].spec_name);
				  			  li.append(type[j].spec_name + ":");
				  		for (var i = 0; i < _array.length; i++) {
				  			if(type[j].spec_name == _array[i].spec_name){
				  				var checkBox=document.createElement("input");  
								  	  checkBox.setAttribute("type","checkbox");  
								      checkBox.setAttribute("id",_array[i].id);  
								  	  checkBox.setAttribute("name","checkname");  
								  	  checkBox.setAttribute("title",_array[i].spec_name);  
								  	  checkBox.setAttribute("value",_array[i].id);
								  	  for(var y = 0; y < menuJson.length; y++){
								  		  if(menuJson[y].id ==  _array[i].id){
								  	  		checkBox.setAttribute("checked","checked");
								  		  }
								  	  }
								  	  
									  li.appendChild(checkBox);  
									  li.appendChild(document.createTextNode(_array[i].spec_value));  
									  ul.appendChild(li);  
				  			}
							
								  
				　　	}
				  	  }
				  	  
				executerDiv.append(ul);
			}
		})
		}
}

</script>
</html>