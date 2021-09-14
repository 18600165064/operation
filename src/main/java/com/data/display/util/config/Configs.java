package com.data.display.util.config;


import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(name = "configs")
public class Configs {
    /**
     * 后台地址（物流回调）
     */
//    private static final String YM_API = "http://60.205.217.185:8082/";

    private static final String YM_API = "https://adm.yuemee.com/";

    private static final String PRO_HOST = "shop.yuemee.com";
    private static final String QRCODE_URL = "http://shop.yuemee.com/yuemee-admin";
    private static final String IMAGE_URL = "http://img.yuemee.com";
    private static final String SHARE_URL = "https://shop.yuemee.com/yuemee-admin";
    private static final String ADMIN_URL = "https://shop.yuemee.com/yuemee-admin/";
    private static final String SOLR_MULTIQUERY_URL = "http://39.105.194.74:8089/solr/multiquery";
    private static final String SOLR_ADD_URL = "http://39.105.194.74:8089/solr/add";
    private static final String SALEDETAIL = "http://127.0.0.1:8099/api";
    private static final String YM_OA = "https://shop.yuemee.com/yuemee-oa/";
    private static final String INVITER_CODE_URL = "https://shop.yuemee.com/yuemee-admin/mine/myCode.html";

    private static final String SMALL_CODE_URL="https://interface.yuemee.com/imageHandle/getProSmallCode";

    public static String getSmallCodeUrl() {
        return SMALL_CODE_URL;
    }

    public static String getProHost() {
        return PRO_HOST;
    }

    public static String getQrcodeUrl() {
        return QRCODE_URL;
    }

    public static String getImageUrl() {
        return IMAGE_URL;
    }

    public static String getShareUrl() {
        return SHARE_URL;
    }

    public static String getYmOa() {
        return YM_OA;
    }

    public static String getYmApi() {
        return YM_API;
    }

    public static String getAdminUrl() {
        return ADMIN_URL;
    }

    public static String getSolrMultiqueryUrl() {
        return SOLR_MULTIQUERY_URL;
    }

    public static String getSolrAddUrl() {
        return SOLR_ADD_URL;
    }


    public static String getSaleDetail() {
        return SALEDETAIL;
    }


    public static String getInviterCodeUrl() {
        return INVITER_CODE_URL;
    }

}
