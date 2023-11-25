package com.desafioFinal.DesafioFinal.config;

import com.desafioFinal.DesafioFinal.repositories.google.GoogleCalendarDataStoreFactory;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class GoogleConfig {

    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    @Value("${google.credentials.file}")
    private String googleCredentialFile;
    @Value("${google.responseType}")
    private String responseType;
    @Value("${google.redirectURI}")
    private String googleRedirectURI;
    private final GoogleCalendarDataStoreFactory googleCalendarDataStoreFactory;

    @Autowired
    public GoogleConfig(GoogleCalendarDataStoreFactory googleCalendarDataStoreFactory) {
        this.googleCalendarDataStoreFactory = googleCalendarDataStoreFactory;
    }


    @Bean
    public JsonFactory jsonFactory() {
        return JacksonFactory.getDefaultInstance();
    }

    //
    @Bean
    public NetHttpTransport getGoogleNetHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean(name = "myFlow")
    public GoogleAuthorizationCodeFlow getFlow() throws IOException, GeneralSecurityException {

        return new GoogleAuthorizationCodeFlow.Builder(getGoogleNetHttpTransport(), jsonFactory(), loadSecrets(), SCOPES)
                .setDataStoreFactory(googleCalendarDataStoreFactory)
                .setAccessType("offline")
                .build();

    }

    @Bean(name = "myAuthCodeRequestUrl")
    public AuthorizationCodeRequestUrl authorizationCodeRequestUrl(@Qualifier("myFlow") GoogleAuthorizationCodeFlow myFlow) {
        AuthorizationCodeRequestUrl authorizationCodeRequestUrl = myFlow.newAuthorizationUrl();
        authorizationCodeRequestUrl
                .setRedirectUri(googleRedirectURI)
                .setResponseTypes(Collections.singletonList(responseType));

        return authorizationCodeRequestUrl;
    }

    private GoogleClientSecrets loadSecrets() throws IOException {
        InputStream in = new ClassPathResource(googleCredentialFile).getInputStream();
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + googleCredentialFile);
        }

        return GoogleClientSecrets.load(jsonFactory(), new InputStreamReader(in));
    }

}
