package com.example.emsservice.repository;

import com.example.emsservice.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepositoryCrud extends CrudRepository<Employee, String> {
}
