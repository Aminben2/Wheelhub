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

    REVIEWS_READ("reviews:read"),
    REVIEWS_UPDATE("reviews:update"),
    REVIEWS_CREATE("reviews:create"),
    REVIEWS_DELETE("reviews:delete"),

    ;

    private final String permission;
}