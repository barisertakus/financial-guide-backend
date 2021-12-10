package com.bariser.financialguidebackend.utils;

import org.quartz.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateJob {
    private final Scheduler scheduler;

    public CreateJob(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public ResponseEntity<?> createWithCron(String jobName, String jobGroup, String jobDescription, Class jobClass, String triggerName, String triggerGroup, String triggerDescription, String cron) {
        try {
            JobDetail jobDetail = buildJobDetail(jobName, jobGroup, jobDescription, jobClass);
            Trigger trigger = buildTrigger(jobDetail, triggerName, triggerGroup, triggerDescription, cron);
            scheduler.scheduleJob(jobDetail, trigger);

            return ResponseEntity.ok(jobName + " Created");
        } catch (SchedulerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    private JobDetail buildJobDetail(String jobName, String jobGroup, String description, Class jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroup)
                .withDescription(description)
                .storeDurably()
                .build();
    }

    private Trigger buildTrigger(JobDetail jobDetail, String triggerName, String triggerGroup, String description, String cron) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(triggerName, triggerGroup)
                .withDescription(description)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
    }
}
