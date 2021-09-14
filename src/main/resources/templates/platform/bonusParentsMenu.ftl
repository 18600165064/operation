<#include "../common/header.html">
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
                                <input type="text" name="strDate" id="strDate" placeholder="开始时间" class=".col-md-3"/>
                                <input type="text" name="parentsId" id="parentsId" placeholder="合伙人ID" class=".col-md-3"/>
                                <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                                <button type="button" id="withdrawApply" style="float:right" class="btn btn-primary" onclick="export2()">导出</button>
                            </div>
                        </div>
                        <div class="x_content">

                            <table id="datatable" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th width="6%">合伙人昵称</th>
                                    <th width="6%">合伙人ID</th>
                                    <th width="7%">累计一级总人数</th>
                                    <th width="7%">累计二级总人数</th>
                                    <th width="7%">当日新增一级人数</th>
                                    <th width="7%">当日新增二级人数</th>
                                    <th width="7%">累计总单数</th>
                                    <th width="7%">当日新增单数</th>
                                    <th width="7%">当日退货单数</th>
                                    <th width="7%">团队累计退货率</th>
                                    <th width="7%">昨日分红</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
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
        var curDate = new Date();
        $('#strDate').datepicker('setDate', curDate);
        tableFun();
    });

function tableFun(){
    $.ajax({
        url : "${ctx!}/platform/getBonusParentsData",
        dataType : "json",
        success : function(data) {
            if(data.draw == 1) {
                var _table = $('#datatable'), tableBodyHtml = '';
                $.each(data.data,function(k, v) {
                        tableBodyHtml += '<tr>';
                        tableBodyHtml += '<td>' + v.userName + '</td>';
                        tableBodyHtml += '<td>' + v.userId + '</td>';
                        tableBodyHtml += '<td>' + v.todayAll + '</td>';
                        tableBodyHtml += '<td>' + v.twoTodayAll + '</td>';
                        tableBodyHtml += '<td>' + v.today + '</td>';
                        tableBodyHtml += '<td>' + v.twoToday + '</td>';
                        tableBodyHtml += '<td>' + v.orderTodayAll + '</td>';
                        tableBodyHtml += '<td>' + v.orderToday + '</td>';
                        tableBodyHtml += '<td>' + v.refund + '</td>';
                        tableBodyHtml += '<td>' + v.refundProportion + '</td>';
                        tableBodyHtml += '<td>' + v.bonusOut + '</td>';
                        tableBodyHtml += '</tr>';
                    });
                _table.find("tbody").html(tableBodyHtml);
            }
            else
                alert(data.msg);
        },
        async : false
    });
}



    function select() {
        var strDate = $("#strDate").val();
        var parentsId = $("#parentsId").val();

        var param = {
            "strDate": strDate,
            "parentsId":parentsId
        };
        $.ajax({
            url : "${ctx!}/platform/getBonusParentsData",
            dataType : "json",
            data:param,
            success : function(data) {
                if(data.draw == 1) {
                    var _table = $('#datatable'), tableBodyHtml = '';
                    $.each(data.data,function(k, v) {
                        tableBodyHtml += '<tr>';
                        tableBodyHtml += '<td>' + v.userName + '</td>';
                        tableBodyHtml += '<td>' + v.userId + '</td>';
                        tableBodyHtml += '<td>' + v.todayAll + '</td>';
                        tableBodyHtml += '<td>' + v.twoTodayAll + '</td>';
                        tableBodyHtml += '<td>' + v.today + '</td>';
                        tableBodyHtml += '<td>' + v.twoToday + '</td>';
                        tableBodyHtml += '<td>' + v.orderTodayAll + '</td>';
                        tableBodyHtml += '<td>' + v.orderToday + '</td>';
                        tableBodyHtml += '<td>' + v.refund + '</td>';
                        tableBodyHtml += '<td>' + v.refundProportion + '</td>';
                        tableBodyHtml += '<td>' + v.bonusOut + '</td>';
                        tableBodyHtml += '</tr>';
                    });
                    _table.find("tbody").html(tableBodyHtml);
                }
                else
                    alert(data.msg);
            },
            async : false
        });
    }




    /**
     * 导出
     */
    function export2(){
        var strDate = $("#strDate").val();
        window.location.href = "${ctx!}/platform/exportPrem?strDate="+strDate+"";
    }
</script>
</html>