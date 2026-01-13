package com.harsh.homeworkModule3.Student_Management_System.controllers;

import com.harsh.homeworkModule3.Student_Management_System.dto.AdmissionCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.StudentCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.StudentResponse;
import com.harsh.homeworkModule3.Student_Management_System.services.AdmissionService;
import com.harsh.homeworkModule3.Student_Management_System.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Set the parent path
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final AdmissionService admissionService;

    public StudentController(StudentService studentService, AdmissionService admissionService) {
        this.studentService = studentService;
        this.admissionService = admissionService;
    }
    //API:1 Create a new Student
    @PostMapping
    public ResponseEntity<StudentResponse>createNewStudent(@RequestBody @Valid StudentCreateRequest studentCreateRequest){
        StudentResponse savedResponse = studentService.createNewStudent(studentCreateRequest);
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }
    //API:2 Get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponse>>getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    //API:3 Get student by id
    @GetMapping(path = "/{studentId}")
    public ResponseEntity<StudentResponse>getStudentById(@PathVariable Long studentId){
        StudentResponse studentResponse = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentResponse);

    }
    //API:4
    @PostMapping(path = "/{studentId}/professors/{professorId}")
    public ResponseEntity<Void>assignProffesorToStudent(
            @PathVariable
            Long professorId,
            @PathVariable
            Long studentId
    ){
        studentService.assignProfToStudent(professorId,studentId);
        return ResponseEntity.ok().build();

    }
    //Api:5
    @PostMapping(path = "/{studentId}/subjects/{subjectId}")
    public ResponseEntity<Void>assignSubjectToStudent(
            @PathVariable
            Long subjectId,
            @PathVariable
            Long studentId
    ){
        studentService.assignSubjectToStudent(subjectId,studentId);
        return ResponseEntity.ok().build();

    }

    //API: 6
    @PostMapping("/{studentId}/admission")
    public ResponseEntity<Void> admitStudent(
            @PathVariable Long studentId,
            @Valid @RequestBody AdmissionCreateRequest request) {

        admissionService.admitStudent(studentId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
