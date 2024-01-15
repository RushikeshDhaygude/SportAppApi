package com.sportapi.repositories;

import com.sportapi.model.Event;
import com.sportapi.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

}
