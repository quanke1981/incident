package com.example.incident.repository;

import com.example.incident.entity.IncidentEntity;
import com.example.incident.model.IncidentStatus;
import org.springframework.data.jpa.domain.Specification;

public class IncidentSpecification {
    public static Specification<IncidentEntity> titleContains(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isEmpty()) {
                return cb.conjunction();
            }

            return cb.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<IncidentEntity> descriptionContains(String description) {
        return (root, query, cb) -> {
            if (description == null || description.isEmpty()) {
                return cb.conjunction();
            }

            return cb.like(root.get("description"), "%" + description + "%");
        };
    }

    public static Specification<IncidentEntity> statusEquals(String status) {
        return (root, query, cb) -> {
            if (status == null || status.isEmpty()) {
                return cb.conjunction();
            }

            return cb.equal(root.get("status"), status.toUpperCase());
        };
    }
}
