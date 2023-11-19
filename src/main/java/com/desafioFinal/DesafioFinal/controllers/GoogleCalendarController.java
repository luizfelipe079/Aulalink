package com.desafioFinal.DesafioFinal.controllers;


import com.desafioFinal.DesafioFinal.services.GoogleCalendarService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/calendar")
@AllArgsConstructor
public class GoogleCalendarController {

    private final GoogleCalendarService calendarService;

    @PostMapping("/createMeeting")
    public ResponseEntity<String> createMeeting(@RequestParam String summary,
                                                @RequestParam String startDateTime,
                                                @RequestParam String endDateTime,
                                                @RequestParam String attendeeEmail) {
        try {
            DateTime start = new DateTime(startDateTime);
            DateTime end = new DateTime(endDateTime);

            String organizerEmail = getAuthenticatedUserEmail();

            calendarService.createEvent(summary, start, end, organizerEmail, attendeeEmail);

            return ResponseEntity.ok("Reunião marcada com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao marcar a reunião.");
        }
    }


    // TODO: Verificar se o import do Event está correto
    @GetMapping("/getUserEvents")
    public List<Event> getUserEvents(@RequestParam String userEmail) throws IOException {
        return calendarService.getUserEvents(userEmail);
    }

    @DeleteMapping("/deleteEvent")
    public void deleteEvent(@RequestParam String eventId) throws IOException {
        calendarService.deleteEvent(eventId);
    }

    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            throw new RuntimeException("Erro ao tentar obter email do user");
        }
    }
}
