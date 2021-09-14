<#include "../common/header.html">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
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

<style tyle="text/css">
 ul li{
 	list-style:none;
 }

  .upload-file{
	  position: relative;
	  width: 120px;
	  padding: 10px 15px;
	  border: 1px solid rgb(119, 154, 80);
	  border-radius: 5px;
	  background-color: rgb(66, 215, 142);
	  color: #333333;
	  font-size: 14px;
	  text-align: center;
	  overflow: hidden;
  }

 .upload-file span{ //单行显示
 text-overflow: ellipsis;
	 white-space: nowrap;
	 overflow: hidden;
 }

 .upload-file:hover{ //简单的hover效果
 font-size: 15px;
	 border-color: rgb(39, 226, 81);
 }

 .upload-file input[type='file']{
	 height: 100%;
	 width: 100%;
	 position: absolute; //设置为绝对定位，不会影响到其他元素
 top: 0;
	 right: 0;
	 opacity: 0;   //透明度为0
 filter: alpha(opacity=0);
	 cursor: pointer;
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
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>选择分类级别</td>
						                <td width="30%" >
							                  <select class="form-control"  id="select" name="select"  onchange="selectCategory()">
							                  		<option>请选择</option>
							                  		<option value="1">一级分类</option>
							                  		<option value="2">二级分类</option>
							                  		<option value="3">三级分类</option>
							                  </select>
						                </td>
						              </tr>
						              
						              <tr id="firsttr" hidden="hidden">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>一级分类</td>
						                <td width="30%">
							                  <input type="text"  class="form-control validate[required,maxSize[100]]" id="first_category" name="first_category" placeholder="一级分类" data-errormessage-value-missing="一级分类不能为空"/>
							                  <select class="form-control"  id="first_category1" name="first_category1"  onchange="fun1()">
							                  </select>
						                </td>
						              </tr>
						              <tr id="secondtr" hidden="hidden">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>二级分类</td>
						                <td width="30%">
							                  <input type="text"  class="form-control validate[required,maxSize[100]]" id="second_category" name="second_category" placeholder="二级分类" data-errormessage-value-missing="二级分类不能为空"/>
							                  <select class="form-control"  id="second_category2" name="second_category2" onchange="fun2()">
							                  </select>
						                </td>
						              </tr>
						              <tr id="thirdtr" hidden="hidden">
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>三级分类</td>
						                <td width="30%">
							                  <input type="text"  class="form-control validate[required,maxSize[100]]" id="third_category" name="third_category" placeholder="三级分类" data-errormessage-value-missing="三级分类不能为空"/>
							                  <select class="form-control"  id="third_category3" name="third_category3">
							                  </select>
						                </td>
						              </tr>
									  <tr id="fourtr" hidden="hidden">
										  <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>图片</td>
										  <td width="30%" id="imgs">
											  <div class="upload-file">
												  <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
												  <input type="file" name="file" class="btn btn-primary" id="pic" onchange="aa()" style="width: 80px;">
												  <span class="tip">点击上传图片</span>
												  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												  <input type="hidden" name="cate_icon" id="cate_icon"/>
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

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(header, token);
});
var _status = "${status!""}";
var _type = "";
$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
 });

var ids=0;
function aa(){
	ids ++;
	var ss = document.getElementsByName("allImg");
	var num = ss.length;
	console.log(ss.length);
	if (num >= 1){
		bootbox.alert("只能上传一张照片");
		return false;
	}
	var formData = new FormData(document.getElementById("form"));
	$.ajax({
		url:"${ctx!}/classIfication/upload",
		data : formData,
		type:"POST",
		dataType : "json",
		async : false,
		processData: false,
		contentType: false,
		success : function(data) {
			if (data.draw == 1) {
				var im = data.data;

				$("#cate_icon").val(im);

				var d1=document.getElementById("imgs");
				var img=document.createElement("img");
				img.id="images"+ids+"";
				img.name="allImg";
				img.src=""+im+"";
				img.style="width: 150px;height: 150px;vertical-align:middle";
				var del=document.createElement("button");
				del.id="del"+ids+"";
				del.style="width:30px;height:70px;";
				del.type="button";
				del.onclick = function() {
					b1(this.id);
				};
				del.innerHTML = '删除';
				d1.appendChild(img);
				d1.appendChild(del);
			}else{
				bootbox.alert(data.error);
			}
			console.log(data);
		}
	})
}

function b1(id){
	var num = id.substr(id.length-1,1);
	var del = document.getElementById("del"+num+"");
	var img = document.getElementById("images"+num+"");
	$.ajax({
		url:"${ctx!}/spuCommodity/delOssPhoto",
		type:"POST",
		data : {"filePath":img.src},
		success : function(menuJson) {
		}
	})
	del.remove();
	img.remove();
}

function tableFun(tdUrl,tbData){
	$("#first_category1").empty();
	 $.get(tdUrl,tbData, function(result){ 
			var menuJson = JSON.parse(result);
				$("#first_category1").append("<option value = '' selected='selected'>请选择</option>");
	　　　　for (var i = 0; i < menuJson.length; i++) {
	　　　　	$("#first_category1").append("<option value='"+menuJson[i].id+"'>"+menuJson[i].cate_name+"</option>");
		　　}
	})
}


function selectCategory(){
	if($("#select").val() == '1'){
		$('#firsttr').show();
		$('#first_category').show();
		$('#first_category1').hide();
		$('#secondtr').hide();
		$('#thirdtr').hide();
		$('#fourtr').hide();
	}else if($("#select").val() == '2'){
		var srhData = {"cate_level" : 1,"uper_id":0};
		tableFun("${ctx!}/classIfication/getClassIficationData",srhData);
		$('#firsttr').show();
		$('#first_category').hide();
		$('#first_category1').show();
		$('#secondtr').show();
		$('#second_category').show();
		$('#second_category2').hide();
		$('#thirdtr').hide();
		$('#fourtr').hide();
	}else if($("#select").val() == '3'){
		var srhData = {"cate_level" : 1,"uper_id":0};
		tableFun("${ctx!}/classIfication/getClassIficationData",srhData);
		$('#firsttr').show();
		$('#first_category').hide();
		$('#first_category1').show();
		$('#secondtr').show();
		$('#second_category').hide();
		$('#second_category2').show();
		$('#thirdtr').show();
		$('#fourtr').show();
		if(_status == 1){
			$('#third_category3').hide();
		}if(_status == 2){
			$('#third_category').hide();
		}
	}else{
		$('#firsttr').hide();
		$('#secondtr').hide();
		$('#thirdtr').hide();
		$('#fourtr').hide();
	}
}

function fun1(){
	 $("#second_category2").empty();
	 $("#third_category3").empty();
	 var _uper_id = $("#first_category1").val();
	 var srhData = {"cate_level" : 2,"uper_id":_uper_id};
	 $.ajax({
			url:"${ctx!}/classIfication/getClassIficationData",
			data : srhData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
		　　　　for (var i = 0; i < menuJson.length; i++) {
		　　　　	$("#second_category2").append("<option value='"+menuJson[i].id+"'>"+menuJson[i].cate_name+"</option>");
			　　}
				if(menuJson.length > 0){
					fun2();
				}
			}
		})
	 
}


function fun2(){
	$("#third_category3").empty();
	 var _uper_id = $("#second_category2").val();
	 var srhData = {"cate_level" : 3,"uper_id":_uper_id};
	 $.ajax({
			url:"${ctx!}/classIfication/getClassIficationData",
			data : srhData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
		　　　　for (var i = 0; i < menuJson.length; i++) {
		　　　　	$("#third_category3").append("<option value='"+menuJson[i].id+"' title='"+menuJson[i].cate_name+"'>"+menuJson[i].cate_name+"</option>");
			　　}
				if(_status == 2 && menuJson.length > 0){
					fun3();
				}
			}
		})
}



function save(){
	var srhData;
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	if($("#select").val() == '1'){
		var _first_category = $('#first_category').val();
		srhData = {"cate_level" : 1,"uper_id":0,"cate_name":_first_category};
	}else if($("#select").val() == '2'){
		var _first_category = $('#first_category1').val();
		var _second_category = $('#second_category').val();
		srhData = {"cate_level" : 2,"uper_id":_first_category,"cate_name":_second_category};
		if(_first_category == null || _first_category == ''){
			 bootbox.alert("请先选择类目级别");
			 return false;
		}
	}else if($("#select").val() == '3'){
		var arr = new Array(); 
		$("input[type=checkbox]:checked").each(function(i){ 
			arr[i] = $(this).val(); 
		});
		var vals = arr.join(",");
		var _second_category = $('#second_category2').val();
		
		if(_status == 1){
		var third_category = $('#third_category').val();
		var _icon = $("#cate_icon").val();
			srhData = {"cate_level" : 3,"uper_id":_second_category,"cate_name":third_category,"sids":vals,"cate_icon":_icon};
		}if(_status == 2){
			var third_category = $('#third_category3').val();
			srhData = {"id" :third_category,"sids":vals};
		}
		var _first_category = $('#first_category1').val();
		if(_second_category == null || _second_category == '' || _first_category == null || _first_category == ''){
			 bootbox.alert("请先选择类目级别");
			 return false;
		}
		console.log(srhData);
		
	}else{
		 bootbox.alert("请先选择类目级别");
		 return false;
	}
	
	$.ajax({
		url:"${ctx!}/classIfication/addClassIficationData",
		data : srhData,
		dataType : "json",
		async : false,
		success : function(data) {
				if (data.draw == 1) {
					bootbox.alert(data.error, function() {
						window.location.href = "${ctx!}/classIfication/classIficationMenu2";
					});
				}else{
					bootbox.alert(data.error);
				}
		}
	})
};


</script>
</html>