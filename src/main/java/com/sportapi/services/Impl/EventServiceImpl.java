package com.sportapi.services.Impl;

import com.sportapi.model.Event;
import com.sportapi.model.Pool;
import com.sportapi.repositories.EventRepository;
import com.sportapi.repositories.PoolRepository;
import com.sportapi.services.EventService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
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
        try {
            return eventRepository.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error getting all events", e);
        }
    }

    @Override
    public Event getEventById(Long eventId) {
        try {
            Optional<Event> optionalEvent = eventRepository.findById(eventId);
            return optionalEvent.orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));
        } catch (EntityNotFoundException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error getting event by ID: " + eventId, e);
        }
    }

    @Override
    public Event createEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error creating event", e);
        }
    }

    @Override
    public Event updateEvent(Long eventId, Event updatedEvent) {
        try {
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
                throw new EntityNotFoundException("Event not found with ID: " + eventId);
            }
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error updating event with ID: " + eventId, e);
        }
    }

    @Override
    public void deleteEvent(Long eventId) {
        try {
            eventRepository.deleteById(eventId);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error deleting event with ID: " + eventId, e);
        }
    }

    @Override
    public void addPoolToEvent(Long eventId, Long poolId) {
        try {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));

            Pool pool = poolRepository.findById(poolId)
                    .orElseThrow(() -> new EntityNotFoundException("Pool not found with ID: " + poolId));

            event.getPools().add(pool);
            eventRepository.save(event);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error adding pool to event", e);
        }
    }

    @Override
    public List<Pool> getPoolsForEvent(Long eventId) {
        try {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));
            return event.getPools();
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error getting pools for event", e);
        }
    }
}
