package com.sportapi.services.AuthServices;

import com.sportapi.model.Organization;
import com.sportapi.model.DTO.AuthResponse;

public interface AuthService {
    boolean registerOrganization(Long organizationId, String email, String password);
    boolean confirmRegistration(String token);
    AuthResponse authenticateOrganization(Long organizationId, String email, String password);
}
