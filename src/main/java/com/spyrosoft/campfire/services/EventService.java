package com.spyrosoft.campfire.services;

import com.spyrosoft.campfire.exceptions.InvalidDateException;
import com.spyrosoft.campfire.entities.Event;

import java.util.List;

public interface EventService {

    List<Event> getEvents();

    Event getEventById(Long id);

    Event insertEvent(Event event) throws InvalidDateException;

    void updateEvent(Long id, Event event) throws InvalidDateException;

    void deleteEvent(Long id);
}