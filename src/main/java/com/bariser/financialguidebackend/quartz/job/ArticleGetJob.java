package com.bariser.financialguidebackend.quartz.job;

import com.bariser.financialguidebackend.service.ArticleService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Log4j2
public class ArticleGetJob extends QuartzJobBean {
    private final ArticleService articleService;

    public ArticleGetJob(ArticleService articleService) {
        this.articleService = articleService;
    }

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        articleService.getArticlesFromCatcher();

    }
}
