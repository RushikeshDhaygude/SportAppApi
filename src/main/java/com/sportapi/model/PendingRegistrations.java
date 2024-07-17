package com.sportapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class PendingRegistrations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long organizationId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String token;

    public PendingRegistrations() {
    }

    public PendingRegistrations(Long organizationId, String email, String password, String token) {
        this.organizationId = organizationId;
        this.email = email;
        this.password = password;
        this.token = token;
    }

}
