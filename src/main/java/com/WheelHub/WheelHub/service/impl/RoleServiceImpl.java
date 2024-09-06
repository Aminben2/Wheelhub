package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.roleDtos.RoleDto;
import com.WheelHub.WheelHub.entity.Role;
import com.WheelHub.WheelHub.mapper.RoleMapper;
import com.WheelHub.WheelHub.repository.RoleRepository;
import com.WheelHub.WheelHub.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleDto createRole(RoleDto roleDTO) {
        Role role = RoleMapper.dtoToEntity(roleDTO);
        role = roleRepository.save(role);
        return RoleMapper.entityToDTO(role);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(RoleMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Role not found for id:" + id));
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::entityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found for id:" + id));
    }

    @Override
    public RoleDto updateRole(Long id, RoleDto roleDTO) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found for id:" + id));

        existingRole.setName(roleDTO.getName());
        // Note: The userIds set will not be updated as it's a bidirectional relationship and needs special handling.

        Role updatedRole = roleRepository.save(existingRole);
        return RoleMapper.entityToDTO(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found for id:" + id));
        roleRepository.delete(role);
    }
}
