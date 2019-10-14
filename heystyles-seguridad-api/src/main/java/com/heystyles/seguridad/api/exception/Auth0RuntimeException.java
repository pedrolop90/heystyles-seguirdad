package com.heystyles.seguridad.api.exception;

import com.auth0.exception.Auth0Exception;
import com.heystyles.common.types.ExceptionGeneric;
import com.heystyles.seguridad.api.util.UtilHandlersExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class Auth0RuntimeException extends RuntimeException implements ExceptionGeneric<Auth0RuntimeException> {

    public Auth0RuntimeException(Auth0Exception cause) {
        super(cause);
    }

    @Override
    public String message() {
        return "";
    }

    @Override
    public ResponseEntity<Object> handlerResponse(Auth0RuntimeException ex,
                                                  WebRequest request) {
        return UtilHandlersExceptions.handlerExceptionAuth0(ex, request);
    }
}
