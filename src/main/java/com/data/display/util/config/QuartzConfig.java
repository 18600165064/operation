package com.data.display.util.config;

import com.data.display.quartz.*;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: CYN
 * @Date: 2018/12/26 14:11
 * @Description:定时任务配置
 */
@Configuration
public class QuartzConfig {

	/**
	 * 快递详情
	 */
    @Bean
    public JobDetail deliverySyncJobDetail() {
        return JobBuilder.newJob(DeliverySyncJob.class).withIdentity("deliverySyncJob").storeDurably().build();
    }

    @Bean
    public Trigger deliverySyncJobTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 50 * * * ?");
        return TriggerBuilder.newTrigger().forJob(deliverySyncJobDetail())
                .withIdentity("deliverySyncJob","deliverySyncJob")
                .withSchedule(scheduleBuilder).build();
    }


	/**
	 * 订单确认收货状态
	 */
	 @Bean
	 public JobDetail orderJobDetail() {
        return JobBuilder.newJob(OrderJob.class).withIdentity("orderJob").storeDurably().build();
	 }

	 @Bean
	 public Trigger orderJobTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 3 * * ?");
        return TriggerBuilder.newTrigger().forJob(orderJobDetail())
                .withIdentity("orderJob","orderJob")
                .withSchedule(scheduleBuilder).build();
	 }

	 /**
	  * 用户结算
	  */
	 @Bean
	 public JobDetail userOrderSettleJobDetail() {
        return JobBuilder.newJob(UserOrderSettleJob.class).withIdentity("userOrderSettleJob").storeDurably().build();
	 }

	 @Bean
	 public Trigger userOrderSettleJobTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 2 * * ?");
        return TriggerBuilder.newTrigger().forJob(userOrderSettleJobDetail())
                .withIdentity("userOrderSettleJob","userOrderSettleJob")
                .withSchedule(scheduleBuilder).build();
	 }

	/**
	 * 供应商结算
	 * @return
	 */
	 @Bean
	 public JobDetail supplierOrderSettleDetail() {
        return JobBuilder.newJob(SupplierOrderSettleJob.class).withIdentity("supplierOrderSettleJob").storeDurably().build();
	 }

	 @Bean
	 public Trigger supplierOrderSettleJobTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 1 * * ?");
        return TriggerBuilder.newTrigger().forJob(supplierOrderSettleDetail())
                .withIdentity("supplierOrderSettleJob","supplierOrderSettleJob")
                .withSchedule(scheduleBuilder).build();
	 }

	/**
	 * 返现补偿
	 * @return
	 */
	@Bean
	public JobDetail cashCompensateJob() {
		return JobBuilder.newJob(CashCompensateJob.class).withIdentity("cashCompensateJob").storeDurably().build();
	}

	@Bean
	public Trigger cashCompensateJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 0 * * ?");
		return TriggerBuilder.newTrigger().forJob(cashCompensateJob())
				.withIdentity("cashCompensateJob","cashCompensateJob")
				.withSchedule(scheduleBuilder).build();
	}

	/**
	 * 关闭订单
	 * @return
	 */
	@Bean
	public JobDetail orderCloseJob() {
		return JobBuilder.newJob(OrderCloseJob.class).withIdentity("orderCloseJob").storeDurably().build();
	}

	@Bean
	public Trigger orderCloseJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/20 * * * ?");
		return TriggerBuilder.newTrigger().forJob(orderCloseJob())
				.withIdentity("orderCloseJob","orderCloseJob")
				.withSchedule(scheduleBuilder).build();
	}


	/**
	 * 上架商品销量
	 * @return
	 */
	@Bean
	public JobDetail spuSaleAmountJob() {
		return JobBuilder.newJob(SpuSaleAmountJob.class).withIdentity("spuSaleAmountJob").storeDurably().build();
	}

	@Bean
	public Trigger spuSaleAmountJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/10 * * * ?");
		return TriggerBuilder.newTrigger().forJob(spuSaleAmountJob())
				.withIdentity("spuSaleAmountJob","spuSaleAmountJob")
				.withSchedule(scheduleBuilder).build();
	}

	/**
	 * 初始化用户可提现次数
	 * 每月股东分红结算 只修改状态
	 * @return
	 */
	@Bean
	public JobDetail userWithdrawTimesJob() {
		return JobBuilder.newJob(UserWithdrawTimesJob.class).withIdentity("userWithdrawTimesJob").storeDurably().build();
	}

	@Bean
	public Trigger userWithdrawTimesJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 5 0 1 * ?");
		return TriggerBuilder.newTrigger().forJob(userWithdrawTimesJob())
				.withIdentity("userWithdrawTimesJob","userWithdrawTimesJob")
				.withSchedule(scheduleBuilder).build();
	}


	/**
	 * 合伙人池分红定时机制
	 * @return
	 */
	@Bean
	public JobDetail bonusJob() {
		return JobBuilder.newJob(BonusJob.class).withIdentity("bonusJob").storeDurably().build();
	}

	@Bean
	public Trigger bonusJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 00 10 * * ?");
		return TriggerBuilder.newTrigger().forJob(bonusJob())
				.withIdentity("bonusJob","bonusJob")
				.withSchedule(scheduleBuilder).build();
	}


	/**
	 * 股东池分红定时机制
	 */
	@Bean
	public JobDetail bonusGesellschafterJob() {
		return JobBuilder.newJob(BonusGesellschafterJob.class).withIdentity("bonusGesellschafterJob").storeDurably().build();
	}

	@Bean
	public Trigger bonusGesellschafterJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 03 10 * * ?");
		return TriggerBuilder.newTrigger().forJob(bonusGesellschafterJob())
				.withIdentity("bonusGesellschafterJob","bonusGesellschafterJob")
				.withSchedule(scheduleBuilder).build();
	}



	/**
	 * 用户分红 转到可提现钱包
	 * @return
	 */
	@Bean
	public JobDetail bonusUserJob() {
		return JobBuilder.newJob(BonusUserJob.class).withIdentity("bonusUserJob").storeDurably().build();
	}

	@Bean
	public Trigger bonusUserJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 8 * * ?");
		return TriggerBuilder.newTrigger().forJob(bonusUserJob())
				.withIdentity("bonusUserJob","bonusUserJob")
				.withSchedule(scheduleBuilder).build();
	}


	/**
	 * 更改股东结算状态
	 * @return
	 */
	@Bean
	public JobDetail bonusStockholderSettleJob() {
		return JobBuilder.newJob(BonusStockholderSettleJob.class).withIdentity("bonusStockholderSettleJob").storeDurably().build();
	}

	@Bean
	public Trigger bonusStockholderSettleJobTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 23 10 * ?");
		return TriggerBuilder.newTrigger().forJob(bonusStockholderSettleJob())
				.withIdentity("bonusStockholderSettleJob","bonusStockholderSettleJob")
				.withSchedule(scheduleBuilder).build();
	}


}
