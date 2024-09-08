package com.WheelHub.WheelHub.dto.newsArticleDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewsArticleDto {
    private String title;
    private String content;
    private Long authorId;
    private Long vehicleId;
}
