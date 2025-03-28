package com.prm392.dacare.payload.response;

import com.prm392.dacare.model.SkinType;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    private String _id;
    private String username;
    private String phone;
    private String name;
    private String email;
    private String avatar;
    private List<String> role;
    private SkinType skinType;
    private String createdAt;
    private String updatedAt;
}
