package com.example.week2hw.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Provide a department name")
    private String departmentName;

    @NotNull(message = "Can not leave isActive blank")
    private Boolean isActive;


    @NotNull(message = "Department creation date has to be provided")
    @PastOrPresent(message = "date has to be a present or past date")
    private LocalDate createdAt;
}
