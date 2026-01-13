package com.harsh.homeworkModule3.Student_Management_System.repository;

import com.harsh.homeworkModule3.Student_Management_System.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {



}