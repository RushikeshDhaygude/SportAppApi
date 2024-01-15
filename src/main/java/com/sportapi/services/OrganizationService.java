package com.sportapi.services;

import com.sportapi.model.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> getAllOrganizations();

    Organization getOrganizationById(Long organizationId);

    Organization createOrganization(Organization organization);

    Organization updateOrganization(Long organizationId, Organization updatedOrganization);

    void deleteOrganization(Long organizationId);
}
