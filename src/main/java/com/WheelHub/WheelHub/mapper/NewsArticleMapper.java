package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.newsArticleDtos.NewsArticleDto;
import com.WheelHub.WheelHub.entity.NewsArticle;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class NewsArticleMapper {

  public static NewsArticleDto entityToDTO(NewsArticle newsArticle) {
    return NewsArticleDto.builder()
        .title(newsArticle.getTitle())
        .content(newsArticle.getContent())
        .authorId(newsArticle.getAuthor() != null ? newsArticle.getAuthor().getId() : null)
        .vehicleId(newsArticle.getVehicle() != null ? newsArticle.getVehicle().getId() : null)
        .build();
  }

  public static NewsArticle dtoToEntity(NewsArticleDto newsArticleDto) {
    NewsArticle newsArticle =
        NewsArticle.builder()
            .title(newsArticleDto.getTitle())
            .content(newsArticleDto.getContent())
            .build();

    if (newsArticleDto.getAuthorId() != null) {
      User author = new User();
      author.setId(newsArticleDto.getAuthorId());
      newsArticle.setAuthor(author);
    }
    if (newsArticleDto.getVehicleId() != null) {
      Vehicle vehicle = new Vehicle();
      vehicle.setId(newsArticleDto.getVehicleId());
      newsArticle.setVehicle(vehicle);
    }

    return newsArticle;
  }
}
