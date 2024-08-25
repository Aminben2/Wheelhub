package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.SavedSearchDTO;
import com.WheelHub.WheelHub.entity.SavedSearch;

import java.util.List;

public interface SavedSearchService {

    SavedSearchDTO createSavedSearch(SavedSearchDTO savedSearchDTO);

    SavedSearchDTO getSavedSearchById(Long id);

    List<SavedSearchDTO> getAllSavedSearches();

    SavedSearchDTO updateSavedSearch(Long id, SavedSearchDTO savedSearchDTO);

    void deleteSavedSearch(Long id);

    SavedSearch findById(Long id);
}
