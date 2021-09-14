<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
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
                          <h3>用户信息
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <input type="text" placeholder="用户昵称" name="userName" id="userName" class=".col-md-2">
                                  <input type="text" placeholder="用户手机号" name="phone" id="phone" class=".col-md-2">
                                  <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                              </div>
                          </div>
                          <div class="x_content">
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                  <tr>
                                      <th>用户名称</th>
                                      <th>头像</th>
                                      <th>身份</th>
                                      <th>联系方式</th>
                                      <th>升级时间</th>
                                      <th>升级方式</th>
                                      <th>推荐人</th>
                                      <th>归属总监</th>
                                      <th>归属经理</th>
                                      <th>归属员工</th>
                                      <th>归属主管</th>
                                      <th>归属员工经理</th>
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
    var table = $('#datatable').DataTable({
        "searching": false,
        "ordering": false,
        "processing": true,
        "language": {
            "url": '${ctx!}/assets/js/Chinese.json'
        },
        "serverSide": true,
        "ajax": {
            "url": "${ctx!}/data/getUserMessageData",
            //默认为data,这里定义为空，则只需要传不带属性的数据
            // "dataSrc": ""
        },
        "columns": [
            {"data": "nick_name","render": function (data, type, row, meta) {
            	return dataHandle(data);
            	}
            },
            {
                "data": "head_icon",
                "render": function (data, type, row, meta) {
                    return "<img src="+data+" height=\"30\" width=\"30\" />"
                }
            },
            {"data": "level_u"},
            {"data": "phone"},
            {
                "data": "updatetime", "render": function (data, type, row, meta) {
                    return formatDateTime2Date(data)
                }
            },
            {"data": "source"},
            {"data": "inviter_id"},
            {"data": "cheif_id"},
            {"data": "director_id"},
            {"data": "user_id"},
            {"data": "charge_id"},
            {"data": "staff_director_id"}
        ]
    });

    function select() {
        var userName = $("#userName").val();
        var phone = $("#phone").val();
        var param = {
            "userName": userName,
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
