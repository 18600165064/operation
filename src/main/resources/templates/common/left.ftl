<!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                <div class="menu_section">
                    <ul class="nav side-menu">
                    </ul>
                </div>
            </div>
<script>
    $(document).ready(function () {
    <#if Session["menu"]?exists>
      var menuJson =${Session["menu"]};
           if (menuJson.length > 0) {
               var html = "";

               $.each(menuJson, function (idx, item) {
                   if (item.id == item.menu_parent && item.menu_level == 1) {
                       html += "<li><a><i class=\"fa "+item.icon+"\"></i>" + item.menu_name + "<span class=\"fa fa-chevron-down\"></span></a>";
                       html += "<ul class=\"nav child_menu \">";
                   }
                   $.each(menuJson, function (idx, itema) {

                       if (item.id == itema.menu_parent && itema.menu_level == 2) {
                           html += "<li><a href=\""+itema.menu_url+"\">" + itema.menu_name + "</a></li>";
                       }
                   });
                   if (item.id == item.menu_parent && item.menu_level == 1) {
                       html += "</ul>";
                       html += "</li>"
                   }
               });
               $(".side-menu").append(html);
           }
    </#if>

    });
</script>