package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.newsArticleDtos.NewsArticleDto;
import java.util.List;

public interface NewsArticleService {
  NewsArticleDto save(NewsArticleDto newsArticleDto);

  NewsArticleDto update(Long id, NewsArticleDto newsArticleDto);

  NewsArticleDto findById(Long id);

  List<NewsArticleDto> findAll();

  void deleteById(Long id);
}
