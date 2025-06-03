package com.smartcity.data_processor.service;

import com.smartcity.common.model.SensorData;
import com.smartcity.data_processor.model.ProcessedSensorData;
import com.smartcity.data_processor.repository.ProcessedSensorDataRepository;
import org.springframework.stereotype.Service;


@Service
public class SensorDataProcessorService {

    private final ProcessedSensorDataRepository repository;

    public SensorDataProcessorService(ProcessedSensorDataRepository repository) {
        this.repository = repository;
    }

    public void process(SensorData incoming) {
        // 1) Applica trasformazioni, filtri, calcoli statistici, ecc.
        double originalValue = incoming.getValore();
        double computedValue = originalValue * 2; // esempio di calcolo

        ProcessedSensorData processedSensorData = new ProcessedSensorData();

        // 2) Popola i campi
        processedSensorData.setIdSensorData(incoming.getId());
        processedSensorData.setTimestamp(incoming.getTimestamp());
        processedSensorData.setOriginalValue(originalValue);
        processedSensorData.setComputedValue(computedValue);

        // 3) Salva su DB
        repository.save(processedSensorData);
    }
}
