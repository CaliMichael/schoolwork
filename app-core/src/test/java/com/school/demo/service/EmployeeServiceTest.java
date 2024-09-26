package com.school.demo.service;

import com.school.demo.model.Employee;
import com.school.demo.repo.EmployeeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        List<Employee> expectedEmployees = List.of(new Employee(), new Employee());
        when(employeeDao.getAllEmployees()).thenReturn(expectedEmployees);

        // Act
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        // Assert
        assertEquals(expectedEmployees, actualEmployees);
        verify(employeeDao, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        UUID id = UUID.randomUUID();
        Employee expectedEmployee = new Employee();
        when(employeeDao.getEmployeeById(id)).thenReturn(Optional.of(expectedEmployee));

        // Act
        Optional<Employee> actualEmployee = employeeService.getEmployeeById(id);

        // Assert
        assertTrue(actualEmployee.isPresent());
        assertEquals(expectedEmployee, actualEmployee.get());
        verify(employeeDao, times(1)).getEmployeeById(id);
    }

    @Test
    void testAddEmployee() {
        // Arrange
        Employee newEmployee = new Employee();
        when(employeeDao.addEmployee(newEmployee)).thenReturn(newEmployee);

        // Act
        Employee addedEmployee = employeeService.addEmployee(newEmployee);

        // Assert
        assertEquals(newEmployee, addedEmployee);
        verify(employeeDao, times(1)).addEmployee(newEmployee);
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        Employee updatedEmployee = new Employee();
        when(employeeDao.updateEmployee(updatedEmployee)).thenReturn(updatedEmployee);

        // Act
        Employee result = employeeService.updateEmployee(updatedEmployee);

        // Assert
        assertEquals(updatedEmployee, result);
        verify(employeeDao, times(1)).updateEmployee(updatedEmployee);
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(employeeDao).deleteEmployee(id);

        // Act
        employeeService.deleteEmployee(id);

        // Assert
        verify(employeeDao, times(1)).deleteEmployee(id);
    }
}
