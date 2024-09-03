package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.savedSearchDtos.SavedSearchDto;
import com.WheelHub.WheelHub.entity.SavedSearch;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.mapper.SavedSearchMapper;
import com.WheelHub.WheelHub.repository.SavedSearchRepository;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.service.SavedSearchService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedSearchServiceImpl implements SavedSearchService {

    private final SavedSearchRepository savedSearchRepository;
    private final UserRepository userRepository;

    @Override
    public SavedSearchDto createSavedSearch(SavedSearchDto savedSearchDTO) {
        User user = userRepository.findById(savedSearchDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + savedSearchDTO.getUserId()));

        SavedSearch savedSearch = SavedSearchMapper.dtoToEntity(savedSearchDTO);
        savedSearch.setUser(user);
        savedSearch = savedSearchRepository.save(savedSearch);
        return SavedSearchMapper.entityToDTO(savedSearch);
    }

    @Override
    public SavedSearchDto getSavedSearchById(Long id) {
        return savedSearchRepository.findById(id)
                .map(SavedSearchMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("SavedSearch not found for id:" + id));
    }

    @Override
    public List<SavedSearchDto> getAllSavedSearches() {
        return savedSearchRepository.findAll().stream()
                .map(SavedSearchMapper::entityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public SavedSearch findById(Long id) {
        return savedSearchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavedSearch not found for id:" + id));
    }

    @Override
    public SavedSearchDto updateSavedSearch(Long id, SavedSearchDto savedSearchDTO) {
        SavedSearch existingSavedSearch = savedSearchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavedSearch not found for id:" + id));

        User user = userRepository.findById(savedSearchDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + savedSearchDTO.getUserId()));

        existingSavedSearch.setUser(user);
        existingSavedSearch.setSearchCriteria(savedSearchDTO.getSearchCriteria());

        SavedSearch updatedSavedSearch = savedSearchRepository.save(existingSavedSearch);
        return SavedSearchMapper.entityToDTO(updatedSavedSearch);
    }

    @Override
    public void deleteSavedSearch(Long id) {
        SavedSearch savedSearch = savedSearchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavedSearch not found for id:" + id));
        savedSearchRepository.delete(savedSearch);
    }
}
