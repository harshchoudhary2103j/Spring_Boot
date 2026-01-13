package com.harsh.homeworkModule3.Student_Management_System.services;

import com.harsh.homeworkModule3.Student_Management_System.dto.StudentCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.StudentResponse;
import com.harsh.homeworkModule3.Student_Management_System.entity.Professor;
import com.harsh.homeworkModule3.Student_Management_System.entity.Student;
import com.harsh.homeworkModule3.Student_Management_System.entity.Subject;
import com.harsh.homeworkModule3.Student_Management_System.exceptions.ResourceNotFoundException;
import com.harsh.homeworkModule3.Student_Management_System.repository.ProfessorRepository;
import com.harsh.homeworkModule3.Student_Management_System.repository.StudentRepository;
import com.harsh.homeworkModule3.Student_Management_System.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository,ModelMapper modelMapper,
                          ProfessorRepository professorRepository,
                          SubjectRepository subjectRepository){
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    //Service 1: To register Student in the DataBase
    public StudentResponse createNewStudent(StudentCreateRequest inputStudent){
        Student toSaveStudent = modelMapper.map(inputStudent, Student.class);
        Student savedStudent =  studentRepository.save(toSaveStudent);
        return modelMapper.map(savedStudent,StudentResponse.class);

    }
    //Service 2: To Return all the Students
    public List<StudentResponse> getAllStudents(){
        List<Student>studentEntity = studentRepository.findAll();
        return studentEntity
                .stream()
                .map(student -> modelMapper.map(student,StudentResponse.class))
                .collect(Collectors.toUnmodifiableList());
    }

    //Service 3: To return student by id
    public StudentResponse getStudentById(Long studentId) {
        isExistsByStudentId(studentId);
        Student student = studentRepository.findById(studentId).get();

        return modelMapper.map(student, StudentResponse.class);
    }

    //Service 4: Assigning professors to Student
    @Transactional
    public void assignProfToStudent(Long profId,Long studentId){
        isExistsByStudentId(studentId);
        Student student = studentRepository.findById(studentId).get();
        Professor professor = professorRepository.findById(profId).orElseThrow();
        student.getProfessors().add(professor);

    }
    //Service 5: Assign Subject to Student
    @Transactional
    public void assignSubjectToStudent(Long subjectId,Long studentId){
        isExistsByStudentId(studentId);
        Student student = studentRepository.findById(studentId).get();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        student.getSubjects().add(subject);

    }


    public void isExistsByStudentId(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) throw new ResourceNotFoundException("Student not found with id: "+studentId);
    }




}
