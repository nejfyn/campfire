package com.spyrosoft.campfire;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class EventLoader implements CommandLineRunner {
    public final EventRepository eventRepository;

    public EventLoader(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEvents();
    }

    private void loadEvents() {
        if (eventRepository.count() == 0) {
            Event event1 = new Event("Fotografia", "cokolwiek", "Piotr");
            Event event2 = new Event("Morsowanie", "nic ciekawego", "Pawel");
            eventRepository.save(event1);
            eventRepository.save(event2);
            System.out.println("Sample events loaded");
        }
    }
}
