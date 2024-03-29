package com.gupaoedu.listener;

import com.gupaoedu.job.MyJob1;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

/**
 * 测试监听器
 */
public class MySchedulerListenerTest {
	public static void main(String[] args) throws SchedulerException {

		// JobDetail
		JobDetail jobDetail = JobBuilder.newJob(MyJob1.class).withIdentity("job1", "group1").build();

		// Trigger
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

		// SchedulerFactory
		SchedulerFactory  factory = new StdSchedulerFactory();

		// Scheduler
		Scheduler scheduler = factory.getScheduler();

		scheduler.scheduleJob(jobDetail, trigger);

		// 创建Scheduler Listener
		scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());

		scheduler.start();
		
	}

}
