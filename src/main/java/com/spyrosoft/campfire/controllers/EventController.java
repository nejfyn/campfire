package com.spyrosoft.campfire.controllers;

import com.spyrosoft.campfire.entities.Event;
import com.spyrosoft.campfire.services.EventService;
import com.spyrosoft.campfire.exceptions.InvalidDateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity getEvent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(eventService.getEventById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity saveEvent(@RequestBody Event event) {
        try {
            Event event1 = eventService.insertEvent(event);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("event", "/event/" + event1.getId().toString());
            return new ResponseEntity<>(event1, httpHeaders, HttpStatus.CREATED);
        } catch (InvalidDateException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity updateEvent(@PathVariable("id") Long id, @RequestBody Event event) {
        try {
            eventService.updateEvent(id, event);
            return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
        } catch (InvalidDateException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}