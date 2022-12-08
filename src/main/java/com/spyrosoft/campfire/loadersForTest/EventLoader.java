package com.spyrosoft.campfire.loadersForTest;

import com.spyrosoft.campfire.entities.Event;
import com.spyrosoft.campfire.repos.EventRepository;
import com.spyrosoft.campfire.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class EventLoader implements CommandLineRunner {
    public final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventLoader(EventRepository eventRepository,
                       UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEvents();
    }

    private void loadEvents() {
        if (eventRepository.count() == 0) {
            Event event1 = new Event("Fotografia", "cokolwiek", "Piotr", LocalDateTime.parse("2022-11-25T09:00:00"), 30);
            Event event2 = new Event("Wedkarstwo", "cos ciekawego", "Pawel", LocalDateTime.parse("2022-12-02T09:00:00"), 45);
            eventRepository.save(event1);
            eventRepository.save(event2);
            System.out.println("Sample events loaded");
        }
//        if (userRepository.count() == 0) {
//            Event event1 = new Event("Fotografia", "cokolwiek", "Piotr", LocalDateTime.parse("2022-11-25T09:00:00"), 30);
//            Event event2 = new Event("Wedkarstwo", "cos ciekawego", "Pawel", LocalDateTime.parse("2022-12-02T09:00:00"), 45);
//            eventRepository.save(event1);
//            eventRepository.save(event2);
//            System.out.println("Sample events loaded");
    }
}