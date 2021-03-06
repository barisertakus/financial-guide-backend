package com.bariser.financialguidebackend.service;

import com.bariser.financialguidebackend.dto.ArticleDTO;
import com.bariser.financialguidebackend.entity.Article;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ArticleService {
    String getArticlesFromCatcher() throws JsonProcessingException;
    String getArticlesFromCatcherByTopic(String topic) throws JsonProcessingException;

    List<ArticleDTO> getAll();

    List<ArticleDTO> getLatestArticles();

    List<ArticleDTO> getLatestByTopic(String topic);

    List<ArticleDTO> getLastSearchResults(String topic);

    List<ArticleDTO> getSearchResults(String search);
}
