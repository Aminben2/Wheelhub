package com.WheelHub.WheelHub.mapper;

import com.softskillsmanagementcore.dto.ExampleDTO;
import com.softskillsmanagementcore.entity.Example;

public class ExampleMapper {

    public static ExampleDTO entityToDTO(Example example){
        return ExampleDTO.builder()
                .id(example.getId())
                .name(example.getName())
                .build();
    }

    public static Example dtoToEntity(ExampleDTO exampleDTO){
        return Example.builder()
                .id(exampleDTO.getId())
                .name(exampleDTO.getName())
                .build();
    }
}
