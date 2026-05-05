package com.damsservice.organizations.controller;

import com.damsservice.organizations.dto.request.CreateOrganizationRequest;
import com.damsservice.organizations.dto.request.UpdateOrganizationRequest;
import com.damsservice.organizations.dto.response.OrganizationResponse;
import com.damsservice.organizations.mapper.OrganizationMapper;
import com.damsservice.organizations.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/organizations")
@Tag(name = "Organization Controller", description = "Management of organizations")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    public OrganizationController(OrganizationService organizationService, OrganizationMapper organizationMapper) {
        this.organizationService = organizationService;
        this.organizationMapper = organizationMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new organization")
    public OrganizationResponse create(@Valid @RequestBody CreateOrganizationRequest request) {
        return organizationMapper.toResponse(
                organizationService.create(request.getName(), request.getDescription())
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get organization by id")
    public OrganizationResponse getById(@PathVariable Long id) {
        return organizationMapper.toResponse(organizationService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all organizations")
    public List<OrganizationResponse> getAll() {
        return organizationService.getAll().stream()
                .map(organizationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an organization")
    public OrganizationResponse update(@PathVariable Long id, @Valid @RequestBody UpdateOrganizationRequest request) {
        return organizationMapper.toResponse(
                organizationService.update(id, request.getName(), request.getDescription())
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an organization")
    public void delete(@PathVariable Long id) {
        organizationService.delete(id);
    }
}
