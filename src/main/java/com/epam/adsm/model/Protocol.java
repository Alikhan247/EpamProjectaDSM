package com.epam.adsm.model;

/**
 * Created by akmatleu on 17.05.17.
 */
public class Protocol extends BaseEntity{

    private EventPrototype eventPrototype;
    private TaskPrototype taskPrototype;

    public Protocol() {
    }

    public EventPrototype getEventPrototype() {
        return eventPrototype;
    }

    public void setEventPrototype(EventPrototype eventPrototype) {
        this.eventPrototype = eventPrototype;
    }

    public TaskPrototype getTaskPrototype() {
        return taskPrototype;
    }

    public void setTaskPrototype(TaskPrototype taskPrototype) {
        this.taskPrototype = taskPrototype;
    }
}
