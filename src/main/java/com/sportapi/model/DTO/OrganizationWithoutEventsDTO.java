package com.sportapi.model.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrganizationWithoutEventsDTO {
    private Long id;
    private String name;
    private String contact;
    private String email;

    // Constructors, getters, and setters

    public OrganizationWithoutEventsDTO() {
        // Default constructor
    }

    public OrganizationWithoutEventsDTO(Long id, String name, String contact, String email) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }


}
