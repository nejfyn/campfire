package com.spyrosoft.campfire.services;

import com.spyrosoft.campfire.exceptions.InvalidDateException;
import com.spyrosoft.campfire.entities.Event;
import com.spyrosoft.campfire.repos.EventRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public Event getEventById(Long id) throws NoSuchElementException {
        if (eventRepository.findById(id).isPresent()) {
            return eventRepository.findById(id).get();
        } else {
            throw new NoSuchElementException("Such event isn't scheduled");
        }
    }

    @Override
    public Event insertEvent(Event event) throws InvalidDateException {
        validateDate(event);
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Long id, Event event) throws InvalidDateException, NoSuchElementException {
        if (eventRepository.findById(id).isPresent()) {
            Event eventFromDb = eventRepository.findById(id).get();
            System.out.println(eventFromDb.toString());
            if (!eventFromDb.getDate().toString().equals(event.getDate().toString())) {
                validateDate(event);
            }
            eventFromDb.setTopic(event.getTopic());
            eventFromDb.setDescription(event.getDescription());
            eventFromDb.setAuthor(event.getAuthor());
            eventFromDb.setDate(event.getDate());
            eventFromDb.setDurationInMins(event.getDurationInMins());
            eventRepository.save(eventFromDb);
        } else {
            throw new NoSuchElementException("Such event isn't scheduled");
        }
    }

    @Override
    public void deleteEvent(Long id) {
        if (eventRepository.findById(id).isPresent()) {
            eventRepository.deleteById(id);
        }
        else {
            throw new NoSuchElementException("Such event isn't scheduled");
        }
    }

    private void validateDate(Event event) throws InvalidDateException {
        if (event.getDate().isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("Date must be in the future");
        } else if (event.getDate().getDayOfWeek() != DayOfWeek.FRIDAY) {
            throw new InvalidDateException("Date must be on Friday");
        } else if (isDateWithDurationAddedOccupied(getEvents(), event)) {
            throw new InvalidDateException("Date must be set on vacant date");
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