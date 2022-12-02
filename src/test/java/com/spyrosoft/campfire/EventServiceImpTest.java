package com.spyrosoft.campfire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase
@SpringBootTest
class EventServiceImpTest {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventService eventService;

    @BeforeEach
    public void eraseList() {
        eventRepository.deleteAll();
    }

    @Test
    public void doEventsSave() {
        //given
        eventRepository.save(new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusHours(1), 32));
        eventRepository.save(new Event("dssdfv", "ssaafsad", "asdfgsd", LocalDateTime.now().plusHours(14), 32));
        eventRepository.save(new Event("dsgdfv", "safsagfdd", "bfgdasd", LocalDateTime.now().plusHours(21), 32));
        //then
        assertEquals(3, eventRepository.findAll().size());
    }

    @Test
    public void doAllTheEventsDelete() {
        //given
        eventRepository.save(new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusHours(1), 32));
        eventRepository.save(new Event("dssdfv", "ssaafsad", "asdfgsd", LocalDateTime.now().plusHours(14), 32));
        eventRepository.save(new Event("dsgdfv", "safsagfdd", "bfgdasd", LocalDateTime.now().plusHours(21), 32));
        //when
        eventRepository.deleteAll();
        //then
        assertEquals(0, eventRepository.findAll().size());
    }

    @Test
    public void doEventsDeleteById() {
        //given
        eventRepository.save(new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusHours(1), 32));
        eventRepository.save(new Event("dssdfv", "ssaafsad", "asdfgsd", LocalDateTime.now().plusHours(14), 32));
        eventRepository.save(new Event("dsgdfv", "safsagfdd", "bfgdasd", LocalDateTime.now().plusHours(21), 32));
        long id = eventRepository.findAll().get(0).getId();
        //when
        eventRepository.deleteById(id);
        //then
        assertEquals(2, eventRepository.findAll().size());
    }

    @Test
    public void doEventsUpdate() throws InvalidDateException {
        //given
        Event eventFromDb = new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusDays(2).plusHours(1), 32);
        Event newEvent = new Event("dsgdfv", "safsagfdd", "bfgdasd", LocalDateTime.now().plusDays(2).plusHours(3), 32);
        eventRepository.save(eventFromDb);
        long id = eventRepository.findAll().get(0).getId();
        //when
        eventService.updateEvent(id, newEvent);
        //then
        assertEquals("dsgdfv", eventService.getEventById(id).getTopic());
    }

    @Test
    public void isDateInThePast() {
        //given
        Event event = new Event("dsgsffv", "safsgfsad", "ahgfsd", LocalDateTime.now().minusHours(1), 50);
        //then
        assertThrows(InvalidDateException.class, () -> eventService.insertEvent(event), "Date must be in the future");
    }

    @Test
    public void TheDayIsntFriday() {
        assertThrows(InvalidDateException.class,
                () -> eventService.insertEvent(new Event("dsfv", "safsad", "asd", LocalDateTime.parse("2022-12-22T09:00:00"), 32)),
                "Date must be on Friday");
    }

    @Test
    public void doEventsCollidePartlyAfter() {
        //given
        Event event1 = new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusMinutes(30), 32);
        Event event2 = new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusMinutes(60), 40);
        eventRepository.save(event1);
        //then
        assertThrows(InvalidDateException.class, () -> eventService.insertEvent(event2), "Date must be set on vacant date");
    }

    @Test
    public void doEventsCollidePartlyBefore() {
        //given
        Event event1 = new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusMinutes(60), 40);
        Event event2 = new Event("dwesfv", "sfdafsad", "assdd", LocalDateTime.now().plusMinutes(30), 32);
        eventRepository.save(event1);
        //then
        assertThrows(InvalidDateException.class, () -> eventService.insertEvent(event2), "Date must be set on vacant date");
    }

    @Test
    public void doEventsCollideWithin() {
        //given
        Event event1 = new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusMinutes(30), 120);
        Event event2 = new Event("dfsfv", "safsfad", "asfd", LocalDateTime.now().plusMinutes(60), 40);
        eventRepository.save(event1);
        //then
        assertThrows(InvalidDateException.class, () -> eventService.insertEvent(event2), "Date must be set on vacant date");
    }

    @Test
    public void doEventsNotCollideAfter() {
        //given
        Event event1 = new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusMinutes(30), 32);
        Event event2 = new Event("dfsfv", "sfafsad", "afsd", LocalDateTime.now().plusMinutes(90), 40);
        eventRepository.save(event1);
        eventRepository.save(event2);
        //then
        assertEquals(2, eventRepository.findAll().size());
    }

    @Test
    public void doEventsNotCollideBefore() {
        //given
        Event event1 = new Event("dfsfv", "sfafsad", "afsd", LocalDateTime.now().plusMinutes(90), 40);
        Event event2 = new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusMinutes(30), 32);
        eventRepository.save(event1);
        eventRepository.save(event2);
        //then
        assertEquals(2, eventRepository.findAll().size());
    }
}