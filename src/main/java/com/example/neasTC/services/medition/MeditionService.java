package com.example.neasTC.services.medition;

import com.example.neasTC.DTOs.MeditionRequestDTO;
import com.example.neasTC.DTOs.MeditionResponseDTO;
import com.example.neasTC.DTOs.RelatoryResponseDTO;
import com.example.neasTC.models.meditions.Medition;
import com.example.neasTC.repositories.medition.MeditionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class MeditionService {
    private final MeditionRepository meditionRepository;
    private final RestClient restClient;

    public MeditionService(
        RestClient restClient,
        MeditionRepository meditionRepository) {
        this.meditionRepository = meditionRepository;
        this.restClient = restClient;
    }

    @Transactional
    public MeditionResponseDTO saveMedition(MeditionRequestDTO dto){
        Medition medition = new Medition(
                dto.room(),
                dto.temperature(),
                dto.humidity(),
                dto.pressure(),
                dto.sensor()
        );
        medition.verifyStatus(BigDecimal.valueOf(25),BigDecimal.ONE);

        medition = meditionRepository.save(medition);

        keepCognitAlive();

        return MeditionResponseDTO.fromEntity(medition);
    }

    public RelatoryResponseDTO getRelatory(){
        List<Medition> meditions = meditionRepository.findAll();

        MeditionResponseDTO lastInRoomA = meditions.stream()
                .filter(m -> m.getRoom().equals("A"))
                .max(Comparator.comparing(Medition::getCreatedAt))
                .map(MeditionResponseDTO::fromEntity)
                .orElse(null);

        MeditionResponseDTO lastInRoomB = meditions.stream()
                .filter(m -> m.getRoom().equals("B"))
                .max(Comparator.comparing(Medition::getCreatedAt))
                .map(MeditionResponseDTO::fromEntity)
                .orElse(null);

        List<MeditionResponseDTO> allMeditions = meditions.stream()
                .map(MeditionResponseDTO::fromEntity)
                .toList();

        return new RelatoryResponseDTO(
                lastInRoomA,
                lastInRoomB,
                allMeditions
        );
    }

    public String keepCognitAlive(){
        return restClient.get()
                .uri("https://cognit-2t72.onrender.com/keep")
                .retrieve()
                .toBodilessEntity();
    }
}
