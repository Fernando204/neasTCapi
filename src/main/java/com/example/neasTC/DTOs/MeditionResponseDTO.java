package com.example.neasTC.DTOs;

import com.example.neasTC.enums.meditions.MeditionStatus;
import com.example.neasTC.models.meditions.Medition;

import java.math.BigDecimal;
import java.time.Instant;

public record MeditionResponseDTO(
        String id,
        String room,
        BigDecimal temperature,
        BigDecimal humidity,
        BigDecimal pressure,
        MeditionStatus status,
        Integer sensor,
        Instant createdAt
) {
    public static MeditionResponseDTO fromEntity(Medition medition){
        return new MeditionResponseDTO(
                medition.getId(),
                medition.getRoom(),
                medition.getTemperature(),
                medition.getHumidity(),
                medition.getPressure(),
                medition.getStatus(),
                medition.getSensor(),
                medition.getCreatedAt()
        );
    }

}
