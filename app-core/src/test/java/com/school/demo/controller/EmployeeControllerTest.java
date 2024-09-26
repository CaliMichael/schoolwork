package com.school.demo.controller;

import com.school.demo.model.Employee;
import com.school.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;  // The class we are testing

    @Mock
    private EmployeeService employeeService;  // Mock the service layer

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    // Test for getAllEmployees
    @Test
    void getAllEmployeesTest() {
        // Arrange
        List<Employee> employeeList = new ArrayList<>();
        Employee firstEmployee = new Employee();
        firstEmployee.setId(UUID.randomUUID());
        firstEmployee.setName("John");
        firstEmployee.setEmployeeCode("S1234");
        firstEmployee.setEmail("john@gmail.com");
        firstEmployee.setAddress("USA");
        Employee secondEmployee = new Employee();
        secondEmployee.setId(UUID.randomUUID());
        secondEmployee.setName("Jane");
        secondEmployee.setEmployeeCode("S224");
        secondEmployee.setEmail("jane@gmail.com");
        secondEmployee.setAddress("Portugal");
        employeeList.add(firstEmployee);
        employeeList.add(secondEmployee);
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        // Act
        List<Employee> result = employeeController.getAllEmployees();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Jane", result.get(1).getName());
        verify(employeeService, times(1)).getAllEmployees();  // Verify service call
    }

    // Test for findEmployeeById
    @Test
    void findEmployeeByIdTest() {
        // Arrange
        UUID id = UUID.randomUUID();
        Employee employee = new Employee();
        employee.setName("John");
        when(employeeService.getEmployeeById(id)).thenReturn(Optional.of(employee));

        // Act
        Optional<Employee> result = employeeController.findEmployeeById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
        verify(employeeService, times(1)).getEmployeeById(id);  // Verify service call
    }

    // Test for addEmployee
    @Test
    void addEmployeeTest() {
        // Arrange
        Employee employee = new Employee();
        employee.setName("John");
        when(employeeService.addEmployee(employee)).thenReturn(employee);

        // Act
        Employee result = employeeController.addEmployee(employee);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(employeeService, times(1)).addEmployee(employee);  // Verify service call
    }

    // Test for updateEmployee
    @Test
    void updateEmployeeTest() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setName("John");
        when(employeeService.updateEmployee(employee)).thenReturn(employee);

        // Act
        Employee result = employeeController.updateEmployee(employee);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(employeeService, times(1)).updateEmployee(employee);  // Verify service call
    }

    // Test for deleteEmployee
    @Test
    void deleteEmployeeTest() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(employeeService).deleteEmployee(id);

        // Act
        employeeController.deleteEmployee(id);

        // Assert
        verify(employeeService, times(1)).deleteEmployee(id);  // Verify service call
    }
}
