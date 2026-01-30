package com.example.TestingApplications.repositories;

import com.example.TestingApplications.TestContainerConfiguration;
import com.example.TestingApplications.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;
    @BeforeEach
    void setUp(){
        employee = Employee.builder()
                .name("Harsh")
                .email("harsh@gmail.com")
                .salary(100L)
                .build();
    }
    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {

        //Arrange/Given
        employeeRepository.save(employee);

        //Act/When
        List<Employee>employees = employeeRepository.findByEmail(employee.getEmail());


        //Assert/Then
        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees).isNotEmpty();
        Assertions.assertThat(employees.get(0).getEmail()).isEqualTo(employee.getEmail());

    }
    @Test
    void testFindByEmail_whenEmailIsNotValid_thenReturnEmptyEmployee() {
        //Given
        String email = "notPresent.123@gmail.com";

        //Act/When
        List<Employee>employees = employeeRepository.findByEmail(employee.getEmail());

        //Assert/Then
        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees).isEmpty();



    }
}