package com.prm392.dacare.payload.response;

import com.prm392.dacare.model.Routine;  // Import Routine từ model
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineResponse {
    private List<Routine> routines;  // List chứa các routine

}
