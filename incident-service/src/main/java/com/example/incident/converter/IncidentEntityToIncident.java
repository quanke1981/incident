package com.example.incident.converter;

import com.example.incident.entity.IncidentEntity;
import com.example.incident.model.Incident;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IncidentEntityToIncident implements Converter<IncidentEntity, Incident> {

    @Override
    public Incident convert(@Nonnull IncidentEntity incidentEntity) {
        Incident incident = new Incident();
        incident.setId(incidentEntity.getId());
        incident.setTitle(incidentEntity.getTitle());
        incident.setDescription(incidentEntity.getDescription());
        incident.setStatus(incidentEntity.getStatus());
        return incident;
    }
}
