package com.bariser.financialguidebackend.repository;

import com.bariser.financialguidebackend.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {
}
