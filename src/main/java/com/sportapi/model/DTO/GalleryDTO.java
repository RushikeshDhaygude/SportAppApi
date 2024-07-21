package com.sportapi.model.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleryDTO {
    private Long id;
    private OrganizationWithoutEventsDTO organization;
    private String imagePath;
    private String imageName;

    // Constructors, getters, and setters

    public GalleryDTO() {
        // Default constructor
    }

    public GalleryDTO(Long id, OrganizationWithoutEventsDTO organization, String imagePath, String imageName) {
        this.id = id;
        this.organization = organization;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    // Getters and setters
}
