<#include "../common/header.html">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
 <link href="${ctx!}/assets/css/jquery.dataTables.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/bootstrap-datepicker.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/fileinput.min.css" rel="stylesheet">
 <link href="${ctx!}/assets/css/form.min.css" rel="stylesheet">
 <script src="${ctx!}/assets/js/jquery.dataTables.js"></script>
 <script src="${ctx!}/assets/js/bootstrap-datepicker.js"></script>
<script src="${ctx!}/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx!}/assets/js/jquery.validationEngine-zh_CN.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.js"></script>
 <script src="${ctx!}/assets/js/jquery.validationEngine.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/bootbox.min.js"></script>
<script src="${ctx!}/assets/js/fileinput.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
<script src="${ctx!}/assets/js/locales/zh.js"></script>
<script src="${ctx!}/assets/js/plugins/piexif.js"></script>
<script src="${ctx!}/assets/js/ueditor/ueditor.config.js"></script>
<script src="${ctx!}/assets/js/ueditor/ueditor.all.js"></script>




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
                          <h3>新增SKU
                          </h3>
                      </div>
                  </div>

                  <div class="clearfix"></div>
                  <div class="row">
                      <div class="x_panel">
                          <div class="x_title">
                              <div class="clearfix">
                                  <!-- 搜索 -->
                              </div>
                          </div>
                          <div class="x_content">
                             <!-- 正文 -->
                              <form id="form" class="form-horizontal" method="post" enctype="multipart/form-data">
						            <table class="table table-bordered">
						              <tbody>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SKU名称</td>
						                <td width="30%" >
						                	  <input type="hidden" name="spuid" id="spuid" />
							                  <input type="text"  class="form-control validate[required[maxSize[40]]]" id="sku_name" name="sku_name" placeholder="SKU名称" data-errormessage-value-missing="SKU名称不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>规格</td>
						                <td width="30%" >
						                	<input type="hidden"  class="form-control" id="spec" name="spec"/>
											<div id="specifications">
						                		
						                	</div>	
						                </td>
						              </tr>
						               <tr>
						              	<td class="input-group-addon" width="20%"><em style="color:red;"> * </em>SKU主图</td>
						                <td width="30%" >
										    <!-- 注意事项：Input type类型为file class为样式 id随意 name随意   multiple（如果是要多图上传一定要加上，不加的话每次只能选中一张图）-->
										    <input type="file" class="file" id="img2" name="image1">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						                </td>
						              </tr>
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>市场价</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required]" id="market_price" name="market_price" placeholder="市场价" data-errormessage-value-missing="市场价不能为空" readonly="true"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>单买价格</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required]" id="price" name="price" placeholder="单买价格" data-errormessage-value-missing="单买价格不能为空" readonly="true"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
						               <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>供货价</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required]" id="supply_price" name="supply_price" placeholder="供货价" data-errormessage-value-missing="供货价不能为空" readonly="true"/>
						                </td>
						              </tr>
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>服务费</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required]" id="service_price" name="service_price" placeholder="服务费" data-errormessage-value-missing="服务费不能为空" readonly="true"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>佣金</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required]" id="commission" name="commission" placeholder="佣金" data-errormessage-value-missing="佣金不能为空" readonly="true" onblur="message()"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>8人团价格</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required]" id="group_price_eight" name="group_price_eight" placeholder="8人团价格" data-errormessage-value-missing="8人团价格不能为空" readonly="true" onblur="message1()"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>5人团价格</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required]" id="group_price_five" name="group_price_five" placeholder="5人团价格" data-errormessage-value-missing="5人团价格不能为空" readonly="true" onblur="message2()"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>3人团价格</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required]" id="group_price_three" name="group_price_three" placeholder="3人团价格" data-errormessage-value-missing="3人团价格不能为空" readonly="true" onblur="message3()"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>库存</td>
						                <td width="30%" >
							                  <input type="text"  class="form-control validate[required,custom[onlyNumberSp]]" id="stock" name="stock" placeholder="库存" data-errormessage-value-missing="库存不能为空"/>
						                </td>
						              </tr>
						              <tr>
						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>运费模板</td>
						                <td width="30%" >
							                  <select class="form-control"  id="shipping_id" name="shipping_id" onchange="selectById()">
							                  	
							                  </select>
						                </td>
						              </tr>
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>结算运费首单金额</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="default_price" name="default_price" placeholder="结算运费首单金额" data-errormessage-value-missing="结算运费首单金额不能为空"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>结算运费首单数</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required,custom[onlyNumberSp],min[1]]" id="default_number" name="default_number" placeholder="结算运费首单数" data-errormessage-value-missing="结算运费首单数不能为空"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>结算运费续件金额</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required,custom[onlyNumberTwo],min[0.1]]" id="sub_dis_fee" name="sub_dis_fee" placeholder="结算运费续件金额" data-errormessage-value-missing="结算运费续件金额不能为空"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
<!-- 						              <tr> -->
<!-- 						                <td class="input-group-addon" width="20%"><em style="color:red;"> * </em>结算运费续件数</td> -->
<!-- 						                <td width="30%" > -->
<!-- 							                  <input type="text"  class="form-control validate[required,custom[onlyNumberSp],min[1]]" id="sub_dis_value" name="sub_dis_value" placeholder="结算运费续件数" data-errormessage-value-missing="结算运费续件数不能为空"/> -->
<!-- 						                </td> -->
<!-- 						              </tr> -->
						              </tbody>
						            </table>
						            <div class="ibox-footer" style="text-align: center">
						              <!--操作按钮 开始-->
						              <div class="ibox-btns">
						                <input class="btn btn-primary submit" type="button" onclick="save()" value="保存" />
						                <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>
						              </div>
						              <!--操作按钮 开始-->
						            </div>
						          </form>
                              <!-- 正文 -->
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
var token = $("meta[name='_csrf']").attr("content"); 
var header = $("meta[name='_csrf_header']").attr("content"); 
$(document).ajaxSend(function(e, xhr, options) { 
	xhr.setRequestHeader(header, token); 
});
var _type = "";
var _spuid = "${spuid!""}";

$("#img2").fileinput({
	language : 'zh',
    uploadUrl : "${ctx!}/spuCommodity/uploadFile",
    showPreview : true, //是否显示预览
    showCaption: true,//是否显示标题
    dropZoneEnabled: false,//是否显示拖拽区域
    autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
    minFileCount: 0,
    uploadAsync: false,
    showUpload:false,
    showRemove:false,
    maxFileCount: 1,//最大上传数量
    maxFileSize:500,//上传最大500
    minImageWidth:748,//图片的最小宽度
    maxImageWidth:752,//图片的最大宽度
    minImageHeight:748,//图片的最小高度
    maxImageHeight:752,//图片的最大高度
    browseOnZoneClick: true,
    msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
    enctype: 'multipart/form-data',
//     overwriteInitial: true,//不覆盖已上传的图片
    allowedFileExtensions : [ "jpg", "png", "gif", "jpeg"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    layoutTemplates :{ 
    	// actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
//         actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
})  


//绑定一个文件加载完成的事件
$('#img2').on('fileloaded',function(event, file, previewId, index, reader){
    var img = new Image();
    img.src = reader.result;//reder.result是base64图片格式的数据
    img.onload=function(argument){//可以通过img获取图片的信息再自己做逻辑判断
        if(this.width >= 752 || this.width <= 748 || this.height >=  752 || this.height <= 748){
        	bootbox.alert("图片比例不正确");
            $('.kv-file-remove:last').trigger('click');//不符合的话就触发对应的图片的删除点击事件
        }
    }
});



$(function () {
	_errFm =  $('#form');
	_errFm.validationEngine('attach', {
		  maxErrorsPerField:1,
		  autoHidePrompt: true,
		  autoHideDelay: 2000
		});
	
	tableFun3("${ctx!}/specifications/selSpecName");
	tableFun2("${ctx!}/supplierAccountShipping/shippingList");
 });
var arr = new Array();
function tableFun(tdUrl,tbData){
	 $.get(tdUrl,tbData, function(result){ 
			var menuJson = JSON.parse(result);
				  $("#spuid").val(menuJson.spuid);
				  $("#supply_price").val(menuJson.supply_price);
				  $("#group_price_three").val(menuJson.group_price_three);
				  $("#group_price_five").val(menuJson.group_price_five);
				  $("#group_price_eight").val(menuJson.group_price_eight);
				  $("#market_price").val(menuJson.market_price);
				  $("#price").val(menuJson.price);
				  $("#commission").val(menuJson.commission);
				  $("#service_price").val(menuJson.service_price);
				  arr = eval(menuJson.spu_spec);
			var executerDiv=$("#specifications");  
		 	 	  executerDiv.innerHTML="";  
			var ul=document.createElement("ul");
			var type = eval(_type);
		 	for(var j = 0; j< type.length; j++){
		 		var li=document.createElement("li");  
		 			  li.setAttribute("value",type[j].spec_name);
		 			  li.append(type[j].spec_name + ":");
		 		for (var i = 0; i < arr.length; i++) {
		 			if(type[j].spec_name == arr[i].spec_name){
		 				var checkBox=document.createElement("input");  
						  	  checkBox.setAttribute("type","checkbox");  
						      checkBox.setAttribute("id",arr[i].id);  
						  	  checkBox.setAttribute("name","checkname");  
						  	  checkBox.setAttribute("title",arr[i].spec_name);  
						  	  checkBox.setAttribute("value",arr[i].id);
						  	  checkBox.setAttribute("checked","checked");
						  	  
							  li.appendChild(checkBox);  
							  li.appendChild(document.createTextNode(arr[i].spec_value));  
							  ul.appendChild(li);  
		 				}
		　			}
		 	  }
			executerDiv.append(ul);
	})
}
 
 
 
function tableFun2(tdUrl){
	 $.get(tdUrl, function(result){ 
			var menuJson = JSON.parse(result);
				  for (var i = 0; i < menuJson.length; i++) {
		　　　　	$("#shipping_id").append("<option value='"+menuJson[i].sas_id+"'>"+menuJson[i].templet_name+"</option>");
			　　 }
	})
} 


function tableFun3(tdUrl,tbData){        
	$.ajax({
			url:tdUrl,
			dataType : "json",
			async : false,
			success : function(menuJson) {
					  _type = menuJson;
			  var  srhData = {"id" : _spuid};
					 tableFun("${ctx!}/spuCommodity/selectById",srhData);
			}
		})
}
 
 
//  function selectById(){
// 	 alert($("#shipping_id").val());
//  }
 
 
function save(){
	if (!_errFm.validationEngine('validate')) {
	    return false;
	  }
	
	var _shipping = $("#shipping_id").val();
	if(_shipping == null || _shipping == '' || _shipping == 'undefind'){
		bootbox.alert("请先添加运费模板");
		 return false;
	}
	
	
	var _name = $("#img2").val();
	if(_name == null || _name == '' || _name == 'undefind'){
		bootbox.alert("请添加SKU主图");
		return false;
	}
	
		var array = new Array(); 
		var array2 = new Array(); 
		var array3 = new Array();
		var array4 = new Array();
		$("input[type=checkbox]:checked").each(function(i){ 
			array[i] = $(this).val();
			array2[i] = $(this).attr("title");
		});
		
		for(var i = 0; i < array2.length; i++){
			 if(array3.indexOf(array2[i]) == -1){
				 	array3.push(array2[i]);
		     }
		}
		
		var vals = array.join(",");
		var vals2 = array2.join(",");
		$("#spec").val(vals);
		
		for(var i = 0; i < arr.length; i++){
			if(array4.indexOf(arr[i].spec_name) == -1){
				array4.push(arr[i].spec_name);
	        }
		}
		
		if(array2.toString() != array4.toString()){
			bootbox.alert("规格每行必须选取一个");
			 return false;
		}
		
		var formdata = new FormData(document.getElementById("form"));
		$.ajax({
			url:"${ctx!}/product/addSku",
			data : formdata,
			type:"POST",
			dataType : "json",
			async : false,
			processData: false,
			contentType: false,
			success : function(data) {
					if (data.draw == 1) {
						bootbox.alert(data.error, function() {
							window.location.href = "${ctx!}/spuCommodity/supplierSpuMenu";
						});
					}else{
						bootbox.alert(data.error);
					}
			}
		})
};

</script>
</html>