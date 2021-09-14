package com.data.display.service.schoolService.impl;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.schoolMapper.SchoolColumeMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.school.SchoolColume;
import com.data.display.service.schoolService.SchoolColumeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SchoolColumeServiceImpl implements SchoolColumeService {

    private static final Logger logger = LoggerFactory.getLogger(SchoolColumeServiceImpl.class);

    @Resource
    private SchoolColumeMapper schoolColumeMapper;


    @Override
    public Page<SchoolColume> getColumeData(DataTableDTO dataTableDTO, SchoolColume schoolColume) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        return schoolColumeMapper.getColumeData(schoolColume);
    }

    @Override
    public DataTableResult addSchoolColumeData(SchoolColume schoolColume) {
        DataTableResult result = new DataTableResult();
        result.setDraw(1);
        result.setError("添加成功");
        try{
            schoolColume.setCreate_time(new Date());
            result.setDraw(schoolColumeMapper.addSchoolColumeData(schoolColume));
        }catch (Exception e){
            result.setDraw(0);
            result.setError("系统错误");
            logger.error("栏目新增错误"+ JSON.toJSON(e));
        }
        return result;
    }

    @Override
    public SchoolColume selectById(String id) {
        return schoolColumeMapper.selectById(id);
    }

    @Override
    public DataTableResult editShoolColumeData(SchoolColume schoolColume) {
        DataTableResult result = new DataTableResult();
        result.setDraw(1);
        result.setError("修改成功");
        try{
            result.setDraw(schoolColumeMapper.editShoolColumeData(schoolColume));
        }catch (Exception e){
            result.setDraw(0);
            result.setError("系统错误");
            logger.error("栏目修改错误"+ JSON.toJSON(e));
        }
        return result;
    }

    @Override
    public List<SchoolColume> getShoolColumeList(SchoolColume schoolColume) {
        return schoolColumeMapper.getShoolColumeList(schoolColume);
    }
}
