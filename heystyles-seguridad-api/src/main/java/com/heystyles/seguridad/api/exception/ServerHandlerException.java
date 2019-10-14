package com.heystyles.seguridad.api.exception;

import com.heystyles.common.exception.ErrorResponseException;
import com.heystyles.common.exception.ServerJpaResponseExceptionHandler;
import com.heystyles.common.types.ExceptionGeneric;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ServerHandlerException extends ServerJpaResponseExceptionHandler {

    @ExceptionHandler({
            Auth0RuntimeException.class
    })
    public final ResponseEntity<Object> serverHandler(Exception ex, WebRequest request) {
        if (ex instanceof ExceptionGeneric) {
            return ((ExceptionGeneric) ex).handlerResponse(ex, request);
        }
        return handleErrorResponseFailure((ErrorResponseException) ex, request);
    }
}
