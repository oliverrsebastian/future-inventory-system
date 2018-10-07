package com.blibli.inventory.controllers;

import com.blibli.inventory.models.Request;
import com.blibli.inventory.services.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@Controller
public class RequestController {
    RequestService service = new RequestService();
    Pageable pageable;

    @RequestMapping(value = "/api/employees", produces = "application/json",
            method = RequestMethod.GET)
    public List<Request> listOfEmployee() throws IOException {
        List<Request> listOfRequests = service.getRequestList(pageable);
        return listOfRequests;
    }

    @RequestMapping(value = "api/employee/{id}", consumes = "application/json", produces = "application/json",
            method = RequestMethod.GET)
    public Request RequestData(@RequestParam String id) throws IOException{
        Request request = service.getRequest(id);
        return request;
    }

    @RequestMapping(value = "api/employee", consumes = "application/json", produces = "application/json",
            method = RequestMethod.POST)
    public String saveRequest(DefaultMultipartHttpServletRequest request){
        Request requestData =  new Request();
        requestData.setId(request.getParameter("id"));
        requestData.setEmployeeId(request.getParameter("employeeId"));
        requestData.setItemId(request.getParameter("itemId"));
        requestData.setQty(Integer.parseInt(request.getParameter("qty")));
        requestData.setStatus(request.getParameter("status"));
        requestData.setNotes(request.getParameter("notes"));
        String error = service.saveRequest(requestData);
        if(error.length() > 0)
            return "failed";
        return "success";
    }

    @RequestMapping(value = "api/employee", consumes = "application/json", produces = "application/json",
            method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestParam List<String> ids){
        return service.deleteRequests(ids);
    }
}
