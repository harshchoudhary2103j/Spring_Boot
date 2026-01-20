package com.harsh.homeworkModule3.Student_Management_System.clients;

import com.harsh.homeworkModule3.Student_Management_System.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();
}
