package com.sportapi.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private String location;

    // Other columns as needed

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    // Constructors, getters, and setters

    public Event() {
        // Default constructor
    }

    public Event(String eventName,String location,LocalDate eventdate) {
        this.eventName = eventName;
        this.location=location;
        this.eventDate=eventdate;
    }


    // Getters and setters for all fields

    public Long getId() {
        return event_id;
    }

    public void setId(Long event_id) {
        this.event_id = event_id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate ){
        this.eventDate=eventDate;
    }

    public String getLocation(){
        return location;
    }

    public  void setLocation(String location){
        this.location=location;
    }
}
