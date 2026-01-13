package com.harsh.homeworkModule3.Student_Management_System.services;

import com.harsh.homeworkModule3.Student_Management_System.dto.ProfessorCreateRequest;
import com.harsh.homeworkModule3.Student_Management_System.dto.ProfessorResponse;
import com.harsh.homeworkModule3.Student_Management_System.entity.Professor;
import com.harsh.homeworkModule3.Student_Management_System.exceptions.ResourceNotFoundException;
import com.harsh.homeworkModule3.Student_Management_System.repository.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;

    public ProfessorService(ProfessorRepository professorRepository, ModelMapper modelMapper) {
        this.professorRepository = professorRepository;
        this.modelMapper = modelMapper;
    }

    //Service 1: Create A professor
    public ProfessorResponse createProfessor(ProfessorCreateRequest professor){
        Professor toSaveProf = modelMapper.map(professor,Professor.class);
        Professor savedProf = professorRepository.save(toSaveProf);
        return modelMapper.map(savedProf,ProfessorResponse.class);

    }
    //Service 2: Get all Professor
    public List<ProfessorResponse>getAllProfessors(){
        List<Professor>professors = professorRepository.findAll();
        return professors
                .stream()
                .map(professor -> modelMapper.map(professor,ProfessorResponse.class))
                .collect(Collectors.toUnmodifiableList());
    }
    //Service 3: Get Professor by id
    public ProfessorResponse getProfessorById(Long professorId){
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + professorId));

        return modelMapper.map(professor,ProfessorResponse.class);
    }


}

