package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.roleDtos.RoleDto;
import com.WheelHub.WheelHub.entity.Role;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto roleDTO);

    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles();

    RoleDto updateRole(Long id, RoleDto roleDTO);

    void deleteRole(Long id);

    Role findById(Long id);
}

