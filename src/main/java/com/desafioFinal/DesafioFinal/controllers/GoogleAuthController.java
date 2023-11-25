package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.annotations.CurrentUser;
import com.desafioFinal.DesafioFinal.models.google.CustomerPrinciple;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {

    @Qualifier("myFlow")
    private GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow;

    @Qualifier("myAuthCodeRequestUrl")
    private AuthorizationCodeRequestUrl googleAuthorizationCodeRequestUrl;

    @Value("${google.redirectURI}")
    private String redirectURI;

    @Autowired
    public GoogleAuthController(GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow
            , AuthorizationCodeRequestUrl googleAuthorizationCodeRequestUrl) {
        this.googleAuthorizationCodeFlow = googleAuthorizationCodeFlow;
        this.googleAuthorizationCodeRequestUrl = googleAuthorizationCodeRequestUrl;
    }

    @PostMapping("/login/callback")
    public ResponseEntity<?> googleLoginCallback(@RequestParam(value = "code") String code, @CurrentUser CustomerPrinciple currentUser) {

        try {
            TokenResponse response = googleAuthorizationCodeFlow.newTokenRequest(code)
                    .setRedirectUri(redirectURI)
                    .execute();
            googleAuthorizationCodeFlow.createAndStoreCredential(response, currentUser.getUserID());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }


}
