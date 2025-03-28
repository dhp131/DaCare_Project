package com.prm392.dacare.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Step {
    private int stepNumber;
    private String stepName;
    private String stepDescription;
    @Setter
    @Getter
    private List<Product> products;
    @Setter
    @Getter
    private String id;

}
