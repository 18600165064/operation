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
                        <h3>SKU
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
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SKU名称</td>
                                        <td width="30%" >
                                            <input type="hidden" name="id" id="id" />
                                            <input type="hidden" name="spuid" id="spuid" />
                                            <input type="hidden" name="skuid" id="skuid" />
                                            <input type="text"  class="form-control validate[required]" id="sku_name" name="sku_name" placeholder="SKU名称" data-errormessage-value-missing="SKU名称不能为空" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>规格</td>
                                        <td width="30%" >
                                            <input type="hidden"  class="form-control" id="spec" name="spec"/>
                                            <div id="specifications">

                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SKU主图</td>
                                        <td width="30%" >
                                            <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
                                            <input type="file" class="file" id="img2" name="image1">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input type="hidden" name="sku_image" id="sku_image"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>状态</td>
                                        <td width="30%" >
                                            <select class="form-control"  id="on_sale" name="on_sale">
                                                <option id="0" value="0">上架</option>
                                                <option id="1" value="1">下架</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>市场价</td>
                                        <td width="30%" >
                                            <input type="text"  class="form-control validate[required]" id="market_price" name="market_price" placeholder="市场价" data-errormessage-value-missing="市场价不能为空" readonly="readonly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>团价</td>
                                        <td width="30%" >
                                            <input type="text"  class="form-control validate[required]" id="price" name="price" placeholder="单买价格" data-errormessage-value-missing="单买价格不能为空" readonly="readonly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供货价</td>
                                        <td width="30%" >
                                            <input type="text"  class="form-control validate[required]" id="supply_price" name="supply_price" placeholder="供货价" data-errormessage-value-missing="供货价不能为空" readonly="readonly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>服务费</td>
                                        <td width="30%" >
                                            <input type="text"  class="form-control validate[required]" id="service_price" name="service_price" placeholder="服务费" data-errormessage-value-missing="服务费不能为空" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>佣金</td>
                                        <td width="30%" >
                                            <input type="text"  class="form-control validate[required]" id="commission" name="commission" placeholder="3人团佣金" data-errormessage-value-missing="3人团佣金不能为空" readonly="readonly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>库存</td>
                                        <td width="30%" >
                                            <input type="text"  class="form-control validate[required,custom[onlyNumberSp]]" id="stock" name="stock" placeholder="库存" data-errormessage-value-missing="库存不能为空" readonly="readonly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>运费模板</td>
                                        <td width="30%" >
                                            <select class="form-control"  id="shipping_id" name="shipping_id" disabled="true">

                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>结算运费模板</td>
                                        <td width="30%" >
                                            <select class="form-control"  id="settle_id" name="settle_id" disabled="true">

                                            </select>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="ibox-footer" style="text-align: center">
                                    <!--操作按钮 开始-->
                                    <div class="ibox-btns">
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
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    var _id = "${id!""}";
    var _type = "";
    // var ue = UE.getEditor('editor');

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
        maxFileCount: 1,//最大上传数量
        maxFileSize:10240,//上传最大10MB
        browseOnZoneClick: true,
        msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
        enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
        allowedFileExtensions : [ "jpg", "png", "gif", "jpeg", "mp4"],
        browseClass : "btn btn-primary", //按钮样式
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
        layoutTemplates :{
            // actionDelete:'', //去除上传预览的缩略图中的删除图标
            actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        }
    })

    $('#img2').on('filepredelete', function(event, key) {
        $("#sku_image").val("");
    });



    $(function () {
        _errFm =  $('#form');
        _errFm.validationEngine('attach', {
            maxErrorsPerField:1,
            autoHidePrompt: true,
            autoHideDelay: 2000
        });
        $("#img2").fileinput('destroy');
        tableFun3("${ctx!}/specifications/selSpecName");
        tableFun2("${ctx!}/supplierAccountShipping/shippingList");
    });


    function tableFun3(tdUrl,tbData){
        $.get(tdUrl,tbData, function(result){
            var menuJson = JSON.parse(result);
            _type = menuJson;
        })
    }


    var arr = new Array();
    function tableFun(tdUrl,tbData){
        $.get(tdUrl,tbData, function(result){
            var menuJson = JSON.parse(result);
            $("#id").val(menuJson.id);
            $("#spuid").val(menuJson.spuid);
            $("#skuid").val(menuJson.skuid);
            $("#sku_image").val(menuJson.sku_image);
            $("#sku_name").val(menuJson.sku_name);
            $("#stock").val(menuJson.stock);
            $("#on_sale").val(menuJson.on_sale);
            $("#supply_price").val(menuJson.supply_price);
            $("#market_price").val(menuJson.market_price);
            $("#price").val(menuJson.price);
            $("#service_price").val(menuJson.service_price);
            $("#commission").val(menuJson.commission);
            $("#shipping_id").val(menuJson.shipping_id);
            $("#settle_id").val(menuJson.settle_id);

            var executerDiv=$("#specifications");
            executerDiv.innerHTML="";
            var ul=document.createElement("ul");
            var type = eval(_type);
            arr = eval(menuJson.spec);
            for(var j = 0; j< type.length; j++){
                var li=document.createElement("li");
                li.setAttribute("value",type[j].spec_name);
                li.append(type[j].spec_name + ":");
                for (var i = 0; i < arr.length; i++) {
                    if(type[j].spec_name == arr[i].spec_name){
                        var checkBox=document.createElement("input");
                        checkBox.setAttribute("type","checkbox");
                        checkBox.setAttribute("id",arr[i].id);
                        checkBox.setAttribute("name","checkname");
                        checkBox.setAttribute("title",arr[i].spec_name);
                        checkBox.setAttribute("value",arr[i].id);
                        checkBox.setAttribute("checked","checked");
                        checkBox.setAttribute("disabled","disabled");

                        li.appendChild(checkBox);
                        li.appendChild(document.createTextNode(arr[i].spec_value));
                        ul.appendChild(li);
                    }
                }
            }
            executerDiv.append(ul);


            $("#img2").fileinput({
                language : 'zh',
                uploadUrl : "#",
                autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
                dropZoneEnabled: false,//是否显示拖拽区域
                showBrowse:false,
                showRemove:false,
                showUpload:false,
                minFileCount: 0,
                uploadAsync: false,
                maxFileCount: 1,//最大上传数量
                maxFileSize:10240,//上传最大10MB
                msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
                enctype: 'multipart/form-data',
                overwriteInitial: false,//不覆盖已上传的图片
                allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
                browseClass : "btn btn-primary", //按钮样式
                previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
                initialPreviewAsData: true,
                layoutTemplates :{
                    actionDelete:'', //去除上传预览的缩略图中的删除图标
                    actionUpload:'',//去除上传预览缩略图中的上传图片；
                    actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
                },
                initialPreviewFileType: 'image',
                initialPreview:menuJson.sku_image,
                initialPreviewConfig:[{
                    url: '${ctx!}/spuCommodity/delOssPhoto'// 预展示图片的删除调取路径
                }]
            })


            $("#img3").fileinput({
                language : 'zh',
                uploadUrl : "#",
                autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
                dropZoneEnabled: false,//是否显示拖拽区域
                showBrowse:false,
                showRemove:false,
                showUpload:false,
                minFileCount: 0,
                uploadAsync: false,
                maxFileCount: 1,//最大上传数量
                maxFileSize:10240,//上传最大10MB
                msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
                enctype: 'multipart/form-data',
                overwriteInitial: false,//不覆盖已上传的图片
                allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
                browseClass : "btn btn-primary", //按钮样式
                previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
                layoutTemplates :{
                    actionDelete:'', //去除上传预览的缩略图中的删除图标
                    actionUpload:'',//去除上传预览缩略图中的上传图片；
                    actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
                },
                initialPreviewAsData: true,
                initialPreviewFileType: 'image',
                initialPreview:menuJson.banner_image,
                initialPreviewConfig:[{
                    url: '${ctx!}/spuCommodity/delOssPhoto'// 预展示图片的删除调取路径
                }]
            })

        })
    }


    function tableFun2(tdUrl){
        $.ajax({
            url:tdUrl,
            dataType : "json",
            async : false,
            success : function(menuJson) {
                for (var i = 0; i < menuJson.length; i++) {
                    $("#shipping_id").append("<option value='"+menuJson[i].sas_id+"'>"+menuJson[i].templet_name+"</option>");
                }
                tableFun4("${ctx!}/supplierSettle/supplierSettleList");
            }
        })
    }


    function tableFun4(tdUrl){
        $.get(tdUrl, function(result){
            var menuJson = JSON.parse(result);
            for (var i = 0; i < menuJson.length; i++) {
                $("#settle_id").append("<option value='"+menuJson[i].sas_id+"'>"+menuJson[i].templet_name+"</option>");
            }
            var srhData = {"id" : _id};
            tableFun("${ctx!}/product/selectById",srhData);
        })
    }





</script>
</html>