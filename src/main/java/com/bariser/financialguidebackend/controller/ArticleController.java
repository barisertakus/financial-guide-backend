package com.bariser.financialguidebackend.controller;

import com.bariser.financialguidebackend.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("getArticles")
    public ResponseEntity<String> getArticles() throws JsonProcessingException {
        return ResponseEntity.ok(articleService.getArticles());
    }
}
