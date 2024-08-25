package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.SavedSearchDTO;
import com.WheelHub.WheelHub.entity.SavedSearch;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;


@Component
public class SavedSearchMapper {

    public static SavedSearchDTO entityToDTO(SavedSearch savedSearch) {
        return SavedSearchDTO.builder()
                .id(savedSearch.getId())
                .userId(savedSearch.getUser() != null ? savedSearch.getUser().getId() : null)
                .searchCriteria(savedSearch.getSearchCriteria())
                .createdAt(savedSearch.getCreatedAt())
                .build();
    }

    public static SavedSearch dtoToEntity(SavedSearchDTO savedSearchDTO) {
        SavedSearch savedSearch = SavedSearch.builder()
                .id(savedSearchDTO.getId())
                .searchCriteria(savedSearchDTO.getSearchCriteria())
                .createdAt(savedSearchDTO.getCreatedAt())
                .build();

        if (savedSearchDTO.getUserId() != null) {
            User user = new User();
            user.setId(savedSearchDTO.getUserId());
            savedSearch.setUser(user);
        }

        return savedSearch;
    }
}
