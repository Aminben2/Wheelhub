package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.savedSearchDtos.SavedSearchDto;
import com.WheelHub.WheelHub.entity.SavedSearch;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SavedSearchMapper {

  public static SavedSearchDto entityToDTO(SavedSearch savedSearch) {
    return SavedSearchDto.builder()
        .userId(savedSearch.getUser() != null ? savedSearch.getUser().getId() : null)
        .searchCriteria(savedSearch.getSearchCriteria())
        .build();
  }

  public static SavedSearch dtoToEntity(SavedSearchDto savedSearchDTO) {
    SavedSearch savedSearch =
        SavedSearch.builder().searchCriteria(savedSearchDTO.getSearchCriteria()).build();

    if (savedSearchDTO.getUserId() != null) {
      User user = new User();
      user.setId(savedSearchDTO.getUserId());
      savedSearch.setUser(user);
    }

    return savedSearch;
  }
}
