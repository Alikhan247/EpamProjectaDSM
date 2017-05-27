package com.epam.adsm.model;

/**
 * Created by akmatleu on 09.05.17.
 */
public class Drug extends BaseEntity{

    private String name;

    public Drug(){}


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Drug{" +
                "name='" + name + '\'' +
                '}';
    }
}
