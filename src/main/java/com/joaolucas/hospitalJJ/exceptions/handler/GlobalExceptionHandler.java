package com.joaolucas.hospitalJJ.exceptions.handler;

import com.joaolucas.hospitalJJ.exceptions.BadRequestException;
import com.joaolucas.hospitalJJ.exceptions.BusinessLogicException;
import com.joaolucas.hospitalJJ.exceptions.ResourceNotFoundException;
import com.joaolucas.hospitalJJ.exceptions.body.ExceptionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseBody> handleGenericException(Exception exception){

        String error = HttpStatus.INTERNAL_SERVER_ERROR.name();
        int errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseBody> handleResourceNotFoundException(ResourceNotFoundException exception){

        String error = HttpStatus.NOT_FOUND.name();
        int errorCode = HttpStatus.NOT_FOUND.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ExceptionResponseBody> handleBusinessLogicException(BusinessLogicException exception){

        String error = HttpStatus.UNPROCESSABLE_ENTITY.name();
        int errorCode = HttpStatus.UNPROCESSABLE_ENTITY.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponseBody> handleBadRequestException(BadRequestException exception){

        String error = HttpStatus.BAD_REQUEST.name();
        int errorCode = HttpStatus.BAD_REQUEST.value();
        String message = exception.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        ExceptionResponseBody response = new ExceptionResponseBody(error, errorCode, message, timestamp);

        return ResponseEntity.status(errorCode).body(response);
    }
}
