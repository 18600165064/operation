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
                        <h3>编辑评论2
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
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SPU</td>
                                        <td width="30%" >
                                            <select class="form-control" id="spuid" name="spuid" >

                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> 最多四张 </em><br/>图片</td>
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
    var _id = "${id!""}";
    var myArray = [];
    var ids=0;

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


    $(function () {
        tableFun();
    });

    function tableFun(){
        $.ajax({
            url:"${ctx!}/yMComment/getSpuData",
            type:"POST",
            dataType : "json",
            async : false,
            processData: false,
            contentType: false,
            success : function(menuJson) {
                for (var i = 0; i < menuJson.length; i++) {
                    $("#spuid").append("<option value='"+menuJson[i].spuid+"'>"+menuJson[i].spu_name+"</option>");
                }
                var srhData = {"id" : _id};
                tableFun2("${ctx!}/yMComment/selectById",srhData);
            }
        })
    }

    function tableFun2(tdUrl,tbData){
        $.get(tdUrl,tbData, function(result){
            var menuJson = JSON.parse(result);
            $("#id").val(menuJson.id);
            $("#spuid").val(menuJson.spuid);
            $("#images").val(menuJson.images);
            $("#comment").val(menuJson.comment);
            if (menuJson.images != null && menuJson.images != '' && menuJson.images != 'undefind'){
                myArray=eval(menuJson.list);
                _myArray2 = eval(menuJson.list);
                var arrs = menuJson.images.split(",");

                for (var i = 0; i < arrs.length; i++) {
                    var d1=document.getElementById("imgs");
                    var img=document.createElement("img");
                    img.id="img"+ids+"";
                    img.src=""+arrs[i]+"";
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
            url:"${ctx!}/yMComment/editYMCommentData",
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