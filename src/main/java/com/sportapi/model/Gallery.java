package com.sportapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "galleries")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Lob
    @Column
    private String imagePath;

    // Constructors, getters, and setters

    public Gallery() {
        // Default constructor
    }

    public Gallery(Organization organization, String imagePath) {
        this.organization = organization;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
