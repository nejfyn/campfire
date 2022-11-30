package com.spyrosoft.campfire;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImp implements EventService {
    EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getEvents() {
        return new ArrayList<>(eventRepository.findAll());
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Event insertEvent(Event event) throws InvalidDateException {
        validateDate(event);
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Long id, Event event) throws InvalidDateException {
        Event eventFromDb = eventRepository.findById(id).get();
        System.out.println(eventFromDb.toString());
        eventFromDb.setTopic(event.getTopic());
        eventFromDb.setDescription(event.getDescription());
        eventFromDb.setAuthor(event.getAuthor());
        eventFromDb.setDate(event.getDate());
        eventFromDb.setDurationInMins(event.getDurationInMins());
        validateDate(eventFromDb);
        eventRepository.save(eventFromDb);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private void validateDate(Event event) throws InvalidDateException {
        if (event.getDate().isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("Date must be in the future");
        } else if (event.getDate().getDayOfWeek() != DayOfWeek.FRIDAY) {
            throw new InvalidDateException("Date must be on Friday");
        } else if (isDateWithDurationAddedOccupied(getEvents(), event)) {
            throw new InvalidDateException(("Date must be set on vacant date"));
        }
    }

    private boolean isDateWithDurationAddedOccupied(List<Event> events, Event eventToSave) {
        for (Event event : events) {
            if (!event.getDate().plusMinutes(event.getDurationInMins()).isBefore(eventToSave.getDate()) &&
                    !eventToSave.getDate().plusMinutes(eventToSave.getDurationInMins()).isBefore(event.getDate())) {
                return true;
            }
        }
        return false;
    }
}