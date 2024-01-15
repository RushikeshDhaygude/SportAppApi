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
    @Column(nullable = false)
    private byte[] image;

    // Other columns as needed

    // Constructors, getters, and setters

    public Gallery() {
        // Default constructor
    }

    public Gallery(Organization organization, byte[] image) {
        this.organization = organization;
        this.image = image;
    }

    // Getters and setters for all fields

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
