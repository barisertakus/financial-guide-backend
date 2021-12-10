package com.bariser.financialguidebackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ArticleService {
    String getArticlesFromCatcher() throws JsonProcessingException;
}
