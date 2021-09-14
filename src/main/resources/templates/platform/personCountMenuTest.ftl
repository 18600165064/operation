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
        <h3>累计会员人数统计
        </h3>
    </div>
</div>

<div class="clearfix"></div>
<div class="row">
<div class="x_panel">
<div class="x_title">
    <div class="clearfix">
        <!-- 搜索 -->
        <#--<input type="text" name="strDate" id="strDate" placeholder="整天时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>-->
        <input type="text" name="beginDate" id="beginDate" placeholder="开始时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>
        <input type="text" name="endDate" id="endDate" placeholder="结束时间" class=".col-md-3" style="font-size: 20px !important;" readonly="readonly"/>
        <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
    </div>
</div>
    <div class="x_content">
        <!-- 正文 -->
        <form id="form" class="form-horizontal" method="post" enctype="multipart/form-data">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td class="input-group-addon" width="10%">累计总用户数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num1" name="num1"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计总支付人数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num2" name="num2"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日新增人数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num3" name="num3"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日小程序人数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num4" name="num4"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计总开团数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num5" name="num5"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计成功开团数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num6" name="num6"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">开团成功率</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num7" name="num7"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日新增总开团数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num11" name="num11"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日开团率</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num12" name="num12"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计开团人数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num13" name="num13"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">成功开团人数</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num14" name="num14"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">成功开团人数占比</td>
                    <td>
                        <span style="text-align:center;display:block;" id="num15" name="num15"/>
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
    $(function () {
        // laydate.render({
        //     elem: '#strDate',type: 'date',
        //     value:new Date()
        // });

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

        $.get("${ctx!}/platform/getPersonCountData",function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {
                $("#num1").html(menuJson.data.num1);
                $("#num2").html(menuJson.data.num2);
                $("#num3").html(menuJson.data.num3);
                $("#num4").html(menuJson.data.num20);
                $("#num5").html(menuJson.data.num4);
                $("#num6").html(menuJson.data.num5);
                $("#num7").html(menuJson.data.num17);

                $("#num11").html(menuJson.data.num18);
                $("#num12").html(menuJson.data.num21);
                $("#num13").html(menuJson.data.num9);
                $("#num14").html(menuJson.data.num10);
                $("#num15").html(menuJson.data.num19);
            }else{
                bootbox.alert(menuJson.error);
            }
        });
    });


    function select() {
        var beginDate = $("#beginDate").val();
        var endDate = $("#endDate").val();
        var param = {
            "beginDate":beginDate,
            "endDate":endDate
        };

        $.get("${ctx!}/platform/getPersonCountData",param,function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {
                $("#num1").html(menuJson.data.num1);
                $("#num2").html(menuJson.data.num2);
                $("#num3").html(menuJson.data.num3);
                $("#num4").html(menuJson.data.num20);
                $("#num5").html(menuJson.data.num4);
                $("#num6").html(menuJson.data.num5);
                $("#num7").html(menuJson.data.num17);
                $("#num11").html(menuJson.data.num18);
                $("#num12").html(menuJson.data.num21);
                $("#num13").html(menuJson.data.num9);
                $("#num14").html(menuJson.data.num10);
                $("#num15").html(menuJson.data.num19);
            }else{
                bootbox.alert(menuJson.error);
            }
        });


    }

</script>
</html>