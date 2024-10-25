package com.example.incident.service;

import com.example.incident.entity.IncidentEntity;
import com.example.incident.model.Incident;
import com.example.incident.model.PageInfo;
import com.example.incident.repository.IncidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.incident.converter.IncidentEntityToIncident.INCIDENT_LIST;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final GenericConversionService mvcConversionService;

    public IncidentService(IncidentRepository incidentRepository, GenericConversionService mvcConversionService) {
        this.incidentRepository = incidentRepository;
        this.mvcConversionService = mvcConversionService;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "incidentCache", key = "#id")
    public Incident getIncident(long id) {
        return mvcConversionService.convert(getIncidentById(id), Incident.class);
    }

    @Transactional(readOnly = true)
    public PageInfo<Incident> getIncidents(Pageable pageable) {
        return new PageInfo<>(incidentRepository.findAll(pageable)
                .map(incidentEntity -> mvcConversionService.convert(incidentEntity, Incident.class)));
    }

    public Incident createIncident(String name, String description) {
        IncidentEntity incidentEntity = new IncidentEntity();
        incidentEntity.setName(name);
        incidentEntity.setDescription(description);

        return mvcConversionService.convert(incidentRepository.save(incidentEntity), Incident.class);
    }

    @CacheEvict(cacheNames = "incidentCache", key = "#id")
    public void deleteIncident(long id) {
        if (!incidentRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Incident with id %1$d is not found", id));
        }

        incidentRepository.deleteById(id);
    }

    @Transactional
    @CacheEvict(cacheNames = "incidentCache", key = "#incident.id")
    public Incident updateIncident(Incident incident, long id) {
        IncidentEntity incidentEntity = getIncidentById(id);
        incidentEntity.setName(incident.getName());
        incidentEntity.setDescription(incident.getDescription());
        return mvcConversionService.convert(incidentRepository.save(incidentEntity), Incident.class);
    }

    private IncidentEntity getIncidentById(long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Incident with id %1$d is not found", id)));
    }
}
