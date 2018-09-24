package com.future.inventorysystem.controllers;

import com.future.inventorysystem.models.Employee;
import com.future.inventorysystem.services.EmployeeServices;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServices employeeServices;

    @GetMapping("/api/employee/{id}")
    public Optional<Employee> getUserDetail(@PathVariable String id) {
        return employeeServices.getEmployee(id);
    }

    @PostMapping("/api/employee/save")
    @PutMapping("/api/employee/save")
    public String saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeServices.saveEmployee(employee);
    }

    @DeleteMapping("/api/employees")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) throws Exception {
        return employeeServices.getEmployee(id)
                .map(employee -> {
                    employeeServices.deleteEmployee(employee);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Exception() {
                });
    }
}
