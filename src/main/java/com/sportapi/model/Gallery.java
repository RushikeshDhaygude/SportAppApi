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
    private String imagePath;

    @Column(nullable = false)
    private String imageName;

    // Constructors, getters, and setters

    public Gallery() {
        // Default constructor
    }

    public Gallery(Organization organization, String imagePath, String imageName) {
        this.organization = organization;
        this.imagePath = imagePath;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
