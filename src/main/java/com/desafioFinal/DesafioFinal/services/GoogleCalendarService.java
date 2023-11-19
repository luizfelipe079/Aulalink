package com.desafioFinal.DesafioFinal.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GoogleCalendarService {

    private static final String APPLICATION_NAME = "Your Application Name";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private Calendar calendarService;

    public GoogleCalendarService() throws GeneralSecurityException, IOException {
        this.calendarService = initializeCalendarService();
    }

    private Calendar initializeCalendarService() throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        InputStream in = GoogleCalendarService.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        List<String> scopes = List.of("https://www.googleapis.com/auth/calendar");
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void createEvent(String summary, DateTime startDateTime, DateTime endDateTime,
                            String organizerEmail, String attendeeEmail) throws IOException {
        Event event = new Event()
                .setSummary(summary)
                .setDescription("A meeting between two users.");

        EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("UTC");
        event.setStart(start);

        EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("UTC");
        event.setEnd(end);

        EventAttendee organizer = new EventAttendee().setEmail(organizerEmail);
        EventAttendee attendee = new EventAttendee().setEmail(attendeeEmail);
        event.setAttendees(List.of(organizer, attendee));

        calendarService.events().insert("primary", event).execute();
    }

    public List<Event> getUserEvents(String userEmail) throws IOException {
        Events events = calendarService.events().list("primary")
                .setTimeMin(new DateTime(System.currentTimeMillis()))
                .setSingleEvents(true)
                .setOrderBy("startTime")
                .execute();

        return events.getItems();
    }

    public void deleteEvent(String eventId) throws IOException {
        calendarService.events().delete("primary", eventId).execute();
    }
}