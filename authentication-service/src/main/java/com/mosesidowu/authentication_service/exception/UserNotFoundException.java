package com.mosesidowu.authentication_service.exception;

public class UserNotFoundException extends DarumException {
    public UserNotFoundException(String username) {
        super("User not found: " + username);
    }
}