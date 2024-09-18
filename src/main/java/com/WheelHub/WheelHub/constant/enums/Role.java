package com.WheelHub.WheelHub.constant.enums;

import static com.WheelHub.WheelHub.constant.enums.Permission.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {
  ADMIN(
      Set.of(
          USERS_DELETE,
          USERS_CREATE,
          USERS_READ,
          USERS_UPDATE,
          USER_READ_BY_USERNAME,
          VEHICLES_CREATE,
          VEHICLES_UPDATE,
          VEHICLES_DELETE,
          VEHICLES_READ,
          VEHICLES_IMAGE_UPLOAD,
          REVIEWS_READ,
          REVIEWS_UPDATE,
          REVIEWS_CREATE,
          REVIEWS_DELETE,
          PRICE_DROP_READ,
          PRICE_DROP_UPDATE,
          PRICE_DROP_CREATE,
          PRICE_DROP_DELETE,
          VEHICLE_IMAGE_READ,
          VEHICLE_IMAGE_UPDATE,
          VEHICLE_IMAGE_CREATE,
          VEHICLE_IMAGE_DELETE,
          VEHICLE_FEATURE_READ,
          VEHICLE_FEATURE_UPDATE,
          VEHICLE_FEATURE_CREATE,
          VEHICLE_FEATURE_DELETE,
          VEHICLE_CATEGORY_READ,
          VEHICLE_CATEGORY_UPDATE,
          VEHICLE_CATEGORY_CREATE,
          VEHICLE_CATEGORY_DELETE,
          VEHICLE_TYPE_READ,
          VEHICLE_TYPE_UPDATE,
          VEHICLE_TYPE_CREATE,
          VEHICLE_TYPE_DELETE)),
  BUYER(
      Set.of(
          VEHICLES_READ,
          USER_READ_BY_USERNAME,
          REVIEWS_READ,
          REVIEWS_UPDATE,
          REVIEWS_CREATE,
          REVIEWS_DELETE,
          VEHICLE_FEATURE_READ,
          VEHICLE_IMAGE_READ,
          PRICE_DROP_READ,
          VEHICLE_TYPE_READ,
          VEHICLE_CATEGORY_READ)),
  SELLER(
      Set.of(
          VEHICLES_CREATE,
          VEHICLES_UPDATE,
          VEHICLES_DELETE,
          VEHICLES_READ,
          VEHICLES_IMAGE_UPLOAD,
          USER_READ_BY_USERNAME,
          REVIEWS_READ,
          VEHICLE_FEATURE_UPDATE,
          VEHICLE_FEATURE_CREATE,
          VEHICLE_FEATURE_DELETE,
          VEHICLE_IMAGE_UPDATE,
          VEHICLE_IMAGE_CREATE,
          VEHICLE_IMAGE_DELETE,
          PRICE_DROP_UPDATE,
          PRICE_DROP_CREATE,
          PRICE_DROP_DELETE,
          VEHICLE_TYPE_READ,
          VEHICLE_CATEGORY_READ));

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities =
        getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
