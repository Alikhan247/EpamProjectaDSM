package com.epam.adsm.model;

/**
 * Created by akmatleu on 17.05.17.
 */
public class Task  extends BaseEntity{
    private TaskPrototype taskPrototype;
    private Event event;
    private int taskProgress;

    public Task() {}

    public TaskPrototype getTaskPrototype() {
        return taskPrototype;
    }

    public void setTaskPrototype(TaskPrototype taskPrototype) {
        this.taskPrototype = taskPrototype;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(int taskProgress) {
        this.taskProgress = taskProgress;
    }

}
