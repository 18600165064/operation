package com.data.display.mapper.schoolMapper;

import com.data.display.model.school.SchoolContent;
import com.github.pagehelper.Page;

public interface SchoolContentMapper {

    Page<SchoolContent> getContentData(SchoolContent schoolContent);

    SchoolContent selectById(String id);

    Integer addSchoolContentData(SchoolContent schoolContent);

    Integer editShoolContentData(SchoolContent schoolContent);
}
