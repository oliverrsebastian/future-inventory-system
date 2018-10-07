package com.blibli.inventory.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {

    private String id;
    private String employeeId;
    private String itemId;
    private int qty;
    private String status;
    private String notes;
}
