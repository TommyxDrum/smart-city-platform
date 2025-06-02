package com.smartcity.data_processor.repository;

import com.smartcity.data_processor.model.ProcessedSensorData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProcessedSensorDataRepository
        extends MongoRepository<ProcessedSensorData, UUID> {
    // Qui puoi definire query extra, es. findByTipoAndZona(...)
}
