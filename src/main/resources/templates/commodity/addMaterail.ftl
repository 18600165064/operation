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
                          <h3>新增素材
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
						              <tr >
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>轮播图</td>
						                <td width="30%" id="imgs">
                                            <div class="upload-file">
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
                                            <input type="file" name="files" class="btn btn-primary" id="pic" onchange="aa()" style="width: 80px;">
                                            <span class="tip">点击上传图片</span>
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<input type="hidden" name="images" id="images"/>
											<input type="hidden" name="spuid" id="spuid"/>
                                            </div>
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
		 								<tr>
			                                <td class="input-group-addon" width="20%"> <em>*</em>描述</td>
			                                <td width="30%">
			                                	<textarea id="descr" name="descr" style="width:100%;height:160px;"></textarea> 
			                                </td>
		                            	</tr>
						              </tbody>
						            </table>
						            <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
						                <input class="btn btn-primary submit" type="button" id="saveSpu"  onclick="save()" value="保存" />
						                <input class="btn btn-primary" type="button" onclick="history()" value="返回"/>
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
var token = $("meta[name='_csrf']").attr("content"); 
var header = $("meta[name='_csrf_header']").attr("content"); 
$(document).ajaxSend(function(e, xhr, options) { 
	xhr.setRequestHeader(header, token); 
});

var _spuid = "${spuid!""}";

$("#img4").fileinput({
	language : 'zh',
    uploadUrl : "#",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    showUpload:false,
    showRemove:false,
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    minImageWidth:748,//图片的最小宽度
    maxImageWidth:752,//图片的最大宽度
    minImageHeight:598,//图片的最小高度
    maxImageHeight:752,//图片的最大高度
    uploadAsync: false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:500,//上传最大500
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
    uploadUrl : "#",
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

$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	var srhData = {"id" : _spuid};
	tableFun("${ctx!}/spuCommodity/selectById",srhData);
 });


function tableFun(tdUrl,tbData){
	$.get(tdUrl,tbData, function(result){ 
		var menuJson = JSON.parse(result);
			  $("#spuid").val(menuJson.spuid);
	})
}

var ids=0;
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
        url:"${ctx!}/materail/upload",
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
	if(images != null && images != '' && images != 'undefind'){
		var formdata = new FormData(document.getElementById("form"));
		$.ajax({
			url:"${ctx!}/materail/addMaterailData",
//	 		data : $("#form").serialize(),
			data : formdata,
			type:"POST",
			dataType : "json",
			async : false,
			processData: false,
			contentType: false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/spuCommodity/supplierSpuMenu";
						});
					}else{
						bootbox.alert(data.error);
					}
			}
		})
	}else{
		bootbox.alert("轮播图错误");
		return false;
	}
	
};

	function history(){
		window.location.href = "${ctx!}/spuCommodity/supplierSpuMenu";
	}


</script>
</html>