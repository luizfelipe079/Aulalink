package com.desafioFinal.DesafioFinal.facade;

import com.desafioFinal.DesafioFinal.dtos.google.EventDTO;
import com.desafioFinal.DesafioFinal.exceptions.EventsNotFoundException;
import com.desafioFinal.DesafioFinal.models.google.*;
import com.desafioFinal.DesafioFinal.services.google.CalendarEventsService;
import com.desafioFinal.DesafioFinal.services.google.GoogleEventsService;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CalendarEventGetFacade {

    private CalendarEventsService customEventService;
    private GoogleEventsService googleEventsService;


    @Autowired
    public CalendarEventGetFacade(CalendarEventsService customEventService, GoogleEventsService googleEventsService) {
        this.customEventService = customEventService;
        this.googleEventsService = googleEventsService;
    }

    public CalendarEventsResponse getAllEvents(String userID) {

        ApiResponse apiResponse = new ApiResponse();

        List<CalendarEvent> customEvents = new LinkedList<>();
        List<Event> googleEvents = new LinkedList<>();

        customEvents = getCustomEventsList(apiResponse, userID);
        googleEvents = getGoogleEventsList(apiResponse, userID);

        List<EventDTO> eventDTOS = CalendarEventMapper.mapCalendarEventListToDTO(customEvents);
        eventDTOS.addAll(GoogleEventMapper.mapGoogleEventsToDTO(googleEvents));

        Set<EventDTO> eventDTOSet = new HashSet<>(eventDTOS);

        return new CalendarEventsResponse(apiResponse, new Timestamp(new Date().getTime()), eventDTOSet);
    }

    private List<CalendarEvent> getCustomEventsList(ApiResponse apiResponse, String userID) {

        List<CalendarEvent> customEvents = new LinkedList<>();

        try {
            customEvents = customEventService.getAllEvents(userID);
        } catch (EventsNotFoundException e) {
            UpdateResponse.updateResponse(apiResponse, HttpStatus.PARTIAL_CONTENT, e);
        }

        return customEvents;

    }

    private List<Event> getGoogleEventsList(ApiResponse apiResponse, String userID) {

        List<Event> googleEvents = new LinkedList<>();

        try {
            googleEvents = googleEventsService.getGoogleEvents(userID);
        } catch (IOException e) {

            UpdateResponse.updateResponse(apiResponse, HttpStatus.PARTIAL_CONTENT, e);
        }


        return googleEvents;

    }

}
