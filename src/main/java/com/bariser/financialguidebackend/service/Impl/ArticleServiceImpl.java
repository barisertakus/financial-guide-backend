package com.bariser.financialguidebackend.service.Impl;

import com.bariser.financialguidebackend.dto.ApiResponse;
import com.bariser.financialguidebackend.entity.Article;
import com.bariser.financialguidebackend.repository.ArticleRepository;
import com.bariser.financialguidebackend.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public String getArticlesFromCatcher() throws JsonProcessingException {
        final String uri = "https://api.newscatcherapi.com/v2/latest_headlines?countries=TR";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        httpHeaders.set("x-api-key", "9CW1ZjBAxP6BXlIHe5wnp3Brvgt0PdA4-Hut5M5xZQI");
        HttpEntity httpEntity = new HttpEntity<>("body",httpHeaders);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ApiResponse apiResponse = objectMapper.readValue(exchange.getBody(), ApiResponse.class);
        List<Article> articles = apiResponse.getArticles();
        articleRepository.saveAll(articles);
        return "Successfully saved.";
    }
}
