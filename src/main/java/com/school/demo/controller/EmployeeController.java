package com.school.demo.controller;

import com.school.demo.model.Employee;
import com.school.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all Employees
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/find")
    public Optional<Employee> findEmployeeById(@RequestParam("id")UUID id){
        return employeeService.getEmployeeById(id);
    }

    // Add Employee (POST)
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Update Employee (PUT)
    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete")
    public void deleteEmployee(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee.getId());
    }
}
