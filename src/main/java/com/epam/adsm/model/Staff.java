package com.epam.adsm.model;

/**
 * Created by akmatleu on 11.05.17.
 */
public class Staff extends Person {

    private boolean activity_status;
    private String role;
    private boolean delete_status;


    public Staff(){}



    public boolean isActivity_status() {
        return activity_status;
    }

    public void setActivity_status(boolean activity_status) {
        this.activity_status = activity_status;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDelete_status() {
        return delete_status;
    }

    public void setDelete_status(boolean delete_status) {
        this.delete_status = delete_status;
    }

    @Override
    public String toString() {
        return "Staff{" +
                ", activity_status=" + activity_status +
                ", role='" + role + '\'' +
                ", delete_status=" + delete_status +
                '}';
    }
}
