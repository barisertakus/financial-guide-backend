package com.bariser.financialguidebackend.service;

import com.bariser.financialguidebackend.dto.ArticleDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ArticleService {
    String getArticlesFromCatcher() throws JsonProcessingException;
    List<ArticleDTO> getAll();
}
