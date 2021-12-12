package com.bariser.financialguidebackend.controller;

import com.bariser.financialguidebackend.dto.ArticleDTO;
import com.bariser.financialguidebackend.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/article")
@CrossOrigin
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("getArticlesFromCatcher")
    public ResponseEntity<String> getArticlesFromCatcher() throws JsonProcessingException {
        return ResponseEntity.ok(articleService.getArticlesFromCatcher());
    }

    @GetMapping("getArticlesFromCatcherByTopic")
    public ResponseEntity<String> getArticlesFromCatcherByTopic(@RequestParam String topic) throws JsonProcessingException {
        return ResponseEntity.ok(articleService.getArticlesFromCatcherByTopic(topic));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<ArticleDTO>> getAll(){
        return ResponseEntity.ok(articleService.getAll());
    }

    @GetMapping("getLatest")
    public ResponseEntity<List<ArticleDTO>> getLatestArticles(){
        return ResponseEntity.ok(articleService.getLatestArticles());
    }

    @GetMapping("getByTopic")
    public ResponseEntity<List<ArticleDTO>> getLatestByTopic(@RequestParam String topic){
        return ResponseEntity.ok(articleService.getLatestByTopic(topic));
    }

}
