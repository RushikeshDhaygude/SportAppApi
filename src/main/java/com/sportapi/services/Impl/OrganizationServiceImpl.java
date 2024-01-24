package com.sportapi.services.Impl;

import com.sportapi.model.Organization;
import com.sportapi.repositories.OrganizationRepository;
import com.sportapi.services.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        try {
            return organizationRepository.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error getting all organizations", e);
        }
    }

    @Override
    public Organization getOrganizationById(Long organizationId) {
        try {
            Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);
            return optionalOrganization.orElse(null);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error getting organization by ID: " + organizationId, e);
        }
    }

    @Override
    public Organization createOrganization(Organization organization) {
        try {
            return organizationRepository.save(organization);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error creating organization", e);
        }
    }

    @Override
    public Organization updateOrganization(Long organizationId, Organization updatedOrganization) {
        try {
            Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);
            if (optionalOrganization.isPresent()) {
                Organization existingOrganization = optionalOrganization.get();
                existingOrganization.setName(updatedOrganization.getName());
                existingOrganization.setContact(updatedOrganization.getContact());
                existingOrganization.setEmail(updatedOrganization.getEmail());
                // Update other fields as needed
                return organizationRepository.save(existingOrganization);
            } else {
                throw new EntityNotFoundException("Organization not found with ID: " + organizationId);
            }
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error updating organization with ID: " + organizationId, e);
        }
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        try {
            organizationRepository.deleteById(organizationId);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error deleting organization with ID: " + organizationId, e);
        }
    }
}
