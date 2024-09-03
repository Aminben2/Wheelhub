package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.savedSearchDtos.SavedSearchDto;
import com.WheelHub.WheelHub.entity.SavedSearch;

import java.util.List;

public interface SavedSearchService {

    SavedSearchDto createSavedSearch(SavedSearchDto savedSearchDTO);

    SavedSearchDto getSavedSearchById(Long id);

    List<SavedSearchDto> getAllSavedSearches();

    SavedSearchDto updateSavedSearch(Long id, SavedSearchDto savedSearchDTO);

    void deleteSavedSearch(Long id);

    SavedSearch findById(Long id);
}
