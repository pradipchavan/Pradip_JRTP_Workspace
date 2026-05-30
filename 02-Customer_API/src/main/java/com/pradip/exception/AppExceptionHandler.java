package com.pradip.exception;

import com.pradip.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {

        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setStatus(500);
        apiResponse.setMessage(e.getMessage());
        apiResponse.setData(null);

        log.error(e.getMessage());

        return  new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
