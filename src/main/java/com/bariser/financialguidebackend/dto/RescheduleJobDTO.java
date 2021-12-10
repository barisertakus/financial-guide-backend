package com.bariser.financialguidebackend.dto;

import lombok.Data;

@Data
public class RescheduleJobDTO {

    private int hour;
    private String cron;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;

}
