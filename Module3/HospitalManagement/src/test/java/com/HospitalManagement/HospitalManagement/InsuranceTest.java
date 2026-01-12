package com.HospitalManagement.HospitalManagement;

import com.HospitalManagement.HospitalManagement.entity.Appointment;
import com.HospitalManagement.HospitalManagement.entity.Insurance;
import com.HospitalManagement.HospitalManagement.services.AppointmentService;
import com.HospitalManagement.HospitalManagement.services.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private  AppointmentService appointmentService;

    @Test
    public void testAssignedInsuranceToPatient(){
        Insurance insurance = Insurance.builder()
                        .provider("HDFC")
                                .policyNumber("HDFC_23G")
                                        .validUntil(LocalDate.of(2028,1,12))
                                                .build();
    insuranceService.assignInsuranceToPatient(insurance,1L);
    insuranceService.deletePatient(1L);






    }
    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026,11,5,0,0,0))
                .reason("Cancer")
                .build();


    }
}
