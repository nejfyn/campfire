package com.spyrosoft.campfire;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String topic;
    private String description;
    private Calendar day;
    private Calendar month;
    private Calendar year;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public void setMonth(Calendar month) {
        this.month = month;
    }

    public void setYear(Calendar year) {
        this.year = year;
    }
}
