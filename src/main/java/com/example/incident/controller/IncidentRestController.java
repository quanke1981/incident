package com.example.incident.controller;

import com.example.incident.model.GenericResponse;
import com.example.incident.model.Incident;
import com.example.incident.service.IncidentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/incident")
public class IncidentRestController {

    private final IncidentService incidentService;

    public IncidentRestController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }


    @GetMapping("/{id}")
    public GenericResponse<Incident> getIncident(Long id) {
        return new GenericResponse<>(true, incidentService.getIncident(id));
    }
}
