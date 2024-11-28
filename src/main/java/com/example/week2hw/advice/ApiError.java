package com.example.week2hw.advice;

import lombok.Builder;
import lombok.Data;
import lombok.val;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
    private String message;
    private HttpStatus status;
    private List<String> subErrors;
}
