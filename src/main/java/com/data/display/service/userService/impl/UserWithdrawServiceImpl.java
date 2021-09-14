package com.data.display.service.userService.impl;


import com.alibaba.fastjson.JSON;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.mapper.richMapper.RichMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.mapper.userMapper.UserWithdrawMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.rich.Information;
import com.data.display.model.rich.Rich;
import com.data.display.model.user.SysUser;
import com.data.display.model.user.UserInfo;
import com.data.display.model.user.UserInfoFinace;
import com.data.display.model.user.UserWithdraw;
import com.data.display.service.PayService;
import com.data.display.service.userService.UserWithdrawService;
import com.data.display.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service("userWithdraw")
public class UserWithdrawServiceImpl implements UserWithdrawService {

	private static final Logger log = LoggerFactory.getLogger(UserWithdrawServiceImpl.class);
	
	@Resource
    private UserWithdrawMapper userWithdrawMapper;
	@Autowired
	private PayService payService;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private UserInfoFinaceMapper userInfoFinaceMapper;
	@Resource
    private RedisUtil redisUtil;
	@Resource
	private InformationMapper informationMapper;
	
	@Override
	public Page<UserWithdraw> getUserWithdrawData(DataTableDTO dataTableDTO, UserWithdraw userWithdraw) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return userWithdrawMapper.getUserWithdrawData(userWithdraw);
	}

	@Override
	public UserWithdraw selectById(Integer id) {
		return userWithdrawMapper.selectById(id);
	}

	@Override
	public Integer updateUserWithdraw(UserWithdraw userWithdraw) {
		return userWithdrawMapper.updateUserWithdraw(userWithdraw);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
	public DataTableResult withdraw(UserWithdraw userWithdraw) {
		log.info(JSON.toJSONString(userWithdraw));
		log.debug("提现金额"+ userWithdraw.getTotal());
		DataTableResult result = new DataTableResult();
		SysUser user = UserUtil.getUserMessage();
		userWithdraw = userWithdrawMapper.selectById(userWithdraw.getId());
		if (userWithdraw.getStatus() != 0) {//提现请求状态，0提交  2打款     ,1审核,,3完成,4拒绝,5关闭  todo 现在取值只有 0 2
            result.setDraw(0);
			result.setError("该提现请求已处理,不能再次转账");
			return result;
        }
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(userWithdraw.getUser_id());
		List<UserInfo> list = userInfoMapper.getUserInfoListByOthers(userInfo);
		if(list.size() == 0 || StringUtil.isBlank(list.get(0).getOpen_id_small())){
			result.setDraw(0);
			result.setError("该用户未通过微信登录注册");
			return result;
		}
		
		 //检查账单，防止重复提交
		DataTableResult result2 = payService.balanceQuery(userWithdraw.getOrder_no());
		log.info("账单请求结果"+JSON.toJSONString(result2));
        if (result2.getDraw() == 0) {
			throw new RuntimeException("账单错误");
        }
		
        //XXX 正式要解开
		log.info("转账单号==========="+userWithdraw.getOrder_no());
        DataTableResult result3 = payService.balanceWithdraw(user.getId(),list.get(0).getOpen_id_small(),userWithdraw.getTotal().toString(),userWithdraw.getOrder_no(),"");
//        DataTableResult result3 = payService.balanceWithdraw(user.getId(),list.get(0).getOpen_id_small(),"1",userWithdraw.getOrder_no(),"");
//        log.debug("提现转账请求结果"+JSON.toJSONString(result3));
        if (result3.getDraw() == 0) {
			throw new RuntimeException("微信提现失败");
        }
        try{
			if (redisUtil.hHasKey(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userWithdraw.getUser_id()))) {
				int num = Integer.parseInt(redisUtil.hget(RedisConstantUtil.SENDNUM_LIMIT, userWithdraw.getUser_id().toString()).toString()) + 1;
				redisUtil.hset(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userWithdraw.getUser_id()), String.valueOf(num));
			} else {
				LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);//当天23点59分59秒
				long endLong = DateUtil.remainingSeconds(today_end) + 1;
				redisUtil.hset(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userWithdraw.getUser_id()), "1", endLong);
			}
		}catch (Exception e){
			log.info("redis错误");
		}


        //发送消息
		Information information = new Information();
		information.setCreate_time(new Date());
		information.setJump_page(0);
		information.setStatus(0);
		information.setUser_id(userWithdraw.getUser_id());
		information.setTitle("您发起的提现已成功");
		information.setSub_title("您发起的提现已成功");
		information.setMessage("您发起的提现已成功");
		informationMapper.insertYmInformation(information);

        userWithdraw.setBack_user_id(user.getId());
        userWithdraw.setProcess_time(new Date());
        userWithdraw.setStatus(2);
        try {
        	result.setError("转账操作成功");
        	result.setDraw(userWithdrawMapper.updateUserWithdraw(userWithdraw));
		} catch (Exception e) {
			result.setError("转账操作失败");
		}
		return result;
	}

	@Override
	public DataTableResult rejected(UserWithdraw userWithdraw) {
		DataTableResult result = new DataTableResult();
		SysUser user = UserUtil.getUserMessage();
		userWithdraw = userWithdrawMapper.selectById(userWithdraw.getId());
		BigDecimal userMoney = userWithdraw.getMoney();
		int userId = userWithdraw.getUser_id();
//		UserInfoFinace userInfoFinace = new UserInfoFinace();
//		userInfoFinace.setUser_id(userWithdraw.getUser_id());
//		List<UserInfoFinace> finaceList = userInfoFinaceMapper.getData(userInfoFinace);
		if (userWithdraw.getStatus() != 0) {//提现请求状态，0提交  2打款     ,1审核,,3完成,4拒绝,5关闭
			result.setDraw(0);
			result.setError("该请求已驳回,金额已退回到原账户");
			return result;
        }
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(userWithdraw.getUser_id());
		List<UserInfo> list = userInfoMapper.getUserInfoListByOthers(userInfo);
		if(list.size() == 0 || StringUtil.isBlank(list.get(0).getOpen_id_small())){
			result.setDraw(0);
			result.setError("该用户未通过微信登录注册");
			return result;
		}
		
        userWithdraw.setBack_user_id(user.getId());
        userWithdraw.setProcess_time(new Date());
        userWithdraw.setStatus(4);
        result.setDraw(userWithdrawMapper.updateUserWithdraw(userWithdraw));
        result.setDraw(1);
        result.setError("该请求已驳回");


		UserInfoFinace userInfoFinace = new UserInfoFinace();
		userInfoFinace.setUser_id(userId);
		UserInfoFinace userInfoFinace2 = userInfoFinaceMapper.selectByUserId(userInfoFinace);
		BigDecimal alMoney = userInfoFinace2.getAlready_withdraw();
		BigDecimal canAmount = userInfoFinace2.getCan_withdraw().add(userMoney);
		userInfoFinace.setCan_withdraw(canAmount);
		userInfoFinace.setAlready_withdraw(userInfoFinace2.getAlready_withdraw().subtract(userWithdraw.getMoney()));
		Integer withdrawTimes = userInfoFinace2.getCan_withdraw_times();
		if (withdrawTimes < 4){
			withdrawTimes = withdrawTimes + 1;
		}
		userInfoFinaceMapper.updateUserFinaceByUserId(userId,canAmount,userMoney,alMoney,withdrawTimes);


		//发送消息
		Information information = new Information();
		information.setCreate_time(new Date());
		information.setJump_page(10);
		information.setStatus(0);
		information.setUser_id(userWithdraw.getUser_id());
		information.setTitle("您发起的提现被驳回");
		information.setSub_title("您发起的提现被驳回");
		information.setMessage("您发起的提现被驳回");
		informationMapper.insertYmInformation(information);

        return result;
	}
	
}
