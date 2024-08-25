package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.RoleDTO;
import com.WheelHub.WheelHub.entity.Role;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO getRoleById(Long id);

    List<RoleDTO> getAllRoles();

    RoleDTO updateRole(Long id, RoleDTO roleDTO);

    void deleteRole(Long id);

    Role findById(Long id);
}

