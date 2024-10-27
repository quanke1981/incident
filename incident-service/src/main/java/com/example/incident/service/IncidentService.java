package com.example.incident.service;

import com.example.incident.entity.IncidentEntity;
import com.example.incident.model.Incident;
import com.example.incident.model.PageInfo;
import com.example.incident.repository.IncidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class IncidentService {

    private static final int BATCH_SIZE = 50;

    private final IncidentRepository incidentRepository;
    private final GenericConversionService mvcConversionService;
    private final IncidentService self;

    public IncidentService(
            IncidentRepository incidentRepository,
            GenericConversionService mvcConversionService,
            @Lazy IncidentService incidentService
    ) {
        this.incidentRepository = incidentRepository;
        this.mvcConversionService = mvcConversionService;
        this.self = incidentService;
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

    public List<Incident> createIncidents(List<Incident> incidents) {
        List<Incident> resultList = new ArrayList<>();
        for (int i = 0; i < incidents.size(); i+= BATCH_SIZE) {
            List<Incident> subList = incidents.subList(i, Math.min(incidents.size(), i + BATCH_SIZE));
            resultList.addAll(self.createIncidentsInBatch(subList));
        }
        return resultList;
    }

    /**
     * Batch create incidents with small transaction.
     * @param incidents list of incident to be created.
     * @return created incidents
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Incident> createIncidentsInBatch(List<Incident> incidents) {
        List<IncidentEntity> incidentEntities = incidentRepository.saveAll(incidents.stream().map(incident -> {
            IncidentEntity incidentEntity = new IncidentEntity();
            incidentEntity.setTitle(incident.getTitle());
            incidentEntity.setDescription(incident.getDescription());
            incidentEntity.setStatus(incident.getStatus());
            return incidentEntity;
        }).collect(toList()));

        return incidentEntities.stream()
                .map(incidentEntity -> mvcConversionService.convert(incidentEntity, Incident.class))
                .collect(toList());
    }

    @CacheEvict(cacheNames = "incidentCache", key = "#id")
    public void deleteIncident(long id) {
        if (!incidentRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Incident with id %1$d is not found", id));
        }

        incidentRepository.deleteById(id);
    }

    @Transactional
    @CacheEvict(cacheNames = "incidentCache", key = "#id")
    public Incident updateIncident(Incident incident, long id) {
        IncidentEntity incidentEntity = getIncidentById(id);
        incidentEntity.setTitle(incident.getTitle());
        incidentEntity.setDescription(incident.getDescription());
        incidentEntity.setStatus(incident.getStatus());
        return mvcConversionService.convert(incidentRepository.save(incidentEntity), Incident.class);
    }

    private IncidentEntity getIncidentById(long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Incident with id %1$d is not found", id)));
    }
}
