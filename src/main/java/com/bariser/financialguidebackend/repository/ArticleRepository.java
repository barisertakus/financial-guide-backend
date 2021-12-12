package com.bariser.financialguidebackend.repository;

import com.bariser.financialguidebackend.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findFirst10By();
}
