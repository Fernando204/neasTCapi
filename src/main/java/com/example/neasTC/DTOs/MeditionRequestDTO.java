package com.example.neasTC.DTOs;

import java.math.BigDecimal;

public record MeditionRequestDTO(
        String room,
        BigDecimal temperature,
        BigDecimal humidity,
        BigDecimal pressure
) {
}
