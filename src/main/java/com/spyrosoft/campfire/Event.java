package com.spyrosoft.campfire;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column
    private String topic;
    @Column
    private String description;
    @Column
    private String author;
//    @Column
//    private Calendar date;


    public Event(String topic, String description, String author) {
        this.topic = topic;
        this.description = description;
        this.author = author;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAuthor(String author) { this.author = author; }
    //    public void setDate(int year, int month, int day) { date.set(year, month, day);}


    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    //    public Calendar getDate() {
//        return date;
//    }

//    public void setDate(Calendar date) {
//
//    }
}
