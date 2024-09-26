package com.school.demo.service;

import com.school.demo.model.Employee;
import com.school.demo.repo.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Optional<Employee> getEmployeeById(UUID id) {
        return employeeDao.getEmployeeById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(UUID id) {
        employeeDao.deleteEmployee(id);
    }
}
