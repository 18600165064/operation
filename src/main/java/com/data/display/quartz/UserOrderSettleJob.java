package com.data.display.quartz;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.data.display.model.user.YmAcountBill;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.orderMapper.RebateMapper;
import com.data.display.mapper.userMapper.UserAccountBillMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.model.order.Order;
import com.data.display.model.order.Rebate;
import com.data.display.model.user.UserAccountBill;
import com.data.display.model.user.UserInfoFinace;
import com.data.display.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * ym_rebate
 * 用户订单结算 ---> 订单已完成状态  10天之前的 ，将佣金  转到  用户  可提现金额 ， 生成流水
 * @author l
 *
 */
@DisallowConcurrentExecution
public class UserOrderSettleJob extends QuartzJobBean {

	 private static Logger _log = LoggerFactory.getLogger(UserOrderSettleJob.class);

	 @Resource
	 private OrderMapper orderMapper;
	 
	 @Resource
	 private RebateMapper rebateMapper;
	 
	 @Resource
	 private UserInfoFinaceMapper userInfoFinaceMapper;
	 
	 @Resource
	 private UserAccountBillMapper userAccountBillMapper;
	 
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Date dt1= DateUtil.getStartTime2(-11);
		_log.info("开始时间============>"+DateUtil.dateToString(dt1,DateUtil.YYYY_MM_DD_HH_MM_SS));
    	
    	Date dt2= DateUtil.getEndTime2(-11);
    	_log.info("结束时间============>"+DateUtil.dateToString(dt2,DateUtil.YYYY_MM_DD_HH_MM_SS));

		UserInfoFinace userInfoFinace = new UserInfoFinace();
    	Order order = new Order();
		order.setStartTime(dt1);
		order.setEndTime(dt2);
		order.setOrder_status("ywc");
		order.setIf_primary(0);
		List<Order> orderList = orderMapper.selectByOthers3(order);
		_log.info("符合结算订单数量为"+orderList.size());
		for (Order order2 : orderList) {
			Rebate rebate = new Rebate();
			rebate.setOrder_no(order2.getOrder_no());
			rebate.setStatus(1);
			List<Rebate> rebateList = rebateMapper.selectRebateByOthers(rebate);
			for (Rebate rebate2 : rebateList) {

				rebate2.setUpdate_time(new Date());
				rebate2.setSettle_time(new Date());
				rebate2.setStatus(3);
				rebateMapper.updateRebate(rebate2);
				
				userInfoFinace.setUser_id(rebate2.getUser_id());
				userInfoFinace = userInfoFinaceMapper.selectByUserId(userInfoFinace);
				if(userInfoFinace != null){
					//佣金
					BigDecimal rebateAmount = rebate2.getCommission();
					//账户流水
					UserAccountBill userAccountBill = new UserAccountBill();
					userAccountBill.setUser_id(userInfoFinace.getUser_id());
					userAccountBill.setCreate_time(new Date());
					userAccountBill.setRemark("编号为 "+userInfoFinace.getUser_id()+"的用户，分佣进账 "+rebateAmount+" 元");
					userAccountBill.setChange_amt(rebateAmount);
					userAccountBill.setType("+");
					userAccountBill.setWallet(userInfoFinace.getCan_withdraw().add(rebateAmount));
					userAccountBillMapper.addUserAccountBillData(userAccountBill);
					//可提现金额
					BigDecimal canAmount = userInfoFinace.getCan_withdraw();
					//累计提现金额
					userInfoFinace.setCan_withdraw(canAmount.add(rebateAmount));
					userInfoFinaceMapper.updateUserInfoFinace(userInfoFinace);
				}
			}

			YmAcountBill ymAcountBill = new YmAcountBill();
			ymAcountBill.setOrder_no(order2.getOrder_no());
			ymAcountBill.setSettle_time(new Date());
			ymAcountBill.setUpdate_time(new Date());
			ymAcountBill.setStatus(2);
			rebateMapper.updateYmAcountBill(ymAcountBill);

		}
		
    	
    	
	}

}
