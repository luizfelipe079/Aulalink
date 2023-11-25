package com.desafioFinal.DesafioFinal.models.google;

import com.desafioFinal.DesafioFinal.dtos.google.CalendarEventRequest;
import com.desafioFinal.DesafioFinal.dtos.google.EventDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CalendarEventMapper {

    public static CalendarEvent mapRequestToCalendarEvent(CalendarEventRequest request, String userID) {
        CalendarEvent calendarEvent = new CalendarEvent();

        if (request.getEventID() == null) {
            calendarEvent.setEventID(UUIDGenerator.generateUnique());
        } else {
            calendarEvent.setEventID(request.getEventID());
        }

        calendarEvent.setUserID(userID);
        calendarEvent.setDescription(request.getDescription());
        calendarEvent.setEventName(request.getEventName());
        calendarEvent.setStartTime(request.getStartTime());
        calendarEvent.setEndTime(request.getEndTime());

        return calendarEvent;
    }

    public static List<EventDTO> mapCalendarEventListToDTO(List<CalendarEvent> calendarEvents) {

        List<EventDTO> eventDTOS = calendarEvents.stream().map(event -> {
            EventDTO newEvent = new EventDTO();
            newEvent.setDescription(event.getDescription())
                    .setStartTime(event.getStartTime())
                    .setEndTime(event.getEndTime())
                    .setEventID(event.getEventID())
                    .setEventName(event.getEventName());

            return newEvent;
        }).collect(Collectors.toList());

        return eventDTOS;
    }


}
