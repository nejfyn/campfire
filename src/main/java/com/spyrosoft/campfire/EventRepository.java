package com.spyrosoft.campfire;

import ch.qos.logback.core.boolex.EventEvaluatorBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
