package com.example.incident.service;

import com.example.incident.entity.IncidentEntity;
import com.example.incident.model.Incident;
import com.example.incident.repository.IncidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final GenericConversionService mvcConversionService;

    public IncidentService(IncidentRepository incidentRepository, GenericConversionService mvcConversionService) {
        this.incidentRepository = incidentRepository;
        this.mvcConversionService = mvcConversionService;
    }

    public Incident getIncident(long id) {
        IncidentEntity incidentEntity = incidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Incident with id %1$d is not found", id)));

        return mvcConversionService.convert(incidentEntity, Incident.class);
    }
}
