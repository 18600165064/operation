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
                          <h3>编辑消息
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
						                <input type="text" id="id" name="id" hidden="hidden"/>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>标题</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required[maxSize[40]]]" id="title" name="title" placeholder="标题" data-errormessage-value-missing="标题不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;">  </em>SPUID</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control" id="spuid" name="spuid"/>
						                </td>
						              </tr>
						              <tr>
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>图片</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img2" name="image1">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="image" id="image"/>
						                </td>
						              </tr>
						              <tr>
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>视频</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img3" name="video">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="video_url" id="video_url"/>
						                </td>
						              </tr>
						              <tr>
			                                <td class="input-group-addon" width="20%"> <em>*</em>内容</td>
			                                <td width="30%">
			                                	<script id="editor" type="text/plain" style="width: 100%; height: 250px;" name="content" data-errormessage="不能超过2000个字符"></script>
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
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>类型</td>
						                <td width="30%" >
							                  <select class="form-control"  id="type" name="type">
							                  		<option id="1" value="1">欢迎语</option>
							                  		<option id="2" value="2">常见问题</option>
							                  		<option id="3" value="3">首页弹窗海报</option>
							                  		<option id="4" value="4">系统公告</option>
							                  		<option id="5" value="5">拼团赚钱</option>
							                  		<option id="6" value="6">拼团规则</option>
							                  		<option id="7" value="7">他咋拼</option>
							                  		<option id="8" value="8">宣言</option>
							                  		<option id="9" value="9">我要开团海报</option>
                                                    <option id="10" value="10">页面公告</option>
                                                    <option id="11" value="11">龙虎榜海报</option>
                                                    <option id="12" value="12">首页海报</option>
                                                    <option id="13" value="13">我要参团海报</option>
                                                    <option id="14" value="14">商品中间页</option>
							                  </select>
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

var token = $("meta[name='_csrf']").attr("content"); 
var header = $("meta[name='_csrf_header']").attr("content"); 
$(document).ajaxSend(function(e, xhr, options) { 
	xhr.setRequestHeader(header, token); 
});

$("#img2").fileinput({
	language : 'zh',
    uploadUrl : "#",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: false,
    showRemove:false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:500,//上传最大500
    minImageWidth:750,//图片的最小宽度
    maxImageWidth:752,//图片的最大宽度
    minImageHeight:750,//图片的最小高度
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
    uploadUrl : "#",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: false,
    showRemove:false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:15360,//上传最大15MB
    browseOnZoneClick: true,
    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
    enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
    allowedFileExtensions : [ "mp4"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    layoutTemplates :{ 
    	// actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
}) 

$('#img2').on('filepredelete', function(event, key) {  
        $("#image").val("");
 });
 
$('#img3').on('filepredelete', function(event, key) {  
    $("#video_url").val("");
});

$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	$("#img2").fileinput('destroy'); 
	$("#img3").fileinput('destroy'); 
	var srhData = {"id" : _id};
	tableFun("${ctx!}/rich/selectById",srhData);
	$("#type").attr("disabled",true);
 });
 
 
function tableFun(tdUrl,tbData){
	$.get(tdUrl,tbData, function(result){ 
		    var ue = UE.getEditor('editor');
			var menuJson = JSON.parse(result);
			$("#id").val(menuJson.id);
			$("#title").val(menuJson.title);
			$("#spuid").val(menuJson.spuid);
			$("#image").val(menuJson.image);
			$("#video_url").val(menuJson.video_url);
			$("#editor").val(menuJson.content);
			$("#is_top").val(menuJson.is_top);
			$("#type").val(menuJson.type);
			
			if(menuJson.type == 8){
				$("#title").attr("class","form-control validate[required[maxSize[9]]");
			}else{
				$("#title").attr("class","form-control validate[required]");
			}

            if(menuJson.type == 5 || menuJson.type == 6 || menuJson.type == 7 || menuJson.type == 14){
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
                    initialPreview:menuJson.image,
                    initialPreviewConfig:[{
                        url: '${ctx!}/spuCommodity/delOssPhoto?filePath='+menuJson.image+''// 预展示图片的删除调取路径
                    }]
                })
            }else if (menuJson.type == 11 || menuJson.type == 3 || menuJson.type == 12){

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
            }else{
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
                    minImageWidth:748,//图片的最小宽度
                    maxImageWidth:752,//图片的最大宽度
                    minImageHeight:748,//图片的最小高度
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
            }

			$("#img3").fileinput({
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
// 			    enctype: 'multipart/form-data',
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
	})
}
 
 
function save(){
	$("#type").attr("disabled",false);
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
		var formdata = new FormData(document.getElementById("form"));
		$.ajax({
			url:"${ctx!}/rich/editRichData",
			data : formdata,
			type:"POST",
			dataType : "json",
			async : false,
			processData: false,
			contentType: false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/rich/richMenu";
						});
					}else{
						bootbox.alert(data.error);
					}
			}
		})
};

</script>
</html>