package com.school.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name= "EMPLOYEE")
@Entity
@Data
public class Employee {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;
}
