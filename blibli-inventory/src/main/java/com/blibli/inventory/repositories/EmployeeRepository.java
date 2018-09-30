package com.blibli.inventory.repositories;

import com.blibli.inventory.models.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.awt.print.Pageable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeRepository {
    private JdbcTemplate jdbc;

    public EmployeeRepository(DataSource dataSource){
        jdbc = new JdbcTemplate(dataSource);
    }

    public Employee getEmployee(String id){
        String sql = "SELECT from employee WHERE id =" + id;
        return jdbc.query(sql, new ResultSetExtractor<Employee>() {
            @Override
            public Employee extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    Employee employee = new Employee();
                    employee.setId(resultSet.getString("id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setDob(resultSet.getString("dob"));
                    employee.setPosition(resultSet.getString("position"));
                    employee.setDivisision(resultSet.getString("division"));
                    return employee;
                }
                return null;
            }
        });
    }

    public List<Employee> getEmployeeList(final Pageable pageable){
        String sql = "SELECT from employee";
        List<Employee> listOfEmployee = jdbc.query(sql, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                if(i > pageable.getNumberOfPages()){
                    return null;
                }
                Employee employee = new Employee();

                employee.setId(resultSet.getString("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getString("dob"));
                employee.setPosition(resultSet.getString("position"));
                employee.setDivisision(resultSet.getString("division"));
                return employee;
            }
        });
        return listOfEmployee;
    }

    public String deleteEmployee(String id){
        String sql = "DELETE from employee WHERE id = " + id;
        if(jdbc.update(sql) == 1){
            return "success";
        }else
            return "failed";
    }

    public String saveEmployee(Employee employee){
        String sql = "";
        if(employee.getId() == "not found"){
            sql = "INSERT INTO employee (name, email, password, dob, position, division) VALUES (?, ?, ?, ?, ?, ?)";
            jdbc.update(sql, employee.getName(), employee.getEmail(), employee.getPassword(), employee.getDob()
                , employee.getPosition(), employee.getDivisision());
        }
        else {
            sql = "UPDATE employee set name = {0}, email = {1}, password = {2}, dob = {3}, position = {4}, division = {5}" +
                    " WHERE id = {6}";
            jdbc.update(sql, employee.getName(), employee.getEmail(), employee.getPassword(), employee.getDob()
                    , employee.getPosition(), employee.getDivisision(), employee.getId());
        }
        return "success";
    }

    public List<Employee> getSuperiorList(final Pageable pageable){
        String sql = "SELECT from employee WHERE id IN (SELECT superiorId from employee)";
        List<Employee> listOfSuperior =  jdbc.query(sql, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                if (i > pageable.getNumberOfPages()) {
                    return null;
                }
                Employee employee = new Employee();

                employee.setId(resultSet.getString("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getString("dob"));
                employee.setPosition(resultSet.getString("position"));
                employee.setDivisision(resultSet.getString("division"));
                return employee;
            }
        });
        return listOfSuperior;
    }
}
