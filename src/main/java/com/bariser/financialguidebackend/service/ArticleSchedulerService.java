package com.bariser.financialguidebackend.service;

import org.springframework.http.ResponseEntity;

public interface ArticleSchedulerService {
    ResponseEntity<?> createArticleJob();
}
