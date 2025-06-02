package com.smartcity.data_processor.service;

import com.smartcity.common.model.SensorData;
import com.smartcity.data_processor.model.ProcessedSensorData;
import com.smartcity.data_processor.repository.ProcessedSensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorDataProcessorService {

    private final ProcessedSensorDataRepository repository;

    // Esempio di soglia minima per elaborare “temperatura”
    @Value("${processor.temperatura.soglia:9999.0}")
    private double sogliaTemperatura;

    /**
     * Metodo che ascolta il topic “sensor-data” e riceve oggetti SensorData.
     * Il consumer group è “data-processor-group” (definito in application.yml).
     */
    @KafkaListener(topics = "sensor-data", containerFactory = "kafkaListenerContainerFactory")
    public void processSensorData(SensorData data) {
        // Esempio di log iniziale
        System.out.println("Ricevuto SensorData: " + data);

        // Applico un filtro: salvo solo i dati di temperatura sopra soglia
        if ("temperatura".equalsIgnoreCase(data.getTipo())
                && data.getValore() > sogliaTemperatura) {

            ProcessedSensorData psd = new ProcessedSensorData();
            psd.setId(data.getId());           // mantengo stesso ID
            psd.setTipo(data.getTipo());
            psd.setValore(data.getValore());
            psd.setUnita(data.getUnita());
            psd.setTimestamp(data.getTimestamp());
            psd.setZona(data.getZona());
            psd.setProcessedAt(LocalDateTime.now());

            // Salvo su MongoDB
            repository.save(psd);

            System.out.println("Salvato ProcessedSensorData: " + psd);
        } else {
            // Potresti decidere di scartare o fare altro per gli altri tipi
            System.out.println("Scartato dati non-pertinenti: tipo=" + data.getTipo());
        }
    }
}
