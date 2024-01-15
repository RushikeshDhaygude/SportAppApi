package com.sportapi.services.Impl;

import com.sportapi.model.Organization;
import com.sportapi.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements com.sportapi.services.OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization getOrganizationById(Long organizationId) {
        Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);
        return optionalOrganization.orElse(null);
    }

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization updateOrganization(Long organizationId, Organization updatedOrganization) {
        Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);
        if (optionalOrganization.isPresent()) {
            Organization existingOrganization = optionalOrganization.get();
            existingOrganization.setName(updatedOrganization.getName());
            existingOrganization.setContact(updatedOrganization.getContact());
            existingOrganization.setEmail(updatedOrganization.getEmail());
            // Update other fields as needed
            return organizationRepository.save(existingOrganization);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }
}
