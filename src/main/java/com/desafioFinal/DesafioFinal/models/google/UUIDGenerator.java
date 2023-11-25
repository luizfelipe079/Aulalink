package com.desafioFinal.DesafioFinal.models.google;

import java.util.UUID;

public class UUIDGenerator {

    public static String generateUnique() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
