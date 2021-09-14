<#include "../common/header.html">
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>
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
                          <h3>菜单管理
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                              
                              </div>
                          </div>
                          <div class="x_content">

								<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
					                <div class="menu_section">
					                    <ul class="nav menu">
					                    	
					                    </ul>
					                </div>
					            </div>
					            
					            <input class="btn btn-primary" type="button" id="save" onclick="saveChk()" value="保存"/>
					            <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>

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
var _roleId = "${roleId!""}";

	$(function () {
		var srhData = {};
		tableFun("${ctx!}/user/getMenuRoleAllData?roleId="+_roleId+"");
	 });


	function tableFun(tdUrl) {
		 $.get(tdUrl, function(result){ 
			var menuJson = JSON.parse(result);
			 if (menuJson.length > 0) {
	               var html = "";

	               $.each(menuJson, function (idx, item) {
	                   if (item.id == item.menu_parent && item.menu_level == 1) {
	                	   if(item.isChecked == true){
		                       html += "<li><a href=\"javascript:void(0);\"><input type=\"checkbox\" checked=\"checked\" id="+item.id+999+" name=\"menu\" value="+item.id+" onclick=\"allselect("+item.id+")\"><i class=\"fa "+item.icon+"\"></i><span onclick=\"showUp("+item.id+")\">" + item.menu_name + "</span></a>";
	                	   }else{
	                      	   html += "<li><a href=\"javascript:void(0);\"><input type=\"checkbox\" name=\"menu\" id="+item.id+999+" value="+item.id+" onclick=\"allselect("+item.id+")\"><i class=\"fa "+item.icon+"\"></i><span onclick=\"showUp("+item.id+")\">" + item.menu_name + "</span></a>";
	                	   }
	                       html += "<ul class=\"hidden\" id="+item.id+">";
	                   }
		                   $.each(menuJson, function (idx, itema) {
		                       if (item.id == itema.menu_parent && itema.menu_level == 2) {
		                    	   if(itema.isChecked == true){
		                           		html += "<li><a><input type=\"checkbox\" class="+itema.menu_parent+" checked=\"checked\" name=\"menu\" value="+itema.id+" onclick=\"fatherselect("+itema.menu_parent+")\"><i class=\"fa "+itema.icon+"\"></i>" + itema.menu_name + "</a></li>";
		                    	   }else{
		                           		html += "<li><a><input type=\"checkbox\" class="+itema.menu_parent+" name=\"menu\" value="+itema.id+" onclick=\"fatherselect("+itema.menu_parent+")\"><i class=\"fa "+itema.icon+"\"></i>" + itema.menu_name + "</a></li>";
		                    	   }
		                       }
		                   });
	                   if (item.id == item.menu_parent && item.menu_level == 1) {
	                       html += "</ul>";
	                       html += "</li>"
	                   }
	               });
	               $(".menu").append(html); 
	           }
			 
			})
	}
		

	
	  function saveChk(){
	    	obj = document.getElementsByName("menu");
	    	check_val = [];
	   	    for(k in obj){
	   	        if(obj[k].checked)
	   	            check_val.push(obj[k].value);
	   	    }
	   	    
		   	$.ajax({
		 		url:"${ctx!}/user/updateMenuByRoleId",
		 		data : {"menuIds":check_val.join(','),"roleId":_roleId},
		 		dataType : "json",
		 		async : false,
		 		success : function(data) {
			 			if (data.draw == 1) {
							bootbox.alert(data.error,function(){
								window.location.reload();
							});
						}else{
							bootbox.alert(data.error);
						}
		 		}
		 	})
	   	    
	    }
	    
	    function showUp(menuId){
			if(document.getElementById(menuId).getAttribute("class") == "hidden"){
				document.getElementById(menuId).setAttribute("class","show");
			}else{
				document.getElementById(menuId).setAttribute("class","hidden");
			}
	    }
	
	    
	    function fatherselect(parentId){
	    	tagNamesObj=document.getElementsByClassName(parentId);
	    	obj=document.getElementById(parentId+"999");
	    	 for(i=0;i<tagNamesObj.length;i++){
	              if(tagNamesObj[i].checked){
	                obj.checked = true;
	                }
	          }
	    }
	    
	    function allselect(id){
            obj=document.getElementById(id+"999");
            tagNamesObj=document.getElementsByClassName(id);
            if(obj.checked){
                for(i=0;i<tagNamesObj.length;i++){
                    tagNamesObj[i].checked=true;
                }
            }else{
                for(i=0;i<tagNamesObj.length;i++){
                    tagNamesObj[i].checked=false;
                }
            }
        }
	    
</script>
</html>