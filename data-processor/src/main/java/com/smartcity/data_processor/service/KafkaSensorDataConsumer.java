package com.smartcity.data_processor.service;

import com.smartcity.common.model.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KafkaSensorDataConsumer {
    // Logger per stampare informazioni su console o file di log
    private static final Logger log = LoggerFactory.getLogger(KafkaSensorDataConsumer.class);

    /**
     * Mappa che associa ogni tipo di sensore (es. "temperatura", "umidità", ecc.)
     * all'istanza di servizio che sa come elaborarne i dati.
     * Le chiavi sono le stringhe restituite da SensorDataProcessorService.getTipo(),
     * i valori sono i rispettivi bean che implementano quell'interfaccia.
     */
    private final Map<String, SensorDataProcessorService> processorMap;

    /**
     * Costruttore: Spring inietta una lista di tutti i bean che implementano
     * SensorDataProcessorService (es. TemperatureProcessor, HumidityProcessor, ecc.).
     * Con un metodo stream + toMap, costruisce processorMap in cui:
     *   key = processor.getTipo()
     *   value = il bean processor stesso
     *
     * In questo modo, al runtime, basterà fare processorMap.get(tipoSensore)
     * per recuperare l'istanza corretta che sa come elaborare quel tipo di dato.
     */
    public KafkaSensorDataConsumer(List<SensorDataProcessorService> processors) {
        this.processorMap = processors.stream()
                .collect(Collectors.toMap(
                        SensorDataProcessorService::getTipo, // chiave: il tipo di sensore gestito
                        p -> p                              // valore: l'istanza del processor
                ));
    }

    /**
     * Metodo annotato @KafkaListener: viene automaticamente registrato come consumer Kafka
     * che ascolta il topic indicato in "${kafka.topic.sensor-data}" (cioè "sensor-data").
     * Ogni volta che arriva un nuovo messaggio JSON deserializzato in un oggetto SensorData,
     * questo metodo viene invocato con quell'oggetto.
     *
     * @param data l'oggetto SensorData deserializzato dal messaggio Kafka
     */
    @KafkaListener(topics = "${kafka.topic.sensor-data}")
    public void onSensorDataReceived(SensorData data) {
        // Stampo a log il tipo di sensore e il valore ricevuto
        log.info("Ricevuto SensorData: tipo={} valore={}", data.getTipo(), data.getValore());

        // Cerco nella mappa il processor corrispondente al tipo di sensore ricevuto
        SensorDataProcessorService chosen = processorMap.get(data.getTipo());
        if (chosen == null) {
            // Se non esiste alcun processor per questo tipo, loggo un errore e ignoro
            log.error("Nessun processor trovato per tipo='{}'. Ignoro.", data.getTipo());
            return;
        }

        // Se ho trovato un processor valido, lo invoco passando l'oggetto SensorData
        chosen.process(data);
    }
}
