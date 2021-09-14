package com.data.display.mapper.platformMapper;

import com.data.display.model.platform.YMAscription;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface YMAscriptionMapper {

    List<YMAscription> selectByUserId(@Param("list")ArrayList<String> uperid,@Param("spuid")String spuid);

    YMAscription selectByUserId2(YMAscription ascription);

    List<YMAscription> selectUser(String user_id);

}
