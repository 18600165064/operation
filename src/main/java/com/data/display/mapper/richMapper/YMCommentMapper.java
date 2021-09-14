package com.data.display.mapper.richMapper;

import com.data.display.model.rich.YMComment;
import com.github.pagehelper.Page;

import java.util.Map;

public interface YMCommentMapper {

    Page<YMComment> getYMCommentData(YMComment yMComment);

    YMComment selectById(String id);

    /**
     * 获取虚拟用户
     * @param id
     * @return
     */
    Map<String,Object> selectByUserId(String id);

    Integer addYMCommentData(YMComment yMComment);

    Integer editYMCommentData(YMComment yMComment);

}
