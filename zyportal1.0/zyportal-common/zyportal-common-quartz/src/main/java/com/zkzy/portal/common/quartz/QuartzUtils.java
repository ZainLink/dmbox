package com.zkzy.portal.common.quartz;

import org.quartz.*;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/6/29 0029.
 * Quartz 工具类
 */

public class QuartzUtils {


    /**
     * 验证是否存在
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public  boolean checkExists(Scheduler scheduler,String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }

    /**
     * 获取 triggerKey
     * @param jobName
     * @param jobGroup
     * @return
     */
    public  TriggerKey getTriggerKey(String jobName,String jobGroup){
        return TriggerKey.triggerKey(jobName, jobGroup);
    }

    /**
     * 获取 Trigger by triggerKey
     * @param triggerKey
     * @return
     * @throws SchedulerException
     */
    public  Trigger getTrigger(Scheduler scheduler,TriggerKey triggerKey) throws SchedulerException {
        return scheduler.getTrigger(triggerKey);
    }

    /**
     * 获取 Trigger by jobName  和 jobGroup
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public  Trigger getTrigger(Scheduler scheduler,String jobName,String jobGroup) throws SchedulerException {
        TriggerKey triggerKey=getTriggerKey(jobName, jobGroup);
        return scheduler.getTrigger(triggerKey);
    }

    /**
     * 获得 quartz中Job的执行状态
     * @param scheduler
     * @param triggerKey
     * @return
     * @throws SchedulerException
     */
    public Trigger.TriggerState getTriggerState(Scheduler scheduler, TriggerKey triggerKey) throws SchedulerException {
        return scheduler.getTriggerState(triggerKey);
    }


    /**
     * 获取 jobKey
     * @param jobName
     * @param jobGroup
     * @return
     */
    public  JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

    /**
     * 替换加入到调度中的任务
     * @param jobDetail
     * @param triggerSet
     * @param replace
     * @throws SchedulerException
     */
    public  void scheduleJob(Scheduler scheduler,JobDetail jobDetail, HashSet<Trigger> triggerSet, boolean replace) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, triggerSet, true);
    }

    /**
     *任务加入到调度中
     * @param jobDetail
     * @param cronTrigger
     * @throws SchedulerException
     */
    public  void scheduleJob(Scheduler scheduler,JobDetail jobDetail, CronTrigger cronTrigger) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 重启定时任务  by triggerKey
     * @param triggerKey
     * @throws SchedulerException
     */
    public  void resumeTrigger(Scheduler scheduler,TriggerKey triggerKey) throws SchedulerException {
        scheduler.resumeTrigger(triggerKey);
    }

    /**
     * 暂停定时任务 by triggerKey
     * @param triggerKey
     */
    public  void pauseTrigger(Scheduler scheduler,TriggerKey triggerKey) throws SchedulerException {
        scheduler.pauseTrigger(triggerKey);
    }

    /**
     * 移除定时任务 by  triggerKey
     * @param triggerKey
     * @throws SchedulerException
     */
    public  void unscheduleJob(Scheduler scheduler,TriggerKey triggerKey) throws SchedulerException {
        scheduler.unscheduleJob(triggerKey);
    }

    /**
     * 获取 JobDetail  from scheduler
     * @param jobKey
     * @return
     * @throws SchedulerException
     */
    public  JobDetail getJobDetail(Scheduler scheduler,JobKey jobKey) throws SchedulerException {
        return scheduler.getJobDetail(jobKey);
    }

    /**
     * 初始化一个 JobDetail
     * @return
     */
    public  JobDetail initJobDetail(Class <? extends Job> jobClass,JobKey jobKey,String jobDescription){
        return JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(jobDescription).build();
    }

    /**
     * 获取 CronScheduleBuilder
     * @param cronExpression
     * @return
     */
    public  CronScheduleBuilder getCronScheduleBuilder(String cronExpression){
        return CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
    }

    /**
     * 获取  CronTrigger
     * @return
     */
    public  CronTrigger getCronTrigger(TriggerKey triggerKey,JobKey jobKey,CronScheduleBuilder cronScheduleBuilder,String jobDescription ){
        return TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey).withDescription(jobDescription).withSchedule(cronScheduleBuilder).build();
    }
    public  CronTrigger getCronTrigger(TriggerKey triggerKey,JobKey jobKey,String cronExpression,String jobDescription ){
        CronScheduleBuilder cronScheduleBuilder=getCronScheduleBuilder(cronExpression);
        return TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey).withDescription(jobDescription).withSchedule(cronScheduleBuilder).build();
    }





}
