package com.damsservice.organizations.mapper;

import com.damsservice.organizations.dto.response.OrganizationResponse;
import com.damsservice.organizations.entity.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public OrganizationResponse toResponse(Organization organization) {
        if (organization == null) {
            return null;
        }

        OrganizationResponse response = new OrganizationResponse();
        response.setId(organization.getId());
        response.setName(organization.getName());
        response.setDescription(organization.getDescription());
        response.setCreatedAt(organization.getCreated_at());
        response.setUpdatedAt(organization.getUpdated_at());

        return response;
    }
}
