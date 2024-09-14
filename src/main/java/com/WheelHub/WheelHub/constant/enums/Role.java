package com.WheelHub.WheelHub.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.WheelHub.WheelHub.constant.enums.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(
            Set.of(
                    VEHICLES_READ,
                    USER_READ_BY_USERNAME
            )
    ),
    ADMIN(
            Set.of(
                    USERS_DELETE,
                    USERS_CREATE,
                    USERS_READ,
                    USERS_UPDATE,
                    VEHICLES_CREATE,
                    VEHICLES_UPDATE,
                    VEHICLES_DELETE,
                    VEHICLES_READ,
                    USER_READ_BY_USERNAME
            )
    ),
    BUYER(
            Set.of(
                    VEHICLES_READ,
                    USER_READ_BY_USERNAME
            )
    ),
    SELLER(
            Set.of(
                    VEHICLES_CREATE,
                    VEHICLES_UPDATE,
                    VEHICLES_DELETE,
                    VEHICLES_READ,
                    USER_READ_BY_USERNAME
            )
    )

    ;

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}