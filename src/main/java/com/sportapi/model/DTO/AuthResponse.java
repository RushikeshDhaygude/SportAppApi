package com.sportapi.model.DTO;


import com.sportapi.model.Organization;

public class AuthResponse {
    private String token;
    private Organization organization;

    public AuthResponse(String token, Organization organization) {
        this.token = token;
        this.organization = organization;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
