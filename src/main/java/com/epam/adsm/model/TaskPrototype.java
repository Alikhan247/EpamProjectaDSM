package com.epam.adsm.model;

/**
 * Created by akmatleu on 13.05.17.
 */
public class TaskPrototype extends BaseEntity {


    private String taskPrototypeName;

    public TaskPrototype(String taskPrototypeName) {
        this.taskPrototypeName = taskPrototypeName;
    }

    public TaskPrototype() {}


    public String getTaskPrototypeName() {
        return taskPrototypeName;
    }

    public void setTaskPrototypeName(String taskPrototypeName) {
        this.taskPrototypeName = taskPrototypeName;
    }

    @Override
    public String toString() {
        return "TaskPrototype{" +
                ", taskPrototypeName='" + taskPrototypeName + '\'' +
                '}';
    }
}
