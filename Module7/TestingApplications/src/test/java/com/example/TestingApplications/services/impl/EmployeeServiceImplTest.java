package com.example.TestingApplications.services.impl;
import com.example.TestingApplications.TestContainerConfiguration;
import com.example.TestingApplications.dto.EmployeeDto;
import com.example.TestingApplications.entities.Employee;
import com.example.TestingApplications.exceptions.ResourceNotFoundException;
import com.example.TestingApplications.repositories.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp(){
        mockEmployee = Employee.builder()
                .id(1L)
                .name("Harsh")
                .email("harsh@gmail.com")
                .salary(200L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee,EmployeeDto.class);

    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdIsPresent_ThenReturnEmployeeDto(){

        //Assign
        Long id = mockEmployee.getId();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee)); //stubbing


        //Act
        EmployeeDto employeeDto=employeeService.getEmployeeById(id);


        //Assert
        Assertions.assertThat(employeeDto.getId()).isEqualTo(id);
        Assertions.assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        verify(employeeRepository,atLeast(1)).findById(id);
    }
    @Test
    void testGetEmployeeById_whenEmployeeNotPresent_thenThrowException(){
        //arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        //act and assert
        Assertions.assertThatThrownBy(()->employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testCreateNewEmployee_WhenValidEmployee_ThenCreateNewEmployee(){
        //assign

        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

        //act

        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

        //assert

        Assertions.assertThat(employeeDto).isNotNull();
        Assertions.assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        ArgumentCaptor<Employee>employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        Assertions.assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());


    }
    @Test
    void testCreateNewEmployee_WhenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException(){
        //arrange
        when(employeeRepository.findByEmail(mockEmployeeDto.getEmail())).thenReturn(List.of(mockEmployee));

        //act
        Assertions.assertThatThrownBy(()->employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: "+mockEmployee.getEmail());

        verify(employeeRepository).findByEmail(mockEmployee.getEmail());
        verify(employeeRepository,never()).save(any());


    }

    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExist_thenThrowException(){
        //arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(()->employeeService.updateEmployee(1L,mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");
        verify(employeeRepository).findById(1L);
        verify(employeeRepository,never()).save(any());

    }
    @Test
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException(){
        //arrange
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setEmail("random@gmail.com");
        //assert
        Assertions.assertThatThrownBy(()->employeeService.updateEmployee(mockEmployeeDto.getId(),mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee cannot be updated");
        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository,never()).save(any());

    }
    @Test
    void testUpdateEmployee_whenValidEmployee_thenUpdateEmployee(){
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Random");
        mockEmployeeDto.setSalary(199L);
        Employee newEmployee = modelMapper.map(mockEmployeeDto, Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);
        //act
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(mockEmployeeDto.getId(),mockEmployeeDto);

        Assertions.assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);
        verify(employeeRepository).save(any(Employee.class));


    }

    @Test
    void testDeleteEmployee_whenEmployeeIsNotPresent_thenThrowException(){
        //arrange
        when(employeeRepository.existsById(anyLong())).thenReturn(false);

        //Asset and act
        Assertions.assertThatThrownBy(()->employeeService.deleteEmployee(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).existsById(1L);
        verify(employeeRepository,never()).deleteById(1L);



    }

    @Test
    void testDeleteEmployee_whenEmployeeExists_thenDeleteEmployee(){
        //arrange
        when(employeeRepository.existsById(anyLong())).thenReturn(true);

        Assertions.assertThatCode(()->employeeService.deleteEmployee(1L))
                        .doesNotThrowAnyException();
        verify(employeeRepository).existsById(1L);
        verify(employeeRepository).deleteById(1L);


    }




}