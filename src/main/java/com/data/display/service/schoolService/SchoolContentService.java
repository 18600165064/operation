package com.data.display.service.schoolService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.school.SchoolContent;
import com.github.pagehelper.Page;

public interface SchoolContentService {

    Page<SchoolContent> getContentData(DataTableDTO dataTableDTO, SchoolContent schoolContent);

    SchoolContent selectById(String id);

    DataTableResult addSchoolContentData(SchoolContent schoolContent);

    DataTableResult editShoolContentData(SchoolContent schoolContent);
}
