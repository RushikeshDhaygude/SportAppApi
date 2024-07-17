package com.sportapi.controllers.AuthControllers;

import com.sportapi.services.AuthServices.AuthService;
import com.sportapi.model.DTO.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam Long organizationId, @RequestParam String email, @RequestParam String password) {
        boolean isRegistered = authService.registerOrganization(organizationId, email, password);
        if (isRegistered) {
            return ResponseEntity.ok("Registration initiated. Please wait for admin confirmation.");
        } else {
            return ResponseEntity.badRequest().body("Invalid organization ID or email");
        }
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam String token) {
        System.out.println("Token received for confirmation in controller: " + token); // Debug statement
        boolean isConfirmed = authService.confirmRegistration(token);
        if (isConfirmed) {
            return ResponseEntity.ok("Registration confirmed successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam Long organizationId, @RequestParam String email, @RequestParam String password) {
        AuthResponse authResponse = authService.authenticateOrganization(organizationId, email, password);
        if (authResponse != null) {
            return ResponseEntity.ok(authResponse);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
