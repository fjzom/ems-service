package com.example.emsservice.repository;

import com.example.emsservice.domain.Employee;
import com.example.emsservice.repository.Mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryJdbcTemplate {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EmployeeMapper mapper;

    public static final String SELECT_EMPLOYEES = "SELECT * FROM EMPLOYEE  e JOIN EMPLOYEE_SALARY  es ON e.EMPLOYEE_SALARY_FK = es.SALARY_ID;";
    public static final String SELECT_EMPLOYEE = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = %s;";
    public static final String INSERT_EMPLOYEE = "INSERT INTO EMPLOYEE (employee_id, address, emp_name, height, weight, employee_salary_fk) " +
            "VALUES(:employeeId,:address, :empName,:height,:weight, :employeeSalary);";
    public static final String UPDATE_EMPLOYEE = "UPDATE EMPLOYEE SET ADDRESS = :address, EMP_NAME = :empName, HEIGHT = :height, WEIGHT = :weight, EMPLOYEE_SALARY_FK = :employeeSalary WHERE EMPLOYEE_ID = %s;";

    public static final String PATCH_EMPLOYEE = "UPDATE EMPLOYEE SET ADDRESS = :address, WEIGHT = :weight, WHERE EMPLOYEE_ID = :id;";

    public static final String DELETE_EMPLOYEE = "DELETE  FROM EMPLOYEE WHERE EMPLOYEE_ID = :id;";

    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_EMPLOYEES, mapper);
    }


    public Employee findById(final String id) {
        List<Employee> employees = jdbcTemplate.query(String.format(SELECT_EMPLOYEE, id), mapper);
        return  employees.stream().findFirst().get();
    }

    public Integer save(final Employee emp){
        Integer insertNumber =   jdbcTemplate.update(INSERT_EMPLOYEE,new BeanPropertySqlParameterSource(emp));
        return insertNumber;
    }
    public Integer save(final String id, final Employee emp){
        Integer editNumber = jdbcTemplate.update(String.format(UPDATE_EMPLOYEE, id),new BeanPropertySqlParameterSource(emp));
        return editNumber;
    }
    public Integer save(final String id, final Double weight, final String address){
        Integer patchNumber = jdbcTemplate.update(PATCH_EMPLOYEE,new MapSqlParameterSource()
                .addValue("employeeId", id)
                .addValue("weight", weight)
                .addValue("address", address));
        return patchNumber;
    }

    public Integer delete(final String id){
        Integer deleteNumber = jdbcTemplate.update(DELETE_EMPLOYEE, new MapSqlParameterSource()
                .addValue("id", id));
        return deleteNumber;
    }
}
