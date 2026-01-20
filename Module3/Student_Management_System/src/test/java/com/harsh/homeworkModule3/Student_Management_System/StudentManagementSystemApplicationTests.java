package com.harsh.homeworkModule3.Student_Management_System;

import com.harsh.homeworkModule3.Student_Management_System.clients.EmployeeClient;
import com.harsh.homeworkModule3.Student_Management_System.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentManagementSystemApplicationTests {

    @Autowired
   private EmployeeClient employeeClient;
	@Test
	void contextLoads() {
	}
    @Test
    void getAllEmployees(){
        List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
        System.out.println((employeeDTOList));
    }

}
