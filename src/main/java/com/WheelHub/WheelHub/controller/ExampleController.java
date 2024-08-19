package com.WheelHub.WheelHub.controller;

import com.softskillsmanagementcore.dto.ErrorResponseDTO;
import com.softskillsmanagementcore.dto.ExampleDTO;
import com.softskillsmanagementcore.service.ExampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Example", description = "Example API")
@RequestMapping("example")
public class ExampleController {

    private final ExampleService exampleService;

    @Operation(summary = "Get an Example", description = "Get an Example by Id", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ExampleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("{id}")
    public ResponseEntity<ExampleDTO> findById(@PathVariable("id") String id){
        return new ResponseEntity<>(exampleService.findById(id), HttpStatus.OK);
    }
}
