package com.blibli.inventory.repositories;

import com.blibli.inventory.models.Request;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.awt.print.Pageable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestRepository{
        private JdbcTemplate jdbc;

        public RequestRepository(DataSource dataSource){
            jdbc = new JdbcTemplate(dataSource);
        }

        public Request getRequest(String id){
            String sql = "SELECT FROM requests WHERE id = " + id;
            return jdbc.query(sql, new ResultSetExtractor<Request>() {
                @Override
                public Request extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    if(resultSet.next()){
                        Request request = new Request();
                        request.setId(resultSet.getString("id"));
                        request.setEmployeeId(resultSet.getString("employeeId"));
                        request.setItemId(resultSet.getString("itemId"));
                        request.setQty(Integer.parseInt(resultSet.getString("qty")));
                        request.setStatus(resultSet.getString("status"));
                        request.setNotes(resultSet.getString("notes"));
                        return request;
                    }
                    return null;
                }
            });
        }

        public List<Request> getRequestList(final Pageable pageable){
            String sql = "SELECT FROM requests";
            return jdbc.query(sql, new RowMapper<Request>() {
                @Override
                public Request mapRow(ResultSet resultSet, int i) throws SQLException {
                    if(i < pageable.getNumberOfPages()){
                        Request request = new Request();
                        request.setId(resultSet.getString("id"));
                        request.setEmployeeId(resultSet.getString("employeeId"));
                        request.setItemId(resultSet.getString("itemId"));
                        request.setQty(Integer.parseInt(resultSet.getString("qty")));
                        request.setStatus(resultSet.getString("status"));
                        request.setNotes(resultSet.getString("notes"));
                        return request;
                    }
                    return null;
                }
            });
        }

        public String saveRequest(Request request){
            String sql = "";
            if(request.getId() == "not found"){
                sql = "INSERT INTO requests (employeeId, itemId, qty, status, notes) VALUES (?, ?, ?, ?, ?)";
                jdbc.update(sql, request.getEmployeeId(), request.getItemId(), request.getQty(), request.getStatus()
                        , request.getNotes());
            }
            else {
                sql = "UPDATE requests set employeeId = {0}, itemId = {1}, qty = {2}, status = {3}, notes = {4}}" +
                        " WHERE id = {5}";
                jdbc.update(sql, request.getEmployeeId(), request.getItemId(), request.getQty(), request.getStatus()
                        , request.getNotes(), request.getId());
            }
            return "success";
        }

        public String deleteRequest(String id){
            String sql = "DELETE from requests WHERE id = " + id;
            if(jdbc.update(sql) == 1){
                return "success";
            }else
                return "failed";
        }
}
