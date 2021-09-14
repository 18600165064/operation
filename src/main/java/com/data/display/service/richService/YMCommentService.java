package com.data.display.service.richService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.rich.YMComment;
import com.github.pagehelper.Page;

import java.util.Map;

public interface YMCommentService {

    Page<YMComment> getYMCommentData(DataTableDTO dataTableDTO, YMComment yMComment);

    YMComment selectById(String id);

    Map<String,Object> selectByUserId(String id);

    Integer addYMCommentData(YMComment yMComment);

    Integer editYMCommentData(YMComment yMComment);
}
