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
 <script src="${ctx!}/assets/js/plugins/piexif.js"></script>


<style>
    .file {
        position: relative;
        display: inline-block;
        background: #D0EEFF;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #1E88C7;
        text-decoration: none;
        text-indent: 0;
        line-height: 20px;
    }
    .file input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
    }
    .file:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
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
                          <h3>新增评论
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
                                          <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SPU</td>
                                          <td width="30%" >
                                              <select class="form-control" id="spuid" name="spuid" >

                                              </select>
                                          </td>
                                      </tr>
						              <tr>
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> 最多四张 </em><br/>图片</td>
						                <td width="30%" id="imgs">
                                            <input type="file" name="files" class="btn btn-primary" id="pic" onchange="aa()" style="width: 80px;">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input type="hidden" name="images" id="images"/>
						                </td>
						              </tr>
						              <tr>
			                                <td class="input-group-addon" width="20%"> <em>*</em>内容</td>
			                                <td width="30%">
                                                <textarea id="comment" name="comment" style="width:100%;height:160px;"></textarea>
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
var ids=0;
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(header, token);
});



$(function () {
    tableFun("${ctx!}/yMComment/getSpuData");
});

function tableFun(tdUrl){
    $.get(tdUrl, function(result){
        var menuJson = JSON.parse(result);
        for (var i = 0; i < menuJson.length; i++) {
            $("#spuid").append("<option value='"+menuJson[i].spuid+"'>"+menuJson[i].spu_name+"</option>");
        }
    })
}

function aa(){
    ids ++;
    var ss = document.getElementsByTagName("img");
    var num = ss.length;
    console.log(ss.length);
    if (num >= 4){
        bootbox.alert("最多四张照片");
        return false;
    }
    var formData = new FormData(document.getElementById("form"));
    $.ajax({
        url:"${ctx!}/yMComment/upload",
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
                    img.id="img"+ids+"";
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
    var img = document.getElementById("img"+num+"");
    del.remove();
    img.remove();
}

    function save(){
        var images = "";
        var ss = document.getElementsByTagName("img");
        for (var i = 0; i < ss.length; i++) {
            if (i == ss.length - 1) {
                images += ss[i].src;
            }else{
                images += ss[i].src + ",";
            }
        }
        $("#images").val(images);
		var formdata = new FormData(document.getElementById("form"));
		$.ajax({
			url:"${ctx!}/yMComment/addYMCommentData",
			data : formdata,
			type:"POST",
			dataType : "json",
			async : false,
			processData: false,
			contentType: false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/yMComment/yMCommentMenu";
						});
					}else{
						bootbox.alert(data.error);
					}
			}
		})
    };

</script>
</html>