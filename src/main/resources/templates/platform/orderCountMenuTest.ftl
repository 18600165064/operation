<#include "../common/header.html">
<meta name="viewport" content="width=device-width, initial-scale=0.7, maximum-scale=0.7, minimum-scale=0.7, user-scalable=no" />
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
        <h3>累计订单数统计
        </h3>
    </div>
</div>

<div class="clearfix"></div>
<div class="row">
<div class="x_panel">
<div class="x_title">
    <div class="clearfix">
        <!-- 搜索 -->
        <#--<input type="text" name="strDate" id="strDate" placeholder="开始时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>-->
        <input type="text" name="beginDate" id="beginDate" placeholder="开始时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>
        <input type="text" name="endDate" id="endDate" placeholder="结束时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>
        <select class=".col-md-3" id="spuid" name="spuid" style="font-size: 20px;width:280px;">

        </select>
        <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
    </div>
</div>
    <div class="x_content">
        <!-- 正文 -->
        <form id="form" class="form-horizontal" method="post" enctype="multipart/form-data">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td class="input-group-addon" width="10%">累计总订单数</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num16" name="num16"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计总支付订单数</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num17" name="num17"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日新增订单数</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num18" name="num18"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日下单转化<br/>(当日未关闭订单数/小程序人数)</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num19" name="num19"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日下单增长比<br/>(今日下单数-昨日下单数)/昨日下单数</td>
                    <td width="16%" >
                        <span style="text-align:center;display:block;" id="num20" name="num20"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日新增支付订单数</td>
                    <td width="16%" >
                        <span style="text-align:center;display:block;" id="num21" name="num21"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日支付人数</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num22" name="num22"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日新增人数</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="addPerson" name="addPerson"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日支付转化<br/>(当日支付人数/小程序人数)</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num23" name="num23"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">总销量(盒)</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="salesNum2" name="salesNum2"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日总销量(盒)</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="salesNum" name="salesNum"/>
                    </td>
                </tr>
            </tbody>
        </table>
    <div class="ibox-footer" style="text-align: center">
    <!--操作按钮 开始-->
    <div class="ibox-btns">
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


    $(function () {

        $.get("${ctx!}/platform/getOrderCountData",function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {

                $("#num16").html(menuJson.data.num11);
                $("#num17").html(menuJson.data.num12);
                $("#num18").html(menuJson.data.num13);
                $("#num19").html(menuJson.data.num22);
                $("#num20").html(menuJson.data.num23);
                $("#num21").html(menuJson.data.num14);
                $("#num22").html(menuJson.data.num15);
                $("#addPerson").html(menuJson.data.addPerson);
                $("#num23").html(menuJson.data.num24);
                $("#salesNum2").html(menuJson.data.count2);
                $("#salesNum").html(menuJson.data.count1);

            }else{
                bootbox.alert(menuJson.error);
            }
        });

        tableFun3("${ctx!}/yMComment/getSpuData");

    });


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

    function select() {
        var beginDate = $("#beginDate").val();
        var endDate = $("#endDate").val();
        var _spuid = $("#spuid").val();

        var param = {
            "beginDate":beginDate,
            "endDate":endDate,
            "spuid":_spuid
        };

        $.get("${ctx!}/platform/getOrderCountData",param,function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {

                $("#num16").html(menuJson.data.num11);
                $("#num17").html(menuJson.data.num12);
                $("#num18").html(menuJson.data.num13);
                $("#num19").html(menuJson.data.num22);
                $("#num20").html(menuJson.data.num23);
                $("#num21").html(menuJson.data.num14);
                $("#num22").html(menuJson.data.num15);
                $("#addPerson").html(menuJson.data.addPerson);
                $("#num23").html(menuJson.data.num24);
                $("#salesNum2").html(menuJson.data.count2);
                $("#salesNum").html(menuJson.data.count1);

            }else{
                bootbox.alert(menuJson.error);
            }
        });


    }

</script>
</html>