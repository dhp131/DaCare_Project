package com.prm392.dacare.payload.response;

import com.prm392.dacare.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private String access_token;
    private User data;
}
