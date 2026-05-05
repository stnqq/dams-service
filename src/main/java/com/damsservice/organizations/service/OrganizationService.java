package com.damsservice.organizations.service;

import com.damsservice.common.exception.ResourceNotFoundException;
import com.damsservice.organizations.entity.Organization;
import com.damsservice.organizations.repo.OrganizationRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepo organizationRepo;

    public OrganizationService(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    @Transactional
    public Organization create(String name, String description) {
        if (organizationRepo.existsByName(name)) {
            throw new IllegalArgumentException("Organization with name " + name + " already exists");
        }
        Organization organization = new Organization(name, description);
        return organizationRepo.save(organization);
    }

    @Transactional
    public Organization getById(Long id) {
        return organizationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organization with id " + id + " not found"));
    }

    @Transactional
    public List<Organization> getAll() {
        return organizationRepo.findAll();
    }

    @Transactional
    public Organization update(Long id, String name, String description) {
        Organization organization = getById(id);

        if (!organization.getName().equals(name) && organizationRepo.existsByName(name)) {
            throw new IllegalArgumentException("Organization with name " + name + " already exists");
        }

        organization.setName(name);
        organization.setDescription(description);

        return organizationRepo.save(organization);
    }

    @Transactional
    public void delete(Long id) {
        Organization organization = getById(id);
        organizationRepo.delete(organization);
    }
}
