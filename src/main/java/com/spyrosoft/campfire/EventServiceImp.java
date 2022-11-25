package com.spyrosoft.campfire;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImp implements EventService {
    EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository) { this.eventRepository = eventRepository; }

    @Override
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Event insertEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Long id, Event event) {
        Event eventFromDb = eventRepository.findById(id).get();
        System.out.println(eventFromDb.toString());
        eventFromDb.setTopic(event.getTopic());
        eventFromDb.setDescription(event.getDescription());
        eventFromDb.setAuthor(event.getAuthor());
//        eventFromDb.setDate(event.getDate());
        eventRepository.save(eventFromDb);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}