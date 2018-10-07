package com.blibli.inventory.services;


import com.blibli.inventory.models.Request;
import com.blibli.inventory.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRepository repository;

    public Request getRequest(String id, String employeeId) {
        return repository.getRequest(id, employeeId);
    }

    public List<Request> getAllRequestList(String orderBy, String orderType, Pageable pageable){
        return repository.getAllRequestList(orderBy, orderType, pageable);
    }

    public List<Request> getEmployeeRequestList(String employeeId, String orderBy, String orderType, Pageable pageable){
        return repository.getEmployeeRequestList(employeeId, orderBy, orderType, pageable);
    }

    public String saveRequest(Request request){
        return repository.saveRequest(request);
    }

    public String deleteRequests(List<String> ids){
        String error = "";
        for(int i = 0; i < ids.size(); i++){
            error = repository.deleteRequest(ids.get(i));
        }
        if(error.length() > 0){
            return error;
        }
        return "success";
    }
}
