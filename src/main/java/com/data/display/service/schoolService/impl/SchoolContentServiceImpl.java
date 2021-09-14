package com.data.display.service.schoolService.impl;

import com.data.display.mapper.schoolMapper.SchoolContentMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.school.SchoolContent;
import com.data.display.service.schoolService.SchoolContentService;
import com.data.display.util.PhotoHandleUtil;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SchoolContentServiceImpl implements SchoolContentService {

    @Resource
    SchoolContentMapper schoolContentMapper;


    @Override
    public Page<SchoolContent> getContentData(DataTableDTO dataTableDTO, SchoolContent schoolContent) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        return schoolContentMapper.getContentData(schoolContent);
    }

    @Override
    public SchoolContent selectById(String id) {
        return schoolContentMapper.selectById(id);
    }

    @Override
    public DataTableResult addSchoolContentData(SchoolContent schoolContent) {
        DataTableResult result = new DataTableResult();
        result.setDraw(1);
        result.setError("保存成功");
        try {
            schoolContent.setCreate_time(new Date());
            result.setDraw(schoolContentMapper.addSchoolContentData(schoolContent));

            if (StringUtil.isNotBlank(schoolContent.getContent())){
                Thread thread = new Thread(new PhotoHandleUtil(null,null,3,null,null,schoolContent,schoolContentMapper));
                thread.start();
            }

        }catch (Exception e){
            result.setDraw(0);
            result.setError("保存失败");
        }
        return result;
    }

    @Override
    public DataTableResult editShoolContentData(SchoolContent schoolContent) {
        DataTableResult result = new DataTableResult();
        result.setDraw(1);
        result.setError("修改成功");
        try {
            schoolContent.setUpdate_time(new Date());
            result.setDraw(schoolContentMapper.editShoolContentData(schoolContent));

            if (StringUtil.isNotBlank(schoolContent.getContent())){
                Thread thread = new Thread(new PhotoHandleUtil(null,null,3,null,null,schoolContent,schoolContentMapper));
                thread.start();
            }

        }catch (Exception e){
            result.setDraw(0);
            result.setError("修改失败");
        }
        return result;
    }
}
