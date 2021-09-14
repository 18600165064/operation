<#include "../common/header.html">
<meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no" />
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
        <h3>平台统计
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
                    <td class="input-group-addon" width="100%" colspan="6" style="color: red">人数</td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计总用户数</td>
                    <td class="input-group-addon" width="10%">累计总支付人数</td>
                    <td class="input-group-addon" width="10%">当日新增人数</td>
                    <td class="input-group-addon" width="10%">当日小程序人数</td>
                    <td class="input-group-addon" width="10%">累计总开团数</td>
                    <td class="input-group-addon" width="10%">累计成功开团数</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num1" name="num1"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num2" name="num2"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num3" name="num3"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num4" name="num4"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num5" name="num5"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num6" name="num6"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">开团成功率</td>
                    <#--<td class="input-group-addon" width="10%">当日新增3人团数</td>-->
                    <#--<td class="input-group-addon" width="10%">当日新增5人团数</td>-->
                    <#--<td class="input-group-addon" width="10%">当日新增8人团数</td>-->
                    <td class="input-group-addon" width="10%">当日新增总开团数</td>
                    <td class="input-group-addon" width="10%">当日开团率</td>
                    <td class="input-group-addon" width="10%">累计开团人数</td>
                    <td class="input-group-addon" width="10%">成功开团人数</td>
                    <td class="input-group-addon" width="10%">成功开团人数占比</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num7" name="num7"/>
                    </td>
                    <#--<td width="17%" >-->
                        <#--<input type="text"  class="form-control" id="num8" name="num8"/>-->
                    <#--</td>-->
                    <#--<td width="17%" >-->
                        <#--<input type="text"  class="form-control" id="num9" name="num9"/>-->
                    <#--</td>-->
                    <#--<td width="17%" >-->
                        <#--<input type="text"  class="form-control" id="num10" name="num10"/>-->
                    <#--</td>-->
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num11" name="num11"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num12" name="num12"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num13" name="num13"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num14" name="num14"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num15" name="num15"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="100%" colspan="6" style="color: red">单数</td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计总订单数</td>
                    <td class="input-group-addon" width="10%">累计总支付订单数</td>
                    <td class="input-group-addon" width="10%">当日新增订单数</td>
                    <td class="input-group-addon" width="10%">当日下单转化<br/>(当日未关闭订单数/小程序人数)</td>
                    <td class="input-group-addon" width="10%">当日下单增长比<br/>(今日下单数-昨日下单数)/昨日下单数</td>
                    <td class="input-group-addon" width="10%">当日新增支付订单数</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num16" name="num16"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num17" name="num17"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num18" name="num18"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num19" name="num19"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num20" name="num20"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num21" name="num21"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日支付人数</td>
                    <td class="input-group-addon" width="10%">当日支付转化<br/>(当日支付人数/小程序人数)</td>
                    <td class="input-group-addon" width="10%">当日总销量(盒)</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num22" name="num22"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num23" name="num23"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="salesNum" name="salesNum"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="100%" colspan="6" style="color: red">团队累计支付人数</td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">萍萍团队</td>
                    <td class="input-group-addon" width="10%">王献伟团队</td>
                    <td class="input-group-addon" width="10%">王双团队</td>
                    <td class="input-group-addon" width="10%">小琴团队</td>
                    <td class="input-group-addon" width="10%">徐杰铭团队</td>
                    <td class="input-group-addon" width="10%">陕西团队</td>
                </tr>
				
				
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="numCount25" name="numCount25"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="numCount24" name="numCount24"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="numCount27" name="numCount27"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="numCount28" name="numCount28"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="numCount29" name="numCount29"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="numCount30" name="numCount30"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="100%" colspan="6" style="color: red">团队当日新增支付人数</td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">萍萍团队</td>
                    <td class="input-group-addon" width="10%">王献伟团队</td>
                    <td class="input-group-addon" width="10%">王双团队</td>
                    <td class="input-group-addon" width="10%">小琴团队</td>
                    <td class="input-group-addon" width="10%">徐杰铭团队</td>
                    <td class="input-group-addon" width="10%">陕西团队</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num25" name="num25"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num24" name="num24"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num27" name="num27"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num28" name="num28"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="num29" name="num29"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="num30" name="num30"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="100%" colspan="6" style="color: red">分红池统计</td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日合伙人分红池新增金额</td>
                    <td class="input-group-addon" width="10%">当日超级合伙人分红池新增金额</td>
                    <td class="input-group-addon" width="10%">当日风控池新增金额</td>
                    <td class="input-group-addon" width="10%">当日新增合伙人数量</td>
                    <td class="input-group-addon" width="10%">当日新增超级合伙人数量</td>
                    <td class="input-group-addon" width="10%">累计合伙人总数</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="bonus1" name="bonus1"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="bonus2" name="bonus2"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="bonus3" name="bonus3"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="bonus4" name="bonus4"/>
                    </td>
                    <td width="16%" >
                        <input type="text"  class="form-control" id="bonus5" name="bonus5"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="bonus6" name="bonus6"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计超级合伙人总数</td>
                    <td class="input-group-addon" width="10%">当日合伙人平均分红</td>
                    <td class="input-group-addon" width="10%">当日超级合伙人平均分红</td>
                </tr>
                <tr>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="bonus7" name="bonus7"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="bonus8" name="bonus8"/>
                    </td>
                    <td width="17%" >
                        <input type="text"  class="form-control" id="bonus9" name="bonus9"/>
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

        $.get("${ctx!}/platform/getPlatformData",tdData,function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {
                $("#num1").val(menuJson.data.num1);
                $("#num2").val(menuJson.data.num2);
                $("#num3").val(menuJson.data.num3);
                $("#num4").val(menuJson.data.num20);
                $("#num5").val(menuJson.data.num4);
                $("#num6").val(menuJson.data.num5);
                $("#num7").val(menuJson.data.num17);

                $("#num11").val(menuJson.data.num18);
                $("#num12").val(menuJson.data.num21);
                $("#num13").val(menuJson.data.num9);
                $("#num14").val(menuJson.data.num10);
                $("#num15").val(menuJson.data.num19);
                $("#num16").val(menuJson.data.num11);
                $("#num17").val(menuJson.data.num12);
                $("#num18").val(menuJson.data.num13);
                $("#num19").val(menuJson.data.num22);
                $("#num20").val(menuJson.data.num23);
                $("#num21").val(menuJson.data.num14);
                $("#num22").val(menuJson.data.num15);
                $("#num23").val(menuJson.data.num24);
                $("#salesNum").val(menuJson.data.salesNum);
                $("#num24").val(menuJson.data.team11579);
                $("#num25").val(menuJson.data.team11554-menuJson.data.team11579);
                $("#num27").val(menuJson.data.team10157-menuJson.data.team11554-menuJson.data.team10271);
                $("#num28").val(menuJson.data.team10574);
                $("#num29").val(menuJson.data.team10279);
                $("#num30").val(menuJson.data.team10271);
                $("#numCount24").val(menuJson.data.teamAll11579);
                $("#numCount25").val(menuJson.data.teamAll11554-menuJson.data.teamAll11579);
                $("#numCount27").val(menuJson.data.teamAll10157-menuJson.data.teamAll11554-menuJson.data.teamAll10271);
                $("#numCount28").val(menuJson.data.teamAll10574);
                $("#numCount29").val(menuJson.data.teamAll10279);
                $("#numCount30").val(menuJson.data.teamAll10271);

                $("#bonus1").val(menuJson.data.bonus1);
                $("#bonus2").val(menuJson.data.bonus2);
                $("#bonus3").val(menuJson.data.bonus3);
                $("#bonus4").val(menuJson.data.bonus4);
                $("#bonus5").val(menuJson.data.bonus5);
                $("#bonus6").val(menuJson.data.bonus6);
                $("#bonus7").val(menuJson.data.bonus7);
                $("#bonus8").val(menuJson.data.bonus8);
                $("#bonus9").val(menuJson.data.bonus9);
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

        $.get("${ctx!}/platform/getPlatformData",param,function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {
                $("#num1").val(menuJson.data.num1);
                $("#num2").val(menuJson.data.num2);
                $("#num3").val(menuJson.data.num3);
                $("#num4").val(menuJson.data.num20);
                $("#num5").val(menuJson.data.num4);
                $("#num6").val(menuJson.data.num5);
                $("#num7").val(menuJson.data.num17);
                // $("#num8").val(menuJson.data.num6);
                // $("#num9").val(menuJson.data.num7);
                // $("#num10").val(menuJson.data.num8);
                $("#num11").val(menuJson.data.num18);
                $("#num12").val(menuJson.data.num21);
                $("#num13").val(menuJson.data.num9);
                $("#num14").val(menuJson.data.num10);
                $("#num15").val(menuJson.data.num19);
                $("#num16").val(menuJson.data.num11);
                $("#num17").val(menuJson.data.num12);
                $("#num18").val(menuJson.data.num13);
                $("#num19").val(menuJson.data.num22);
                $("#num20").val(menuJson.data.num23);
                $("#num21").val(menuJson.data.num14);
                $("#num22").val(menuJson.data.num15);
                $("#num23").val(menuJson.data.num24);
                $("#salesNum").val(menuJson.data.salesNum);
                $("#num24").val(menuJson.data.team11579);
                $("#num25").val(menuJson.data.team11554-menuJson.data.team11579);
                $("#num27").val(menuJson.data.team10157-menuJson.data.team11554-menuJson.data.team10271);
                $("#num28").val(menuJson.data.team10574);
                $("#num29").val(menuJson.data.team10279);
                $("#num30").val(menuJson.data.team10271);

                $("#numCount24").val(menuJson.data.teamAll11579);
                $("#numCount25").val(menuJson.data.teamAll11554-menuJson.data.teamAll11579);
                $("#numCount27").val(menuJson.data.teamAll10157-menuJson.data.teamAll11554-menuJson.data.teamAll10271);
                $("#numCount28").val(menuJson.data.teamAll10574);
                $("#numCount29").val(menuJson.data.teamAll10279);
                $("#numCount30").val(menuJson.data.teamAll10271);


                $("#bonus1").val(menuJson.data.bonus1);
                $("#bonus2").val(menuJson.data.bonus2);
                $("#bonus3").val(menuJson.data.bonus3);
                $("#bonus4").val(menuJson.data.bonus4);
                $("#bonus5").val(menuJson.data.bonus5);
                $("#bonus6").val(menuJson.data.bonus6);
                $("#bonus7").val(menuJson.data.bonus7);
                $("#bonus8").val(menuJson.data.bonus8);
                $("#bonus9").val(menuJson.data.bonus9);
            }else{
                bootbox.alert(menuJson.error);
            }
        });
    }


</script>
</html>