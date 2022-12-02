package com.spyrosoft.campfire;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping({"/events/{id}"})
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public ResponseEntity saveEvent(@RequestBody Event event) {
        try {
            Event event1 = eventService.insertEvent(event);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("event", "/event/" + event1.getId().toString());
            return new ResponseEntity<>(event1, httpHeaders, HttpStatus.CREATED);
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity updateEvent(@PathVariable("id") Long id, @RequestBody Event event) {
        try {
            eventService.updateEvent(id, event);
            return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}