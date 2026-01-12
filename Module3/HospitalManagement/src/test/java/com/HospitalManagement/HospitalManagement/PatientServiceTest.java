package com.HospitalManagement.HospitalManagement;

import com.HospitalManagement.HospitalManagement.dto.CPatientInfo;
import com.HospitalManagement.HospitalManagement.dto.IPatientInfo;
import com.HospitalManagement.HospitalManagement.entity.Patient;
import com.HospitalManagement.HospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {
   @Autowired
   private PatientRepository patientRepository;

    @Test
    public void testPatient(){
//        List<Patient> patientList = patientRepository.findAll();
//       List<IPatientInfo> patientList = patientRepository.getAllPatientInfo();
//        List<CPatientInfo> patientList = patientRepository.getAllPatientsInfoConcrete();
//
//
//        for(CPatientInfo p: patientList){
//            System.out.println(p);
//        }
//        int rowsAffected = patientRepository.updatePatientNameWithId("Harsh Choudhary",1L);
//        System.out.println(rowsAffected);
        List<Patient>patientList = patientRepository.findAll();
        for(Patient p: patientList){
            System.out.println(p);
        }
    }
}
