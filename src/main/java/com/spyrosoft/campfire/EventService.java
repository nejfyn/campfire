package com.spyrosoft.campfire;

import java.util.List;

public interface EventService {

    List<Event> getEvents();

    Event getEventById(Long id);

    Event insertEvent(Event event);

    void updateEvent(Long id, Event event);

    void deleteEvent(Long id);
}