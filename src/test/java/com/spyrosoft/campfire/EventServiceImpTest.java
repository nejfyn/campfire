package com.spyrosoft.campfire;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
class EventServiceImpTest {

    @Autowired
    EventRepository eventRepository;

    @Test
    public void doEventsSave() {
        //given
        eventRepository.save(new Event("dsfv", "safsad", "asd", LocalDateTime.now().plusHours(1), 32));
        //then
        assertEquals(1, eventRepository.findAll().size());
    }

    //TODO: test about the past, not-Fridays and occupied dates
//    @Test
//    public void isDateInThePast() {
//        //then
//        assertThrows(InvalidDateException.class ,
//                () -> eventRepository.save(new Event("dsfv", "safsad", "asd", LocalDateTime.now().minusHours(1), 32)),
//                "Date must be in the future");
//    }
}