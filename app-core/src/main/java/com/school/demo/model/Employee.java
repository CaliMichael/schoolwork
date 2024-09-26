package com.school.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name= "EMPLOYEE", uniqueConstraints = @UniqueConstraint(columnNames = "EMPLOYEE_CODE"))
@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")  // Generates a UUID
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMPLOYEE_CODE", nullable = false)
    private String employeeCode;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;
}
