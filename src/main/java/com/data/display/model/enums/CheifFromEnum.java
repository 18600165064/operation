package com.data.display.model.enums;

import java.util.List;

/**
 * @Author: CYN
 * @Date: 2019/1/17 13:29
 * @Description:
 */
public enum CheifFromEnum implements IndexedEnum {

    /**
     * 总监升级方式
     */
    SOUTCE_ZERO(0, "NONE"),
    SOUTCE_ONE(1, "内部"),
    SOUTCE_TWO(2, "晋升"),
    SOUTCE_THREE(3, "卡位"),
    SOUTCE_FOUR(4, "卡邀"),
    SOUTCE_FIVE(5, "特批"),
    SOUTCE_SIX(6, "内部职能部门");
    private static final List<CheifFromEnum> INDEXS = IndexedEnumUtil.toIndexes(CheifFromEnum.values());

    /**
     * 索引
     */
    private int index;

    /**
     * 拼音
     */
    private String message;


    CheifFromEnum(int index, String message) {
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
    public static CheifFromEnum indexOf(final int index) {
        return IndexedEnumUtil.valueOf(INDEXS, index);
    }
}
