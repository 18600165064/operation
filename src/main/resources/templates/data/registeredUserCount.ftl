<#include "../common/header.html">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
  <script src="${ctx!}/assets/js/echarts.min.js"></script>
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
                          <h3>注册用户数量
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_title">
                          <div class="clearfix">
                              <input type="text" name="strDate" id="strDate" placeholder="开始时间" class=".col-md-3"/>
                              <input type="text" name="endDate" id="endDate" placeholder="结束时间" class=".col-md-3"/>
                              <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                          </div>
                      </div>
                      <div id="userActive" style="width: 100%;height:700px"></div>
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

</html>
<script>
    $('#strDate').datepicker('setDate', new Date());
    $('#endDate').datepicker('setDate', new Date());
    var myChart = echarts.init(document.getElementById('userActive'));
    myChart.setOption({
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            type: 'line'
        }]
    });
    // 异步加载数据
    var strDate = $("#strDate").val();
    var endDate = $("#endDate").val();
    $.get('${ctx!}/data/getRegisteredUserCount?strDate=' + strDate + '&endDate=' + endDate).done(function (data) {
        // 填入数据
        myChart.setOption({
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                data: data.times
            },
            series: [{
                data: data.counts
            }]
        });
    });

    function select() {
        var strDate1 = $("#strDate").val();
        var endDate1 = $("#endDate").val();
        $.get('${ctx!}/data/getRegisteredUserCount?strDate=' + strDate1 + '&endDate=' + endDate1).done(function (data) {
            // 填入数据
            myChart.setOption({
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    data: data.times
                },
                series: [{
                    data: data.counts
                }]
            });
        });
    }
</script>