package com.WheelHub.WheelHub.repository;

import com.softskillsmanagementcore.entity.Example;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends ListCrudRepository<Example, String> {
}
