package com.heystyles.seguridad.api.exception;

import javax.ws.rs.BadRequestException;

public class Auth0AssignmentException extends BadRequestException {

    public Auth0AssignmentException(String message) {
        super(message);
    }
}
