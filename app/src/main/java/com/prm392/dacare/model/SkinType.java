package com.prm392.dacare.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkinType {
    private String _id;
    private String type;
    private String description;
    private int pointMin;
    private int pointMax;
    private String cause;
    private String symptom;
    private String treatment;
    private Instant updatedAt;
}
