package com.prm392.dacare.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine {
    private String _id;
    private String routineName;
    private String routineDescription;
    private SkinType skinType;
    private List<Step> steps;
    private String createdAt;
    private String updatedAt;
    private boolean active;
}
