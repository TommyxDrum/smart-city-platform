package com.smartcity.data_processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "processed_sensor_data")
public class ProcessedSensorData {

    @Id
    private UUID id;               // stesso ID del SensorData originale (o uno nuovo)

    private String tipo;           // es. “temperatura”, “umidità”, …
    private double valore;         // valore misurato o elaborato
    private String unita;          // “°C”, “%”, …
    private LocalDateTime timestamp;  // momento della rilevazione originale
    private String zona;           // “Zona Nord”, “Centro”, …

    private LocalDateTime processedAt; // momento in cui è stato elaborato

    // Potresti aggiungere altri campi aggregati, es. media, flag di allarme, ecc.
}
