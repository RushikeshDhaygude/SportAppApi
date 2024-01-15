//package com.sportapi.model;
//
//
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "pools")
//public class Pool {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "organization_id", nullable = false)
//    private Organization organization;
//
//    @ManyToOne
//    @JoinColumn(name = "event_id", nullable = false)
//    private Event event;
//
//    @Column(nullable = false)
//    private String poolName;
//
//    // Many teams can belong to one pool
//    @OneToMany(mappedBy = "pool", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Teams> teams;
//
//    // Other columns as needed
//
//    // Constructors, getters, and setters
//
//    public Pool() {
//        // Default constructor
//    }
//
//    public Pool(Organization organization, Event event, String poolName, List<Teams> teams) {
//        this.organization = organization;
//        this.event = event;
//        this.poolName = poolName;
//        this.teams = teams;
//    }
//
//    // Getters and setters for all fields
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Organization getOrganization() {
//        return organization;
//    }
//
//    public void setOrganization(Organization organization) {
//        this.organization = organization;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
//
//    public String getPoolName() {
//        return poolName;
//    }
//
//    public void setPoolName(String poolName) {
//        this.poolName = poolName;
//    }
//
//    public List<Teams> getTeams() {
//        return teams;
//    }
//
//    public void setTeams(List<Teams> teams) {
//        this.teams = teams;
//    }
//}
