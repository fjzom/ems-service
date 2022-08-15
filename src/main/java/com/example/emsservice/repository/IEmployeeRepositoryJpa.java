package com.example.emsservice.repository;

import com.example.emsservice.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepositoryJpa extends JpaRepository<Employee, String> {
    void save(String employeeId, Employee emp);
//    void save(String employeeId, Double weight, String address);
    void deleteById(String employeeId);
}
