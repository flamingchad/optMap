package com.optmap.optmap.errorHandling;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiError> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("Unhandled exception encountered: {}", e.getMessage(), e);

        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                statusCode,
                "Internal Server Error",
                "Something went wrong!",
                request.getRequestURL().toString()
        );

        return ResponseEntity.status(statusCode).body(apiError);
    }

}
