package com.example.neasTC.controllers.medition;

import com.example.neasTC.DTOs.MeditionRequestDTO;
import com.example.neasTC.DTOs.MeditionResponseDTO;
import com.example.neasTC.DTOs.RelatoryResponseDTO;
import com.example.neasTC.services.medition.MeditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;

@RestController
@CrossOrigin("*")
@RequestMapping("/medition")
public class MeditionController {
    private MeditionService meditionService;

    public MeditionController(MeditionService meditionService) {
        this.meditionService = meditionService;
    }

    @GetMapping
    public ResponseEntity<RelatoryResponseDTO> getRelatory(){
        return ResponseEntity.ok(
                meditionService.getRelatory()
        );
    }

    @PostMapping
    public ResponseEntity<MeditionResponseDTO> saveMedition(
            @RequestBody MeditionRequestDTO dto
            ){
        return ResponseEntity.ok(
                meditionService.saveMedition(dto)
        );
    }
}
