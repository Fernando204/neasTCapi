package com.example.neasTC.DTOs;

import java.util.List;

public record RelatoryResponseDTO(
        MeditionResponseDTO lastMeditionA,
        MeditionResponseDTO lastMeditionB,
        List<MeditionResponseDTO> allMeditions
) {

}
