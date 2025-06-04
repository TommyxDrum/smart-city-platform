package com.smartcity.data_processor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "processed_sensor_data")
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedSensorData {
    @Id
    private String id;                // ID generato dal DB (opzionale)
    private String tipo;               // Tipo valore
    private UUID idSensorData;          // lo stesso ID che arriva dal SensorData
    private LocalDateTime timestamp;        // marca temporale originale
    private Double originalValue;     // valore grezzo
    private Double computedValue;     // valore elaborato/escalato
}
