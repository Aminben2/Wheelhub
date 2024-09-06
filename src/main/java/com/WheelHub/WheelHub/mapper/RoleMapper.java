package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.roleDtos.RoleDto;
import com.WheelHub.WheelHub.entity.Role;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class RoleMapper {

    public static RoleDto entityToDTO(Role role) {
        if (role == null) {
            return null;
        }

        Set<Long> userIds = role.getUsers() != null ? role.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toSet()) : Set.of();

        return RoleDto.builder()
                .name(role.getName())
                .build();
    }

    public static Role dtoToEntity(RoleDto roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role.RoleBuilder roleBuilder = Role.builder()
                .name(roleDTO.getName());

        return roleBuilder.build();
    }
}
