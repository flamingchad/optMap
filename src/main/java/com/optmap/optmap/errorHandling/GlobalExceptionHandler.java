package com.optmap.optmap.errorHandling;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

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

    @ExceptionHandler(value = {InvalidRouteRequestException.class})
    public ResponseEntity<ApiError> invalidRouteRequestException(InvalidRouteRequestException e, HttpServletRequest request) {
        log.error("Invalid input detected from front-end: {}", e.getMessage(), e);

        int statusCode = HttpStatus.BAD_REQUEST.value();

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                statusCode,
                "Bad Request",
                "There must be at-least 2 coordinates!",
                request.getRequestURL().toString()
        );
        return ResponseEntity.status(statusCode).body(apiError);
    }

    @ExceptionHandler(value = {OsrmServiceException.class})
    public ResponseEntity<ApiError> osrmServiceException(OsrmServiceException e, HttpServletRequest request) {
        log.error("Issue with OSRM detected: {}", e.getMessage(), e);

        int statusCode = HttpStatus.SERVICE_UNAVAILABLE.value();

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                statusCode,
                "Service Unavailable",
                "There is an issue with the routing service.",
                request.getRequestURL().toString()
        );

        return ResponseEntity.status(statusCode).body(apiError);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> methodArgumentNotValidException (MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("Validation failed: {}", e.getMessage());

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        int statusCode = HttpStatus.BAD_REQUEST.value();

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                statusCode,
                "Invalid arguments supplied",
                "Validation failed for: " + errors,
                request.getRequestURI()
        );

        return ResponseEntity.status(statusCode).body(apiError);
    }

    @ExceptionHandler(value = {NominatimServiceException.class})
    public ResponseEntity<ApiError> nominatimServiceException(NominatimServiceException e, HttpServletRequest request) {
        log.error("Issue with Nominatim detected: {}", e.getMessage(), e);

        int statusCode = HttpStatus.SERVICE_UNAVAILABLE.value();

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                statusCode,
                "Service Unavailable",
                "There is an issue with the Search service.",
                request.getRequestURL().toString()
        );

        return ResponseEntity.status(statusCode).body(apiError);
    }

    @ExceptionHandler(value = {InvalidSearchRequestException.class})
    public ResponseEntity<ApiError> invalidSearchRequestException(InvalidSearchRequestException e, HttpServletRequest request) {
        log.error("Invalid input detected from front-end: {}", e.getMessage(), e);

        int statusCode = HttpStatus.BAD_REQUEST.value();

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                statusCode,
                "Bad Request",
                "Bad search query",
                request.getRequestURL().toString()
        );
        return ResponseEntity.status(statusCode).body(apiError);
    }
}
