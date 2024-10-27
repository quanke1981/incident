package com.example.incident.controller;

import com.example.incident.model.Incident;
import com.example.incident.model.PageInfo;
import com.example.incident.service.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IncidentRestController {

    private final IncidentService incidentService;

    public IncidentRestController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/incidents/{id}")
    @Operation(summary = "Get an incident", description = "Get an incident by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Incident is not found by given id"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Incident> getIncident(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.getIncident(id));
    }

    @GetMapping("/incidents")
    @Operation(summary = "Get incidents", description = "Get incidents by pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PageInfo<Incident>> getAllIncidents(Pageable pageable) {
        return ResponseEntity.ok(incidentService.getIncidents(pageable));
    }

    @PostMapping("/incidents")
    @Operation(summary = "Create incidents", description = "Create incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Incident>> createIncident(@RequestBody List<Incident> incidents) {
        return ResponseEntity.ok(incidentService.createIncidents(incidents));
    }

    @PutMapping("/incidents/{id}")
    @Operation(summary = "Update an incident", description = "Update an incident by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Incident with given id doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Incident> updateIncident(@RequestBody Incident incident, @PathVariable Long id) {
        return ResponseEntity.ok(incidentService.updateIncident(incident, id));
    }

    @DeleteMapping("/incidents/{id}")
    @Operation(summary = "Delete an incident", description = "Delete an incident by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Incident with given id doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.ok().build();
    }
}
