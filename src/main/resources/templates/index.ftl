<#include "./common/header.html">
  <body class="nav-md">
  <div class="container body">
      <div class="main_container">
          <div class="col-md-3 left_col">
              <div class="left_col scroll-view">
                  <div class="navbar nav_title" style="border: 0;">
                      <div class="site_title"><i class="fa fa-paw"></i><span>拼团赚赚</span></div>
                  </div>
                  <div class="clearfix"></div>
                  <!-- sidebar menu -->
           <#include "./common/left.ftl">
                  <!-- /sidebar menu -->

              </div>
          </div>

    <#include "./common/nav.ftl">

          <!-- page content -->
          <div class="right_col" role="main">
              <div class="row top_tiles">
                  <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                      <div style="background:#169F85; color:#FFF" class="tile-stats">
                          <div class="count"><span onclick="personCount()">累计人数统计</span></div>
                      </div>
                  </div>
              </div>
              <div class="row top_tiles">
                  <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                      <div style="background:#f0ad4e; color:#FFF" class="tile-stats">
                          <div class="count"><span onclick="orderCount()">累计订单统计</span></div>
                      </div>
                  </div>
              </div>
              <div class="row top_tiles">
                  <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                      <div style="background:#d9534f; color:#FFF" class="tile-stats">
                          <div class="count"><span onclick="bonusPersonCount()">合伙人业绩统计</span></div>
                      </div>
                  </div>
              </div>
              <div class="row top_tiles">
                  <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                      <div style="background:#d9534f; color:#FFF" class="tile-stats">
                          <div class="count"><span onclick="bonusSupplerCount()">超级合伙人业绩统计</span></div>
                      </div>
                  </div>
              </div>
              <div class="row top_tiles">
                  <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                      <div style="background:#985f0d; color:#FFF" class="tile-stats">
                          <div class="count"><span onclick="bonusCount()">分红池统计</span></div>
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

    function bonusCount() {
        window.location.href = "${ctx!}/platform/bonusCountTest";
    }

    function bonusPersonCount() {
        window.location.href = "${ctx!}/platform/bonusPersonCountTest";
    }


    function orderCount() {
        window.location.href = "${ctx!}/platform/orderCountTest";
    }


    function personCount() {
        window.location.href = "${ctx!}/platform/personCountTest";
    }

    function bonusSupplerCount() {
        window.location.href = "${ctx!}/platform/bonusSupplerTest";
    }

</script>




</html>
