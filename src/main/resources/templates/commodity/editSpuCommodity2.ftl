<#include "../common/header.html">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
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

<style>
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
                          <h3>编辑SPU
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
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SPU编号</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required]" id="spuid" name="spuid" placeholder="SPU编号" data-errormessage-value-missing="SPU编号不能为空" readonly="readonly"/>
						                </td>
						              </tr>
						              <tr>
						              	 <td hidden="hidden">
						              	 	<input type="text" id="id" name="id">
						              	 </td>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SPU名称</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required[maxSize[35]]" id="spu_name" name="spu_name" placeholder="SPU名称" data-errormessage-value-missing="SPU名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>品牌名称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required[maxSize[35]]" id="brand_name" name="brand_name" placeholder="品牌名称" data-errormessage-value-missing="品牌名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>商品副标题</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required[maxSize[25]]" id="spu_subheading" name="spu_subheading" placeholder="商品副标题" data-errormessage-value-missing="商品副标题不能为空"/>
						                </td>
						              </tr>
									  <tr>
										  <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>关键字</td>
										  <td width="30%" >
											  <input type="text"  class="form-control validate[required[maxSize[7]]" id="spu_keyword" name="spu_keyword" placeholder="关键字" data-errormessage-value-missing="关键字不能为空"/>
										  </td>
									  </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>一级分类</td>
						                <td width="30%" >
						                	  <input type="hidden"  class="form-control" id="first_category_name" name="first_category_name"/>
							                  <select class="form-control"  id="first_category" name="first_category">
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>二级分类</td>
						                <td width="30%" >
						                	  <input type="hidden"  class="form-control" id="second_category_name" name="second_category_name"/>
							                  <select class="form-control"  id="second_category" name="second_category">
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>三级分类</td>
						                <td width="30%" >
						                	  <input type="hidden"  class="form-control" id="third_category_name" name="third_category_name"/>
							                  <select class="form-control"  id="third_category" name="third_category">
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
						               <tr>
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>产品主图</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img2" multiple="multiple" name="image1">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="image" id="image"/>
						                </td>
						              </tr>
						              <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>轮播图</td>
						                <td width="30%" id="imgs">
											<div class="upload-file">
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
											<input type="file" name="files" class="btn btn-primary" id="pic" onchange="aa()" style="width: 80px;">
											<span class="tip">点击上传图片</span>
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="images" id="images"/>
											</div>
						                </td>
						              </tr>
						              <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>视频封面图</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img4" multiple="multiple" name="videoImage">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="video_image" id="video_image"/>
						                </td>
						              </tr>
						               <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>视频</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img5" multiple="multiple" name="videoUrl">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="video_url" id="video_url"/>
						                </td>
						              </tr>
						              <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>首页banner图片</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img6" name="bannerImage">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="banner_image" id="banner_image"/>
						                </td>
						              </tr>
									  <tr>
										  <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>支付成功图片</td>
										  <td width="30%" >
											  <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
											  <input type="file" class="file" id="img7" name="successImage">
											  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											  <input type="hidden" name="success_image" id="success_image"/>
										  </td>
									  </tr>
									  <tr>
										  <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>支付成功动态图片</td>
										  <td width="30%" >
											  <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
											  <input type="file" class="file" id="img8" name="gifImage">
											  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											  <input type="hidden" name="success_gif_image" id="success_gif_image"/>
										  </td>
									  </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>是否是首页banner图</td>
						                <td width="30%" >
							                  <select class="form-control"  id="is_banner" name="is_banner">
							                  		<option id="0" value="0">否</option>
							                  		<option id="1" value="1">是</option>
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>是否置顶</td>
						                <td width="30%" >
							                  <select class="form-control"  id="is_top" name="is_top">
							                  		<option id="0" value="0">否</option>
							                  		<option id="1" value="1">是</option>
							                  </select>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>市场价</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="market_price" name="market_price" placeholder="市场价" data-errormessage-value-missing="市场价不能为空" readonly/>
						                </td>
						              </tr>
						               <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>团价</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="price" name="price" placeholder="单买价格" data-errormessage-value-missing="单买价格不能为空" readonly/>
						                </td>
						              </tr>
						               <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供货价</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="supply_price" name="supply_price" placeholder="供货价" data-errormessage-value-missing="供货价不能为空" readonly/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>服务费</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="service_price" name="service_price" placeholder="服务费" data-errormessage-value-missing="服务费不能为空" readonly/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>佣金</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="commission" name="commission" placeholder="佣金" data-errormessage-value-missing="佣金不能为空" readonly/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>拼团时长（小时单位）</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberOne],min[0.1]]" id="valid_duration" name="valid_duration" placeholder="拼团时长" data-errormessage-value-missing="拼团时长不能为空"/>
						                </td>
						              </tr>
		 								<tr>
			                                <td class="input-group-addon" width="20%"> <em>*</em>产品详情</td>
			                                <td width="30%">
			                                	<script id="editor" type="text/plain" style="width: 100%; height: 250px;" name="spuDesc.skudesc" data-errormessage="不能超过2000个字符"></script>
			                                </td>
		                            	</tr>
						              </tbody>
						            </table>
						            <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
						                <input class="btn btn-primary submit" type="button" id="saveSpu" onclick="save()" value="保存" />
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
var ids=0;
var _type = "";
var _id = "${id!""}";
var myArray = new Array();
var token = $("meta[name='_csrf']").attr("content"); 
var header = $("meta[name='_csrf_header']").attr("content"); 
$(document).ajaxSend(function(e, xhr, options) { 
	xhr.setRequestHeader(header, token); 
});


	$("#img2").fileinput({
		language : 'zh',
	    uploadUrl : "#",
	    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
	    minFileCount: 0,
	    uploadAsync: false,
	    maxFileCount: 1,//最大上传数量
	    maxFileSize:500,//上传最大500
	    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
	    enctype: 'multipart/form-data',
	    overwriteInitial: false,//不覆盖已上传的图片
	    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
	    browseClass : "btn btn-primary", //按钮样式
	    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	})


	$("#img4").fileinput({
		language : 'zh',
	    uploadUrl : "#",
	    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
	    minFileCount: 0,
	    uploadAsync: false,
	    maxFileCount: 1,//最大上传数量
	    maxFileSize:500,//上传最大500
	    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
	    enctype: 'multipart/form-data',
	    overwriteInitial: false,//不覆盖已上传的图片
	    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
	    browseClass : "btn btn-primary", //按钮样式
	    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	})
	
	
	$("#img5").fileinput({
		language : 'zh',
	    uploadUrl : "#",
	    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
	    minFileCount: 0,
	    uploadAsync: false,
	    maxFileCount: 1,//最大上传数量
	    maxFileSize:15360,//上传最大15MB
	    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
	    enctype: 'multipart/form-data',
	    overwriteInitial: false,//不覆盖已上传的图片
	    allowedFileExtensions : ["mp4"],
	    browseClass : "btn btn-primary", //按钮样式
	    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	})
	
	$("#img6").fileinput({
		language : 'zh',
	    uploadUrl : "#",
	    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
	    minFileCount: 0,
	    uploadAsync: false,
	    maxFileCount: 1,//最大上传数量
	    maxFileSize:500,//上传最大500
	    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
	    enctype: 'multipart/form-data',
	    overwriteInitial: false,//不覆盖已上传的图片
	    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
	    browseClass : "btn btn-primary", //按钮样式
	    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	})

	$("#img7").fileinput({
		language : 'zh',
		uploadUrl : "#",
		autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
		minFileCount: 0,
		uploadAsync: false,
		maxFileCount: 1,//最大上传数量
		maxFileSize:500,//上传最大500
		msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
		enctype: 'multipart/form-data',
		overwriteInitial: false,//不覆盖已上传的图片
		allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
		browseClass : "btn btn-primary", //按钮样式
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	})

	$("#img8").fileinput({
		language : 'zh',
		uploadUrl : "#",
		autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
		minFileCount: 0,
		uploadAsync: false,
		maxFileCount: 1,//最大上传数量
		maxFileSize:500,//上传最大500
		msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
		enctype: 'multipart/form-data',
		overwriteInitial: false,//不覆盖已上传的图片
		allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
		browseClass : "btn btn-primary", //按钮样式
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	})
	
	$('#img2').on('filepredelete', function(event, key) {
        $("#image").val("");
    });  
	
    $('#img2').on('filesuccessremove', function(event, id) {
        if (some_processing_function(id)) {
           console.log('Uploaded thumbnail successfully removed');
        } else {
            return false;
        }
    });

    $('#img4').on('filepredelete', function(event, key) {
        $("#video_image").val("");
    });
    
    $('#img5').on('filepredelete', function(event, key) {  
        $("#video_url").val("");
    });
    
    $('#img6').on('filepredelete', function(event, key) {  
        $("#banner_image").val("");
    });

$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	$("#img2").fileinput('destroy'); 
	$("#img4").fileinput('destroy');
	$("#img5").fileinput('destroy'); 
	$("#img6").fileinput('destroy');
	$("#img7").fileinput('destroy');
	$("#img8").fileinput('destroy');
	var srhData = {"id" : _id};
	tableFun("${ctx!}/spuCommodity/selectById",srhData);
	$("#first_category").attr("disabled",true);
	$("#second_category").attr("disabled",true);  
	$("#third_category").attr("disabled",true);
 });


function tableFun2(tdUrl){
	$.ajax({
		url:"${ctx!}/classIfication/getClassIficationData",
		dataType : "json",
		async : false,
		success : function(menuJson) {
			$("#first_category").append("<option value = '' selected='selected'>请选择</option>");
　　　　for (var i = 0; i < menuJson.length; i++) {
			if(menuJson[i].cate_level == 1){
　　　　		$("#first_category").append("<option value='"+menuJson[i].id+"' title='"+menuJson[i].cate_name+"'>"+menuJson[i].cate_name+"</option>");
			}
	　　}
		}
	})
}


function tableFun(tdUrl,tbData){
	 $.get(tdUrl,tbData, function(result){ 
		 var ue = UE.getEditor('editor');
		 	var _images = "";
			var menuJson = JSON.parse(result);
				tableFun2("${ctx!}/classIfication/getClassIficationData");
				console.log(menuJson.list);
				$("#id").val(menuJson.id);
				$("#spuid").val(menuJson.spuid);
				$("#spu_name").val(menuJson.spu_name);
				$("#spu_subheading").val(menuJson.spu_subheading);
				$("#spu_keyword").val(menuJson.spu_keyword);
				$("#spu_spec").val(menuJson.spu_spec);
				$("#brand_name").val(menuJson.brand_name);
				$("#first_category").val(menuJson.first_category);
				$("#first_category_name").val(menuJson.first_category_name);
				fun1(menuJson.first_category);
				$("#second_category").val(menuJson.second_category);
				$("#second_category_name").val(menuJson.second_category_name);
				fun2(menuJson.second_category);
				$("#third_category").val(menuJson.third_category);
				$("#third_category_name").val(menuJson.third_category_name);
				fun3();
				$("#image").val(menuJson.image);
				$("#images").val(menuJson.images);
				$("#video_image").val(menuJson.video_image);
				$("#video_url").val(menuJson.video_url);
				$("#banner_image").val(menuJson.banner_image);
				$("#success_image").val(menuJson.success_image);
				$("#success_gif_image").val(menuJson.success_gif_image);

				$("#img7").attr("disabled",true);
				$("#img8").attr("disabled",true);

				$("#valid_duration").val(menuJson.valid_duration);
				
				$("#is_banner").val(menuJson.is_banner);
				$("#is_top").val(menuJson.is_top);
				$("#market_price").val(menuJson.market_price);
				$("#supply_price").val(menuJson.supply_price);
				$("#price").val(menuJson.price);
				$("#service_price").val(menuJson.service_price);
				$("#commission").val(menuJson.commission);
				if(menuJson.spuDesc != 'undefind' && menuJson.spuDesc != null){
					$("#editor").val(menuJson.spuDesc.skudesc);
				}

				 if (menuJson.images != null && menuJson.images != '' && menuJson.images != 'undefind'){
					 myArray=eval(menuJson.images);
					 for (var i = 0; i < myArray.length; i++) {
						 var d1=document.getElementById("imgs");
						 var img=document.createElement("img");
						 img.id="images"+ids+"";
						 img.name="allImg";
						 img.src=""+myArray[i]+"";
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
						 ids ++;
					 }
				 }

				$("#img2").fileinput({
					language : 'zh',
				    uploadUrl : "#",
				    autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
				    minFileCount: 0,
				    uploadAsync: false,
				    showRemove:false,
				    showUpload:false,
				    dropZoneEnabled: false,//是否显示拖拽区域
				    maxFileCount: 1,//最大上传数量
				    maxFileSize:500,//上传最大500
				    minImageWidth:750,//图片的最小宽度
				    maxImageWidth:752,//图片的最大宽度
				    minImageHeight:750,//图片的最小高度
				    maxImageHeight:752,//图片的最大高度
				    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
				    enctype: 'multipart/form-data',
				    overwriteInitial: true,//不覆盖已上传的图片
				    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
				    browseClass : "btn btn-primary", //按钮样式
				    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				    initialPreviewAsData: true,
				    initialPreviewFileType: 'image',
				    initialPreview:menuJson.image,
				    initialPreviewConfig:[{
													url: '${ctx!}/spuCommodity/delOssPhoto?filePath='+menuJson.image+''// 预展示图片的删除调取路径
												}]
				})
				
				$("#img4").fileinput({
					language : 'zh',
				    uploadUrl : "#",
				    autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
				    minFileCount: 0,
				    uploadAsync: false,
				    showRemove:false,
				    showUpload:false,
				    dropZoneEnabled: false,//是否显示拖拽区域
				    maxFileCount: 1,//最大上传数量
				    maxFileSize:500,//上传最大500
				    minImageWidth:750,//图片的最小宽度
				    maxImageWidth:752,//图片的最大宽度
				    minImageHeight:750,//图片的最小高度
				    maxImageHeight:752,//图片的最大高度
				    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
				    enctype: 'multipart/form-data',
				    overwriteInitial: true,//不覆盖已上传的图片
				    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
				    browseClass : "btn btn-primary", //按钮样式
				    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				    initialPreviewAsData: true,
				    initialPreviewFileType: 'image',
				    initialPreview:menuJson.video_image,
				    initialPreviewConfig:[{
													url: '${ctx!}/spuCommodity/delOssPhoto?filePath='+menuJson.video_image+''// 预展示图片的删除调取路径
												}]
				})
				
				
				$("#img5").fileinput({
					language : 'zh',
				    uploadUrl : "#",
				    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
				    minFileCount: 0,
				    uploadAsync: false,
				    showRemove:false,
				    showUpload:false,
				    dropZoneEnabled: false,//是否显示拖拽区域
				    maxFileCount: 1,//最大上传数量
				    maxFileSize:15360,//上传最大15MB
				    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
				    enctype: 'multipart/form-data',
				    overwriteInitial: true,//不覆盖已上传的图片
				    allowedFileExtensions : [ "mp4"],
				    browseClass : "btn btn-primary", //按钮样式
				    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				    previewFileType:'video',
				    initialPreviewAsData: true,
				    initialPreviewFileType: 'video',
				    initialPreview:menuJson.video_url,
				    initialPreviewConfig:[{
													url: '${ctx!}/spuCommodity/delOssPhoto?filePath='+menuJson.video_url+''// 预展示图片的删除调取路径
												}]
				})
				
				
				$("#img6").fileinput({
					language : 'zh',
				    uploadUrl : "#",
				    autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
				    minFileCount: 0,
				    uploadAsync: false,
				    showRemove:false,
				    showUpload:false,
				    dropZoneEnabled: false,//是否显示拖拽区域
				    maxFileCount: 1,//最大上传数量
				    maxFileSize:500,//上传最大500
					minImageWidth:688,//图片的最小宽度
					maxImageWidth:692,//图片的最大宽度
					minImageHeight:386,//图片的最小高度
					maxImageHeight:390,//图片的最大高度
				    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
				    enctype: 'multipart/form-data',
				    overwriteInitial: true,//不覆盖已上传的图片
				    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
				    browseClass : "btn btn-primary", //按钮样式
				    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				    initialPreviewAsData: true,
				    initialPreviewFileType: 'image',
				    initialPreview:menuJson.banner_image,
				    initialPreviewConfig:[{
													url: '${ctx!}/spuCommodity/delOssPhoto?filePath='+menuJson.banner_image+''// 预展示图片的删除调取路径
												}]
				})


				 $("#img7").fileinput({
					 language : 'zh',
					 uploadUrl : "#",
					 autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
					 minFileCount: 0,
					 uploadAsync: false,
					 showRemove:false,
					 showUpload:false,
					 dropZoneEnabled: false,//是否显示拖拽区域
					 maxFileCount: 1,//最大上传数量
					 maxFileSize:500,//上传最大500
					 minImageWidth:688,//图片的最小宽度
					 maxImageWidth:692,//图片的最大宽度
					 minImageHeight:386,//图片的最小高度
					 maxImageHeight:390,//图片的最大高度
					 msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
					 enctype: 'multipart/form-data',
					 overwriteInitial: true,//不覆盖已上传的图片
					 allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
					 browseClass : "btn btn-primary", //按钮样式
					 previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
					 initialPreviewAsData: true,
					 initialPreviewFileType: 'image',
					 initialPreview:menuJson.success_image,
					 initialPreviewConfig:[{
						 url: ''// 预展示图片的删除调取路径
					 }]
				 })

				 $("#img8").fileinput({
					 language : 'zh',
					 uploadUrl : "#",
					 autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
					 minFileCount: 0,
					 uploadAsync: false,
					 showRemove:false,
					 showUpload:false,
					 dropZoneEnabled: false,//是否显示拖拽区域
					 maxFileCount: 1,//最大上传数量
					 maxFileSize:500,//上传最大500
					 minImageWidth:688,//图片的最小宽度
					 maxImageWidth:692,//图片的最大宽度
					 minImageHeight:386,//图片的最小高度
					 maxImageHeight:390,//图片的最大高度
					 msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
					 enctype: 'multipart/form-data',
					 overwriteInitial: true,//不覆盖已上传的图片
					 allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
					 browseClass : "btn btn-primary", //按钮样式
					 previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
					 initialPreviewAsData: true,
					 initialPreviewFileType: 'image',
					 initialPreview:menuJson.success_gif_image,
					 initialPreviewConfig:[{
						 url: ''// 预展示图片的删除调取路径
					 }],
				 })
				
				
	})
}

function fun1(id){
	 $("#second_category").empty();
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
				if(id == ""){
					fun2("");
				}
			}
		})
	 
}

function fun2(id){
	$("#third_category").empty();
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
			}
		})
}

var _specList;
function fun3(){
	var array = [];
	var _spec = $("#spu_spec").val();
		  _specList = eval(_spec);
		  for(var i = 0; i < _specList.length; i++){
				 if(array.indexOf(_specList[i].spec_name) == -1){
					 array.push(_specList[i].spec_name);
			     }
			}
		  
		  
	$("#specifications").empty();
	var executerDiv=$("#specifications");  
  	 	  executerDiv.innerHTML="";  
	var ul=document.createElement("ul");
  	for(var j = 0; j< array.length; j++){
  		var li=document.createElement("li");  
  			  li.setAttribute("value",array[j]);
  			  li.append(array[j] + ":");
  		for (var i = 0; i < _specList.length; i++) {
  			if(array[j] == _specList[i].spec_name){
  				var checkBox=document.createElement("input");  
				  	  checkBox.setAttribute("type","checkbox");  
				      checkBox.setAttribute("id",_specList[i].id);  
				  	  checkBox.setAttribute("name","checkname");  
				  	  checkBox.setAttribute("title",_specList[i].spec_name);  
				  	  checkBox.setAttribute("value",_specList[i].id);
				  	  checkBox.setAttribute("checked","checked");
				  	  checkBox.setAttribute("disabled","disabled");
					  li.appendChild(checkBox);  
					  li.appendChild(document.createTextNode(_specList[i].spec_value));  
					  ul.appendChild(li);  
  			}
　　	}
  	  }
	executerDiv.append(ul);  
}

function aa(){
	ids ++;
	var ss = document.getElementsByName("allImg");
	var num = ss.length;
	console.log(ss.length);
	if (num >= 4){
		bootbox.alert("最多四张照片");
		return false;
	}
	var formData = new FormData(document.getElementById("form"));
	$.ajax({
		url:"${ctx!}/spuCommodity/upload",
		data : formData,
		type:"POST",
		dataType : "json",
		async : false,
		processData: false,
		contentType: false,
		success : function(data) {
			if (data.draw == 1) {
				var im = data.data;
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

	var images = "";
	var ss = document.getElementsByName("allImg");
	for (var i = 0; i < ss.length; i++) {
		if (i == ss.length - 1) {
			images += ss[i].src;
		}else{
			images += ss[i].src + ",";
		}
	}
	$("#images").val(images);

	$("#first_category").attr("disabled",false);
	$("#second_category").attr("disabled",false);
	$("#third_category").attr("disabled",false);
	
	
		var ue = UE.getEditor('editor');
		var _image = $("#image").val();
		var _name = $("#img2").val();
		if(_image == null || _image == '' || _image == 'undefind'){
			if(_name == null || _name == '' || _name == 'undefind'){
				bootbox.alert("请添加SPU主图");
				return false;
			}
		}

		var _bannerImage = $("#banner_image").val();
		var _baner = $("#is_banner").val();
		var _banerName = $("#img6").val();
		if(_bannerImage == null || _bannerImage == '' || _bannerImage == 'undefind'){
			if(_banerName == null || _banerName == '' || _banerName == 'undefind'){
				bootbox.alert("请添加banner图");
				return false;
			}
		}

		var ue = UE.getEditor('editor');
		var _editor = ue.getContent();
		if(_editor == null || _editor == '' || _editor == 'undefind'){
			bootbox.alert("请添加产品详情");
			return false;
		}


		var _marketPrice = $("#market_price").val();  //市场价
		var _price = $("#price").val();  //团价
		var _commission = $("#commission").val();  //佣金
		var _supply = $("#supply_price").val();  //供货价
		var _servicePrice = $("#service_price").val();  //服务费

		if(parseFloat(_price) > parseFloat(_marketPrice)){
			bootbox.alert("团价不能大于市场价");
			return false;
		}
		var _suf = Math.floor(parseFloat(_price)*0.02*100)/100;   //手续费
		var _eightPrice = parseFloat(_supply) + parseFloat(_suf)+ parseFloat(_commission) + parseFloat(_servicePrice);

		var formdata = new FormData(document.getElementById("form"));
		$.ajax({
			url:"${ctx!}/spuCommodity/editSpuData",
			data : formdata,
			type:"POST",
			dataType : "json",
			async : false,
			processData: false,
			contentType: false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/spuCommodity/spuMenu";
						});
					}else{
						bootbox.alert(data.error);
					}
			}
		})
};


</script>
</html>