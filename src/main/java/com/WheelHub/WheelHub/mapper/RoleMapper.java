package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.RoleDTO;
import com.WheelHub.WheelHub.entity.Role;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class RoleMapper {

    public static RoleDTO entityToDTO(Role role) {
        if (role == null) {
            return null;
        }

        Set<Long> userIds = role.getUsers() != null ? role.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toSet()) : Set.of();

        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .userIds(userIds)
                .build();
    }

    public static Role dtoToEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role.RoleBuilder roleBuilder = Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName());

        if (roleDTO.getUserIds() != null) {
            Set<User> users = roleDTO.getUserIds().stream()
                    .map(userId -> {
                        User user = new User();
                        user.setId(userId);
                        return user;
                    })
                    .collect(Collectors.toSet());
            roleBuilder.users(users);
        }

        return roleBuilder.build();
    }
}
