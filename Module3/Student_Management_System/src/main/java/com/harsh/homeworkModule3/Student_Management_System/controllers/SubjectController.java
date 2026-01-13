package com.harsh.homeworkModule3.Student_Management_System.controllers;

import com.harsh.homeworkModule3.Student_Management_System.dto.ProfessorCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.ProfessorResponse;
import com.harsh.homeworkModule3.Student_Management_System.dto.SubjectCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.SubjectResponse;
import com.harsh.homeworkModule3.Student_Management_System.services.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    //API:1 Create a new Subject
    @PostMapping
    public ResponseEntity<SubjectResponse> createNewProfessor(@RequestBody @Valid SubjectCreateRequest subjectCreateRequest){
        SubjectResponse savedResponse = subjectService.createNewSubject(subjectCreateRequest);
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }
    //API:2 Get All Subjects
    @GetMapping
    public ResponseEntity<List<SubjectResponse>>getAllStudents(){
        return ResponseEntity.ok(subjectService.subjectResponseList());
    }
    //API:3 Get subject by id
    @GetMapping(path = "/{subjectId}")
    public ResponseEntity<SubjectResponse>getStudentById(@PathVariable Long subjectId){
        SubjectResponse subjectResponse = subjectService.getSubjById(subjectId);
        return ResponseEntity.ok(subjectResponse);

    }
}
