package com.bariser.financialguidebackend.service.Impl;

import com.bariser.financialguidebackend.quartz.job.ArticleGetJob;
import com.bariser.financialguidebackend.service.ArticleSchedulerService;
import com.bariser.financialguidebackend.utils.CreateJob;
import com.bariser.financialguidebackend.utils.QuartzConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArticleSchedulerServiceImpl implements ArticleSchedulerService {
    private final CreateJob createJob;
    private final String jobName = QuartzConstants.article_get_job;
    private final String jobGroup = QuartzConstants.article_job_group;
    private final String triggerName = QuartzConstants.article_get_trigger;
    private final String triggerGroup = QuartzConstants.article_trigger_group;

    public ArticleSchedulerServiceImpl(CreateJob createJob) {
        this.createJob = createJob;
    }

    @Override
    public ResponseEntity<?> createArticleJob() {
        return createJob.createWithCron(
                this.jobName, this.jobGroup,
                "Execute Article Service Job", ArticleGetJob.class,
                this.triggerName, this.triggerGroup,
                "Execute Article Service Trigger",
                "10,40 * * ? * * *");
    }
}
