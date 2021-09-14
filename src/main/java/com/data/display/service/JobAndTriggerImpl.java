package com.data.display.service;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.data.display.util.BaseJob;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**  
* @ClassName: JobAndTriggerImpl  
* @Description: TODO()  
* @author lixin(1309244704@qq.com)  
* @date 2018年3月15日 上午10:03:00  
* @version V1.0  
*/ 
@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {
	private static final Logger logger = LoggerFactory.getLogger(JobAndTriggerImpl.class);
	@Autowired
	private Scheduler scheduler;
	

	@Override
	public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
		//XXX 启动调度器
		scheduler.start();

		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
				.withIdentity(jobClassName, jobGroupName).build();

		// 表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
				.withSchedule(scheduleBuilder).build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
			logger.info("创建定时任务成功");

		} catch (SchedulerException e) {
			logger.error("创建定时任务失败" + e);
			throw new Exception("创建定时任务失败");
		}
		
	}
	
	@Override
	public void addJob(String jobClassName, String jobGroupName, String cronExpression, String jobDescription,
			Map<String, Object> params) throws Exception {
		
		// 启动调度器
		scheduler.start();
		
		// 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(JobAndTriggerImpl.getClass(jobClassName).getClass())
        		.withIdentity(jobClassName, jobGroupName).withDescription(jobDescription).build();
        Iterator<Entry<String, Object>> var7 = params.entrySet().iterator();
        while(var7.hasNext()) {
            Entry<String, Object> entry = var7.next();
            jobDetail.getJobDataMap().put((String)entry.getKey(), entry.getValue());
        }
		logger.info("jobDetail数据：--------"+jobDetail.toString());
        // 表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        
        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
        		.withSchedule(scheduleBuilder).build();
        
        try {
			scheduler.scheduleJob(jobDetail, trigger);
			logger.info("创建定时任务成功");

		} catch (SchedulerException e) {
			logger.error("创建定时任务失败" + e);
			throw new Exception("创建定时任务失败");
		}
	}

	@Override
	public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
			// 表达式调度构建器（动态修改后不立即执行）
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			logger.error("更新定时任务失败" + e);
			throw new Exception("更新定时任务失败");
		}
	}

	@Override
	public void deleteJob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void pauseJob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void resumejob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
	}
	
	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}
	
}