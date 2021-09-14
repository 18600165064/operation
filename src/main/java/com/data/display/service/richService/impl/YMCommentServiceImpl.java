package com.data.display.service.richService.impl;

import com.data.display.mapper.richMapper.YMCommentMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.rich.YMComment;
import com.data.display.service.richService.YMCommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class YMCommentServiceImpl implements YMCommentService {

    @Resource
    private YMCommentMapper yMCommentMapper;

    @Override
    public Page<YMComment> getYMCommentData(DataTableDTO dataTableDTO, YMComment yMComment) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        return yMCommentMapper.getYMCommentData(yMComment);
    }

    @Override
    public YMComment selectById(String id) {
        return yMCommentMapper.selectById(id);
    }

    @Override
    public Map<String, Object> selectByUserId(String id) {
        return yMCommentMapper.selectByUserId(id);
    }

    @Override
    public Integer addYMCommentData(YMComment yMComment) {
        return yMCommentMapper.addYMCommentData(yMComment);
    }

    @Override
    public Integer editYMCommentData(YMComment yMComment) {
        return yMCommentMapper.editYMCommentData(yMComment);
    }
}
