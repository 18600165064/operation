<#include "../common/header.html">
<meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no" />
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
<link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
<link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
<link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx!}/assets/bootstrap-validator/css/bootstrapValidator.min.css" />
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
<script src="${ctx!}/assets/js/laydate/laydate.js"></script>

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
                        <h3>新增团长信息
                        </h3>
                    </div>
                </div>

                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <div class="clearfix">
                                <input type="text" name="beginDate" id="beginDate" placeholder="开始时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>
                                <select class=".col-md-3" id="spuid" name="spuid" style="font-size: 20px;width:280px;">

                                </select>
                                <button type="button" class="btn btn-primary" style="float:right" onclick="select()">搜索</button>
                                <button type="button" class="btn btn-primary" style="float:right" onclick="export2()">导出</button>
                            </div>
                        </div>
                        <div class="x_content">

                            <table id="datatable" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>电话</th>
                                    <th>姓名</th>
                                    <th>昵称</th>
                                    <th>盒数</th>
                                    <th>推荐人姓名</th>
                                    <th>推荐人昵称</th>
                                    <th>创建时间</th>
                                </tr>
                                </thead>
                            </table>
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
</body>
<script src="${ctx!}/assets/js/custom.js"></script>
<script>
    $(function () {
        laydate.render({
            elem: '#beginDate',
            value:new Date(),
            btns: ['now', 'confirm']
        });
        tableFun("${ctx!}/yMComment/getSpuData");
    });


    var table = $('#datatable').DataTable({
        "searching": false,
        "ordering": false,
        "processing": true,
        "language": {
            "url": '${ctx!}/assets/js/Chinese.json'
        },
        "serverSide": true,
        "ajax": {
            "url": "${ctx!}/platform/getGrouperData",
            //默认为data,这里定义为空，则只需要传不带属性的数据
        },
        "columns": [
            {
                "data": "user_id",
                "class":"text-center"
            },
            {
                "data": "addr_mobile",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandle(data);
                }
            },
            {
                "data": "addr_name",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandle(data);
                }
            },
            {
                "data": "nick_name",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandle(data);
                }
            },
            {
                "data": "num",
                "class":"text-center"
            },
            {
                "data": "addrname",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandle(data);
                }
            },
            {
                "data": "nickname",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandle(data);
                }
            },
            {
                "data": "create_time",
                "class":"text-center",
                "render":function(data, type, row) {
                    return formatDateTime2Date(data);
                }
            }

        ]
    });


    function dataHandle(data){
        if(data != null && data != '' && data != 'undefined'){
            data = data;
        }else{
            data = '';
        }
        return data;
    }


    function select() {
        var beginDate = $("#beginDate").val();
        var _spuid = $("#spuid").val();

        var param = {
            "beginDate":beginDate,
            "spuid":_spuid
        };

        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    }


    function tableFun(tdUrl){
        $.get(tdUrl, function(result){
            var menuJson = JSON.parse(result);
            for (var i = 0; i < menuJson.length; i++) {
                if (menuJson[i].spuid == 'ZY-24020206') {
                    $("#spuid").append("<option value='"+menuJson[i].spuid+"' selected>"+menuJson[i].spu_name+"</option>");
                }else{
                    $("#spuid").append("<option value='"+menuJson[i].spuid+"'>"+menuJson[i].spu_name+"</option>");
                }
            }
        })
    }

    /**
     * 时间戳转时间
     * */
    function formatDateTime2Date(value) {
        if (value == null || value == '') {
            return '';
        }
        var dt;
        if (value instanceof Date) {
            dt = value;
        } else {
            dt = new Date(value);
        }
        return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
    }

    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
        }
        if (/(y+)/.test(format))
            format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(format))
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        return format;
    }


    function export2(){
        var beginDate = $("#beginDate").val();
        window.location.href = "${ctx!}/platform/exportGrouper?beginDate="+beginDate+"";
    }


</script>
</html>