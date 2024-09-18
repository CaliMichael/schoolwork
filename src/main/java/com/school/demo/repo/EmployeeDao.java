package com.school.demo.repo;

import com.school.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(UUID id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);  // Adding employee
    }

    public Employee updateEmployee(Employee employeeDetails) {
        // First, check if the employee exists in the database
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeDetails.getId());

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDetails.getName());
            employee.setEmployeeCode(employeeDetails.getEmployeeCode());
            employee.setEmail(employeeDetails.getEmail());
            employee.setAddress(employeeDetails.getAddress());
            // Update other fields if necessary
            return employeeRepository.save(employee);  // Saving the updated employee
        }
        return null;
    }

    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}
