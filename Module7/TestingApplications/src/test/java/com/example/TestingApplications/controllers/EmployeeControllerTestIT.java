package com.example.TestingApplications.controllers;

import com.example.TestingApplications.TestContainerConfiguration;
import com.example.TestingApplications.dto.EmployeeDto;
import com.example.TestingApplications.entities.Employee;
import com.example.TestingApplications.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100s")
@Import(TestContainerConfiguration.class)
class EmployeeControllerTestIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee testEmployee;
    private EmployeeDto employeeDto;





    @BeforeEach
    void setUp() {
        testEmployee = Employee.builder()
                .name("Harsh")
                .email("harsh@gmail.com")
                .salary(200L)
                .build();

        employeeRepository.deleteAll();
    }


    @Test
    void testGetEmployeeById_success(){
        Employee savedEmployee = employeeRepository.save(testEmployee);
        employeeDto = EmployeeDto.builder()
                .id(savedEmployee.getId())
                .name("Harsh")
                .email("harsh@gmail.com")
                .salary(200L)
                .build();
        webTestClient.get()
                .uri("/employees/{id}",savedEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .isEqualTo(employeeDto);


    }

    @Test
    void testGetEmployeeById_failure(){
        webTestClient.get()
                .uri("/employees/{id}",2L)
                .exchange()
                .expectStatus().isNotFound();


    }

    @Test
    void testCreateNewEmployee_whenEmployeeAlreadyExist_thenThrowException(){
        Employee savedEmployee = employeeRepository.save(testEmployee);
        employeeDto = EmployeeDto.builder()
                .id(savedEmployee.getId())
                .name("Harsh")
                .email("harsh@gmail.com")
                .salary(200L)
                .build();
        webTestClient.post()
                .uri("/employees")
                .bodyValue(employeeDto)
                .exchange()
                .expectStatus().is5xxServerError();


    }
    @Test
    void testCreateNewEmployee_whenEmployeeDoesNotExist_thenCreateEmployee() {

        EmployeeDto requestDto = EmployeeDto.builder()
                .name("Harsh")
                .email("harsh@gmail.com")
                .salary(200L)
                .build();

        webTestClient.post()
                .uri("/employees")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EmployeeDto.class)
                .value(response -> {
                    assertNotNull(response.getId());
                    assertEquals("Harsh", response.getName());
                    assertEquals("harsh@gmail.com", response.getEmail());
                    assertEquals(200L, response.getSalary());
                });
    }

    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExist_thenThrowException(){
        EmployeeDto updatedDto = EmployeeDto.builder()
                .name("Harsh Choudhary")
                .email("harsh@gmail.com")
                .salary(205L)
                .build();
        webTestClient.put()
                .uri("/employees/{id}",1L)
                .bodyValue(updatedDto)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateEmployee_whenEmployeeExist_ButTryingToChangeEmail_thenThrowException(){
        Employee savedEmployee = employeeRepository.save(testEmployee);
        EmployeeDto updatedDto = EmployeeDto.builder()
                .name("Harsh Choudhary")
                .email("harsh123@gmail.com")
                .salary(205L)
                .build();
        webTestClient.put()
                .uri("/employees/{id}",savedEmployee.getId())
                .bodyValue(updatedDto)
                .exchange()
                .expectStatus().is5xxServerError();




    }



    @Test
    void testUpdateEmployee_whenEmployeeisValid_thenUpdateEmployee(){
        Employee savedEmployee = employeeRepository.save(testEmployee);
        EmployeeDto updatedDto = EmployeeDto.builder()
                .id(savedEmployee.getId())
                .name("Harsh Choudhary")
                .email("harsh@gmail.com")
                .salary(205L)
                .build();

        webTestClient.put()
                .uri("/employees/{id}",savedEmployee.getId())
                .bodyValue(updatedDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .isEqualTo(updatedDto);



    }
    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExist(){

        webTestClient.delete()
                .uri("/employees/{id}",1L)
                .exchange()
                .expectStatus().isNotFound();



    }
    @Test
    void testDeleteEmployee_whenEmployeeExist(){
        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.delete()
                .uri("/employees/{id}",savedEmployee.getId())
                .exchange()
                .expectStatus().isNoContent();
    }


}