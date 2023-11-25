package com.desafioFinal.DesafioFinal.facade;

import com.desafioFinal.DesafioFinal.dtos.google.CalendarEventRequest;
import com.desafioFinal.DesafioFinal.models.google.ApiResponse;
import com.desafioFinal.DesafioFinal.models.google.CalendarEventsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarEventFacade {


    private CalendarEventGetFacade calendarEventGetFacade;
    private CalendarEventPostFacade calendarEventPostFacade;
    private CalendarEventPutFacade calendarEventPutFacade;
    private CalendarEventDeleteFacade calendarEventDeleteFacade;

    @Autowired
    public CalendarEventFacade(CalendarEventGetFacade calendarEventGetFacade, CalendarEventPostFacade calendarEventPostFacade
            , CalendarEventPutFacade calendarEventPutFacade, CalendarEventDeleteFacade calendarEventDeleteFacade) {
        this.calendarEventGetFacade = calendarEventGetFacade;
        this.calendarEventPostFacade = calendarEventPostFacade;
        this.calendarEventPutFacade = calendarEventPutFacade;
        this.calendarEventDeleteFacade = calendarEventDeleteFacade;
    }

    public CalendarEventsResponse getAllEvents(String userID) {

        return calendarEventGetFacade.getAllEvents(userID);
    }


    public ApiResponse createEvent(CalendarEventRequest event, String userID) {


        return calendarEventPostFacade.createEvent(event, userID);

    }

    public ApiResponse updateEvent(CalendarEventRequest event, String userID) {

        return calendarEventPutFacade.updateEvent(event, userID);
    }


    public ApiResponse deleteEvent(String eventID, String userID) {


        return calendarEventDeleteFacade.deleteEvent(eventID, userID);
    }



}
