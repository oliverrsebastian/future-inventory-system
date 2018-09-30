package com.blibli.inventory.services;

import com.blibli.inventory.models.Employee;
import com.blibli.inventory.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public Employee getEmployee(String id){
        return repository.getEmployee(id);
    }

    public List<Employee> getSuperiorList(Pageable pageable){
        return repository.getSuperiorList(pageable);
    }

    public List<Employee> getEmployeeList(Pageable pageable){
        return repository.getEmployeeList(pageable);
    }

    public String saveEmployee(Employee employee){
        return repository.saveEmployee(employee);
    }

    public String deleteEmployee(String id){
        return repository.deleteEmployee(id);
    }
}
