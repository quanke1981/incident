package com.example.incident.controller;

import com.example.incident.model.Incident;
import com.example.incident.model.PageInfo;
import com.example.incident.service.IncidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncidentRestController {

    private final IncidentService incidentService;

    public IncidentRestController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/incidents/{id}")
    public ResponseEntity<Incident> getIncident(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.getIncident(id));
    }

    @GetMapping("/incidents")
    public ResponseEntity<PageInfo<Incident>> getAllIncidents(Pageable pageable) {
        return ResponseEntity.ok(incidentService.getIncidents(pageable));
    }

    @PostMapping("/incidents")
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        return ResponseEntity.ok(incidentService.createIncident(incident));
    }

    @PutMapping("/incidents/{id}")
    public ResponseEntity<Incident> updateIncident(@RequestBody Incident incident, @PathVariable Long id) {
        return ResponseEntity.ok(incidentService.updateIncident(incident, id));
    }

    @DeleteMapping("/incidents/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.ok().build();
    }
}
