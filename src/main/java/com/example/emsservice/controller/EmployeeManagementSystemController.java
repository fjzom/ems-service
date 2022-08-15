package com.example.emsservice.controller;

import com.example.emsservice.domain.Employee;
import com.example.emsservice.repository.EmployeeRepositoryJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("ems")
public class EmployeeManagementSystemController {
    private final EmployeeRepositoryJdbcTemplate empRepo;


    @GetMapping( "getemployees")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE1', 'EMPLOYEE')")
    public List<Employee> getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        empRepo.findAll().forEach(e -> employees.add(e));
        return employees;
    }

    @GetMapping( "getemployee")
    @ResponseBody
    public Optional<Employee> getEmployee(@RequestParam("employeeId") String employeeId){

        return Optional.ofNullable(empRepo.findById(employeeId));
    }

    @PostMapping( "addemployee")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE1')")
    public ResponseEntity<String> addEmployees(@RequestBody Employee emp){
        empRepo.save(emp);
        return ResponseEntity.ok("Employee added");
    }

    @PutMapping( path ="editemployee/{employeeId}")
    public ResponseEntity<String> editEmployees(@PathVariable("employeeId") String employeeId, @RequestBody Employee emp){
        empRepo.save(employeeId, emp);
        return ResponseEntity.ok("Employee edited");
    }


    @PatchMapping( "editemployee/{employeeId}/{weight}/{address}")
    public ResponseEntity<String> patchEmployees(@PathVariable("employeeId") String employeeId,
                                                 @PathVariable("weight") Double weight,
                                                 @PathVariable("address") String address ){
        empRepo.save(employeeId, weight, address);
        return ResponseEntity.ok("Employee patch");
    }

    @DeleteMapping( "deleteemployee/{employeeId}")
    public ResponseEntity<String> deleteEmployees(@PathVariable(value = "employeeId") String employeeId){
        empRepo.delete(employeeId);
        return ResponseEntity.ok("Employee deleted");
    }


}
