package com.data.display.service.schoolService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.school.SchoolColume;
import com.github.pagehelper.Page;

import java.util.List;

public interface SchoolColumeService {


    Page<SchoolColume> getColumeData(DataTableDTO dataTableDTO, SchoolColume schoolColume);

    DataTableResult addSchoolColumeData(SchoolColume schoolColume);

    SchoolColume selectById(String id);

    DataTableResult editShoolColumeData(SchoolColume schoolColume);

    List<SchoolColume> getShoolColumeList(SchoolColume schoolColume);
}
