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
                        <h3>合伙人业绩统计
                        </h3>
                    </div>
                </div>

                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <div class="clearfix">
                                <#--<input type="text" name="strDate" id="strDate" placeholder="开始时间" class=".col-md-3" style="font-size: 15px !important;" readonly="readonly"/>-->
                                    <input type="text" name="beginDate" id="beginDate" placeholder="开始时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>
                                    <input type="text" name="endDate" id="endDate" placeholder="结束时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>
                                    <input type="text" name="parentsId" id="parentsId" placeholder="合伙人ID" class=".col-md-3" style="font-size: 20px;" />
                                    <select class=".col-md-3" id="spuid" name="spuid" style="font-size: 20px;width:280px;">

                                    </select>
                                    <button type="button" class="btn btn-primary" style="float:right" onclick="select()">搜索</button>
                            </div>
                        </div>
                        <div class="x_content">

                            <!-- 模态框（Modal） -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" id="myModalLabel">
                                                新增会员
                                            </h4>
                                        </div>
                                        <div class="modal-body">
                                            <table id="datatable2" class="table table-striped table-bordered">
                                                <thead>
                                                <tr>
                                                    <th style="width:20%;">ID</th>
                                                    <th style="width:30%;">昵称</th>
                                                    <th style="width:10%;">手机号</th>
                                                    <th style="width:10%;">头像</th>
                                                    <th style="width:30%;">时间</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal -->
                            </div>


                            <table id="datatable" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>合伙人ID</th>
                                    <th>超级合伙人ID</th>
                                    <th>超级合伙人名称</th>
                                    <th>累计一级人数</th>
                                    <th>累计二级人数</th>
                                    <th>累计三级之后人数</th>
                                    <th>当日新增一级人数</th>
                                    <th>当日新增二级人数</th>
                                    <th>累计总单数</th>
                                    <th>当日新增单数</th>
                                    <th>当日退货单数</th>
                                    <th>团队累计退货率</th>
                                    <th>昨日分红</th>
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
    var _userid = "${userid!""}";
    if (_userid != '' && _userid != null && _userid != 'undefind') {
        $("#parentsId").val(_userid);
        $("#parentsId").attr("disabled","disabled");
    }
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $(function () {
        var now = new Date()
        var yy = now.getFullYear();      //年
        var mm = now.getMonth() + 1;     //月
        var dd = now.getDate();          //日

        var date = yy+"-"+mm+"-"+dd+' 00:00:00';
        date = date.substring(0,19);
        date = date.replace(/-/g,'/');
        var timestamp = new Date(date).getTime();

        var date2 = yy+"-"+mm+"-"+dd+' 23:59:59';
        date2 = date2.substring(0,19);
        date2 = date2.replace(/-/g,'/');
        var timestamp2 = new Date(date2).getTime();



        laydate.render({
            elem: '#beginDate',type: 'datetime',
            value:new Date(timestamp),
            btns: ['now', 'confirm']
        });
        laydate.render({
            elem: '#endDate',type: 'datetime',
            value:new Date(timestamp2),
            btns: ['now', 'confirm']
        });

        tableFun3("${ctx!}/yMComment/getSpuData");

    });

    var table = $('#datatable').DataTable({
        "searching": false,
        "ordering": false,
        "processing": true,
        'iDisplayLength': 20,
        "language": {
            "url": '${ctx!}/assets/js/Chinese.json'
        },
        "serverSide": true,
        "ajax": {
            "url": "${ctx!}/platform/getBonusParentsData",
            "data":{
                "parentsId":_userid
            }
            //默认为data,这里定义为空，则只需要传不带属性的数据
        },
        "columns": [
            {
                "data": "user_name",
                "class":"text-center"
            },
            {
                "data": "user_id",
                "class":"text-center"
            },
            {
                "data": "stockholderId",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandle(data);
                }
            },
            {
                "data": "stockholderName",
                "class":"text-center",
                "render":function(data, type, row) {
                    return dataHandle(data);
                }
            },
            {
                "data": "one_count",
                "class":"text-center"
            },
            {
                "data": "second_count",
                "class":"text-center"
            },
            {
                "data": "three_user_count",
                "class":"text-center"
            },
            {
                "data": "one_user_count",
                "class":"text-center",
                "createdCell": function (td) {
                    $(td).css('color', 'red')
                },
                "render":function(data, type, row) {
                    return '<button class="btn btn-danger btn-xs ml-5 js-delete" data-id="'+data+'" onclick="a('+row.user_id+')">'+data+'</button>';
                }
            },
            {
                "data": "second_user_count",
                "class":"text-center"
            },
            {
                "data": "order_count",
                "class":"text-center"
            },
            {
                "data": "today_order_count",
                "class":"text-center"
            },
            {
                "data": "refund_order_count",
                "class":"text-center"
            },
            {
                "data": "refund_probability",
                "class":"text-center"
            },
            {
                "data": "yesterday_bonus",
                "class":"text-center"
            }
        ]
    });


    function a(userid){
        var beginDate = $("#beginDate").val();
        var endDate = $("#endDate").val();
        var _spuid = $("#spuid").val();

        $('#myModal').modal('show');

        $('#datatable2').DataTable({
            "searching": false,
            "ordering": false,
            "processing": true,
            'iDisplayLength': 20,
            "paging": false,
            "destroy": true,
            "language": {
                "url": '${ctx!}/assets/js/Chinese.json'
            },
            "serverSide": true,
            "ajax": {
                "url": "${ctx!}/platform/getNewPersionData",
                "data":{
                    "parentsId":userid,
                    "beginDate":beginDate,
                    "endDate":endDate,
                    "spuid":_spuid
                }
                //默认为data,这里定义为空，则只需要传不带属性的数据
            },
            "columns": [
                {
                    "data": "user_id",
                    "class":"text-center"
                },
                {
                    "data": "nick_name",
                    "class":"text-center",
                    "render":function(data, type, row) {
                        return dataHandle(data);
                    }
                },
                {
                    "data": "phone",
                    "class":"text-center",
                    "render":function(data, type, row) {
                        return dataHandle(data);
                    }
                },
                {
                    "data": "head_icon",
                    "class":"text-center",
                    "render":function(data, type, row) {
                        return updateImg(data);
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

    }

    function dataHandle(data){
        if(data != null && data != '' && data != 'undefined'){
            data = data;
        }else{
            data = '';
        }
        return data;
    }

    function updateImg(data){
        if(data != null && data != "" && data.length > 0){
            var url = data.substr(0,7);
            if(url != null && url != '' && url == '/upload'){
                var realUrl = "http://img.yuemee.com"+data;
                return "<img src="+realUrl+" height=\"30\" width=\"30\" />";
            }else{
                return "<img src="+data+" height=\"30\" width=\"30\" />";
            }
        }else{
            return "";
        }
    }

    function select() {
        var beginDate = $("#beginDate").val();
        var endDate = $("#endDate").val();
        var parentsId = $("#parentsId").val();
        var _spuid = $("#spuid").val();

        var param = {
            "beginDate":beginDate,
            "endDate":endDate,
            "parentsId":parentsId,
            "spuid":_spuid
        };

        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    }


    function tableFun3(tdUrl){
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

</script>
</html>