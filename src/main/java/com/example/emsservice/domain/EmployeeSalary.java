package com.example.emsservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalary {
    private String salaryId;
    private Integer salary;

}
