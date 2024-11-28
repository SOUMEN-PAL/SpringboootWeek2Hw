package com.example.week2hw.controllers;


import com.example.week2hw.advice.ApiResponse;
import com.example.week2hw.dto.DepartmentDTO;
import com.example.week2hw.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> department = departmentService.getAllDepartments();
        return ResponseEntity.ok(department);
    }

    @GetMapping("/{departmentID}")
    public ResponseEntity<DepartmentDTO> getDepartmentByID(@PathVariable("departmentID") Long departmentID){
        DepartmentDTO departmentDTO = departmentService.findDepartmentById(departmentID);
        return ResponseEntity.ok(departmentDTO);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
       DepartmentDTO savedData = departmentService.createDepartment(departmentDTO);
       return new ResponseEntity<>(savedData , HttpStatus.OK);
    }

    @PutMapping("{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentWithId(@PathVariable("departmentId") Long departmentId , @RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO updatedData = departmentService.updateDepartment(departmentId , departmentDTO);
        return new ResponseEntity<>(updatedData , HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> deleteDepartmentById(@PathVariable("departmentId") Long departmentId){
        DepartmentDTO deletedData = departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.ok(deletedData);
    }


}
