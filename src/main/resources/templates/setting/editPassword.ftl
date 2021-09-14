<#include "../common/header.html">

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
              <div class="row">
                  <div class="col-md-12 col-sm-12 col-xs-12">
                      <div class="x_panel">
                          <div class="x_title">
                              <h2>修改密码</h2>
                              <ul class="nav navbar-right panel_toolbox"></ul>
                              <div class="clearfix"></div>
                          </div>
                          <div class="x_content">
                              <br>
                              <form id="demo-form2"   action="/saveNewPassword" class="form-horizontal form-label-left"  method="post" onsubmit="return saveNewPassword()">
                                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                  <div class="form-group">
                                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">旧密码<span
                                              class="required">*</span>
                                      </label>
                                      <div class="col-md-6 col-sm-6 col-xs-12">
                                          <input type="password" id="oldPassword" name="oldPassword" required="required"
                                                 class="form-control col-md-7 col-xs-12">
                                      </div><span style="color:red">${error}</span>
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">新密码<span
                                              class="required">*</span>
                                      </label>
                                      <div class="col-md-6 col-sm-6 col-xs-12">
                                          <input type="password" id="newPassword" name="newPassword" required="required"
                                                 class="form-control col-md-7 col-xs-12">
                                        </div>
                                  </div>
                                   <div class="form-group">
                                      <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                             for="last-name">确认新密码<span class="required">*</span>
                                      </label>
                                      <div class="col-md-6 col-sm-6 col-xs-12">
                                          <input type="password" id="confirmNewPassword"
                                                 required="required" class="form-control col-md-7 col-xs-12">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                          <button type="submit" class="btn btn-success" >提交</button>
                                          <button class="btn btn-primary" type="reset">重置</button>
                                      </div>
                                  </div>
                              </form>
                          </div>
                      </div>
                  </div>

              </div>
          </div>
      </div>
      <footer>
          <div class="pull-right">
          </div>
          <div class="clearfix"></div>
      </footer>
  </div>
  </div>
  </body>
    <script src="${ctx!}/assets/js/custom.js"></script>
<script>
    function saveNewPassword() {
        var newPassword = $("#newPassword").val();
        var confirmNewPassword = $("#confirmNewPassword").val();
        if (newPassword!=confirmNewPassword) {
            alert("新密码不一致");
            return false;
        }
        $("#demo-form2").submit();
    }
</script>
</html>
