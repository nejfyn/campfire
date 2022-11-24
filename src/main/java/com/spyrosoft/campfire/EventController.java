package com.spyrosoft.campfire;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public void createEvent() {
        Event event = new Event();

        event.setTopic("Fotografia");
        event.setDescription("cokolwiek");

        eventRepository.save(event);
    }
}
