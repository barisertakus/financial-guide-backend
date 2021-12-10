package com.bariser.financialguidebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleDTO {
    private String id;
    private String title;
    private String author;
    private LocalDateTime publishedDate;
    private String publishedDatePrecision;
    private String cleanUrl;
    private String excerpt;
    private String summary;
    private String rights;
    private int rank;
    private String topic;
    private String country;
    private String language;
    private String media;
    private Boolean isOpinion;
    private String twitterAccount;
}
