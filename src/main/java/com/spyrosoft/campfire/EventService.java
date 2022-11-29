package com.spyrosoft.campfire;

import java.util.List;

public interface EventService {

    List<Event> getEvents();

    Event getEventById(Long id);

    Event insertEvent(Event event) throws InvalidDateException;

    void updateEvent(Long id, Event event) throws InvalidDateException;

    void deleteEvent(Long id);
}