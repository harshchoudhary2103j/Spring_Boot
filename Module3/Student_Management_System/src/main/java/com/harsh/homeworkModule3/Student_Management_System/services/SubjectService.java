package com.harsh.homeworkModule3.Student_Management_System.services;

import com.harsh.homeworkModule3.Student_Management_System.dto.SubjectCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.SubjectResponse;
import com.harsh.homeworkModule3.Student_Management_System.entity.Subject;
import com.harsh.homeworkModule3.Student_Management_System.exceptions.ResourceNotFoundException;
import com.harsh.homeworkModule3.Student_Management_System.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;


    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }
    //Service 3: Create new Subject
    public SubjectResponse createNewSubject(SubjectCreateRequest subj){
        Subject toSaveSubj = modelMapper.map(subj,Subject.class);
        Subject savedSubj = subjectRepository.save(toSaveSubj);
        return modelMapper.map(savedSubj,SubjectResponse.class);
    }
    //Service 3: Get all subjects
    public List<SubjectResponse> subjectResponseList(){
        List<Subject> subjects = subjectRepository.findAll();
        return subjects
                .stream()
                .map(subject -> modelMapper.map(subject,SubjectResponse.class))
                .collect(Collectors.toUnmodifiableList());
    }

    //Service 4: Get Subject by id
    public SubjectResponse getSubjById(Long subjId){
        Subject subject = subjectRepository.findById(subjId).orElseThrow(
                ()->new ResourceNotFoundException("Subject not Found with Id: "+ subjId)
        );
        return modelMapper.map(subject,SubjectResponse.class);


    }
}
