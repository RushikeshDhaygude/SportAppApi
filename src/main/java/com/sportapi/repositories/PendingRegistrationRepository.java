package com.sportapi.repositories;


import com.sportapi.model.PendingRegistrations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendingRegistrationRepository extends JpaRepository<PendingRegistrations, Long> {
    Optional<PendingRegistrations> findByToken(String token);
}
