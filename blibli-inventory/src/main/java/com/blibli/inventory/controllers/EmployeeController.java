package com.blibli.inventory.controllers;

import com.blibli.inventory.models.Employee;
import com.blibli.inventory.services.EmployeeService;
import javafx.application.Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();
    Pageable pageable;

    @RequestMapping(value = "/api/employees", produces = "application/json",
            method = RequestMethod.GET)
    public List<Employee> listOfEmployee() throws IOException{
        List<Employee> listOfEmployee = employeeService.getEmployeeList(pageable);
        return listOfEmployee;
    }

    @RequestMapping(value = "/api/superiors", produces = "application/json", method = RequestMethod.GET)
    public List<Employee> listOfSuperior() throws IOException{
        List<Employee> listOfSuperior = employeeService.getSuperiorList(pageable);
        return listOfSuperior;
    }

    @RequestMapping(value = "api/employee/{id}", consumes = "application/json", produces = "application/json",
    method = RequestMethod.GET)
    public Employee EmployeeData(@RequestParam String id) throws IOException{
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

//    public Employee newEmployee(){
//        Employee employee = new Employee();
//        return employee;
//    }

    @RequestMapping(value = "api/employee", consumes = "application/json", produces = "application/json",
    method = RequestMethod.POST)
    public String saveEmployee(DefaultMultipartHttpServletRequest request){
        Employee employee =  new Employee();
        employee.setId(request.getParameter("id"));
        employee.setName(request.getParameter("name"));
        employee.setEmail(request.getParameter("email"));
        employee.setPassword(request.getParameter("password"));
        employee.setPosition(request.getParameter("position"));
        employee.setDivisision(request.getParameter("division"));
        employee.setSuperiorId(request.getParameter("superiorId"));
        String error = employeeService.saveEmployee(employee);
        if(error.length() > 0)
            return "failed";
        return "success";

    }

    @RequestMapping(value = "api/employee", consumes = "application/json", produces = "application/json",
    method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestParam String id){
        return employeeService.deleteEmployee(id);
    }


}
