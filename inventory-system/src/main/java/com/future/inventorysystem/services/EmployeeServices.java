package com.future.inventorysystem.services;

import com.future.inventorysystem.models.Employee;
import com.future.inventorysystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Employee employee;

    public Boolean validateLogin(String email, String password) {
        employee = employeeRepository.findByEmail(email);
        if (employee != null) {
            return employee.getPassword().equals(password);
        }
        return false;
    }

    public Optional<Employee> getEmployee(String id) {
        return employeeRepository.findById(id);
    }

    public String saveEmployee(Employee em){
        employee = em;
        if(employee.getId() != null) {
            //edit
        }
        else {
            //register
        }
        try{
            employeeRepository.save(employee);
            return "save success!";
        }
        catch(Exception e){
            return "save failed!";
        }
    }

    public String deleteEmployee(Employee em){
        try{
            employeeRepository.delete(em);
            return "delete success";
        }
        catch(Exception e) {
            return "delete failed on id " + em.getId();
        }
    }


}
