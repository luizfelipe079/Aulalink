package com.desafioFinal.DesafioFinal.models.google;

import com.desafioFinal.DesafioFinal.dtos.google.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventsResponse {

    private ApiResponse apiResponse;
    private Timestamp createdAt;
    private Set<EventDTO> calendarEventList;


}
