package com.Week2Homework.HomeWork_Module2.controllers;

import com.Week2Homework.HomeWork_Module2.DTO.DepartmentDTO;
import com.Week2Homework.HomeWork_Module2.exceptions.ResourceNotFound;
import com.Week2Homework.HomeWork_Module2.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//This Annotations means that following class is REST in nature
@RestController
@RequestMapping(path = "/departments") //This Shows the parent path
public class DepartmentController {
    private final DepartmentService departmentService;
    //Department Service is injected in Constructor
    //Department Service is used to get DTO of response
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //Get departments by id
    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO>getDepartmentId(@PathVariable(name = "departmentId")Long id){
        Optional<DepartmentDTO>departmentDTO = departmentService.getDepartmentbyId(id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(()-> new ResourceNotFound("No Department with ID found"));

    }
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>>getAllDepartments(@RequestParam(required = false,name = "title") String title){
        return  ResponseEntity.ok(departmentService.getAllDepartment());
    }

    //Register a new Department
    @PostMapping
    public ResponseEntity<DepartmentDTO>createNewDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO savedDTO = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(savedDTO,HttpStatus.CREATED);
    }

    @PutMapping(path = "{departmentId}")
    public ResponseEntity<DepartmentDTO>updateDepartmentById(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentId,departmentDTO));
    }
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> deleteDepartmentById(
            @PathVariable Long departmentId
    ) {
        Optional<DepartmentDTO> deletedDepartment =
                departmentService.deleteDepartmentById(departmentId);

        return deletedDepartment
                .map(deletedDepartment01->ResponseEntity.ok(deletedDepartment01))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
