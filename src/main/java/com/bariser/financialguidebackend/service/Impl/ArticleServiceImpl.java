package com.bariser.financialguidebackend.service.Impl;

import com.bariser.financialguidebackend.dto.ApiResponse;
import com.bariser.financialguidebackend.dto.ArticleDTO;
import com.bariser.financialguidebackend.entity.Article;
import com.bariser.financialguidebackend.repository.ArticleRepository;
import com.bariser.financialguidebackend.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String getArticlesFromCatcher() throws JsonProcessingException {
        final String uri = "https://api.newscatcherapi.com/v2/latest_headlines?countries=TR";
        getAndSaveDataFromURI(uri);
        return "Successfully saved.";
    }

    @Override
    public String getArticlesFromCatcherByTopic(String topic) throws JsonProcessingException {
        final String uri = "https://api.newscatcherapi.com/v2/latest_headlines?countries=TR&topic=" + topic;
        getAndSaveDataFromURI(uri);
        return "Successfully saved.";
    }

    @Override
    public List<ArticleDTO> getAll() {
        List<Article> articles = articleRepository.findAll();
        return modelMapper.map(articles, new TypeToken<List<ArticleDTO>>(){}.getType());
    }

    @Override
    public List<ArticleDTO> getLatestArticles() {
        List<Article> articles = articleRepository.findFirst10By();
        return modelMapper.map(articles, new TypeToken<List<ArticleDTO>>(){}.getType());
    }

    @Override
    public List<ArticleDTO> getLatestByTopic(String topic) {
        List<Article> articles = articleRepository.findFirst10ByTopic(topic);
        return modelMapper.map(articles, new TypeToken<List<ArticleDTO>>(){}.getType());
    }

    @Override
    public List<ArticleDTO> getLastSearchResults(String topic){
        Pageable pageable = PageRequest.of(0, 10);
        List<Article> articles = articleRepository.getLatestSearch(topic, pageable);
        return modelMapper.map(articles, new TypeToken<List<ArticleDTO>>(){}.getType());
    }

    @Override
    public List<ArticleDTO> getSearchResults(String search) {
        Pageable pageable = PageRequest.of(0, 100);
        List<Article> articles = articleRepository.getBySearch(search, pageable);
        return modelMapper.map(articles, new TypeToken<List<ArticleDTO>>(){}.getType());
    }


    public void getAndSaveDataFromURI(String uri) throws JsonProcessingException {
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
    }

}
