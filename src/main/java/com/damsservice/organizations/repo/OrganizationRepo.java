package com.damsservice.organizations.repo;

import com.damsservice.organizations.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);

    boolean existsByName(String name);
}
