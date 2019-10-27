package com.heystyles.seguridad.api.exception;


public class Auth0InvalidUserException extends RuntimeException {

    public Auth0InvalidUserException(String message) {
        super(message);
    }
}
