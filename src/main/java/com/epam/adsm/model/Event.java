package com.epam.adsm.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.Date;
import java.util.List;

/**
 * Created by akmatleu on 16.05.17.
 */
public class Event  extends  BaseEntity{

    private java.time.LocalDate eventDate;
    private double eventProgress;
    private Research research;
    private EventPrototype eventPrototype;

    private List<String> tasksName;
    private List<Integer> taskProgress;
    private List<Integer> taskId;




    public Event() {}


    public java.time.LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(java.time.LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public double getEventProgress() {
        return eventProgress;
    }

    public void setEventProgress(double eventProgress) {
        this.eventProgress = eventProgress;
    }

    public Research getResearch() {
        return research;
    }

    public void setResearch(Research research) {
        this.research = research;
    }

    public EventPrototype getEventPrototype() {
        return eventPrototype;
    }

    public void setEventPrototype(EventPrototype eventPrototype) {
        this.eventPrototype = eventPrototype;
    }


    public List<String> getTasksName() {
        return tasksName;
    }

    public void setTasksName(List<String> tasksName) {
        this.tasksName = tasksName;
    }

    public List<Integer> getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(List<Integer> taskProgress) {
        this.taskProgress = taskProgress;
    }

    public List<Integer> getTaskId() {
        return taskId;
    }

    public void setTaskId(List<Integer> taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Event{" +
                ", eventDate=" + eventDate +
                ", eventProgress=" + eventProgress +
                '}';
    }
}
