package com.smartcity.data_collector.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Rappresenta una singola rilevazione di un sensore")
public class SensorData {

    @Builder.Default
    @Schema(description = "Identificativo univoco del sensore/evento", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id = UUID.randomUUID();

    @NotBlank
    @Schema(description = "Tipo di dato (es. Temperatura, Traffico)", example = "Temperatura")
    private String tipo;

    @PositiveOrZero
    @Schema(description = "Valore misurato o simulato", example = "23.5")
    private double valore;

    @NotBlank
    @Schema(description = "Unità di misura (°C, %, km/h, ecc.)", example = "°C")
    private String unita;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Timestamp della rilevazione", example = "2025-05-29T14:30:00")
    private LocalDateTime timestamp = LocalDateTime.now();

    @NotBlank
    @Schema(description = "Area urbana o codice geografico (es. Zona A)", example = "Zona A")
    private String zona;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }

    public String getUnita() {
        return unita;
    }

    public void setUnita(String unita) {
        this.unita = unita;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
