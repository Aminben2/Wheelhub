package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.VehicleDTO;
import com.WheelHub.WheelHub.entity.Role;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.VehicleMapper;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        User seller = userRepository.findById(vehicleDTO.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + vehicleDTO.getSellerId()));

        Vehicle vehicle = VehicleMapper.dtoToEntity(vehicleDTO);
        vehicle.setSeller(seller);

        vehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.entityToDTO(vehicle);
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(VehicleMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + id));
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + id));
    }
    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(VehicleMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
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
