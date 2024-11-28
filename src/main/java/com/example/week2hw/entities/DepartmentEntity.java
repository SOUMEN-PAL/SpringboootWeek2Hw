package com.example.week2hw.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Departments")
@Data
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false , unique = true)
    private String departmentName;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDate createdAt;
}
