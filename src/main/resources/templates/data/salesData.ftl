<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
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
                          <h3>销售数据
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="员工昵称" name="staffName" id="staffName"
                                         class=".col-md-2">
                                   <input type="text" placeholder="手机号" name="phone" id="phone" class=".col-md-2">
                                  <input type="text" name="strDate" id="strDate" placeholder="开始时间" class=".col-md-3"/>
                                  <input type="text" name="endDate" id="endDate" placeholder="结束时间" class=".col-md-3"/>
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>id</th>
                                      <th>员工昵称</th>
                                      <th>预估收入</th>
<!--                                       <th>可提现收入</th> -->
                                      <th>直接礼包佣金</th>
                                      <th>间接礼包佣金</th>
                                      <th>管理佣金收入</th>
                                      <th>商品销售额</th>
                                      <th>礼包销售额</th>
                                      <th>日期</th>
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
  </div>
  </div>
  </body>
    <script src="${ctx!}/assets/js/custom.js"></script>
<script>
    $('#strDate').datepicker('setDate',new Date());
    $('#endDate').datepicker('setDate',new Date());
    var table = $('#datatable').DataTable({
    	"bProcessing":true,
    	"searching": false,
        "ordering": false,
        "language": {
            "url": '${ctx!}/assets/js/Chinese.json'
        },
        "serverSide": true,
        "ajax": {
				"url": "${ctx!}/data/getCommissionsEarnedData",
            //默认为data,这里定义为空，则只需要传不带属性的数据
            // "dataSrc": ""
        },
        "columns": [
            {"data":"userId","visible": false},
            {"data": "nickName","render": function (data, type, row, meta) {
            	return dataHandle(data);
            }
            },
            {
                "data": "estimateIncome", "render": function (data, type, row, meta) {
                    return data+row.directGiftIncome+row.indirectGiftIncome+row.manageIncome*0.2+row.boleIncome+row.recommendIncome;
                }
            },
//             {"data": "cashIncome"},
            {"data": "directGiftIncome"},
            {"data": "indirectGiftIncome"},
            {
                "data": "manageIncome", "render": function (data, type, row, meta) {
                    return data * 0.2;
                }
            },
            {"data": "commodityAmount"},
            {"data": "giftPackageAmount"},
            {
                "data": "createTime", "render": function (data, type, row, meta) {
                    return formatDateTime2Date(data)
                }
            }
        ]
    });

    function select() {
        var strDate = $("#strDate").val();
        var endDate = $("#endDate").val();
        var staffName = $("#staffName").val();
        var phone = $("#phone").val();
        var param = {
            "strDate": strDate,
            "endDate": endDate,
            "staffName": staffName,
            "phone":phone
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
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
        return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
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
    
    
    function dataHandle(data){
    	if(data != null && data != '' && data != 'undefined'){
    		data = data;
    	}else{
    		data = '';
    	}
    	return data;
    }
    
</script>
</html>
