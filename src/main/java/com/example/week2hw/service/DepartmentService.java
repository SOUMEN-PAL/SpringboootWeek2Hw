package com.example.week2hw.service;

import com.example.week2hw.dto.DepartmentDTO;
import com.example.week2hw.entities.DepartmentEntity;
import com.example.week2hw.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        try {
            List<DepartmentEntity> departmentEntities = repository.findAll();

            return departmentEntities
                    .stream()
                    .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                    .collect(Collectors.toList());

        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to access the Database") {
            };
        } catch (MappingException e) {
            throw new MappingException(e.getErrorMessages().stream().toList());
        }
    }

    public DepartmentDTO findDepartmentById(Long departmentID) {
        DepartmentEntity departmentEntity = repository.findById(departmentID).orElseThrow(() -> new EntityNotFoundException("Department with id " + departmentID + " not found."));
        return modelMapper.map(departmentEntity , DepartmentDTO.class);
    }


    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        try{
            DepartmentEntity entityToSave = modelMapper.map(departmentDTO, DepartmentEntity.class);
            DepartmentEntity savedEntity = repository.save(entityToSave);
            return modelMapper.map(savedEntity, DepartmentDTO.class);
        }catch (DataIntegrityViolationException ex) {
            // Handle cases like duplicate emails or other database constraint violations
            throw new DataIntegrityViolationException("Department with " + departmentDTO.getDepartmentName() + " already exists");
        }
    }

    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        try{
            DepartmentEntity existingEntity = repository.findById(departmentId).orElseThrow(() -> new EntityNotFoundException("Department with id " + departmentId + " not found."));

            modelMapper.map(departmentDTO, existingEntity);

            if (existingEntity.getId() == null) {
                throw new IllegalStateException("Employee ID should not be null for update");
            }

            DepartmentEntity savedEntity = repository.save(existingEntity);

            return modelMapper.map(savedEntity, DepartmentDTO.class);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Department with " + departmentDTO.getDepartmentName() + " already exists to another id");
        }
    }

    public DepartmentDTO deleteDepartmentById(Long departmentId) {
        if(!repository.existsById(departmentId)){
            throw new EntityNotFoundException("Department with id " + departmentId + " not found");
        }

        DepartmentEntity deletedEntity = repository.findById(departmentId).get();
        repository.deleteById(departmentId);
        return modelMapper.map(deletedEntity , DepartmentDTO.class);
    }
}
