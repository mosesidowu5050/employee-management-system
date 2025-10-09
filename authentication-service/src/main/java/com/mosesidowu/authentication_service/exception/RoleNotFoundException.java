package com.mosesidowu.authentication_service.exception;

public class RoleNotFoundException extends DarumException {
    public RoleNotFoundException(String roleName) {
        super("Role not found: " + roleName);
    }
}