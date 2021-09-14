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
                          <h3>其它用户维度
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
                              <input type="text" name="sameDate" id="sameDate" placeholder="一天时间" class=".col-md-3"/>
                              <button type="button" class="btn btn-primary" onclick="sameDay()">当日</button>
                          </div>
                      </div>
                      <div id="otherUserDimension" style="width: 100%;height:700px"></div>
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
    $('#sameDate').datepicker('setDate', new Date());
    var myChart = echarts.init(document.getElementById('otherUserDimension'));
    myChart.setOption({
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['晋升店主','激活店主','代开店主','晋升总监','晋升经理','卡位总监','卡位经理']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ]
    });

    function select() {
        var strDate1 = $("#strDate").val();
        var endDate1 = $("#endDate").val();
        $.get('${ctx!}/data/getOtherUserDimension?strDate=' + strDate1 + '&endDate=' + endDate1).done(function (data) {
            // 填入数据
            myChart.setOption({
                xAxis: {
                    data: data.times
                },
                series: [
                    {
                        name:'晋升店主',
                        type:'bar',
                        data:data.one
                    },
                    {
                        name:'激活店主',
                        type:'bar',
                        data:data.two
                    },
                    {
                        name:'代开店主',
                        type:'bar',
                        data:data.three
                    },
                    {
                        name:'晋升总监',
                        type:'bar',
                        data:data.four
                    },
                    {
                        name:'晋升经理',
                        type:'bar',
                        data:data.five
                    },
                    {
                        name:'卡位总监',
                        type:'bar',
                        data:data.six
                    },
                    {
                        name:'卡位经理',
                        type:'bar',
                        data:data.seven
                    }
                ]
            });
        });
    }
    
    
    function sameDay() {
    	$('#strDate').datepicker('setDate', new Date());
        $('#endDate').datepicker('setDate', new Date());
        var sameDate = $("#sameDate").val();
        var endDate = new Date(sameDate);
        var endDate1 = formatDateTime2Date(endDate);
        $.get('${ctx!}/data/getOtherUserDimension?strDate=' + endDate1 + '&endDate=' + sameDate).done(function (data) {
            // 填入数据
            myChart.setOption({
                xAxis: {
                    data: data.times
                },
                series: [
                    {
                        name:'晋升店主',
                        type:'bar',
                        data:data.one
                    },
                    {
                        name:'激活店主',
                        type:'bar',
                        data:data.two
                    },
                    {
                        name:'代开店主',
                        type:'bar',
                        data:data.three
                    },
                    {
                        name:'晋升总监',
                        type:'bar',
                        data:data.four
                    },
                    {
                        name:'晋升经理',
                        type:'bar',
                        data:data.five
                    },
                    {
                        name:'卡位总监',
                        type:'bar',
                        data:data.six
                    },
                    {
                        name:'卡位经理',
                        type:'bar',
                        data:data.seven
                    }
                ]
            });
        });
    }
    
    /**
     * 时间戳转时间
     * */
    function formatDateTime2Date(value) {
        if (value == null || value == '') {
            return '';
        }
        var dt;
        value.setDate(value.getDate()-1);
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