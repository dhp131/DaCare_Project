package com.prm392.dacare.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Routine {
    private String _id;
    private String routineName;
    private String routineDescription;
    private List<Step> steps;

}
