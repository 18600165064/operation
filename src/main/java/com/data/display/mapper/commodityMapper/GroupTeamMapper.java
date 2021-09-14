package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.GroupTeam;
import org.apache.ibatis.annotations.Param;


public interface GroupTeamMapper {
    GroupTeam selectDataBySpuid(@Param("user_id") String user_id);

    Integer insertData(GroupTeam groupTeam);

    Integer updateGroup(GroupTeam group);

    Integer deleteByPrimaryKey(String id);
}
