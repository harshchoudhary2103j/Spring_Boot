package com.HospitalManagement.HospitalManagement.repository;

import com.HospitalManagement.HospitalManagement.dto.CPatientInfo;
import com.HospitalManagement.HospitalManagement.dto.IPatientInfo;
import com.HospitalManagement.HospitalManagement.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("""
   SELECT p.id AS id,
          p.name AS name
   FROM Patient p
""")
    List<IPatientInfo> getAllPatientInfo();
    @Query("select new com.HospitalManagement.HospitalManagement.dto.CPatientInfo(p.id,p.name) "+
    "from Patient p")
    List<CPatientInfo> getAllPatientsInfoConcrete();

    @Transactional
    @Modifying
    @Query("UPDATE Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name")String name,@Param("id")Long id);

}
