package com.sportapi.services.Impl;

import com.sportapi.model.Event;
import com.sportapi.model.Pool;
import com.sportapi.repositories.EventRepository;
import com.sportapi.repositories.PoolRepository;
import com.sportapi.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    private PoolRepository poolRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.orElse(null);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long eventId, Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setEventName(updatedEvent.getEventName());
            existingEvent.setEventDate(updatedEvent.getEventDate());
            existingEvent.setLocation(updatedEvent.getLocation());
            existingEvent.setOrganizationId(updatedEvent.getOrganizationId());
            // Update other fields as needed
            return eventRepository.save(existingEvent);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public void addPoolToEvent(Long eventId, Long poolId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        Pool pool = poolRepository.findById(poolId).orElse(null);

        if (event != null && pool != null) {
            event.getPools().add(pool);
            eventRepository.save(event);
        }
    }

    @Override
    public List<Pool> getPoolsForEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        return event != null ? event.getPools() : null;
    }


}
