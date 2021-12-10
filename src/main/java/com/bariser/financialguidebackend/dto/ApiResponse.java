package com.bariser.financialguidebackend.dto;

import com.bariser.financialguidebackend.entity.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {

    private String status;

    @JsonProperty(value = "total_hits")
    private int totalHits;

    private int page;

    @JsonProperty(value = "total_pages")
    private int totalPages;

    @JsonProperty(value = "page_size")
    private int pageSize;

    private List<Article> articles;
}
