package com.harsh.homeworkModule3.Student_Management_System.clients.impl;

import com.harsh.homeworkModule3.Student_Management_System.advices.ApiResponse;
import com.harsh.homeworkModule3.Student_Management_System.clients.EmployeeClient;
import com.harsh.homeworkModule3.Student_Management_System.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CEmployeeClient implements EmployeeClient {
    private final RestClient restClient;

    @Override
    public List<EmployeeDTO>getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDTO>>list =  restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,((request, response) -> {
                        throw new RuntimeException("Could not create the employee");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });
            return list.getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
