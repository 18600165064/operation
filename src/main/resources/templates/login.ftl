<#include "./common/header.html">
  <body class="login">
  <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
          <div class="animate form login_form">
              <section class="login_content">
                  <form id="form" action="${ctx!}/api/login" method="post">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                      <h1>拼 团 赚 赚 登 录</h1>
                      <div>
                          <input type="text" class="form-control" name="username" placeholder="用户名"
                                 required="required"/>
                      </div>
                      <div>
                          <input type="password" class="form-control" name="password" placeholder="密码"
                                 required="required"/>
                      </div>
                      <div>
                          <a class="btn btn-default submit" onclick="submit()">登录</a>
                          <a class="reset_pass" href="#">忘记密码?</a>
                      </div>
                      <div class="clearfix">
                          <#if Session["error"]?exists>
                              ${Session["error"]}
                          </#if>
                      </div>
                  </form>
              </section>
          </div>
      </div>
  </div>
  </body>
<script>
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            $('.submit').triggerHandler('click');
        }
    });
    $(".reset_pass").on("click",function () {
        alert("请联系管理员")
    });
    function submit() {
        $("#form").submit();
    }
</script>
</html>
