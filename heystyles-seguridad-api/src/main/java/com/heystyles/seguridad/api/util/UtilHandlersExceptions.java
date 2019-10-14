package com.heystyles.seguridad.api.util;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.heystyles.common.response.Responses;
import com.heystyles.common.types.ErrorResponse;
import com.heystyles.seguridad.api.exception.Auth0RuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public final class UtilHandlersExceptions extends com.heystyles.common.util.UtilHandlersExceptions {

    private UtilHandlersExceptions() {
    }

    public static ResponseEntity<Object> handlerExceptionAuth0(Auth0RuntimeException ex, WebRequest request) {
        Auth0Exception cause = (Auth0Exception) ex.getCause();
        ErrorResponse error = null;
        if (cause instanceof APIException) {
            APIException aux = (APIException) cause;
            error = Responses.error(HttpStatus.BAD_REQUEST, aux.getDescription());
        }
        else {
            error = Responses.error(HttpStatus.BAD_REQUEST, cause.getLocalizedMessage());
        }
        return handleExceptionInternal(cause, error, new HttpHeaders(), error.getStatus(), request);
    }

}
