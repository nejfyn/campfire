package com.spyrosoft.campfire;

import org.hibernate.cache.spi.access.CachedDomainDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
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

    @GetMapping({"/{id}"})
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        Event event1 = eventService.insertEvent(event);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("event", "/event" + event1.getId().toString());
        return new ResponseEntity<>(event1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Event> updateEvent(@PathVariable("id") Long id, @RequestBody Event event) {
        eventService.updateEvent(id, event);
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping
//    public void createEvent() {
//        Event event = new Event();
//
//        event.setTopic("Fotografia");
//        event.setDescription("cokolwiek");
////        event.setDate(2022,11,9);
//
//        eventRepository.save(event);
//    }


}
