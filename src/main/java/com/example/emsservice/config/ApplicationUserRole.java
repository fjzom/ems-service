package com.example.emsservice.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.emsservice.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ)),
    ADMIN(Sets.newHashSet(EMPLOYEE_READ,EMPLOYEE_WRITE)),
    EMPLOYEE1(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE));

    private final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
