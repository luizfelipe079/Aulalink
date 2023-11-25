package com.desafioFinal.DesafioFinal.repositories.google;

import com.desafioFinal.DesafioFinal.models.google.GoogleCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GoogleCredentialRepository extends JpaRepository<GoogleCredential, String> {

    Optional<GoogleCredential> findByUserID(String userID);

    Optional<GoogleCredential> findByAccessToken(String token);

    void deleteByUserID(String userID);

    Set<String> findAllByUserID();
}
