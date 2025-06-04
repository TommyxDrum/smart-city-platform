package com.smartcity.data_processor.service;

import com.smartcity.common.model.SensorData;
import com.smartcity.data_processor.model.ProcessedSensorData;
import com.smartcity.data_processor.repository.ProcessedSensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

// 2) Service: recupera separatamente somma e conteggio e calcola la media
@Service
public class TemperatureProcessor implements SensorDataProcessorService {

    private final ProcessedSensorDataRepository repository;

    public TemperatureProcessor(ProcessedSensorDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getTipo() {
        return "temperatura";
    }

    @Override
    public ProcessedSensorData process(SensorData incoming) {
        double valoreCorrente = incoming.getValore();

        // 2.1) Prendo somma e count dal repository
        Double sumStorico = repository.findSumByTipo("temperatura");
        Long countStorico = repository.findCountByTipo("temperatura");

        double nuovaMedia;
        if (sumStorico == null || countStorico == null || countStorico == 0) {
            // nessun dato storico: media = valore corrente
            nuovaMedia = valoreCorrente;
        } else {
            // media ponderata su (countStorico + 1) elementi
            nuovaMedia = (sumStorico + valoreCorrente) / (countStorico + 1);
        }

        // 2.2) Creo e salvo l’oggetto ProcessedSensorData
        ProcessedSensorData psd = new ProcessedSensorData();
        psd.setIdSensorData(incoming.getId());
        psd.setTimestamp(incoming.getTimestamp());
        psd.setTipo(incoming.getTipo());
        psd.setOriginalValue(valoreCorrente);
        psd.setComputedValue(nuovaMedia);

        repository.save(psd);
        return psd;
    }
}

