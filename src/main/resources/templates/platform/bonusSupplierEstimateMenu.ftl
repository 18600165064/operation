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
                        <h3>超级合伙人分红预估
                        </h3>
                    </div>
                </div>

                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <div class="clearfix">
                                <input type="text" name="amount" id="amount" placeholder="新增金额" class=".col-md-3"/>
                                <select class=".col-md-3" id="spuid" name="spuid" style="font-size: 20px;width:280px;">

                                </select>
                                <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                                <button type="button" class="btn btn-primary" onclick="update()">确认新增</button>
                            </div>
                        </div>
                        <div class="x_content">

                            <table id="datatable" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th width="6%">超级合伙人昵称</th>
                                    <th width="6%">分红金额</th>
                                    <th width="7%">增量分红</th>
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
        tableFun();
        tableFun3("${ctx!}/yMComment/getSpuData");
    });

function tableFun(){
    $.ajax({
        url : "${ctx!}/platform/getBonusSupplierEstimateData",
        dataType : "json",
        success : function(data) {
            if(data.draw == 1) {
                var _table = $('#datatable'), tableBodyHtml = '';
                $.each(data.data,function(k, v) {
                        tableBodyHtml += '<tr>';
                        tableBodyHtml += '<td>' + v.userName + '</td>';
                        tableBodyHtml += '<td>' + v.amount3 + '</td>';
                        tableBodyHtml += '<td>' + v.amount4 + '</td>';
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
        var amount = $("#amount").val();
        var _spuid = $("#spuid").val();

        var param = {
            "amount": amount,
            "spuid":_spuid
        };
        $.ajax({
            url : "${ctx!}/platform/getBonusSupplierEstimateData",
            dataType : "json",
            data:param,
            success : function(data) {
                if(data.draw == 1) {
                    var _table = $('#datatable'), tableBodyHtml = '';
                    $.each(data.data,function(k, v) {
                        tableBodyHtml += '<tr>';
                        tableBodyHtml += '<td>' + v.userName + '</td>';
                        tableBodyHtml += '<td>' + v.amount3 + '</td>';
                        tableBodyHtml += '<td>' + v.amount4 + '</td>';
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

    function update() {
        var _spuid = $("#spuid").val();

        bootbox.confirm("确认新增金额?", function(result){
            if(result) {
                var amount = $("#amount").val();
                $.get('${ctx!}/platform/updatePoolAmount?amount='+amount+'&type=2&spuid='+_spuid+'', function(result){
                    var data = JSON.parse(result);
                    if (data.draw == 1) {
                        bootbox.alert(data.error,function(){
                            window.location.reload();
                        });
                    }else{
                        bootbox.alert(data.error);
                    }
                })
            }

        })

    }

</script>
</html>