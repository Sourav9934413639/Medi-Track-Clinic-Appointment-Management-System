package com.airtribe.meditrack.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class MedicalEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final LocalDateTime createdAt;

    protected MedicalEntity(String id) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public abstract String getDisplayName();
}
