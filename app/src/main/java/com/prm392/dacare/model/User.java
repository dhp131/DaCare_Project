package com.prm392.dacare.model;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String _id;
    private String username;
    private String phone;
    private String name;
    private String email;
    private String avatar;
    private List<String> role;
    private String skinType;
    private String createdAt;
    private String updatedAt;
}
