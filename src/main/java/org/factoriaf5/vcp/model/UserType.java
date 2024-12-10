package org.factoriaf5.vcp.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    ADMIN,
    USER;

    @JsonCreator
    public static UserType fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return UserType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid user type: " + value);
        }
    }
}
