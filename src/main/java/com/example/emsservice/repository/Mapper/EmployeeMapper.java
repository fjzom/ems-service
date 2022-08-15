package com.example.emsservice.repository.Mapper;

import com.example.emsservice.domain.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Employee.builder()
                        .employeeId(rs.getString("employee_id"))
                        .address(rs.getString("address"))
                        .empName(rs.getString("emp_name"))
                        .height(rs.getDouble("height"))
                        .weight(rs.getDouble("weight"))
                        .employeeSalary(rs.getString("employee_salary_fk"))
                        .build();
    }
}

