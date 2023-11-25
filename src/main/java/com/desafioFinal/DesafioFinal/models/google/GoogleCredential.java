package com.desafioFinal.DesafioFinal.models.google;

import com.google.api.client.auth.oauth2.StoredCredential;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "google_credential")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoogleCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expiration_time")
    private String expirationTime;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now().toString();
        updatedAt = Instant.now().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now().toString();
    }

    public GoogleCredential(String key, StoredCredential credential) {
        this.userID = key;
        this.accessToken = credential.getAccessToken();
        this.expirationTime = credential.getExpirationTimeMilliseconds().toString();
        this.refreshToken = credential.getRefreshToken();
        this.createdAt = Instant.now().toString();
        this.updatedAt = Instant.now().toString();
    }

    public void apply(StoredCredential credential) {
        this.accessToken = credential.getAccessToken();
        this.expirationTime = credential.getExpirationTimeMilliseconds().toString();
        this.refreshToken = credential.getRefreshToken();
        this.updatedAt = Instant.now().toString();
    }
}
