package com.epam.adsm.model;

public class EventPrototype extends BaseEntity {
    private int eventInterval;

    public EventPrototype() {}

    public int getEventInterval() {
        return eventInterval;
    }

    public void setEventInterval(int eventInterval) {
        this.eventInterval = eventInterval;
    }

}
