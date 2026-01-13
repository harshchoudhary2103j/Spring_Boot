package com.harsh.homeworkModule3.Student_Management_System.controllers;

import com.harsh.homeworkModule3.Student_Management_System.dto.ProfessorCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.ProfessorResponse;
import com.harsh.homeworkModule3.Student_Management_System.dto.StudentCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.StudentResponse;
import com.harsh.homeworkModule3.Student_Management_System.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    //API:1 Create a new Professor
    @PostMapping
    public ResponseEntity<ProfessorResponse> createNewProfessor(@RequestBody @Valid ProfessorCreateRequest professorCreateRequest){
        System.out.println(">>> ProfessorController HIT <<<");
        ProfessorResponse savedResponse = professorService.createProfessor(professorCreateRequest);
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }
    //API:2 Get All Professors
    @GetMapping
    public ResponseEntity<List<ProfessorResponse>>getAllStudents(){
        return ResponseEntity.ok(professorService.getAllProfessors());
    }
    //API:3 Get professor by id
    @GetMapping(path = "/{professorId}")
    public ResponseEntity<ProfessorResponse>getStudentById(@PathVariable Long professorId){
        ProfessorResponse professorResponse = professorService.getProfessorById(professorId);
        return ResponseEntity.ok(professorResponse);

    }
}
