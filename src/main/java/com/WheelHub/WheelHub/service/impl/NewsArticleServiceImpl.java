package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.newsArticleDtos.NewsArticleDto;
import com.WheelHub.WheelHub.entity.NewsArticle;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.NewsArticleMapper;
import com.WheelHub.WheelHub.repository.NewsArticleRepository;
import com.WheelHub.WheelHub.service.NewsArticleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsArticleServiceImpl implements NewsArticleService {

    private final NewsArticleRepository newsArticleRepository;

    @Override
    public NewsArticleDto save(NewsArticleDto newsArticleDto) {
        NewsArticle newsArticle = NewsArticleMapper.dtoToEntity(newsArticleDto);
        newsArticle = newsArticleRepository.save(newsArticle);
        return NewsArticleMapper.entityToDTO(newsArticle);
    }

    @Override
    public NewsArticleDto update(Long id, NewsArticleDto newsArticleDto) {
        NewsArticle existingNewsArticle = newsArticleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News article not found for id: " + id));

        existingNewsArticle.setTitle(newsArticleDto.getTitle());
        existingNewsArticle.setContent(newsArticleDto.getContent());

        if (newsArticleDto.getAuthorId() != null) {
            User author = new User();
            author.setId(newsArticleDto.getAuthorId());
            existingNewsArticle.setAuthor(author);
        }
        if (newsArticleDto.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(newsArticleDto.getVehicleId());
            existingNewsArticle.setVehicle(vehicle);
        }

        NewsArticle updatedNewsArticle = newsArticleRepository.save(existingNewsArticle);
        return NewsArticleMapper.entityToDTO(updatedNewsArticle);
    }


    @Override
    public NewsArticleDto findById(Long id) {
        return newsArticleRepository.findById(id)
                .map(NewsArticleMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("News article not found for id: " + id));
    }

    @Override
    public List<NewsArticleDto> findAll() {
        return newsArticleRepository.findAll().stream()
                .map(NewsArticleMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        NewsArticle newsArticle = newsArticleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News article not found for id: " + id));
        newsArticleRepository.delete(newsArticle);
    }
}
