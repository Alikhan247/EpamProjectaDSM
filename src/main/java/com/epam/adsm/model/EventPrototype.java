package com.epam.adsm.model;

import org.joda.time.Duration;
import org.joda.time.Interval;

/**
 * Created by akmatleu on 13.05.17.
 */
public class EventPrototype extends BaseEntity {

    private String eventPrototypeName;
    private int eventInterval;



    public EventPrototype() {}


    public String getEventPrototypeName() {
        return eventPrototypeName;
    }

    public void setEventPrototypeName(String eventPrototypeName) {
        this.eventPrototypeName = eventPrototypeName;
    }

    public int getEventInterval() {
        return eventInterval;
    }

    public void setEventInterval(int eventInterval) {
        this.eventInterval = eventInterval;
    }

    @Override
    public String toString() {
        return "EventPrototype{" +
                ", eventPrototypeName='" + eventPrototypeName + '\'' +
                ", eventInterval=" + eventInterval +
                '}';
    }
}
