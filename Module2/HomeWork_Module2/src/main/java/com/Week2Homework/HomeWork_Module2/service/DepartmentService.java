package com.Week2Homework.HomeWork_Module2.service;

import com.Week2Homework.HomeWork_Module2.DTO.DepartmentDTO;
import com.Week2Homework.HomeWork_Module2.entity.DepartmentEntity;
import com.Week2Homework.HomeWork_Module2.exceptions.ResourceNotFound;
import com.Week2Homework.HomeWork_Module2.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    //Department Entity is INJECTED to carry out CRUD
    private final DepartmentRepository departmentRepository;
    //ModelMapper to map Entity to DTO and vice vers
    private final ModelMapper modelMapper;


    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }
//Get department by ID
    public Optional<DepartmentDTO> getDepartmentbyId(Long id) {
            return departmentRepository.findById(id).map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }
// Get all the Department List
    public  List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity>departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(@Valid DepartmentDTO departmentDTO) {
        DepartmentEntity toSaveEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, DepartmentDTO.class);
    }

    public  DepartmentDTO updateDepartmentById(Long departmentId,DepartmentDTO departmentDTO) {
        isExistById(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedEntity, DepartmentDTO.class);

    }


    public Optional<DepartmentDTO> deleteDepartmentById(Long departmentId) {
        isExistById(departmentId);
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);


        departmentRepository.deleteById(departmentId);
        return departmentEntity
                .map(departmentEntity1 -> modelMapper.map(departmentEntity1,DepartmentDTO.class));

    }
    public void isExistById(Long departmentId){
        boolean exist = departmentRepository.existsById(departmentId);
        if(!exist) throw new ResourceNotFound("Department with this Id dont Exist");
    }
}
