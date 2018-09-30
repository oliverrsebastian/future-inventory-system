package com.blibli.inventory.models;

import com.sun.javafx.beans.IDProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private String id;
    private String superiorId;
    private String name;
    private String email;
    private String password;
    private String dob;
    private String position;
    private String divisision;
}
