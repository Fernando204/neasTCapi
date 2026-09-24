package com.example.neasTC.repositories.medition;

import com.example.neasTC.models.meditions.Medition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeditionRepository extends MongoRepository<Medition,String> {
}
