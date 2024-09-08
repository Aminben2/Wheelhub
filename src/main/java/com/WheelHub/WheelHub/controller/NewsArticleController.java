package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.newsArticleDtos.NewsArticleDto;
import com.WheelHub.WheelHub.service.NewsArticleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news-articles")
@RequiredArgsConstructor
@Validated
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @PostMapping("/")
    public ResponseEntity<NewsArticleDto> createNewsArticle(@Valid @RequestBody NewsArticleDto newsArticleDto) {
        try {
            NewsArticleDto createdNewsArticle = newsArticleService.save(newsArticleDto);
            return new ResponseEntity<>(createdNewsArticle, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticleDto> getNewsArticleById(@PathVariable @Min(1) Long id) {
        try {
            NewsArticleDto newsArticleDto = newsArticleService.findById(id);
            return new ResponseEntity<>(newsArticleDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<NewsArticleDto>> getAllNewsArticles() {
        try {
            List<NewsArticleDto> newsArticles = newsArticleService.findAll();
            return new ResponseEntity<>(newsArticles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsArticleDto> updateNewsArticle(@PathVariable @Min(1) Long id, @Valid @RequestBody NewsArticleDto newsArticleDto) {
        try {
            NewsArticleDto updatedNewsArticle = newsArticleService.update(id, newsArticleDto);
            return new ResponseEntity<>(updatedNewsArticle, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsArticle(@PathVariable @Min(1) Long id) {
        try {
            newsArticleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
