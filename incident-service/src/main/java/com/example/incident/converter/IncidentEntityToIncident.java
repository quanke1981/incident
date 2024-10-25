package com.example.incident.converter;

import com.example.incident.entity.IncidentEntity;
import com.example.incident.model.Incident;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncidentEntityToIncident implements Converter<IncidentEntity, Incident> {

    public static final TypeDescriptor INCIDENT_LIST = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Incident.class));

    @Override
    public Incident convert(@Nonnull IncidentEntity incidentEntity) {
        Incident incident = new Incident();
        incident.setId(incidentEntity.getId());
        incident.setName(incidentEntity.getName());
        incident.setDescription(incidentEntity.getDescription());
        return incident;
    }
}
