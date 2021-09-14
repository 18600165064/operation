package com.data.display.model.enums;

import java.util.List;

/**
 * @Author: CYN
 * @Date: 2019/1/22 15:03
 * @Description: 身份
 */
public enum IdentityEnum implements IndexedEnum {

    /**
     * 身份
     */
    LEVEL_ZERO(0,"小白"),
    LEVEL_ONE(1,"店主"),
    LEVEL_TWO(2,"总监"),
    LEVEL_THREE(3,"经理");
    private static final List<IdentityEnum> INDEXS = IndexedEnumUtil.toIndexes(IdentityEnum.values());

    /**
     * 索引
     */
    private int index;

    /**
     * 拼音
     */
    private String message;


    IdentityEnum(int index, String message) {
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
    public static IdentityEnum indexOf(final int index) {
        return IndexedEnumUtil.valueOf(INDEXS, index);
    }
    
    
}
