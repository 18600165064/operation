package com.data.display;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.display.mapper.bonusMapper.BonusPartenerMapper;
import com.data.display.mapper.platformMapper.YMAscriptionMapper;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.platform.YMAscription;
import com.data.display.model.rich.Information;
import com.data.display.model.rich.WxFormId;
import com.data.display.model.rich.WxNotice;
import com.data.display.model.user.UserInfo;
import com.data.display.service.PayService;
import com.data.display.service.bonusService.YMBonusPartenerService;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.service.bonusService.YMBonusPoolService;
import com.data.display.service.bonusService.YMBonusStockholderService;
import com.data.display.service.userService.UserInfoService;
import com.data.display.util.DateUtil;
import com.data.display.util.RedisConstantUtil;
import com.data.display.util.RedisUtil;
import com.data.display.util.StringUtil;
import com.data.display.util.config.WxConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class YMAscriptionTest {

    private static Logger _log = LoggerFactory.getLogger(YMAscriptionTest.class);

    @Resource
    YMAscriptionMapper ascriptionMapper;

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void contextLoads() throws Exception {
        try{
            /**
             * 下属所有用户
             */
            Integer ax = 0;
            ArrayList<String> groupList = new ArrayList<String> ();
            groupList.add(0,"10361");
            List<YMAscription> groupList2 = new ArrayList<>();
            while (ax == 0){
                List<YMAscription> groupList3 = ascriptionMapper.selectByUserId(groupList,"ZY-97431990");
                ArrayList<String> groupList4 = new ArrayList<String> ();
                if (groupList3.size() == 0){
                    ax = 1;
                }else{
                    for (int j = 0; j < groupList3.size(); j++) {
                        groupList4.add(j,String.valueOf(groupList3.get(j).getUser_id()));
                    }
                    groupList = groupList4;
                    groupList2.addAll(groupList3);
                }
            }
            System.out.println(groupList2.size());
        }catch (Exception e){
            System.out.println(e);
        }

    }

}

