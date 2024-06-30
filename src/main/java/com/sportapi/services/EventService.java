package com.sportapi.services;

import com.sportapi.model.Event;
import com.sportapi.model.Pool;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    Event getEventById(Long eventId);


    Event createEvent(Event event);

    Event updateEvent(Long eventId, Event updatedEvent);

    void deleteEvent(Long eventId);

    void addPoolToEvent(Long eventId, Long poolId);

    List<Pool> getPoolsForEvent(Long eventId);
}
