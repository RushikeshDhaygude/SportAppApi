package com.sportapi.controllers;

import com.sportapi.model.Event;
import com.sportapi.model.Pool;
import com.sportapi.repositories.EventRepository;
import com.sportapi.repositories.PoolRepository;
import com.sportapi.services.EventService;
import com.sportapi.services.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    private PoolService poolService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PoolRepository poolRepository;



    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long eventId) {
        Event event = eventService.getEventById(eventId);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable("id") Long eventId,
            @RequestBody Event updatedEvent
    ) {
        Event event = eventService.updateEvent(eventId, updatedEvent);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping("/createPool")
//    public Pool createPool(@RequestBody Pool pool) {
//        return poolService.createPool(pool);
//    }
//
//
//    @PostMapping("/{eventId}/pools/{poolId}")
//    public void addPoolToEvent(@PathVariable Long eventId, @PathVariable Long poolId) {
//        eventService.addPoolToEvent(eventId, poolId);
//    }
//
//    @GetMapping("/{eventId}/pools")
//    public List<Pool> getPoolsForEvent(@PathVariable Long eventId) {
//        return eventService.getPoolsForEvent(eventId);
//    }

}
