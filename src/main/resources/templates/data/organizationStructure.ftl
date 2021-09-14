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
                          <h3>组织结构
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_title">
                      </div>
                      <div id="organizationStructure" style="width: 100%;height:10000PX;"></div>
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
	var _hierarchy = 2;
    var _size = 100;
	var _bottom = "1%";
	var _name = "${name!""}";
	var arrays = new Array();
	var arrays2 = new Array();
	var arrays3 = new Array();
    var myChart = echarts.init(document.getElementById('organizationStructure'));
    myChart.showLoading();
    //  $.get('${ctx!}/assets/js/flare.json', function (data) {
    $.get('${ctx!}/data/getOrganizationStructure', function (msg) {
        var data = JSON.parse(msg);
        myChart.hideLoading();
        console.log(data);
        if(_name != "admin"){
        	//优先查询一级目录，没有匹配再正常查询
        	for (var i = 0; i < data.children.length; i++) {
    			if(data.children[i].name == _name){
    				 arrays[0] = data.children[i];
    				 data.children = arrays;
    				 break;
    				}
        	}

        	aaa:
        	for (var i = 0; i < data.children.length; i++) {
    			if(data.children[i].name == _name){
    				 arrays[0] = data.children[i];
    				 data.children = arrays;
    				 break;
    				}
    			bbb:
    			for(var j = 0; j < data.children[i].children.length; j++){
    				for(var y = 0; y< data.children[i].children[j].children.length; y++){
    					if(data.children[i].children[j].children[y].name == _name){
    						arrays[0] = data.children[i];
    						arrays2[0] = data.children[i].children[j].children[y];
    						data.children = arrays;
    						data.children[0].children = arrays2;
        					break aaa;
    					}
    				}
    			}
    		}
        	if(data.children[0].children.length != 1){
                _size = 85;
            }
	        _bot = _size - data.children[0].children.length;
	        _bottom = _bot+"%";
	        data = data.children[0];
	        _hierarchy = 1;
        }else{
        	for (var i = 0; i < data.children.length; i++) {
        		if(data.children[i].children.length > 1){
        			arrays3.push(data.children[i]);
        		}
        	}
//         	document.getElementById("organizationStructure").style.width = data.children.length*4 + "px";
        	data.children = arrays3;
        }
		
        echarts.util.each(data.children, function (datum, index) {
        	index % 2 === 0 && (datum.collapsed = true);
        });

        myChart.setOption(option = {
            tooltip: {
                trigger: 'item',
                triggerOn: 'mousemove'
            },
            series: [
                {
                    type: 'tree',

                    data: [data],

                    top: '1%',
                    left: '7%',
                    bottom: _bottom,
                    right: "20%",

                    symbolSize: 7,

                    label: {
                        normal: {
                            position: 'right',
                            verticalAlign: 'middle',
                            align: 'right',
                            fontSize: 9
                        }
                    },

                    leaves: {
                        label: {
                            normal: {
                                position: 'right',
                                verticalAlign: 'middle',
                                align: 'left'
                            }
                        }
                    },
                    expandAndCollapse: true,
//                     roam: true,    //滚轮缩放
        			initialTreeDepth : _hierarchy,  //展示层级数,默认是2
                    animationDuration: 550,
                    animationDurationUpdate: 750
                }
            ]
        });
    });
    
</script>