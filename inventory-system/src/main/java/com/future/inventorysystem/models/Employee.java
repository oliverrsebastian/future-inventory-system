package com.future.inventorysystem.models;


import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class Employee {

    @Id
    private String id;

    private String superiorId;
    private String name;
    private String dob;
    private String position;
    private String division;


    private String email;
    private String password;

}
