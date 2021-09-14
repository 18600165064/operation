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
        <h3>分红池统计
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
                    <td class="input-group-addon" width="10%">合伙人分红池累计金额</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus10" name="bonus10"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">超级合伙人分红池累计金额</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus11" name="bonus11"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">风控池累计金额</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus12" name="bonus12"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日合伙人分红池新增金额</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus1" name="bonus1"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日超级合伙人分红池新增金额</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus2" name="bonus2"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日风控池新增金额</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus3" name="bonus3"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日新增合伙人数量</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus4" name="bonus4"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日新增超级合伙人数量</td>
                    <td width="16%" >
                        <span style="text-align:center;display:block;" id="bonus5" name="bonus5"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计合伙人总数</td>
                    <td width="16%" >
                        <span style="text-align:center;display:block;" id="bonus6" name="bonus6"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">累计超级合伙人总数</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus7" name="bonus7"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日合伙人平均分红</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus8" name="bonus8"/>
                    </td>
                </tr>
                <tr>
                    <td class="input-group-addon" width="10%">当日超级合伙人平均分红</td>
                    <td width="17%" >
                        <span style="text-align:center;display:block;" id="bonus9" name="bonus9"/>
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

        tableFun3("${ctx!}/yMComment/getSpuData");

        $.get("${ctx!}/platform/getBonusCountData",function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {

                $("#bonus1").html(menuJson.data.bonus1);
                $("#bonus2").html(menuJson.data.bonus2);
                $("#bonus3").html(menuJson.data.bonus3);
                $("#bonus4").html(menuJson.data.bonus4);
                $("#bonus5").html(menuJson.data.bonus5);
                $("#bonus6").html(menuJson.data.bonus6);
                $("#bonus7").html(menuJson.data.bonus7);
                $("#bonus8").html(menuJson.data.bonus8);
                $("#bonus9").html(menuJson.data.bonus9);
                $("#bonus10").html(menuJson.data.bonus10);
                $("#bonus11").html(menuJson.data.bonus11);
                $("#bonus12").html(menuJson.data.bonus12);
            }else{
                bootbox.alert(menuJson.error);
            }
        });

    });


    function select() {
        var beginDate = $("#beginDate").val();
        var endDate = $("#endDate").val();
        var _spuid = $("#spuid").val();
        var param = {
            "beginDate":beginDate,
            "endDate":endDate,
            "spuid":_spuid
        };

        $.get("${ctx!}/platform/getBonusCountData",param,function (result) {
            var menuJson = JSON.parse(result);
            if (menuJson.draw == 1) {

                $("#bonus1").html(menuJson.data.bonus1);
                $("#bonus2").html(menuJson.data.bonus2);
                $("#bonus3").html(menuJson.data.bonus3);
                $("#bonus4").html(menuJson.data.bonus4);
                $("#bonus5").html(menuJson.data.bonus5);
                $("#bonus6").html(menuJson.data.bonus6);
                $("#bonus7").html(menuJson.data.bonus7);
                $("#bonus8").html(menuJson.data.bonus8);
                $("#bonus9").html(menuJson.data.bonus9);
                $("#bonus10").html(menuJson.data.bonus10);
                $("#bonus11").html(menuJson.data.bonus11);
                $("#bonus12").html(menuJson.data.bonus12);
            }else{
                bootbox.alert(menuJson.error);
            }
        });
    }


    $("#strDate").click(function(){
        document.activeElement.blur();
    })


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

</script>
</html>