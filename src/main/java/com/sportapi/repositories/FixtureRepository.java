package com.sportapi.repositories;



import com.sportapi.model.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {
    // Add custom query methods if needed
}
