package com.bariser.financialguidebackend.controller;

import com.bariser.financialguidebackend.dto.ArticleDTO;
import com.bariser.financialguidebackend.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("getArticlesFromCatcher")
    public ResponseEntity<String> getArticlesFromCatcher() throws JsonProcessingException {
        return ResponseEntity.ok(articleService.getArticlesFromCatcher());
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAll(){
        return ResponseEntity.ok(articleService.getAll());
    }
}
