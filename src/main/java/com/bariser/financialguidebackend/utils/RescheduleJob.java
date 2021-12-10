package com.bariser.financialguidebackend.utils;

import com.bariser.financialguidebackend.dto.RescheduleJobDTO;
import org.quartz.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RescheduleJob {

    private final Scheduler scheduler;

    public RescheduleJob(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public ResponseEntity<?> rescheduleByHour(RescheduleJobDTO rescheduleJobDTO, String triggerName, String triggerGroup, String jobName, String jobGroup) {
        String cron = createCronFromHour(rescheduleJobDTO.getHour());
        if (cron == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("hour should be lower than 720");
        }

        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        JobKey jobKey = new JobKey(jobName, jobGroup);
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            Trigger newTrigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(trigger.getKey().getName(), trigger.getKey().getGroup())
                    .withDescription(trigger.getDescription())
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            scheduler.rescheduleJob(triggerKey, newTrigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok("successfully updated");
    }

    public ResponseEntity<?> rescheduleByCron(RescheduleJobDTO rescheduleJobDTO, String triggerName, String triggerGroup, String jobName, String jobGroup) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        JobKey jobKey = new JobKey(jobName, jobGroup);
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            Trigger newTrigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(trigger.getKey().getName(), trigger.getKey().getGroup())
                    .withDescription(trigger.getDescription())
                    .withSchedule(CronScheduleBuilder.cronSchedule(rescheduleJobDTO.getCron()))
                    .build();
            scheduler.rescheduleJob(triggerKey, newTrigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok("successfully updated");
    }

    private String createCronFromHour(int hour) {
        String cron = null;
        int day = hour / 24;
        if (day >= 30) {
            return null;
        }
        if (day > 0) {
            hour = hour % 24;
            cron = "0 0 " + hour + " 1/" + day + " * ? *";
        } else {
            cron = "0 0 0/" + hour + " " + "1/1 * ? *";
        }

        return cron;
    }
}