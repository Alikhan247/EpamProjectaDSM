package com.epam.adsm.model;

public class Protocol extends BaseEntity{
    private TaskPrototype taskPrototype;

    public Protocol() {
    }

    public TaskPrototype getTaskPrototype() {
        return taskPrototype;
    }

    public void setTaskPrototype(TaskPrototype taskPrototype) {
        this.taskPrototype = taskPrototype;
    }
}
