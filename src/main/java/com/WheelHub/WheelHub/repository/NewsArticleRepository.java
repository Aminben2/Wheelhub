package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {
}

