package com.blibli.inventory.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String id;
    private String sku;
    private String name;
    private int price;
    private String location;
}
