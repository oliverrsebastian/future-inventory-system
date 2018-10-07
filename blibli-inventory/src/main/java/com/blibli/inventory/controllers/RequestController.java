package com.blibli.inventory.controllers;

import com.blibli.inventory.models.Request;
import com.blibli.inventory.services.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "/api/requests", produces = "application/json",
            method = RequestMethod.GET)
    public List<Request> listOfAllRequests(@RequestParam String orderBy,
                                           @RequestParam String orderType) throws IOException {
        List<Request> listOfRequests = service.getAllRequestList(orderBy, orderType, pageable);
        return listOfRequests;
    }

    @RequestMapping(value = "/api/requests", produces = "application/json",
            method = RequestMethod.GET)
    public List<Request> listOfEmployeeRequests(@RequestParam String employeeId,
                                           @RequestParam String orderBy,
                                           @RequestParam String orderType) throws IOException {
        List<Request> listOfRequests = service.getEmployeeRequestList(employeeId, orderBy, orderType, pageable);
        return listOfRequests;
    }

    @RequestMapping(value = "api/requests/{id}", consumes = "application/json", produces = "application/json",
            method = RequestMethod.GET)
    public Request RequestData(@RequestParam String id, @RequestParam String employeeId) throws IOException{
        Request request = service.getRequest(id, employeeId);
        return request;
    }

    @RequestMapping(value = "api/requests", consumes = "application/json", produces = "application/json",
            method = RequestMethod.POST)
    public String saveRequest(DefaultMultipartHttpServletRequest request){
        Request requestData =  new Request();
        if(request.getParameter("id").equals(null)){
            requestData.setId("not found");
        }else {
            requestData.setId(request.getParameter("id"));
        }
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

    @RequestMapping(value = "api/requests", consumes = "application/json", produces = "application/json",
            method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestParam List<String> ids){
        return service.deleteRequests(ids);
    }
}
