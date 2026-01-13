package com.harsh.homeworkModule3.Student_Management_System.services;

import com.harsh.homeworkModule3.Student_Management_System.dto.AdmissionCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.entity.AdmissionRecord;
import com.harsh.homeworkModule3.Student_Management_System.entity.Student;
import com.harsh.homeworkModule3.Student_Management_System.exceptions.ResourceNotFoundException;
import com.harsh.homeworkModule3.Student_Management_System.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdmissionService {
    private final StudentRepository studentRepository;

    public AdmissionService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Service: Admission of student
    @Transactional
    public void admitStudent(Long studentId, AdmissionCreateRequest admissionCreateRequest){
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()-> new ResourceNotFoundException("Student not found with id: "+ studentId)
        );
        if(student.getAdmissionRecord()!=null){
            throw new ResourceNotFoundException("Student already admitted");
        }
        AdmissionRecord admissionRecord = new AdmissionRecord();
        admissionRecord.setFees(admissionCreateRequest.getFees());
        admissionRecord.setStudent(student);
        student.setAdmissionRecord(admissionRecord);
        studentRepository.save(student);
    }
}
