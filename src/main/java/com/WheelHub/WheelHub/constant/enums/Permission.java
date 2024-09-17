package com.WheelHub.WheelHub.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    USERS_READ("users:read"),
    USERS_UPDATE("users:update"),
    USERS_CREATE("users:create"),
    USERS_DELETE("users:delete"),
    USER_READ_BY_USERNAME("users:read-by-username"),

    VEHICLES_READ("vehicles:read"),
    VEHICLES_UPDATE("vehicles:update"),
    VEHICLES_CREATE("vehicles:create"),
    VEHICLES_DELETE("vehicles:delete"),
    VEHICLES_IMAGE_UPLOAD("vehicles:imageUpload"),

    REVIEWS_READ("reviews:read"),
    REVIEWS_UPDATE("reviews:update"),
    REVIEWS_CREATE("reviews:create"),
    REVIEWS_DELETE("reviews:delete"),

    PRICE_DROP_READ("priceDrop:read"),
    PRICE_DROP_UPDATE("priceDrop:update"),
    PRICE_DROP_CREATE("priceDrop:create"),
    PRICE_DROP_DELETE("priceDrop:delete"),

    VEHICLE_IMAGE_READ("vehicleImage:read"),
    VEHICLE_IMAGE_UPDATE("vehicleImage:update"),
    VEHICLE_IMAGE_CREATE("vehicleImage:create"),
    VEHICLE_IMAGE_DELETE("vehicleImage:delete"),

    VEHICLE_FEATURE_READ("vehicleFeature:read"),
    VEHICLE_FEATURE_UPDATE("vehicleFeature:update"),
    VEHICLE_FEATURE_CREATE("vehicleFeature:create"),
    VEHICLE_FEATURE_DELETE("vehicleFeature:delete"),

    VEHICLE_CATEGORY_READ("vehicleCategory:read"),
    VEHICLE_CATEGORY_UPDATE("vehicleCategory:update"),
    VEHICLE_CATEGORY_CREATE("vehicleCategory:create"),
    VEHICLE_CATEGORY_DELETE("vehicleCategory:delete"),

    VEHICLE_TYPE_READ("vehicleType:read"),
    VEHICLE_TYPE_UPDATE("vehicleType:update"),
    VEHICLE_TYPE_CREATE("vehicleType:create"),
    VEHICLE_TYPE_DELETE("vehicleType:delete"),
    ;

    private final String permission;
}