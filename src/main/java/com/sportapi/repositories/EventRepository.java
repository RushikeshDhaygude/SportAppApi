package com.sportapi.repositories;




import com.sportapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Add any custom queries if needed
}
