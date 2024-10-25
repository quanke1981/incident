package com.example.incident.repository;

import com.example.incident.entity.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<IncidentEntity, Long> {
    Optional<IncidentEntity> findById(long id);
}
