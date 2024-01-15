package com.sportapi.services;

import com.sportapi.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    Event getEventById(Long eventId);

    Event createEvent(Event event);

    Event updateEvent(Long eventId, Event updatedEvent);

    void deleteEvent(Long eventId);
}
