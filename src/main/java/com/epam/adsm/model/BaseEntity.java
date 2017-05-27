package com.epam.adsm.model;

/**
 * Created by akmatleu on 19.05.17.
 */
abstract class BaseEntity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
