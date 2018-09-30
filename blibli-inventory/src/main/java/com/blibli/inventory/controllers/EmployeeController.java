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

    @RequestMapping(value = "/api/employees", consumes = "application/json", method = RequestMethod.GET)
    public ModelAndView listOfEmployee(ModelAndView model) throws IOException{
        List<Employee> listOfEmployee = employeeService.getEmployeeList(pageable);
        model.addObject("listOfEmployee", listOfEmployee);
        model.setViewName("EmployeeList");

        return model;
    }

    @RequestMapping(value = "/api/superiors", consumes = "application/json", method = RequestMethod.GET)
    public ModelAndView listOfSuperior(ModelAndView model) throws IOException{
        List<Employee> listOfSuperior = employeeService.getSuperiorList(pageable);
        model.addObject("listOfSuperior", listOfSuperior);
        model.setViewName("SuperiorList");

        return model;
    }

    public ModelAndView EmployeeData(@RequestParam String id) throws IOException{
        Employee employee = employeeService.getEmployee(id);
        ModelAndView model = new ModelAndView("EmployeeData");
        model.addObject("Employee", employee);
        return model;
    }

    public ModelAndView newEmployee(ModelAndView model){
        Employee employee = new Employee();
        model.addObject("Employee", employee);
        model.setViewName("EmployeeForm");
        return model;
    }

    public ModelAndView saveEmployee(DefaultMultipartHttpServletRequest request){
        Employee employee =  new Employee();
        employee.setId(request.getParameter("id"));
        employee.setName(request.getParameter("name"));
        employee.setEmail(request.getParameter("email"));
        employee.setPassword(request.getParameter("password"));
        employee.setPosition(request.getParameter("position"));
        employee.setDivisision(request.getParameter("division"));
        employee.setSuperiorId(request.getParameter("superiorId"));
        employeeService.saveEmployee(employee);
        return new ModelAndView("redirect:/api/employees");
    }

    public ModelAndView deleteEmployee(@RequestParam String id){
        employeeService.deleteEmployee(id);
        return new ModelAndView("redirect:/api/employees");
    }


}
