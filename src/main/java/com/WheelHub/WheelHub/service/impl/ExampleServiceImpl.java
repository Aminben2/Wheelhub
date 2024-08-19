package com.WheelHub.WheelHub.service.impl;

import com.softskillsmanagementcore.dto.ExampleDTO;
import com.softskillsmanagementcore.mapper.ExampleMapper;
import com.softskillsmanagementcore.repository.ExampleRepository;
import com.softskillsmanagementcore.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

    @Override
    public ExampleDTO findById(String id) {
        return ExampleMapper.entityToDTO(exampleRepository.findById(id).get());
    }
}
