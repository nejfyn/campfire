package com.spyrosoft.campfire;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column
    @NotEmpty
    private String topic;
    @Column
    private String description;
    @Column
    @NotEmpty
    private String author;
    @Column
    @NotNull
    private LocalDateTime date;
    @Column
    @NotNull
    private Integer durationInMins;


    public Event(String topic, String description, String author, LocalDateTime date, int durationInMins) {
        this.topic = topic;
        this.description = description;
        this.author = author;
        this.date = date;
        this.durationInMins = durationInMins;
    }

    public Event() {
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }


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

    public LocalDateTime getDate() {
        return date;
    }

    public int getDurationInMins() {
        return durationInMins;
    }
}