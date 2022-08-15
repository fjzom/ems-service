package com.example.emsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name =  "employee")
public class Employee {
    @Id
    private String employeeId;
    private String empName;
    private Double height;
    private Double weight;
    private  String address;

    private String employeeSalary;
}
