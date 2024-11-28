package com.example.week2hw.advice;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;
    private ApiError apiError;

    public ApiResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }
}
