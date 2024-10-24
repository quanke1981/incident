package com.example.incident.controller;

import com.example.incident.model.GenericResponse;
import com.example.incident.model.Incident;
import com.example.incident.service.IncidentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncidentRestController {

    private final IncidentService incidentService;

    public IncidentRestController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }


    @GetMapping("/incidents/{id}")
    public GenericResponse<Incident> getIncident(@PathVariable Long id) {
        return new GenericResponse<>(true, incidentService.getIncident(id));
    }

    @PostMapping("/incidents")
    public GenericResponse<Incident> createIncident(@RequestBody Incident incident) {
        return new GenericResponse<>(true, incidentService.createIncident(incident.getName(), incident.getDescription()));
    }
}
