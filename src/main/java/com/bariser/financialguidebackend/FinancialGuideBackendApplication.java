package com.bariser.financialguidebackend;

import com.bariser.financialguidebackend.service.ArticleSchedulerService;
import com.bariser.financialguidebackend.utils.QuartzConstants;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FinancialGuideBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialGuideBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner createJobsIfItsNotExist(ArticleSchedulerService articleSchedulerService, Scheduler scheduler) {
        return args -> {
            JobKey jobKey = new JobKey(QuartzConstants.article_get_job, QuartzConstants.article_job_group);
            if (!scheduler.checkExists(jobKey)) {
                articleSchedulerService.createArticleJob();
            }
        };
    }
}
