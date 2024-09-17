package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleDto;
import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleResponseDto;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.entity.VehicleImage;
import com.WheelHub.WheelHub.mapper.VehicleMapper;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.repository.VehicleImageRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final VehicleImageRepository vehicleImageRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public List<String> saveImages(MultipartFile[] imageFiles, Long vehicleId) {
        try {

            // Ensure the vehicle exists
            Vehicle vehicle = vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            // List to hold URLs of uploaded images
            List<String> imageUrls = new ArrayList<>();

            // Generate the directory for saving images
            File dir = new File(uploadDir + "/vehicleImages/");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Loop through each image file
            for (MultipartFile imageFile : imageFiles) {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                File file = new File(dir, fileName);

                // Save the file locally
                imageFile.transferTo(file);

                // Create a VehicleImage entity and save it
                VehicleImage vehicleImage = new VehicleImage();
                vehicleImage.setImageUrl("/uploads/vehicleImages/" + fileName); // Store relative path
                vehicleImage.setVehicle(vehicle);
                vehicleImageRepository.save(vehicleImage);

                // Add the image URL to the list
                imageUrls.add("/uploads/vehicleImages/" + fileName);
            }

            return imageUrls;
        }catch (IOException e) {
            throw new RuntimeException("Failed to save images", e);
        }
    }

    @Override
    @Transactional
    public VehicleDto createVehicle(VehicleDto vehicleDTO) {
        User seller = userRepository.findById(vehicleDTO.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + vehicleDTO.getSellerId()));

        Vehicle vehicle = VehicleMapper.dtoToEntity(vehicleDTO);
        vehicle.setSeller(seller);

        vehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.entityToDTO(vehicle);
    }

    @Override
    public VehicleResponseDto getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(VehicleMapper::entityToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + id));
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + id));
    }
    @Override
    public List<VehicleResponseDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(VehicleMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleDto updateVehicle(Long id, VehicleDto vehicleDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + id));

        User seller = userRepository.findById(vehicleDTO.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + vehicleDTO.getSellerId()));

        existingVehicle.setSeller(seller);
        existingVehicle.setMake(vehicleDTO.getMake());
        existingVehicle.setModel(vehicleDTO.getModel());
        existingVehicle.setYear(vehicleDTO.getYear());
        existingVehicle.setMileage(vehicleDTO.getMileage());
        existingVehicle.setPrice(vehicleDTO.getPrice());
        existingVehicle.setDescription(vehicleDTO.getDescription());
        existingVehicle.setLocation(vehicleDTO.getLocation());

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return VehicleMapper.entityToDTO(updatedVehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + id));
        vehicleRepository.delete(vehicle);
    }
}
