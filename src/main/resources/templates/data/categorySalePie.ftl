<#include "../common/header.html">
  <script src="${ctx!}/assets/js/echarts.min.js"></script>
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
                          <h3>一级类目销售环形图
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_title">
                          <div class="clearfix">
                              <div class="col-md-3 col-sm-3 col-xs-3">
                                  <select class="form-control" id="month">
                                  </select>
                              </div>
                              <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                          </div>
                      </div>
                      <div id="categorySalePie" style="width: 100%;height:700px"></div>
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
    var value = new Date().toLocaleDateString();
    var date = formatDateTime2Date(value);
    var strDate = +echarts.number.parseDate('2018-06-16');
    var localDate = +echarts.number.parseDate(date);
    var dayTime = 3600 * 24 * 1000;
    var data = []
    for (var time = strDate; time < localDate; time += dayTime) {
        var times = echarts.format.formatTime('yyyy-MM', time);
        data.push(times);
    }

    //排重
//     function array(arr) {
//         var new_arr = [];
//         for (var i = 0; i < arr.length; i++) {
//             var items = arr[i];
//             //判断元素是否存在于new_arr中，如果不存在则插入到new_arr的最后
//             if ($.inArray(items, new_arr) == -1) {
//                 new_arr.push(items);
//             }
//         }
//         return new_arr;
//     }
    
    function array(){
    	 var dataArr = [];
    	    var data = new Date();
    	    var year = data.getFullYear();
    	    data.setMonth(data.getMonth() + 1, 1); //获取到当前月份,设置月份
    	    for (var i = 0; i < 12; i++) {
    	      data.setMonth(data.getMonth() - 1); //每次循环一次 月份值减1
    	      var m = data.getMonth() + 1;
    	      m = m < 10 ? "0" + m : m;
    	      dataArr.push(data.getFullYear() + "-" + m);
    	    }
    	     console.log(dataArr);
    	   return dataArr;
    }
    
    
    var array = array();
    var month = "";
    var selected = echarts.format.formatTime('yyyy-MM', localDate);
    for (x in array) {
        if (selected == array[x]) {
            month += "<option value=\"" + array[x] + "\" selected>" + array[x] + "月</option>";
        } else {
            month += "<option value=\"" + array[x] + "\">" + array[x] + "月</option>";
        }
    }
    $("#month").append(month);


    var myChart = echarts.init(document.getElementById('categorySalePie'));
    var pieRadius = [55, 15];

    function getVirtulData(starDate, endDate) {
        var date = +echarts.number.parseDate(starDate);
        var end = +echarts.number.parseDate(endDate);
        var dayTime = 3600 * 24 * 1000;
        var data = [];
        for (var time = date; time <= end; time += dayTime) {
            data.push([
                echarts.format.formatTime('yyyy-MM-dd', time)
                , Math.floor(Math.random() * 10000)
            ]);
        }
        return data;
    }

    function getPieSeries(data, chart) {
        return echarts.util.map(data, function (item, index) {
            var center = chart.convertToPixel('calendar', item);
            return {
                id: index + 'pie',
                type: 'pie',
                center: center,
                label: {
                    normal: {
                        formatter: '{c}',
                        position: 'inside'
                    }
                },
                radius: pieRadius,
                data: item[1]
            };
        });
    }

    //  myChart.setOption({ series: getPieSeries(scatterData, myChart)});

    // 异步加载数据
    $.get('${ctx!}/data/getCategorySalePie?selected=' + selected).done(function (data) {
        var json = JSON.parse(data);
        var da = getVirtulData(json.starDate, json.endDate);
        myChart.setOption({
            tooltip: {
		        trigger: 'item',
		        formatter: "{c}个"
            },
            legend: {
                data: ['美妆个护', '家居家纺', '服装服饰', "3C电器", "鞋帽箱包", "珠宝饰品", "母婴玩具", "食品酒水", "其它"],
                bottom: 'auto'
            },
            calendar: {
                left: 'center',
                orient: 'vertical',
                cellSize: 'auto',
                yearLabel: {
                    show: false,
                    textStyle: {
                        fontSize: 30
                    }
                },
                dayLabel: {
                    margin: 20,
                    firstDay: 1,
                    nameMap: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
                },
                monthLabel: {
                    show: false
                },
                range: [selected]
            },
            series: [{
                id: 'label',
                type: 'scatter',
                coordinateSystem: 'calendar',
                symbolSize: 1,
                label: {
                    normal: {
                        show: true,
                        formatter: function (params) {
                            return echarts.format.formatTime('dd', params.value[0]);
                        },
                        //offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
                        textStyle: {
                            color: '#000',
                            fontSize: 14
                        }
                    }
                },
                data: da
            }]

        });
        myChart.setOption({series: getPieSeries(JSON.parse(json.data), myChart)});
    });

    function select() {
        var selec = $("#month").val();
        $.get('${ctx!}/data/getCategorySalePie?selected=' + selec).done(function (data) {
            var json = JSON.parse(data);
            var da = getVirtulData(json.starDate, json.endDate);
            myChart.setOption({
                tooltip: {
                	trigger: 'item',
    		        formatter: "{c}个"
                },
                legend: {
                    data: ['美妆个护', '家居家纺', '服装服饰', "3C电器", "鞋帽箱包", "珠宝饰品", "母婴玩具", "食品酒水", "其它"],
                    bottom: 'auto'
                },
                calendar: {
                    left: 'center',
                    orient: 'vertical',
                    cellSize: 'auto',
                    yearLabel: {
                        show: false,
                        textStyle: {
                            fontSize: 30
                        }
                    },
                    dayLabel: {
                        margin: 20,
                        firstDay: 1,
                        nameMap: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
                    },
                    monthLabel: {
                        show: false
                    },
                    range: [selec]
                },
                series: [{
                    id: 'label',
                    type: 'scatter',
                    coordinateSystem: 'calendar',
                    symbolSize: 1,
                    label: {
                        normal: {
                            show: true,
                            formatter: function (params) {
                                return echarts.format.formatTime('dd', params.value[0]);
                            },
                            //offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
                            textStyle: {
                                color: '#000',
                                fontSize: 14
                            }
                        }
                    },
                    data: da
                }]

            });
            myChart.setOption({series: getPieSeries(JSON.parse(json.data), myChart)});
        });
    }
</script>