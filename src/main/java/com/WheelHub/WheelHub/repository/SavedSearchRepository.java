package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.SavedSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedSearchRepository extends JpaRepository<SavedSearch, Long> {
}
