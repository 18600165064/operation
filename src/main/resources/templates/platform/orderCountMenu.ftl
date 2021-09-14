<#include "../common/header.html">
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
        <input type="text" name="strDate" id="strDate" placeholder="开始时间" class=".col-md-3"/>
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
                    <td class="input-group-addon" width="10%">累计总支付订单数</td>
                    <td class="input-group-addon" width="10%">当日新增订单数</td>
                    <td class="input-group-addon" width="10%">当日下单转化<br/>(当日未关闭订单数/小程序人数)</td>
                    <td class="input-group-addon" width="10%">当日下单增长比<br/>(今日下单数-昨日下单数)/昨日下单数</td>
                    <td class="input-group-addon" width="10%">当日新增支付订单数</td>
                    <td class="input-group-addon" width="10%">当日支付人数</td>
                    <td class="input-group-addon" width="10%">当日支付转化<br/>(当日支付人数/小程序人数)</td>
                    <td class="input-group-addon" width="10%">总销量(盒)</td>
                    <td class="input-group-addon" width="10%">当日总销量(盒)</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num16" name="num16"/>
                    </td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num17" name="num17"/>
                    </td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num18" name="num18"/>
                    </td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num19" name="num19"/>
                    </td>
                    <td width="16%" >
                        <span style="text-align:center;display:block;" id="num20" name="num20"/>
                    </td>
                    <td width="16%" >
                        <span style="text-align:center;display:block;" id="num21" name="num21"/>
                    </td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num22" name="num22"/>
                    </td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="num23" name="num23"/>
                    </td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="salesNum2" name="salesNum2"/>
                    </td>
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
    var curDate = new Date();
    var preDate = new Date(curDate.getTime() - 24*60*60*1000);
    $('#strDate').datepicker('setDate', curDate);
    var tdData;
    $(function () {
        var _str = $("#strDate").val();
            tdData = {"strDate":_str};

        $.get("${ctx!}/platform/getOrderCountData",tdData,function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {

                $("#num16").html(menuJson.data.num11);
                $("#num17").html(menuJson.data.num12);
                $("#num18").html(menuJson.data.num13);
                $("#num19").html(menuJson.data.num22);
                $("#num20").html(menuJson.data.num23);
                $("#num21").html(menuJson.data.num14);
                $("#num22").html(menuJson.data.num15);
                $("#num23").html(menuJson.data.num24);
                $("#salesNum2").html(menuJson.data.count2);
                $("#salesNum").html(menuJson.data.count1);

            }else{
                bootbox.alert(menuJson.error);
            }
        });
    });


    function select() {
        var strDate = $("#strDate").val();
        var param = {
            "strDate": strDate
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