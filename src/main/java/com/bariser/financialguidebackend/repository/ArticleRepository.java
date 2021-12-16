package com.bariser.financialguidebackend.repository;

import com.bariser.financialguidebackend.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findFirst10By();
    List<Article> findFirst10ByTopic(String topic);

    @Query("SELECT a FROM Article a WHERE :search IS NULL OR a.summary LIKE %:search% ORDER BY a.publishedDate DESC")
    List<Article> getLatestSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT a FROM Article a WHERE :search IS NULL OR a.summary LIKE %:search%")
    List<Article> getBySearch(@Param("search") String search, Pageable pageable);
}
