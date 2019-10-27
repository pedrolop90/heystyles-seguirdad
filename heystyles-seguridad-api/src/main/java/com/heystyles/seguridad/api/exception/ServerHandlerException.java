package com.heystyles.seguridad.api.exception;

import com.heystyles.common.exception.ErrorResponseException;
import com.heystyles.common.exception.ServerJpaResponseExceptionHandler;
import com.heystyles.common.response.Responses;
import com.heystyles.common.types.ErrorResponse;
import com.heystyles.common.types.ExceptionGeneric;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ServerHandlerException extends ServerJpaResponseExceptionHandler {

    @ExceptionHandler({
            Auth0RuntimeException.class,
            Auth0InvalidUserException.class
    })
    public final ResponseEntity<Object> serverHandler(Exception ex, WebRequest request) {
        if (ex instanceof ExceptionGeneric) {
            return ((ExceptionGeneric) ex).handlerResponse(ex, request);
        }
        else if (ex instanceof Auth0InvalidUserException) {
            return handleAuth0InvalidUser((Auth0InvalidUserException) ex, request);
        }
        return handleErrorResponseFailure((ErrorResponseException) ex, request);
    }

    private ResponseEntity<Object> handleAuth0InvalidUser(Auth0InvalidUserException ex, WebRequest request) {
        ErrorResponse error = Responses.error(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }
}
