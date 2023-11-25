package com.desafioFinal.DesafioFinal.facade;

import com.desafioFinal.DesafioFinal.dtos.google.CalendarEventRequest;
import com.desafioFinal.DesafioFinal.exceptions.EventsNotFoundException;
import com.desafioFinal.DesafioFinal.models.google.ApiResponse;
import com.desafioFinal.DesafioFinal.models.google.CalendarEvent;
import com.desafioFinal.DesafioFinal.models.google.UpdateResponse;
import com.desafioFinal.DesafioFinal.services.google.CalendarEventsService;
import com.desafioFinal.DesafioFinal.services.google.GoogleEventsService;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalendarEventPutFacade {

    private CalendarEventsService customEventService;
    private GoogleEventsService googleEventsService;

    @Autowired
    public CalendarEventPutFacade(CalendarEventsService customEventService, GoogleEventsService googleEventsService) {
        this.customEventService = customEventService;
        this.googleEventsService = googleEventsService;
    }

    public ApiResponse updateEvent(CalendarEventRequest event, String userID) {

        ApiResponse apiResponse = new ApiResponse();

        CalendarEvent calendarEvent = null;
        calendarEvent = updateCustomEvent(apiResponse, userID, event);
        updateGoogleEvent(apiResponse, userID, event);

        return apiResponse;
    }

    private CalendarEvent updateCustomEvent(ApiResponse apiResponse, String userID, CalendarEventRequest event) {

        CalendarEvent calendarEvent = new CalendarEvent();

        try {
            calendarEvent = customEventService.updateEvent(event, userID);
        } catch (EventsNotFoundException e) {

            UpdateResponse.updateResponse(apiResponse, HttpStatus.NOT_FOUND, e);
        }

        return calendarEvent;

    }

    private Event updateGoogleEvent(ApiResponse apiResponse, String userID, CalendarEventRequest calendarEvent) {
        Event event = new Event();
        try {
            event = googleEventsService.updateEvent(calendarEvent, userID);
        } catch (IOException e) {

            UpdateResponse.updateResponse(apiResponse, HttpStatus.PARTIAL_CONTENT, e);
        }


        return event;

    }
}
