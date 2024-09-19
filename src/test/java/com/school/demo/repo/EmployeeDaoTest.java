package com.school.demo.repo;

import com.school.demo.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeDaoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeDao employeeDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        List<Employee> expectedEmployees = List.of(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(expectedEmployees);

        // Act
        List<Employee> actualEmployees = employeeDao.getAllEmployees();

        // Assert
        assertEquals(expectedEmployees, actualEmployees);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        UUID id = UUID.randomUUID();
        Employee expectedEmployee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(expectedEmployee));

        // Act
        Optional<Employee> actualEmployee = employeeDao.getEmployeeById(id);

        // Assert
        assertTrue(actualEmployee.isPresent());
        assertEquals(expectedEmployee, actualEmployee.get());
        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    void testAddEmployee() {
        // Arrange
        Employee newEmployee = new Employee();
        when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);

        // Act
        Employee addedEmployee = employeeDao.addEmployee(newEmployee);

        // Assert
        assertEquals(newEmployee, addedEmployee);
        verify(employeeRepository, times(1)).save(newEmployee);
    }

    @Test
    void testUpdateEmployee_EmployeeExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Employee existingEmployee = new Employee();
        existingEmployee.setId(id);
        Employee updatedEmployeeDetails = new Employee();
        updatedEmployeeDetails.setId(id);
        updatedEmployeeDetails.setName("Updated Name");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        // Act
        Employee result = employeeDao.updateEmployee(updatedEmployeeDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void testUpdateEmployee_EmployeeDoesNotExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        Employee updatedEmployeeDetails = new Employee();
        updatedEmployeeDetails.setId(id);

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Employee result = employeeDao.updateEmployee(updatedEmployeeDetails);

        // Assert
        assertNull(result);
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(employeeRepository).deleteById(id);

        // Act
        employeeDao.deleteEmployee(id);

        // Assert
        verify(employeeRepository, times(1)).deleteById(id);
    }
}
