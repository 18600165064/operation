package com.data.display.util.config;

import java.math.BigDecimal;

/**
 * @Author: CYN
 * @Date: 2018/12/30 02:15
 * @Description:
 */
public class Contants {
    public static final String BANNER_NAME = "买一赠一";
    public static final BigDecimal PACKAGE_MONEY = new BigDecimal("499");
    public static final BigDecimal BEFORE_MONEY = new BigDecimal("399");
    public static final BigDecimal BEFOR_CHEIF = new BigDecimal("3990");
    public static final BigDecimal AFTER_CHEIF = new BigDecimal("4990");
    public static final BigDecimal COMMISSION_RATIO = new BigDecimal(0.56);


    // 店主收益
    public static final BigDecimal VIP_BASIC_PROFIT = new BigDecimal(100);


    /**
     * 总监推荐奖
     */
    public static final BigDecimal CHEIF_RECOMEND_BONUS = new BigDecimal(300);

    /**
     * 间接礼包金额  直接礼包金额 存数据库了
     */
    public static final BigDecimal INDIR_GIFT_AMOUNT = new BigDecimal(50);


    /**
     * 卡位经理 回款  12 万
     */
    public static final BigDecimal KAWEI_DIRECTOR_BACK_AMOUNT = new BigDecimal(12 * 10000);


    /**
     * 卡位总监 回款  4990 万
     */
    public static final BigDecimal KAWEI_CHEIF_BACK_AMOUNT = new BigDecimal(4990);

    /**
     * 店主回款  499
     */
    public static final BigDecimal VIP_BACK_AMOUNT = new BigDecimal(499);
    /**
     * 阿里云oss
     */
    public static final String ACCESSID = "LTAIQAiWQ9wbhGtZ";

    public static final String ACCESSKEY = "eM7Fc3UIGpb9cgM0Mkl2Ftpe94qr0M";

    public static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    /**
     * 阿里云bucket
     */
    public static final String BACKET = "yuemee-test";

}
