package com.smartcity.data_collector.service;

import com.smartcity.data_collector.model.SensorData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service  // 1. Dichiara questo bean come servizio gestito da Spring
public class KafkaProducerService {

    // 2. Iniettiamo un KafkaTemplate parametrizzato con <String, SensorData>
    //    - String è il tipo della chiave del messaggio
    //    - SensorData è il tipo del valore/payload (verrà serializzato in JSON)
    private final KafkaTemplate<String, SensorData> kafkaTemplate;

    // 3. Costruttore con Dependency Injection:
    //    Spring passerà un'istanza di KafkaTemplate configurata automaticamente
    public KafkaProducerService(KafkaTemplate<String, SensorData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 4. Metodo pubblico per inviare dati di tipo SensorData su un topic Kafka.
     *
     * @param topic il nome del topic Kafka su cui pubblicare (es. "sensor-data")
     * @param data  l'oggetto SensorData che sarà serializzato e inviato come messaggio
     */
    public void sendSensorData(String topic, SensorData data) {
        // 5. Fire-and-forget: invia il messaggio al topic specificato.
        //    KafkaTemplate si occupa di serializzare `data` in JSON
        //    (configurato tramite JsonSerializer).
        kafkaTemplate.send(topic, data);
    }
}
