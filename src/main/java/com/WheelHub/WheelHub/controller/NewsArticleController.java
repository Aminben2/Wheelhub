package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.newsArticleDtos.NewsArticleDto;
import com.WheelHub.WheelHub.service.NewsArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news-articles")
@RequiredArgsConstructor
@Validated
public class NewsArticleController {

  private final NewsArticleService newsArticleService;

  @PostMapping("/")
  public ResponseEntity<NewsArticleDto> createNewsArticle(
      @Valid @RequestBody NewsArticleDto newsArticleDto) {

    NewsArticleDto createdNewsArticle = newsArticleService.save(newsArticleDto);
    return new ResponseEntity<>(createdNewsArticle, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<NewsArticleDto> getNewsArticleById(@PathVariable @Min(1) Long id) {

    NewsArticleDto newsArticleDto = newsArticleService.findById(id);
    return new ResponseEntity<>(newsArticleDto, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<NewsArticleDto>> getAllNewsArticles() {
    List<NewsArticleDto> newsArticles = newsArticleService.findAll();
    return new ResponseEntity<>(newsArticles, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<NewsArticleDto> updateNewsArticle(
      @PathVariable @Min(1) Long id, @Valid @RequestBody NewsArticleDto newsArticleDto) {

    NewsArticleDto updatedNewsArticle = newsArticleService.update(id, newsArticleDto);
    return new ResponseEntity<>(updatedNewsArticle, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNewsArticle(@PathVariable @Min(1) Long id) {

    newsArticleService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
