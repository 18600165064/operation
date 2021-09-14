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
                          <h3>新增SPU
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
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SPU名称</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required[maxSize[35]]" id="spu_name" name="spu_name" placeholder="SPU名称，最多35个汉字" data-errormessage-value-missing="SPU名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>品牌名称</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required[maxSize[35]]" id="brand_name" name="brand_name" placeholder="品牌名称，最多35个汉字" data-errormessage-value-missing="品牌名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>商品副标题</td>
						                <td width="30%" >
						                  <input type="text"  class="form-control validate[required[maxSize[25]]" id="spu_subheading" name="spu_subheading" placeholder="商品副标题，最多25个汉字" data-errormessage-value-missing="商品副标题不能为空"/>
						                </td>
						              </tr>
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
						               <tr>
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>产品主图<br/>宽:750 高:750</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img2" name="image1">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						                </td>
						              </tr>
						              <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>轮播图<br/>宽:750 高:750<br/>最多四张</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img3" multiple="multiple" name="files">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="images" id="images"/>
						                </td>
						              </tr>
						              <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>视频封面图</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img4" name="videoImage">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="video_image" id="video_image"/>
						                </td>
						              </tr>
						               <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>视频</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img5" multiple="multiple" name="video">
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
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="market_price" name="market_price" placeholder="市场价" data-errormessage-value-missing="市场价不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>单买价格</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="price" name="price" placeholder="单买价格" data-errormessage-value-missing="单买价格不能为空"/>
						                </td>
						              </tr>
						               <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供货价</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="supply_price" name="supply_price" placeholder="供货价" data-errormessage-value-missing="供货价不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>服务费</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="service_price" name="service_price" placeholder="服务费" data-errormessage-value-missing="服务费不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>佣金</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="commission" name="commission" placeholder="佣金" data-errormessage-value-missing="佣金不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>8人团价格</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="group_price_eight" name="group_price_eight" placeholder="8人团价格" data-errormessage-value-missing="8人团价格不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>5人团价格</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="group_price_five" name="group_price_five" placeholder="5人团价格" data-errormessage-value-missing="5人团价格不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>3人团价格</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="group_price_three" name="group_price_three" placeholder="3人团价格" data-errormessage-value-missing="3人团价格不能为空"/>
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
var _type = "";
var _arr = [];
var token = $("meta[name='_csrf']").attr("content"); 
var header = $("meta[name='_csrf_header']").attr("content"); 
$(document).ajaxSend(function(e, xhr, options) { 
	xhr.setRequestHeader(header, token); 
});

var ue = UE.getEditor('editor');

$("#img2").fileinput({
	language : 'zh',
    uploadUrl : "${ctx!}/spuCommodity/uploadFile",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: true,
    showRemove:false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:500,//上传最大500k
    minImageWidth:748,//图片的最小宽度
    maxImageWidth:752,//图片的最大宽度
    minImageHeight:748,//图片的最小高度
    maxImageHeight:752,//图片的最大高度
    browseOnZoneClick: true,
    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
    enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    layoutTemplates :{ 
    	// actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
})   
$("#img3").fileinput({
	language : 'zh',
    uploadUrl : "${ctx!}/spuCommodity/upload",
//     showUploadedThumbs:true, //当设置为false时，下一批选择上传的文件将从预览中清除这些已经上传的文件缩略图。
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: false,
    showRemove:false,  
    maxFileCount: 4,//最大上传数量
    maxFileSize:500,//上传最大500
    minImageWidth:748,//图片的最小宽度
    maxImageWidth:752,//图片的最大宽度
    minImageHeight:748,//图片的最小高度
    maxImageHeight:752,//图片的最大高度
    browseOnZoneClick: true,
    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
    enctype: 'multipart/form-data',
    overwriteInitial: false,//不覆盖已上传的图片
    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    validateInitialCount:true,
    layoutTemplates :{ 
    	// actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
})   
$("#img4").fileinput({
	language : 'zh',
    uploadUrl : "${ctx!}/spuCommodity/uploadFile",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    showRemove:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:500,//上传最大500k
    minImageWidth:748,//图片的最小宽度
    maxImageWidth:752,//图片的最大宽度
    minImageHeight:748,//图片的最小高度
    maxImageHeight:752,//图片的最大高度
    browseOnZoneClick: true,
    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
    enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    layoutTemplates :{ 
    	// actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
}) 
$("#img5").fileinput({
	language : 'zh',
    uploadUrl : "${ctx!}/spuCommodity/uploadFile",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    showRemove:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:15360,//上传最大15MB
    browseOnZoneClick: true,
    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
    enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
	previewFileType:'video',
    allowedFileExtensions : ["mp4"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    layoutTemplates :{ 
    	// actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
}) 

$("#img6").fileinput({
	language : 'zh',
    uploadUrl : "${ctx!}/spuCommodity/uploadFile",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    showRemove:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:500,//上传最大500k
    minImageWidth:688,//图片的最小宽度
    maxImageWidth:692,//图片的最大宽度
    minImageHeight:386,//图片的最小高度
    maxImageHeight:390,//图片的最大高度
    browseOnZoneClick: true,
    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
    enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    layoutTemplates :{ 
    	// actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
})


$("#img7").fileinput({
	language : 'zh',
	uploadUrl : "${ctx!}/spuCommodity/uploadFile",
	showPreview : true, //是否显示预览
	showCaption: true,//是否显示标题
	showUpload:false,
	showRemove:false,
	dropZoneEnabled: false,//是否显示拖拽区域
	autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
	minFileCount: 0,
	uploadAsync: false,
	maxFileCount: 1,//最大上传数量
	maxFileSize:500,//上传最大500k
	browseOnZoneClick: true,
	msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
	enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
	allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
	browseClass : "btn btn-primary", //按钮样式
	previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
	layoutTemplates :{
		actionUpload:'',//去除上传预览缩略图中的上传图片；
	}
})


$("#img8").fileinput({
	language : 'zh',
	uploadUrl : "${ctx!}/spuCommodity/uploadFile",
	showPreview : true, //是否显示预览
	showCaption: true,//是否显示标题
	showUpload:false,
	showRemove:false,
	dropZoneEnabled: false,//是否显示拖拽区域
	autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
	minFileCount: 0,
	uploadAsync: false,
	maxFileCount: 1,//最大上传数量
	maxFileSize:500,//上传最大500k
	browseOnZoneClick: true,
	msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
	enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
	allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
	browseClass : "btn btn-primary", //按钮样式
	previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
	layoutTemplates :{
		actionUpload:'',//去除上传预览缩略图中的上传图片；
	}
})

//异步上传返回错误结果处理
$('#img3').on('filebatchuploaderror', function(event, data, msg) {
});
//异步上传返回结果处理
$("#img3").on("fileuploaded", function (event, data, previewId, index) {
    var obj = data.response;
});

//选择文件后事件处理
var _filesCount = 0;
$('#img3').on('filebatchselected', function(event, files) {
	var filesCount = $('#img3').fileinput('getFilesCount');
	_filesCount += filesCount;
	if(_filesCount > 4){
		 bootbox.alert("最多上传四张图片", function() {});
	}else{
		$('#img3').fileinput('upload');
	}
});

//批量同步上传错误处理
$('#img3').on('filebatchuploaderror', function(event, data, msg) {
	
});
//批量同步上传成功结果处理
$("#img3").on("filebatchuploadsuccess", function (event, data, previewId, index) {
	var im = $("#images").val();
 	var obj = data.response;
	$("#images").val(obj);
	var im2 = $("#images").val();
	if(im != '' && im != 'undefind' && im != null){
		$("#images").val(im+","+im2);
	}
});


$('#img3').on('filepredelete', function(event, key) {  
	alert("123");
}); 

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

function save(){
	
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	var _images = $("#images").val();
	if(_images != null && _images != '' && _images != 'undefind'){
		 
		var _first = $("#first_category").val();
		var _second = $("#second_category").val();
		var _third = $("#third_category").val();
		if(_first == null || _first == '' || _first == 'undefind' || _second == null || _second == '' || _second == 'undefind' || _third == null || _third == '' || _third == 'undefind'){
			bootbox.alert("分类错误");
			return false;
		}
		
		
		var _name = $("#img2").val();
		if(_name == null || _name == '' || _name == 'undefind'){
			bootbox.alert("请添加SPU主图");
			return false;
		}
		
		var _baner = $("#is_banner").val();
		var _banerName = $("#img6").val();
		if(_banerName == null || _banerName == '' || _banerName == 'undefind'){
			bootbox.alert("请添加banner图");
			return false;
		}
		
		var ue = UE.getEditor('editor');
		var _editor = ue.getContent();
		if(_editor == null || _editor == '' || _editor == 'undefind'){
			bootbox.alert("请添加产品详情");
			return false;
		}
		
		
		var _marketPrice = $("#market_price").val();  //市场价
		var _price = $("#price").val();  //单买价
		var _commission = $("#commission").val();  //佣金
		var _supply = $("#supply_price").val();  //供货价
		var _servicePrice = $("#service_price").val();  //服务费
		var _eight = $("#group_price_eight").val();  //8人团价
		var _five = $("#group_price_five").val();  //5人团价
		var _three = $("#group_price_three").val();  //3人团价
		
		if(parseFloat(_price) > parseFloat(_marketPrice)){
			bootbox.alert("单买价格不能大于市场价");
			return false;
		}
		
		$("#group_price_eight").attr("readOnly",false);
		$("#group_price_five").attr("readOnly",false);
		$("#group_price_three").attr("readOnly",false);
		var _suf = Math.floor(parseFloat(_price)*0.02*100)/100;   //手续费
		var _eightPrice = parseFloat(_supply) + parseFloat(_suf)+ parseFloat(_commission) + parseFloat(_servicePrice);
	    if(parseFloat(_eight) < _eightPrice){
			  document.getElementById("group_price_eight").value="";
			  bootbox.alert("8人团价格错误!");
			  return false;
	    }
		
		
	  if(parseFloat(_eight) >= parseFloat(_five)){
		  document.getElementById("group_price_five").value="";
		  bootbox.alert("5人团价格错误!");
		  return false;
	  }
		
	  if(parseFloat(_five) >= parseFloat(_three) || parseFloat(_three) >= parseFloat(_price)){
		  document.getElementById("group_price_three").value="";
		  bootbox.alert("3人团价格错误!");
		  return false;
	  }
		
		var arr = new Array(); 
		$("input[type=checkbox]:checked").each(function(i){ 
			arr[i] = $(this).val(); 
		});
		var vals = arr.join(",");
		
		$("#spu_spec").val(vals);
		
		var _spec = $("#spu_spec").val();
		if(_spec != null && _spec != '' && _spec != 'undefind'){
			var formdata = new FormData(document.getElementById("form"));
			$.ajax({
				url:"${ctx!}/spuCommodity/addSpu",
				timeout : 600000, //超时时间设置，单位毫秒
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
		}else{
			bootbox.alert("分类规格错误");
			return false;
		}
	}else{
		bootbox.alert("轮播图错误");
		return false;
	}
	
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
				if(menuJson != null && menuJson != '' && menuJson != 'undefind'){
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
				if(menuJson != null && menuJson != '' && menuJson != 'undefind'){
						fun3();
				}
			}
		})
}


function fun3(){
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
				_arr = menuJson;
				if(menuJson.length == 0){
					$("#specifications").empty();
				}else{
					$("#specifications").empty();
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

</script>
</html>