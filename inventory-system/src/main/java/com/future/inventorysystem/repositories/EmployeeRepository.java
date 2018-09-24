package com.future.inventorysystem.repositories;

import com.future.inventorysystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByName(String name);
    Employee findByEmail(String email);
}
