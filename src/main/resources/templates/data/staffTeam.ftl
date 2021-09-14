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
                          <h3>员工团队信息
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="员工昵称" name="staffName" id="staffName" class=".col-md-2">
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
                                      <th>员工昵称</th>
                                      <th>直接店主数</th>
                                      <th>间接店主数</th>
                                      <th>直接总监数</th>
                                      <th>间接总监数</th>
                                      <th>直接经理数</th>
                                      <th>间接经理数</th>
                                      <th>直接卡位总监</th>
                                      <th>间接卡位总监</th>
                                      <th>直接卡位总经理</th>
                                      <th>间接卡位总经理</th>
<!--                                       <th>时间</th> -->
                                  </tr>
                                  </thead>
                                  <tfoot>
                                  <tr>
                                      <th>总计</th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                      <th></th>
                                  </tr>
                                  </tfoot>
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
    $('#strDate').datepicker();
    $('#endDate').datepicker();
    // 默认禁用搜索和排序
    var table = $('#datatable').DataTable({
        "searching": false,
        "ordering": true,
        "processing": true,
        "language": {
            "url": '${ctx!}/assets/js/Chinese.json'
        },
        "serverSide": true,
        "ajax": {
            "url": "${ctx!}/data/getStaffTeamData",
            //默认为data,这里定义为空，则只需要传不带属性的数据
            "dataSrc": function (data) {
                $( table.column( 1 ).footer()).html(data.tableData.direct_shopman); 
                $( table.column( 2 ).footer()).html(data.tableData.indirect_shopman); 
                $( table.column( 3 ).footer()).html(data.tableData.direct_cheif); 
                $( table.column( 4 ).footer()).html(data.tableData.indirect_cheif); 
                $( table.column( 5 ).footer()).html(data.tableData.direct_director); 
                $( table.column( 6 ).footer()).html(data.tableData.indirect_director); 
                $( table.column( 7 ).footer()).html(data.tableData.direct_card_cheif); 
                $( table.column( 8 ).footer()).html(data.tableData.indeirct_card_cheif); 
                $( table.column( 9 ).footer()).html(data.tableData.direct_card_director); 
                $( table.column( 10 ).footer()).html(data.tableData.indeirct_card_director); 
                return data.tableData.tabledata;
            }
        },
        "columns": [
            {"data": "nick_name"},
            {"data": "direct_shopman"},
            {"data": "indirect_shopman"},
            {"data": "direct_cheif"},
            {"data": "indirect_cheif"},
            {"data": "direct_director"},
            {"data": "indirect_director"},
            {"data": "direct_card_cheif"},
            {"data": "indeirct_card_cheif"},
            {"data": "direct_card_director"},
            {"data": "indeirct_card_director"}
//             {
//                 "data": "date", "render": function (data, type, row, meta) {
//                     return formatDateTime2Date(data)
//                 }
//             }
        ],
        "footerCallback": function ( row, data, start, end, display ) {

        }
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
</script>
</html>
