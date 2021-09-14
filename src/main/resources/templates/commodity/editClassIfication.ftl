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
						               		<input type="hidden" name="id" id="id"/>
							                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>分类级别</td>
							                <td width="30%" >
								                  <select class="form-control"  id="cate_level" name="cate_level" disabled="true">
								                  		<option value="1">一级分类</option>
					                                  	<option value="2">二级分类</option>
					                                  	<option value="3">三级分类</option>
								                  </select>
							                </td>
							              </tr>
									      <tr>
							                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>分类名称</td>
							                <td width="30%" >
							                  <input type="text"  class="form-control validate[required]" id="cate_name" name="cate_name" readonly="true" placeholder="分类名称" data-errormessage-value-missing="分类名称不能为空"/>
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
							              <tr>
							                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>是否显示</td>
							                <td width="30%" >
												<select class="form-control"  id="is_show" name="is_show">
													<option value="0">是</option>
				                                  	<option value="1">否</option>
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
var _id = "${id!""}";
var ids=0;
$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	 var srhData = {"id" : _id};
	tableFun("${ctx!}/classIfication/selectById",srhData);
 });



function tableFun(tdUrl,tbData){
	 $.ajax({
			url:tdUrl,
			data : tbData,
			dataType : "json",
			async : false,
			success : function(menuJson) {
		　　　　	$("#id").val(menuJson.id);
					$("#cate_level").val(menuJson.cate_level);
					$("#cate_name").val(menuJson.cate_name);
					$("#is_show").val(menuJson.is_show);
				if (menuJson.cate_level == 3) {
					$('#fourtr').show();
					if (menuJson.cate_icon != null && menuJson.cate_icon != '' && menuJson.cate_icon != 'undefind'){
						var d1=document.getElementById("imgs");
						var img=document.createElement("img");
						img.id="images"+ids+"";
						img.name="allImg";
						img.src=""+menuJson.cate_icon+"";
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
					}
				}




			}
		})
}

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

function save(){
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	$("#cate_level").attr("disabled",false);
	
	$.ajax({
		url:"${ctx!}/classIfication/updateClass",
		data : $("#form").serialize(),
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