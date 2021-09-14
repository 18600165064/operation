package com.data.display.mapper.schoolMapper;

import com.data.display.model.school.SchoolColume;
import com.github.pagehelper.Page;

import java.util.List;

public interface SchoolColumeMapper {

    Page<SchoolColume> getColumeData(SchoolColume schoolColume);

    Integer addSchoolColumeData(SchoolColume schoolColume);

    SchoolColume selectById(String id);

    Integer editShoolColumeData(SchoolColume schoolColume);

    List<SchoolColume> getShoolColumeList(SchoolColume schoolColume);
}
