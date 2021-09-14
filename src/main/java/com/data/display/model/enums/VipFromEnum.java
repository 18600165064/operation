package com.data.display.model.enums;

import java.util.List;

/**
 * @Author: CYN
 * @Date: 2019/1/17 13:29
 * @Description:
 */
public enum VipFromEnum implements IndexedEnum {

    /**
     * 店主升级方式
     */
    SOUTCE_ZERO(0, "NONE"),
    SOUTCE_ONE(1, "内部"),
    SOUTCE_TWO(2, "付费"),
    SOUTCE_THREE(3, "卡邀VIP"),
    SOUTCE_FOUR(4, "无码付费"),
    SOUTCE_FIVE(5, "内部职能部");
    private static final List<VipFromEnum> INDEXS = IndexedEnumUtil.toIndexes(VipFromEnum.values());

    /**
     * 索引
     */
    private int index;

    /**
     * 拼音
     */
    private String message;


    VipFromEnum(int index, String message) {
        this.index = index;
        this.message = message;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据index 获取指定形式名称
     *
     * @param index
     * @return
     */
    public static VipFromEnum indexOf(final int index) {
        return IndexedEnumUtil.valueOf(INDEXS, index);
    }
}
