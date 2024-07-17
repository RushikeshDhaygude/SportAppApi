package com.sportapi.services.Impl;

import com.sportapi.model.DTO.AuthResponse;
import com.sportapi.model.Organization;
import com.sportapi.model.PendingRegistrations;
import com.sportapi.repositories.OrganizationRepository;
import com.sportapi.repositories.PendingRegistrationRepository;
import com.sportapi.services.AuthServices.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Value("${app.confirmation.link}")
    private String confirmationLink;

    @Autowired
    private PendingRegistrationRepository pendingRegistrationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Override
    public boolean registerOrganization(Long organizationId, String email, String password) {
        if (organizationRepository.existsById(organizationId)) {
            String token = UUID.randomUUID().toString();
            PendingRegistrations pendingRegistration = new PendingRegistrations(
                    organizationId, email, passwordEncoder.encode(password), token
            );
            pendingRegistrationRepository.save(pendingRegistration);
            sendConfirmationEmail(adminEmail, token);
            System.out.println("Token generated and saved: " + token); // Debug statement
            return true;
        }
        return false;
    }

    @Override
    public boolean confirmRegistration(String token) {
        System.out.println("Token received for confirmation: " + token); // Debug statement
        Optional<PendingRegistrations> pendingRegistrationOpt = pendingRegistrationRepository.findByToken(token);
        if (pendingRegistrationOpt.isPresent()) {
            PendingRegistrations pendingRegistration = pendingRegistrationOpt.get();
            Organization organization = organizationRepository.findById(pendingRegistration.getOrganizationId()).orElse(null);
            System.out.println("Pending Registration found: " + pendingRegistration.getEmail()); // Debug statement
            if (organization != null && organization.getEmail().equals(pendingRegistration.getEmail())) {
                organization.setPassword(pendingRegistration.getPassword());
                organizationRepository.save(organization);
                pendingRegistrationRepository.delete(pendingRegistration);
                return true;
            } else {
                System.out.println("Organization not found or email mismatch"); // Debug statement
            }
        } else {
            System.out.println("Pending registration not found for token"); // Debug statement
        }
        return false;
    }

    @Override
    public AuthResponse authenticateOrganization(Long organizationId, String email, String password) {
        Organization organization = organizationRepository.findById(organizationId).orElse(null);
        if (organization != null && organization.getEmail().equals(email)) {
            if (passwordEncoder.matches(password, organization.getPassword())) {
                String token = generateToken(organizationId, email);
                return new AuthResponse(token, organization);
            }
        }
        return null;
    }

    private String generateToken(Long organizationId, String email) {
        return Jwts.builder()
                .setSubject(String.valueOf(organizationId))
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private void sendConfirmationEmail(String adminEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("Organization Registration Confirmation");
        message.setText("Please confirm the registration by clicking the following link: " +
                confirmationLink + token);
        mailSender.send(message);
    }
}
