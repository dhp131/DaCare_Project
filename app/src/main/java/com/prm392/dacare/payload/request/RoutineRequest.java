package com.prm392.dacare.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineRequest {
    private String skinTypeId;  // ID của loại da để lấy lộ trình chăm sóc da
}
