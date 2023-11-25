package com.desafioFinal.DesafioFinal.dtos.google;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventRequest {

    private String eventID;
    @NonNull
    @NotBlank
    private String eventName;
    @NonNull
    @NotBlank
    private String startTime;
    @NonNull
    @NotBlank
    private String endTime;
    @NonNull
    private String description;
}
