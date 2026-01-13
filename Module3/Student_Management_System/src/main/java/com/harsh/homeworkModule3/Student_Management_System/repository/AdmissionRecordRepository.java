package com.harsh.homeworkModule3.Student_Management_System.repository;

import com.harsh.homeworkModule3.Student_Management_System.entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord, Long> {
}