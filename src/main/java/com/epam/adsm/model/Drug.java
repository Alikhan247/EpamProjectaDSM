package com.epam.adsm.model;

public class Drug extends BaseEntity{

    private String name;

    public Drug(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
