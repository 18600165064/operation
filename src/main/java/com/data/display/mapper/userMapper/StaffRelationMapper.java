package com.data.display.mapper.userMapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.data.display.model.user.StaffRelation;

public interface StaffRelationMapper {

	/**
	 * 新增
	 * @param staffRelation
	 * @return
	 */
	int addStaffRelation(StaffRelation staffRelation);
	
	/**
	 * 根据USERID 获取
	 * @param string
	 * @return
	 */
	StaffRelation getByUserId(@Param("userId")String userId);

	/**
	 * 修改
	 * @param staffRelation
	 * @return
	 */
	int update(StaffRelation staffRelation);

	/**
	 * 根据级别获取不同的用户数据
	 * @param userId
	 * @param levelU
	 * @return
	 */
	List<Map<String,Object>> relationData(@Param("userId")String userId,@Param("levelU")String levelU);

}
