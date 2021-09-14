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
                          <h3>分类导航
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix" style="margin-top:0;padding-right:17px;">
                                  <!-- 搜索 -->
                                  <button class="btn btn-primary" type="button" id="addClassIfication" style="float:right"  onclick="addClassIfication(1)">添加分类/规格</button>
                                  <button class="btn btn-primary" type="button" id="editClassIfication" style="float:right"  onclick="addClassIfication(2)">编辑分类/规格</button>
                              </div>
                          </div>
                          <div class="x_content">
                             <!-- 正文 -->
                              <form id="form" class="form-horizontal">
						           <table class="table table-bordered">
						              <tbody>
						              <tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>一级分类</td>
						                <td width="30%" >
							                  <select class="form-control"  id="first_category" name="first_category"  onchange="fun1()">
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>二级分类</td>
						                <td width="30%" >
							                  <select class="form-control"  id="second_category" name="second_category" onchange="fun2()">
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>三级分类</td>
						                <td width="30%" >
							                  <select class="form-control"  id="third_category" name="third_category" onchange="fun3()">
							                  </select>
						                </td>
						              </tr>
						              
						               <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>当前分类所拥有的规格</td>
						                <td width="30%">
						                	<div id="specifications">
						                	
						                	</div>
						                </td>
						              </tr>
						              
						              
						              </tbody>
						             </table>

						            <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
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
var _type = "";
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
 });


function tableFun(tdUrl,tbData){
	 $.get(tdUrl,tbData, function(result){ 
			var menuJson = JSON.parse(result);
				$("#first_category").append("<option value = '' selected='selected'>请选择</option>");
	　　　　for (var i = 0; i < menuJson.length; i++) {
	　　　　	$("#first_category").append("<option value='"+menuJson[i].id+"'>"+menuJson[i].cate_name+"</option>");
		　　}
	})
}

function tableFun2(tdUrl,tbData){        
	 $.get(tdUrl,tbData, function(result){ 
			var menuJson = JSON.parse(result);
			_type = menuJson;
	})
}

function fun1(){
	 $("#second_category").empty();
	 $("#third_category").empty();
	 $("#specifications").empty();
	 var _uper_id = $("#first_category").val();
	 var srhData = {"cate_level" : 2,"uper_id":_uper_id};
	 if(_uper_id != null && _uper_id != ''){
		 $.ajax({
				url:"${ctx!}/classIfication/getClassIficationData",
				data : srhData,
				dataType : "json",
				async : false,
				success : function(menuJson) {
			　　　　for (var i = 0; i < menuJson.length; i++) {
			　　　　	$("#second_category").append("<option value='"+menuJson[i].id+"'>"+menuJson[i].cate_name+"</option>");
				　　}
					if(menuJson.length > 0){
							fun2();
					}
				}
			})
	 }
	 
}

function fun2(){
	$("#third_category").empty();
	 var _uper_id = $("#second_category").val();
	 var srhData = {"cate_level" : 3,"uper_id":_uper_id};
	 $.ajax({
			url:"${ctx!}/classIfication/getClassIficationData",
			data : srhData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
		　　　　for (var i = 0; i < menuJson.length; i++) {
		　　　　	$("#third_category").append("<option value='"+menuJson[i].id+"'>"+menuJson[i].cate_name+"</option>");
			　　}
				if(menuJson.length > 0){
					fun3();
				}
			}
		})
}


function fun3(){
	var _third_category =  $("#third_category").val();
	var srhData = {"cid" : _third_category};
	if(_third_category != null && _third_category != '' && _third_category != 'undefind'){
		$.ajax({
			url:"${ctx!}/specifications/getAllSpecificationsByCid",
			data : srhData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
				if(menuJson.length == 0){
					$("#specifications").empty();
				}else{
					var executerDiv=$("#specifications");  
				  	  executerDiv.innerHTML="";  
				var ul=document.createElement("ul");
				  	  
				var type = eval(_type);
				  	  for(var j = 0; j< type.length; j++){
				  		var li=document.createElement("li");  
				  			  li.setAttribute("value",type[j].spec_name);
				  			  li.append(type[j].spec_name + ":");
				  		for (var i = 0; i < menuJson.length; i++) {
				  			if(type[j].spec_name == menuJson[i].spec_name){
				  				var checkBox=document.createElement("input");  
								  	  checkBox.setAttribute("type","checkbox");  
								      checkBox.setAttribute("id",menuJson[i].id);  
								  	  checkBox.setAttribute("name","checkname");  
								  	  checkBox.setAttribute("title",menuJson[i].spec_name);  
								  	  checkBox.setAttribute("value",menuJson[i].id);
								  	  checkBox.setAttribute("checked","checked");
								  	  checkBox.setAttribute("disabled","disabled");
								  	  
									  li.appendChild(checkBox);  
									  li.appendChild(document.createTextNode(menuJson[i].spec_value));  
									  ul.appendChild(li);  
				  			}
							
								  
				　　	}
				  	  }
				  	  
				executerDiv.append(ul);  	

				}
			}
		})
		}
}



	function addClassIfication(status){
		window.location.href = "${ctx!}/classIfication/addClassIfication?status="+status+"";
	}


</script>
</html>