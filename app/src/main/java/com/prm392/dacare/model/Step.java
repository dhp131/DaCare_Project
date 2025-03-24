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
public class Step {
    private int stepNumber;
    private String stepName;
    private String stepDescription;
    private List<Product> products;
    private String id;
}
