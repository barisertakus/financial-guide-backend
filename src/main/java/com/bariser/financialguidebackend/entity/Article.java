package com.bariser.financialguidebackend.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String title;
    private String author;
    private LocalDateTime publishedDate;
    private String publishedDatePrecision;
    private String cleanUrl;
    @Column(columnDefinition = "TEXT")
    private String excerpt;
    @Column(columnDefinition = "TEXT")
    private String summary;
    private String rights;
    private int rank;
    private String topic;
    private String country;
    private String language;
 //   private List<String> authors;
    private String media;
    private Boolean isOpinion;
    private String twitterAccount;

}
