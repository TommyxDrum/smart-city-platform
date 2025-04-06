package com.smartcity.data_collector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorData {
    private String id; // Identificativo univoco del sensore/evento
    private String tipo; // Tipo di dato (es. "Temperatura", "traffico"
    private double valore; //Valore misurato o simulato
    private String unita; // Unità di misura (°C, %, km/h, ecc.)
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now(); // Data e ora della rilevazione
    private String zona; //Area urbana o codice geografico (es. “Zona A”)
}
