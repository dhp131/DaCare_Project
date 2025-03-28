package com.prm392.dacare.payload.response;

import com.prm392.dacare.model.SkinType;
import com.prm392.dacare.model.User;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private boolean success;
    private UserRes user;
}

